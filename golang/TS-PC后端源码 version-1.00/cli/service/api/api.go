package api

import (
	"cli/util/http"
	"encoding/json"
	"net/http"
	"io/ioutil"
	"cli/model/cookie"
	"strconv"
)

const (
	//测试地址
	//server = "http://10.8.48.233"
	//server2 = "https://10.8.48.233"
	//生产地址
	server = "http://120.77.246.186"
	server2 = "https://120.77.246.186"//银犁卡https
	//登陆接口
	url_login =  server +"/uias-web/login/applogin"
	//站点验证
	url_siteCode_Verification = server + "/tss-web/getSiteCode"
	//获取订单包裹列表
	url_package_order_list = server +"/tss-web/getSiteOrderList"
	//获取销售商品信息
	url_sale_goods = server +"/tss-web/getProductPrice"
	//银犁卡绑定
	url_card_bind =server2 +"/spcms-web/card/activation"
	//银犁卡支付
	url_card_pay=server2 +"/spcms-web/card/debit"
	//银犁卡充值
	url_card_recharge=server2 +"/spcms-web/card/recharge"
	//银犁卡修改密码
	url_card_pwd=server2 +"/spcms-web/card/updatePasswordhttp"
	//银犁卡信息查询
	url_card_query=server2 +"/spcms-web/card/httpSelectCardInfo"
	//银犁卡冻结激活
	url_card_frozen=server2 +"/spcms-web/card/frozenByHttp"
	//上传订单信息
	url_upload_order_info=server+"/tss-web/order/uploadSiteOrder"
	//微信支付
	url_wechat_pay =  server +"/tss-web/pay/orderQrcode"
	//微信支付结果
	url_wechat_pay_info = server+ "/tss-web/pay/weChatPayQuery"
	//版本检测自动更新
	url_version_check = server + "/tss-web/version/getCurrentVersion"
	//银犁卡消卡
	url_card_pincard = server2 + "/spcms-web/card/cardLogOffRefund"
	//银犁卡消卡确认
	url_card_pincard_confirm = server2 + "/spcms-web/card/cardLogOff"
	//上传用户取件状态到云端
	url_picked = server + "/tss-web/order/setOrderStatus"
)
var result map[string]interface{}

/**
获取包裹订单
 */
func FindPackageOrderList(sitId string,dateType int) map[string]interface{}{
	params := map[string]string{"siteCode":sitId,"dateType":strconv.Itoa(dateType)}
	//将cookie加入
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str :=  http_client.Request(url_package_order_list,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return result
}

/**
 获取调运销售商品信息
 */
func FindSalesGoods(code string) map[string]interface {}{
	params := map[string]string{
		"productCode":code,
	}
	//将cookie加入
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_sale_goods,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return result
}

/**
  银犁卡绑定
 */
func CardBind(customer string) map[string]interface{}{
	params := map[string]string{
		"customer":customer,
	}
	//将cookie加入
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_card_bind,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return result
}

/**
  银犁卡充值
 */
func Recharge(param string,cards string) map[string]interface{}{
	params := map[string]string{
		"rechargeVo":param,
		"card":cards,
	}
	//将cookie加入
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_card_recharge,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return  result
}

/**
  修改密码
 */
func ChangePwd(customer string) map[string]interface{}{
	params := map[string]string{
		"customer":customer,
	}
	//将cookie加入
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_card_pwd,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return  result
}

/**
  卡信息查询
 */
func QueryCardInfo(cards string,idno string,cphone string) map[string]interface{}{
    var result map[string]interface{}
	params := map[string]string{
		"cardsyscode":cards,
		"idno":idno,
		"cphone":cphone,
	}
	//将cookie加入
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_card_query,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return result
}

/**
  卡冻结激活
 */

func FrozenCard(cards string) map[string]interface{}{
	params := map[string]string{
		"card":cards,
	}
	//将cookie加入
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_card_frozen,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return  result
}

/**
  银犁卡支付
 */
func CardPay(debitVo string,cards string) map[string]interface{}{
	params :=map[string]string{
		"debitVo":debitVo,
		"card":cards,
	}
	//将cookie加入
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_card_pay,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return  result
}

/**
  调取Login登陆接口
 */
func Login(username string,password string) bool{
	url := url_login + "?" + "account=" + username + "&" + "password=" + password
	client := &http.Client{}
	req,err := http.NewRequest("POST",url,nil)
	if err != nil {
		return false
	}
	req.Header.Set("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
	req.Header.Set("Cache-Control","no-cache")

	resp,err := client.Do(req)
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return false
	}

	json.Unmarshal([]byte(string(body)),&result)
	if result["status"].(string)!="success"{
		//登录失败
		return false
	}
	//保存cookie
	cookies:=resp.Cookies()
	for _, v := range cookies {
		if v.Name=="JSESSIONID"{
			//删除cookie
			cookie.DelCookie()
			value := v.Value
			cookie.AddCookie("JSESSIONID", value,username)
			break
		}
	}
	return true
}

/**
  站点验证
 */

func VerificationSite(siteCode string) map[string]interface{}{
	params := map[string]string{
		"siteCode":siteCode,
	}
	//将cookie加入
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_siteCode_Verification,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return  result
}

/**
  微信支付
 */
func WeChatPay(orderNo string) map[string]interface{}{
	params := map[string]string{
		"orderNo":orderNo,
	}
	//将cookie加入
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_wechat_pay,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return result
}

/**
  支付结果轮询
 */

func WeChatPayInfo(orderNo string) map[string]interface{}{
	params := map[string]string{
		"orderNo":orderNo,
	}
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_wechat_pay_info,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return result
}

/**
上传订单信息
 */
func UploadOrderInfo(params map[string]interface{}) map[string]interface{}{
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	if data==nil{
		return map[string]interface{}{
			"errcode":403,
			"errmsg":"未登陆",
		}
	}
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.RequestJson(url_upload_order_info,"POST",nil,cookies,params)
	if str==nil{
		return map[string]interface{}{
			"errcode":10000,
			"errmsg":"网络请求失败",
		}
	}
	//403
	if str.(string)=="0"{
		return map[string]interface{}{
			"errcode":403,
			"errmsg":"未登陆",
		}
	}
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return result
}

func CheckVersion() map[string]interface{}{
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_version_check,"POST",nil,cookies,nil)
	if str==nil{
		return map[string]interface{}{
			"errcode":10000,
			"errmsg":"网络请求失败",
		}
	}
	//403
	if str.(string)=="0"{
		return map[string]interface{}{
			"errcode":403,
			"errmsg":"未登陆",
		}
	}
		json.Unmarshal([]byte(str.(string)),&result)
	return result
}

/**
  银犁卡销卡
 */
func PinCard(customer string) map[string]interface{}{
	params := map[string]string{
		"customer":customer,
	}
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_card_pincard,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return result
}

/**
  消卡操作主要用于退款金额查询
 */
func ComfirmPinCard(card string) map[string]interface{}{
	params := map[string]string{
		"card":card,
	}
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_card_pincard_confirm,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return result
}


/**
	上报订单状态
 */
func UpOrderStatus(orderNo string,status string) map[string]interface{}{
	params := map[string]string{
		"orderNo":orderNo,
		"status":status,
	}
	data := cookie.QueryCookie()//获取cookie(map[string]interface{}类型)
	JESSIONID := data["name"]
	value := data["value"]
	cookie := http.Cookie{
		Name:JESSIONID.(string),
		Value:value.(string),
	}
	cookies:= []*http.Cookie{&cookie}
	str := http_client.Request(url_picked,"POST",nil,cookies,params)
	if str !=nil {
		json.Unmarshal([]byte(str.(string)),&result)
	}
	return result
}