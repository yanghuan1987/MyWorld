package cmd

import (
	"fmt"
	"github.com/spf13/cobra"
	"cli/service/package_order"
	"strconv"
	"github.com/widuu/gojson"
	"encoding/json"
	"cli/model/site"
	"cli/model/packages"
	"cli/service/api"
	"strings"
)

/**
  包裹订单
 */
var packageOrderCmd = &cobra.Command{
	Use:"package_order",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
and usage of using your command. For example:
Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
		command := args[0]
		//调用云端同步到本地
		if command == "refresh"{
			data := args[1]
			siteId := site.FindSite()["id"].(string)
			dateType := int(0)
			dateType1 := int(-1)
			page,_:= strconv.Atoi(gojson.Json(data).Get("page").Tostring())
			pageSize,_:= strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
			data1 :=package_order.SyncCloudData(siteId,dateType,dateType1)
			by,_ := json.Marshal(data1)
			code := gojson.Json(string(by)).Get("code").Tostring()
			if code == "100"{
				data := package_order.FindPackageOrderList(page,pageSize)
				bytes,_ := json.Marshal(data)
				fmt.Println(string(bytes))
			}else if gojson.Json(string(by)).Get("code").Tostring() == "10000"{
				m1 := make(map[string]interface{})
				m1["errorcode"] = "0"
				m1["errormsg"] = "登录超时过期，请重新登录"
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}else {
				m1 := make(map[string]interface{})
				m1["errorcode"] = "0"
				m1["errormsg"] = "刷新订单失败"
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}
		}
		//用户取件
		if command == "picked"{
			data := args[1]
			orderNo := gojson.Json(data).Get("orderNo").Tostring()
			if orderNo != "" {
				//查询是否已经上架
				data := package_order.FindByOrder(orderNo)
				if data["status"] == int64(-1){
					m1 := make(map[string]interface{})
					m1["errcode"] = "0"
					m1["errmsg"] = "未上架不能进行取件操作"
					bytes,_ := json.Marshal(m1)
					fmt.Println(string(bytes))
				}else{
					if package_order.Picked(orderNo) {
						//向远端上报订单取件状态
						status := "6"
						data1 :=api.UpOrderStatus(orderNo,status)
						by,_ := json.Marshal(data1)
						//判读是否session过期
						if data1 != nil && gojson.Json(string(by)).Get("obj").Tostring() == "SessionTimeout"{
							m1 := make(map[string]interface{})
							m1["errorcode"] = "0"
							m1["errormsg"] = "登录超时过期，请重新登录"
							bytes,_ := json.Marshal(m1)
							fmt.Println(string(bytes))
						}else {
							//失败后将同步信息设为1(0表示已同步，1表示未同步)
							if gojson.Json(string(by)).Get("obj").Tostring() =="修改订单状态失败" {
								package_order.UpdateSync(orderNo)
							}else {
								package_order.UpdateOrderSync(orderNo)
							}
							//成功返回数据
							data2 := make(map[string]interface{})
							data2["count"]=len(package_order.GetInfo(orderNo))
							data2["list"]=package_order.GetInfo(orderNo)
							data2["totalPages"]=1
							data2["totalRows"]=len(package_order.GetInfo(orderNo))
							bytes,_ := json.Marshal(data2)
							fmt.Println(string(bytes))
						}
					}else {
						m1 := make(map[string]interface{})
						m1["errorcode"] = "0"
						m1["errormsg"] = "用户取件失败"
						bytes,_ := json.Marshal(m1)
						fmt.Println(string(bytes))
					}
				}
			}else {
				m1 := make(map[string]interface{})
				m1["errorcode"] = "0"
				m1["errormsg"] = "用户取件失败"
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}

		}
		//调用根据提货码检索信息
		if command == "query"{
			data := args[1]
			m1 := gojson.Json(data).Get("code").Tostring()
			if package_order.PackageInfo(m1) != nil {
				data1:= make(map[string]interface{})
				data1["count"]=len(package_order.PackageInfo(m1))
				data1["list"]=package_order.PackageInfo(m1)
				data1["totalPages"]=1
				data1["totalRows"]=len(package_order.PackageInfo(m1))
				bytes,_ := json.Marshal(data1)
				fmt.Println(string(bytes))
			}else {
				map1 := make(map[string]interface{})
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}

		//根据订单号或者电话号码检索订单包裹信息
		if command == "search" {
			data := args[1]
			m1 := gojson.Json(data).Get("keyname").Tostring()
			page,_ := strconv.Atoi(gojson.Json(data).Get("page").Tostring())
			pageSize,_ :=strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
			if package_order.GetPackageOrder(m1,m1,page,pageSize) != nil{
				bytes,_ := json.Marshal(package_order.GetPackageOrder(m1,m1,page,pageSize))
				fmt.Println(string(bytes))
			}else {
				map1 := make(map[string]interface{})
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}

		/**
		  确认丢弃
		 */
		if command == "passed" {
			data := args[1]
			number := gojson.Json(data).Get("number").Tostring()
			page,_ := strconv.Atoi(gojson.Json(data).Get("page").Tostring())
			pageSize,_ := strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
			orderNo := strings.Split(number,"-")[0]
			if number != ""{
				//判断是否存在
				data := package_order.FindPackage(number)
				if data != nil {
					if package_order.ConfirmiscardD(number) {
						//更新云端状态
						data1 := api.UpOrderStatus(orderNo, "7")
						by, _ := json.Marshal(data1)
						if data1 != nil && gojson.Json(string(by)).Get("obj").Tostring() == "SessionTimeout"{
							m1 := make(map[string]interface{})
							m1["errorcode"] = "0"
							m1["errormsg"] = "登录超时过期，请重新登录"
							bytes,_ := json.Marshal(m1)
							fmt.Println(string(bytes))
						}else {
							//失败后将同步信息设为1(0表示已同步，1表示未同步)
							if gojson.Json(string(by)).Get("obj").Tostring() == "修改订单状态失败" {
								package_order.UpdateSync(orderNo)
							} else {
								package_order.UpdateOrderSync(orderNo)
							}
							//返回信息
							m2 := packages.FindPackageListPassed(page, pageSize)
							bytes, _ := json.Marshal(m2)
							fmt.Println(string(bytes))
						}
					} else {
						map1 := make(map[string]interface{})
						map1["errorcode"] = "0"
						map1["errormsg"] = "确认丢弃失败"
						bytes, _ := json.Marshal(map1)
						fmt.Println(string(bytes))
					}
				}else {
					map1 := make(map[string]interface{})
					map1["errorcode"] = "0"
					map1["errormsg"] = "没有该包裹信息"
					bytes, _ := json.Marshal(map1)
					fmt.Println(string(bytes))
				}
			}else {
				m2 := packages.FindPackageListPassed(page,pageSize)
				bytes,_ := json.Marshal(m2)
				fmt.Println(string(bytes))
			}

		}

		/**
		   默认展示已丢弃的数据
		 */
		if command == "list" {
			data := args[1]
			page,_ := strconv.Atoi(gojson.Json(data).Get("page").Tostring())
			pageSize,_ := strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
			m2 := packages.FindPackageListPassed(page,pageSize)
			bytes,_ := json.Marshal(m2)
			fmt.Println(string(bytes))
		}
	},
}

func init()  {
	RootCmd.AddCommand(packageOrderCmd)
}
