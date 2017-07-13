package cmd

import (
	"fmt"
	"github.com/spf13/cobra"
	"cli/model/packages"
	_"cli/service/packages"
	"encoding/json"
	"strconv"
	"cli/model/shelf"
	"github.com/widuu/gojson"
	"strings"
	"cli/service/api"
	"cli/service/package_order"
)

/**
  包裹
 */
var packageCmd = &cobra.Command{
	Use:"packages",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
and usage of using your command. For example:
Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
		command := args[0]
		//包裹入柜
		if command == "save" {
			data := args[1]
			shelfId := gojson.Json(data).Get("shelfId").Tostring()
			codeNum := gojson.Json(data).Get("codeNum").Tostring()
			orderNo := strings.Split(codeNum, "-")[0] //通过包裹编码截取订单号
			//获取分页参数
			page, _ := strconv.Atoi(gojson.Json(data).Get("page").Tostring())
			pageSize, _ := strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
			//判断是否是已经上架
			data3 := packages.FindByNum(codeNum)
			order,_ := json.Marshal(data3)
			if data3 == nil || gojson.Json(string(order)).Get("status").Tostring() != "0" {
				//判断货架是否存在
				data2 := shelf.FindById(shelfId)
				if data2 != nil {
					//根据货架ID查询货架信息
					if packages.InPackage(codeNum, shelfId, orderNo) {
						//上报包裹状态
						dat := api.UpOrderStatus(orderNo, "5")
						by1,_ := json.Marshal(dat)
						if gojson.Json(string(by1)).Get("obj").Tostring() == "SessionTimeout"{
							m1 := make(map[string]interface{})
							m1["errorcode"] = "0"
							m1["errormsg"] = "登录超时过期，请重新登录"
							bytes,_ := json.Marshal(m1)
							fmt.Println(string(bytes))
						}else {
							//订单同步
							if dat != nil && gojson.Json(string(by1)).Get("obj").Tostring() == "修改订单状态失败" {
								package_order.UpdateSync(orderNo)
							} else {
								package_order.UpdateOrderSync(orderNo)
							}
							//返回列表
							data1 := packages.FindPackageList(page, pageSize)
							bytes, _ := json.Marshal(data1)
							fmt.Println(string(bytes))
						}
					} else {
						map1 := make(map[string]interface{})
						map1["errorcode"] = "0"
						map1["errormsg"] = "包裹入柜失败"
						bytes, _ := json.Marshal(map1)
						fmt.Println(string(bytes))
					}
				}else {
					map1 := make(map[string]interface{})
					map1["errorcode"] = "0"
					map1["errormsg"] = "货架ID在系统中不存在"
					bytes, _ := json.Marshal(map1)
					fmt.Println(string(bytes))
				}
			}else {
				map1 := make(map[string]interface{})
				map1["errorcode"] = "0"
				map1["errormsg"] = "包裹编码不能重复"
				bytes, _ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}

		}

		//获取包裹列表
		if command == "query"{
			data := args[1]
			page,_:= strconv.Atoi(gojson.Json(data).Get("page").Tostring())
			pageSize,_:= strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
			data1 := packages.FindPackageList(page,pageSize)
			if data1 != nil {
				bytes,_ := json.Marshal(data1)
				fmt.Println(string(bytes))
			}else {
				map1 := make(map[string]interface{})
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}

		//根据条件检索包裹信息
		if command == "search"{
			data := args[1]
			keywords := gojson.Json(data).Get("keyname").Tostring()
			status,_ := strconv.Atoi(gojson.Json(data).Get("status").Tostring())
			now,_ :=strconv.Atoi( gojson.Json(data).Get("nowtime").Tostring())
			//分页参数获取：
			page,_:= strconv.Atoi(gojson.Json(data).Get("page").Tostring())
			pageSize,_:= strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
			data2 := packages.FindPackage(keywords,status,now,page,pageSize)
			if data2 != nil{
				bytes,_:= json.Marshal(data2)
				fmt.Println(string(bytes))
			}else {
				map1 := make(map[string]interface{})
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}

		//删除包裹信息
		if command == "delete"{
			data := args[1]
			codeNum := gojson.Json(data).Get("codeNum").Tostring()
			data3 := packages.DelPackage(codeNum)
			if data3 {
				map1 := make(map[string]interface{})
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}else {
				map1 := make(map[string]interface{})
				map1["errorcode"] = "0"
				map1["errormsg"] = "删除失败"
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}
		//调整货架使用率
		if command == "updateShelf"{
			userate := args[1]
			rate,_ :=strconv.Atoi(userate)
			shelfd := args[2]
			shelfId := string(shelfd)
			if shelf.UpdateSpace(rate,shelfId){
				map1 := make(map[string]interface{})
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}else {
				map1 := make(map[string]interface{})
				map1["errorcode"] = "0"
				map1["errormsg"] = "调整失败"
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}

		if command == "move"{
			//库存移位
			data := args[1]
			shelf_Id := gojson.Json(data).Get("sid").Tostring()
			num1 := gojson.Json(data).Get("number").Tostring()
			//判断是否上架
			data1 := packages.QueryByNum(num1)
			if data1 == nil {
				//判断货架ID是否存在
				data2 := shelf.FindById(shelf_Id)
				if data2 != nil {
					if packages.MovePackage(shelf_Id, num1) {
						map1 := make(map[string]interface{})
						bytes, _ := json.Marshal(map1)
						fmt.Println(string(bytes))
					} else {
						map1 := make(map[string]interface{})
						map1["errorcode"] = "0"
						map1["errormsg"] = "操作失败"
						bytes, _ := json.Marshal(map1)
						fmt.Println(string(bytes))
					}
				}else {
					map1 := make(map[string]interface{})
					map1["errorcode"] = "0"
					map1["errormsg"] = "货架ID在系统中不存在"
					bytes, _ := json.Marshal(map1)
					fmt.Println(string(bytes))
				}
			}else {
				map1 := make(map[string]interface{})
				map1["errorcode"] = "0"
				map1["errormsg"] = "未上架不能进行该操作"
				bytes, _ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}

		//状态轮询用于同步包裹和订单状态
		 if command =="polling" {
			//状态轮询
			 packages.Polling()
		 }
	},
}

func  init()  {
	RootCmd.AddCommand(packageCmd)
}
