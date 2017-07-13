package card

import (
	"cli/service/api"
	"encoding/json"
	"github.com/widuu/gojson"
	"strconv"
	"time"
	"cli/model/cookie"
	"cli/model/site"
	"cli/util/sqlite"
)

//银犁卡管理

/**
  银犁卡绑定
 */
func CardBind(customer string) map[string]interface{}{
	data := api.CardBind(customer)
	bytes,_ := json.Marshal(data)
	obj := gojson.Json(string(bytes)).Get("obj").Tostring()
	if obj == "SessionTimeout" {
		m1 := make(map[string]interface{})
		m1["errorcode"]="0"
		m1["errormsg"]="未授权操作，请重新登录"
		return m1
	}
    return data
}

/**
	银犁卡充值
 */
func Recharge(charge string,cards string) map[string]interface {}{
	data := api.Recharge(charge,cards)
	bytes,_ := json.Marshal(data)
	obj := gojson.Json(string(bytes)).Get("obj").Tostring()
	if obj == "SessionTimeout" {
		m1 := make(map[string]interface{})
		m1["errorcode"]="0"
		m1["errormsg"]="未授权操作，请重新登录"
		return m1
	}
	return  data
}

/**
   修改密码
 */
func ChangePwd(customer string) map[string]interface{}{
	data := api.ChangePwd(customer)
	bytes,_ := json.Marshal(data)
	obj := gojson.Json(string(bytes)).Get("obj").Tostring()
	if obj == "SessionTimeout" {
		m1 := make(map[string]interface{})
		m1["errorcode"]="0"
		m1["errormsg"]="未授权操作，请重新登录"
		return m1
	}
	return data
}

/**
	卡信息查询
 */
func QueryCardInfo(cards string,idno string,cphone string) map[string]interface{}{
	data :=  api.QueryCardInfo(cards,idno,cphone)
	bytes,_ := json.Marshal(data)
	obj := gojson.Json(string(bytes)).Get("obj").Tostring()
	if obj == "SessionTimeout" {
		m1 := make(map[string]interface{})
		m1["errorcode"]="0"
		m1["errormsg"]="未授权操作，请重新登录"
		return m1
	}
	return data
}

/**
	卡冻结
 */
func CardFrozen(cards string) map[string]interface{}{
	data := api.FrozenCard(cards)
	bytes,_ := json.Marshal(data)
	obj := gojson.Json(string(bytes)).Get("obj").Tostring()
	if obj == "SessionTimeout" {
		m1 := make(map[string]interface{})
		m1["errorcode"]="0"
		m1["errormsg"]="未授权操作，请重新登录"
		return m1
	}
	return  data
}

/**
  银犁卡支付
 */

func CardOPay(params string,cards string) map[string]interface{}{
	data := api.CardPay(params,cards)
	bytes,_ := json.Marshal(data)
	obj := gojson.Json(string(bytes)).Get("obj").Tostring()
	if obj == "SessionTimeout" {
		m1 := make(map[string]interface{})
		m1["errorcode"]="0"
		m1["errormsg"]="未授权操作，请重新登录"
		return m1
	}
	return  data
}

/**
  银犁卡肖卡
 */

func PinCard(customer string) map[string]interface{}{
	data := api.PinCard(customer)
	//获取卡信息，用于数据封装
	bytes,_ := json.Marshal(data)
	obj := gojson.Json(string(bytes)).Get("obj").Tostring()
	if obj == "SessionTimeout" {
		m1 := make(map[string]interface{})
		m1["errorcode"]="0"
		m1["errormsg"]="未授权操作，请重新登录"
		return m1
	}
	return  data
}

/**
  消卡确认
 */
 func ConfirmPinCard(card string) map[string]interface{}{
	 data := api.ComfirmPinCard(card)
	 bytes,_ := json.Marshal(data)
	 obj := gojson.Json(string(bytes)).Get("obj").Tostring()
	 if obj == "SessionTimeout" {
		 m1 := make(map[string]interface{})
		 m1["errorcode"]="0"
		 m1["errormsg"]="未授权操作，请重新登录"
		 return m1
	 }
	 return  data
 }

//将银犁卡支付写入记录表中
func InCardRecord(initialmoney string,cname string,idno string,cphone string,cardsyscode string,price string,orderNo string) bool {
		Id := strconv.FormatInt(time.Now().Unix(),10)
		data  := cookie.QueryCookie()//获取用户
	    account := data["account"].(string)
		payTime := strconv.FormatInt(time.Now().Unix(),10)
		siteCode := site.FindSite()["id"].(string)
		old_money,_ := strconv.ParseFloat(initialmoney,64)
		total_price,_ := strconv.ParseFloat(price,64)
	const  sql   ="insert into card_record (id,card_no,cname,id_no,phone,old_money,total_price,order_no,pay_time,pay_status,account,site_code)" +
		" values(?,?,?,?,?,?,?,?,?,?,?,?)"
	return  sqlite.Insert(sql,Id,cardsyscode,cname,idno,cphone,old_money,total_price,orderNo,payTime,1,account,siteCode)
}

/**
  查询银犁卡支付结果列表
 */
func FindPayInfo(keyname string,page int,pageSize int) map[string]interface{}{
	offset := (page-1)*pageSize
	query := "select * "
	sqlExceptSelect := "from card_record where 1=1  AND pay_status = 1"
	if keyname != ""{
		sqlExceptSelect += " AND order_no = '"+ keyname +"'"
	}
	sqlExceptSelect +=  " order by pay_time DESC"
	return  sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,nil)
}

/**
  通过卡号获取信息
 */
func QueryCardByCard(cards string) map[string]interface{}{
	idno := ""
	cphone :=""
	data :=  api.QueryCardInfo(cards,idno,cphone)
	bytes,_ := json.Marshal(data)
	obj := gojson.Json(string(bytes)).Get("obj").Tostring()
	if obj == "SessionTimeout" {
		m1 := make(map[string]interface{})
		m1["errorcode"]="0"
		m1["errormsg"]="未授权操作，请重新登录"
		return m1
	}
	return data
}