package cmd

import (
	"fmt"
	_"cli/service/card"
	"github.com/spf13/cobra"
	"cli/service/card"
	"github.com/widuu/gojson"
	"encoding/json"
	"cli/model/cookie"
	"strconv"
	"cli/model/site"
	"github.com/bitly/go-simplejson"
	"cli/model/sale_order"
)

var cardCmd = &cobra.Command{
	Use:   "card",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
and usage of using your command. For example:
Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
		command := args[0]
		//银犁卡绑定
		if command == "bind" {
			data := args[1]
			bind := make(map[string]interface{})
			//获取操作人信息
			bind = gojson.Json(data).Get("customer").Getdata()
			by,_ := json.Marshal(bind)
				customer := string(by)
				data1 := card.CardBind(customer)
				if data1 != nil {
					bytes,_ := json.Marshal(data1)
					m1 := make(map[string]interface{})
					if gojson.Json(string(bytes)).Get("data").Tostring()=="0001" {
						m1["code"]="200"
						m1["msg"]="绑定成功"
						by3,_ := json.Marshal(m1)
						fmt.Println(string(by3))
					}
					if gojson.Json(string(bytes)).Get("data").Tostring()=="0002" {
						m1["code"]="200"
						m1["msg"]="激活失败"
						by3,_ := json.Marshal(m1)
						fmt.Println(string(by3))
					}
					if gojson.Json(string(bytes)).Get("data").Tostring()=="1001" {
						m1["code"]="200"
						m1["msg"]="卡片已激活"
						by3,_ := json.Marshal(m1)
						fmt.Println(string(by3))
					}
					if gojson.Json(string(bytes)).Get("data").Tostring()=="1002" {
						m1["code"]="200"
						m1["msg"]="身份证已绑定"
						by3,_ := json.Marshal(m1)
						fmt.Println(string(by3))
					}
					if gojson.Json(string(bytes)).Get("data").Tostring()=="1003" {
						m1["code"]="200"
						m1["msg"]="手机已绑定"
						by3,_ := json.Marshal(m1)
						fmt.Println(string(by3))
					}
					if gojson.Json(string(bytes)).Get("data").Tostring()=="1004" {
						m1["code"]="200"
						m1["msg"]="卡号不存在"
						by3,_ := json.Marshal(m1)
						fmt.Println(string(by3))
					}
				}else {
					m1 := make(map[string]interface{})
					m1["errcode"] = "0"
					m1["errmsg"] = "绑定失败"
					bytes,_ := json.Marshal(m1)
					fmt.Println(string(bytes))
				}
		}

		/**
		  卡充值
		 */
		if command == "charge" {
			data := args[1]
			cardsyscode := gojson.Json(data).Get("cardsyscode").Tostring()
			free,_ := strconv.ParseFloat(gojson.Json(data).Get("free").Tostring(),64)
			//获取站点信息
			site := site.FindSite()
			siteCode := site["id"].(string)
			//获取操作人信息
			user := cookie.QueryCookie()
			account := user["account"].(string)
			item := map[string]interface{}{
				"cardsyscode": cardsyscode,
				"siteCode":siteCode,
				"account":account,
				"free":free,
				"employeeId":account,
				"chgtype":int(0),
			}
			info := map[string]interface{}{
				"operatorcode":account,
				"operatorname":account,
			}
			by,_ := json.Marshal(item)
			by1,_ := json.Marshal(info)
			data1 := card.Recharge(string(by),string(by1))
			if data1 != nil {
				bytes,_ := json.Marshal(data1)
				m1 := make(map[string]interface{})
				if gojson.Json(string(bytes)).Get("code").Tostring()=="success" {
					m1["code"]="200"
					m1["msg"]="充值成功"
					by3,_ := json.Marshal(m1)
					fmt.Println(string(by3))
				}else {
					m1["errorcode"]="0"
					m1["errormsg"]="充值失败"
					by4,_ := json.Marshal(m1)
					fmt.Println(string(by4))
				}
			}else {
				m1 := make(map[string]interface{})
				m1["errcode"] = "0"
				m1["errmsg"] = "充值失败"
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}
		}

		//修改密码
		if command == "resetpwd" {
			data := args[1]
			cardsyscode := gojson.Json(data).Get("cardsyscode").Tostring()
			cpasswd := gojson.Json(data).Get("cpasswd").Tostring()
			customer := map[string]interface{}{
				"cardsyscode":cardsyscode,
				"cpasswd":cpasswd,
			}
			by,_ := json.Marshal(customer)
			data1 := card.ChangePwd(string(by))
			if  data1 != nil {
				bytes,_ := json.Marshal(data1)
				if gojson.Json(string(bytes)).Get("code").Tostring() =="success" {
					m1 := make(map[string]interface{})
					m1["code"] = "200"
					m1["msg"] = "修改密码成功"
					by1,_ := json.Marshal(m1)
					fmt.Println(string(by1))
				}else {
					m1 := make(map[string]interface{})
					m1["errorcode"] = "0"
					m1["errormsg"] = "修改密码失败"
					by1,_ := json.Marshal(m1)
					fmt.Println(string(by1))
				}
			}else {
				m1 := make(map[string]interface{})
				m1["errcode"] = "0"
				m1["errmsg"] = "修改密码失败"
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}

		}

		/**
		 查询卡信息
		 */
		if command == "query" {
			data := args[1]
			cards:=gojson.Json(data).Get("cardsyscode").Tostring()
			idno := gojson.Json(data).Get("idno").Tostring()
			cphone := gojson.Json(data).Get("cphone").Tostring()
			data1 := card.QueryCardInfo(cards,idno,cphone)
			if data1 != nil {
				bytes,_ := json.Marshal(data1)
				fmt.Println(string(bytes))
			}else {
				m1 := make(map[string]interface{})
				m1["errorcode"]="0"
				m1["errormsg"]="没有该卡信息"
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}
		}

		/**
		  激活冻结
		 */
		if command == "frozen" {
			data := args[1]
			cardcode := gojson.Json(data).Get("cardsyscode").Tostring()
			status:= gojson.Json(data).Get("status").Tostring()
			//根据卡号获取卡信息
			item := map[string]interface{}{
				"cardsyscode":cardcode,
				"status":status,
			}
			by,_ := json.Marshal(item)
			data1 := card.CardFrozen(string(by))
			if  data1 != nil {
				bytes,_ := json.Marshal(data1)
				if gojson.Json(string(bytes)).Get("code").Tostring() == "success" {
					m1 := make(map[string]interface{})
					m1["code"]="200"
					m1["msg"]="激活或冻结成功"
					bytes,_ := json.Marshal(m1)
					fmt.Println(string(bytes))
				}
			}else {
				m1 := make(map[string]interface{})
				m1["errcode"] = "0"
				m1["errmsg"] = "激活或者冻结失败"
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}
		}

		/**
		  支付
		 */
		if command == "pay" {
			data := args[1]
			//重新封装数据
			data1  := cookie.QueryCookie()//获取用户
			account := data1["account"].(string)
			//站点
			siteCode := site.FindSite()["id"].(string)
			item := map[string]interface{}{
				"cardsyscode":gojson.Json(string(data)).Get("cardsyscode").Tostring(),
				"free":gojson.Json(string(data)).Get("free").Tostring(),
				"ordercode":gojson.Json(string(data)).Get("ordercode").Tostring(),
				"cPasswd":gojson.Json(string(data)).Get("cPasswd").Tostring(),
				"siteCode":siteCode,
				"account":account,
				"employeeId":account,
			}
			cards := map[string]interface{}{
				"operatorcode":account,
				"operatorname":account,
			}

			by3,_ := json.Marshal(item)
			by4,_ := json.Marshal(cards)
			//发送请求
			//获取支付前余额
			dt := card.QueryCardByCard(gojson.Json(string(data)).Get("cardsyscode").Tostring())
			if dt != nil {
				by1,_ := json.Marshal(dt)
				cn_json, _ := simplejson.NewJson([]byte(string(by1)))
				cn_data,_ := cn_json.Get("data").Array()
				initialmoney :=""
				cname :=""
				idno :=""
				cphone :=""
				cardsyscode := ""
				status := 0
				for _,v := range cn_data {
					di, _ := v.(map[string]interface{})
					if di["status"] != nil {
						stat,_ := (di["status"]).(json.Number)
						status,_ = strconv.Atoi(string(stat))
					}
					if di["initialmoney"] != nil {
						money,_  :=(di["initialmoney"]).(json.Number)
						initialmoney = string(money)
					}
					if di["cname"] != nil {
						cname = di["cname"].(string)
					}
					if di["idno"] != nil {
						idno = di["idno"].(string)
					}
					if di["cphone"] != nil {
						cphone = di["cphone"].(string)
					}
					if di["cardsyscode"] != nil {
						cardsyscode = di["cardsyscode"].(string)
					}
				}
				if status != 0 {
					m1 := make(map[string]interface{})
					m1["errorcode"]="0"
					m1["errormsg"]="卡不是激活状态，不能进行支付"
					by5,_ := json.Marshal(m1)
					fmt.Println(string(by5))
				}else {
					price := gojson.Json(string(data)).Get("free").Tostring()
					orderNo := gojson.Json(string(data)).Get("ordercode").Tostring()
					bytes,_ := json.Marshal(card.CardOPay(string(by3),string(by4)))
					//支付成功将信息写入卡支付记录表中
					if gojson.Json(string(bytes)).Get("code").Tostring() == "success" {
						//进行本地库存修改
						sale_order.UpdateAmount(orderNo)
						card.InCardRecord(initialmoney,cname,idno,cphone,cardsyscode,price,orderNo)
						m1 := make(map[string]interface{})
						m1["code"]="200"
						m1["msg"]="支付成功"
						by4,_ := json.Marshal(m1)
						fmt.Println(string(by4))
					}else {
						m1 := make(map[string]interface{})
						m1["errorcode"]="0"
						m1["errormsg"]="支付失败"
						by5,_ := json.Marshal(m1)
						fmt.Println(string(by5))
					}
				}
			}else {
				m1 := make(map[string]interface{})
				m1["errorcode"]="0"
				m1["errormsg"]="卡号信息不存在"
				by,_ := json.Marshal(m1)
				fmt.Println(string(by))
			}
		}

		//消卡
		if command == "pincard"{
			data := args[1]
			customer := map[string] interface{}{
				"cardsyscode":gojson.Json(data).Get("cardsyscode").Tostring(),
				"idno":gojson.Json(data).Get("idno").Tostring(),
				"cphone":gojson.Json(data).Get("cphone").Tostring(),
			}
			by,_ := json.Marshal(customer)
			bytes,_ := json.Marshal(card.PinCard(string(by)))
			if gojson.Json(string(bytes)).Get("code").Tostring() == "success" {
				cardsyscode := gojson.Json(data).Get("cardsyscode").Tostring()
				//查询卡信息用于封装数据
				data1 := card.QueryCardByCard(cardsyscode)
				by1,_ := json.Marshal(data1)
				cn_json, _ := simplejson.NewJson([]byte(string(by1)))
				cn_json2, _ := simplejson.NewJson([]byte(string(bytes)))
				cn_data,_ := cn_json.Get("data").Array()
				_str,_:=cn_json2.Get("data").String()
				cn_data2,_ := simplejson.NewJson([]byte(_str))
				by3,_ := json.Marshal(cn_data2)
				cname :=""
				cphone :=""
				idno := ""
				for _,v := range cn_data {
					di, _ := v.(map[string]interface{})
					if di["cname"] != nil {
						cname = di["cname"].(string)
					}
					if di["cphone"] != nil {
						cphone = di["cphone"].(string)
					}
					if di["idno"] != nil {
						idno = di["idno"].(string)
					}
				}
				returndata := map[string]interface{}{
					"cname":cname,
					"availableBalance":gojson.Json(string(by3)).Get("availableBalance").Tostring(),
					"refund":gojson.Json(string(by3)).Get("refund").Tostring(),
					"isRefund":gojson.Json(string(by3)).Get("isRefund").Tostring(),
					"cardsyscode":gojson.Json(data).Get("cardsyscode").Tostring(),
					"cphone":cphone,
					"idno":idno,
				}
				by2,_ := json.Marshal(returndata)
				fmt.Println(string(by2))
			}else {
				m1 := make(map[string]interface{})
				by,_ := json.Marshal(m1)
				fmt.Println(string(by))
			}
		}

		//消卡确认
		if command == "confirm"{
			data := args[1]
			by1,_ := json.Marshal(cookie.QueryCookie())
			user := gojson.Json(string(by1)).Get("account").Tostring()
			operatorcode := user
			operatorname := user
			cardsyscode := gojson.Json(data).Get("cardsyscode").Tostring()
			//参数封装
			cards := map[string]interface{}{
				"cardsyscode":cardsyscode,
				"operatorcode":operatorcode,
				"operatorname":operatorname,
			}
			by,_ := json.Marshal(cards)
			bytes,_ := json.Marshal(card.ConfirmPinCard(string(by)))
			if gojson.Json(string(bytes)).Get("data").Tostring() == "0001" {
				m1 := make(map[string]interface{})
				m1["code"]="200"
				m1["msg"]="销卡成功"
				by4,_ := json.Marshal(m1)
				fmt.Println(string(by4))
			}else {
				m1 := make(map[string]interface{})
				m1["errorcode"]="0"
				m1["errormsg"]="销卡失败"
				by4,_ := json.Marshal(m1)
				fmt.Println(string(by4))
			}
		}

		//获取银犁卡支付记录
		if command == "cardpayinfo"{
			data := args[1]
			keyname := gojson.Json(data).Get("keyname").Tostring()
			page,_ := strconv.Atoi(gojson.Json(data).Get("page").Tostring())
			pageSize,_ := strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
			data1 := card.FindPayInfo(keyname,page,pageSize)
			bytes,_ := json.Marshal(data1)
			fmt.Println(string(bytes))
		}
	},
}

func init() {
	RootCmd.AddCommand(cardCmd)
}
