package sale_order

import (
	"cli/util/sqlite"
	"strconv"
	"time"
	"encoding/json"
	"github.com/widuu/gojson"
	"cli/model/cookie"
)

/**
销售订单
*/
type SaleOrder struct {
	Id         string
	TotalPrice float64 //总价
	Status     int     //状态	0未支付 1已支付
	IsCanceled int     //是否取消
	Operator   string  //处理人
	SiteId     string  //自提点编号
	PayType    int     //支付方式(0微信 2支付宝 1 银犁卡)
	CreatedAt  string  //创建时间
}

/**
  生成订单
 */
func Store(entity *SaleOrder) bool{
	//查询用户名
	data1 := cookie.QueryCookie()
	user,_ := json.Marshal(data1)
	entity.Operator = gojson.Json(string(user)).Get("account").Tostring()

	entity.CreatedAt = strconv.FormatInt(time.Now().Unix(),10)
	const sql   = "insert into t_sale_order (id,total_price,status,is_canceled,operator,pay_type,created_at) values(?,?,?,?,?,?,?)"
	return sqlite.Insert(sql,entity.Id,entity.TotalPrice,entity.Status,entity.IsCanceled,entity.Operator,entity.PayType,entity.CreatedAt)
}

/**
  扫码销售查询商品信息
 */
func FindByNo(barcode string,page int,pageSize int) map[string]interface{} {
	offset := (page-1)*pageSize
	rs := []rune(barcode)
	number := string(rs[0:13])
	lot := string(rs[13:len(barcode)])
	//根据商品Id和批次号获取商品信息
	query := "select *"
	sqlExceptSelect := " from t_sale_goods where sales_id = ?  and lot = ?"
	return sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,number,lot)
}

/**
  更新支付状态
 */
//func ModifyOrderStatus(orderId string) bool{
//	const  sql   = "update set status = 1 where order"
//}

func UpdateStatusById(orderId string) bool {
	const sql  = "update t_sale_order set status=1 where id=?"
	return sqlite.UpdateOrDelete(sql,orderId)
}

func FindById(orderId string) map[string]interface{} {

	const sql = "select * from t_sale_order where id =?"
	return sqlite.FindFirst(sql, orderId)
}

func Paginate(orderId string,payType int,page int ,pageSize int) map[string]interface{} {
	offset := (page-1)*pageSize
	query := "select * "
	sqlExceptSelect := " from t_sale_order where 1=1 "

	if orderId!=""{
		sqlExceptSelect+=" and id=?"
	}
	if payType!=9{
		sqlExceptSelect+=" and pay_type=?"
	}
	sqlExceptSelect+=" order by created_at desc"

	return sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,offset,orderId,payType)
}

func OrderRecode(keyname string,page int ,pageSize int) map[string]interface{}{
	  offset := (page-1)*pageSize
	  query  := "SELECT * "
	sqlExceptSelect :=" FROM t_sale_order where pay_type = 0  AND status= 1"
	if keyname  != "" {
		sqlExceptSelect += " AND id = '" + keyname +"'"
	}
	sqlExceptSelect += " order by created_at DESC"
	return  sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,nil)
}


/**
  获取订单商品数量
 */
func FindOrder(orderId string) []map[string]interface{}{
	const  sql ="select * from t_sale_order_item where sale_order_id = ?"
	return  sqlite.FindAll(sql,orderId)
}

/**
  根据商品ID查询商品信息
 */

func FindGoods(goodsId string) map[string]interface{}{
	const  sql   = "select * from t_sale_goods where id = ?"
	return  sqlite.FindFirst(sql,goodsId)
}


/**
  更新本地库存
 */
func UpdateAmount(orderId string) bool {
	data := FindOrder(orderId)
	if data != nil {
		for _,v := range data {
			goodsId:= v["sale_goods_id"].(string)
			amount,_ := strconv.ParseInt(v["amount"].(string), 10, 64)
			data1 := FindGoods(goodsId)
			count ,_:= data1["amount"].(int64)
			total := count-amount
			//更新库存
			updateTime := strconv.FormatInt(time.Now().Unix(),10)
			const sql= "update t_sale_goods set amount=?,updated_at=? where id = ?"
			sqlite.UpdateOrDelete(sql, total,updateTime, goodsId)
		}
	}
	return true
}