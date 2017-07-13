package sale_order

import (
	"strings"
	"time"
	"strconv"
	"cli/model/sale_order"
	"cli/model/site"
	"cli/service/api"
	"cli/model/sale_order_item"
	"fmt"
)

func Store(data string, payType int) map[string]interface{} {
	site := site.FindSite()
	siteId := site["id"].(string)
	siteName := site["name"].(string)
	s := strings.Split(data, "|")

	idSlice := strings.Split(s[0], ",")
	priceSlice := strings.Split(s[1], ",")
	amountSlice := strings.Split(s[2], ",")
	code := strings.Split(s[3],",")
	//生成订单id
	orderId := siteId + strconv.FormatInt(time.Now().Unix(), 10)

	//计算价格
	commodityList := make([]map[string]interface{}, 0)
	totalPrice := float64(0)
	totalAmount := int64(0)
	for k, v := range idSlice {
		p, err := strconv.ParseFloat(priceSlice[k], 64)
		if err != nil {
			return map[string]interface{}{
				"errcode": 0,
				"errmsg":  "参数错误",
			}
		}
		a, err := strconv.ParseInt(amountSlice[k], 10, 32)
		if err != nil {
			return map[string]interface{}{
				"errcode": 0,
				"errmsg":  "参数错误",
			}
		}
		price := p * float64(a)
		totalAmount += a
		totalPrice += price
		//将总价进行精度处理
		item := map[string]interface{}{
			"code":code[k],
			"goodsId":v,
			"orderNo":  orderId,
			"actPrice": p,
			"count":    a,
			"subTotal": price,
		}
		commodityList = append(commodityList, item)
	}
	totalPrice2 := fmt.Sprintf("%.2f",totalPrice)
	_totalPrice,_ := strconv.ParseFloat(totalPrice2,64)
	saleOrder := sale_order.SaleOrder{
		Id:         orderId,
		TotalPrice: _totalPrice,
		Status:     0,
		IsCanceled: 0,
		SiteId:     siteId,
		PayType:    payType,
	}
	//先进行网络请求，成功后再插入数据库
	//bytes, _ := json.Marshal(commodityList)
	uploadPackage := map[string]interface{}{
		"orderNo":       orderId,
		"orderType":     "2",
		"siteCode":      siteId,
		"site":          siteName,
		"orderAmount":   _totalPrice,
		"status":        "1",
		"orderCommList": commodityList,
	}
	ret := api.UploadOrderInfo(uploadPackage)

	if ret["errcode"] != nil {
		return ret
	}
	//需要确认返回数据格式
	if ret["status"] != "0" {
		return map[string]interface{}{
			"errcode": 10000,
			"errmsg":  ret["obj"],
		}
	}
	//获取支付链接
		ret1 := api.WeChatPay(orderId)
		if ret1["status"] != "0" {
			return map[string]interface{}{
				"errcode": 10000,
				"errmsg":  ret["obj"],
			}
		}
	a := sale_order.Store(&saleOrder)
	if a {
		b := sale_order_item.StoreBatch(commodityList)
		if b {
			return map[string]interface{}{
				"orderNo":     orderId,
				"totalPrice":  _totalPrice,
				"totalAmount": totalAmount,
				"code":        ret1["obj"],
			}
		}
	}
	return map[string]interface{}{
		"errcode": 0,
		"errmsg":  "操作失败",
	}

}

/**
订单信息查询
 */

func Search(params map[string]string) map[string]interface{} {
	orderId := ""
	payType := 9
	if params["orderNo"] != "" {
		orderId = params["orderNo"]
	}
	if params["payType"] != "" {
		payType, _ = strconv.Atoi(params["payType"])
	}
	page, _ := strconv.Atoi(params["page"])
	pageSize, _ := strconv.Atoi(params["pageSize"])
	return sale_order.Paginate(orderId, payType, page, pageSize)
}

/**
订单状态查询
 */
func OrderInfo(orderId string) map[string]interface{} {
	//先查询本地
	orderInfo := sale_order.FindById(orderId)
	if orderInfo == nil {
		return map[string]interface{}{
			"errcode": 0,
			"errmsg":  "订单不存在",
		}
	}
	if orderInfo["status"].(int64) == 1 {
		return orderInfo
	}
	//远程获取订单状态
	ret := api.WeChatPayInfo(orderId)
	if ret["status"] != "0" {
		return map[string]interface{}{
			"errcode": 10000,
			"errmsg":  ret["obj"],
		}
	}

	//更新本地订单
	b := sale_order.UpdateStatusById(orderId)
	if b {
		//修改本地库存
		b1 := sale_order.UpdateAmount(orderId)
		if  b1{
			return sale_order.FindById(orderId)
		}
	}
	return orderInfo
}

/**
  微信支付记录查询
 */

func OrderRecode(keyname string,page int,pageSize int) map[string]interface{} {
	data := sale_order.OrderRecode(keyname,page,pageSize)
	return data
}
