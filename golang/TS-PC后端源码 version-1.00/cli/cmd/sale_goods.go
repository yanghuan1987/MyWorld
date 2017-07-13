package cmd

import (
	"fmt"
	"github.com/spf13/cobra"
	"cli/model/sale_goods"
	"encoding/json"
	"strconv"
	"github.com/widuu/gojson"
	"cli/model/sale_order"
	"cli/service/api"
	"cli/model/shelf"
)

/**
  销售商品
 */
var saleGoodsCmd = &cobra.Command{
	Use:   "sale_goods",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
and usage of using your command. For example:
Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
		command := args[0]
		//调运商品入柜
		if command == "save" {
			data := args[1]
			m := product.SaleGoods{}
			json.Unmarshal([]byte(data), &m)
			if product.Store(&m) {
				map1 := make(map[string]interface{})
				bytes, _ := json.Marshal(map1)
				fmt.Println(string(bytes))
			} else {
				map1 := make(map[string]interface{})
				map1["errcode"] = "0"
				map1["errmsg"] = "商品入柜失败"
				bytes, _ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}
		//获取调运商品信息
		if command == "query" {
			data := args[1]
			page, _ := strconv.Atoi(gojson.Json(data).Get("page").Tostring())
			pageSize, _ := strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
			data1 := product.FindAll(page, pageSize)
			if data1 != nil {
				bytes, _ := json.Marshal(data1)
				fmt.Println(string(bytes))
			} else {
				map1 := make(map[string]interface{})
				bytes, _ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}
		//删除销售商品
		if command == "delete" {
			data := args[1]
			id := gojson.Json(data).Get("id").Tostring()
			if product.DelSaleGoods(id) {
				map1 := make(map[string]interface{})
				bytes, _ := json.Marshal(map1)
				fmt.Println(string(bytes))
			} else {
				map1 := make(map[string]interface{})
				map1["errcode"] = "0"
				map1["errmsg"] = "删除商品失败"
				bytes, _ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}
		//关键字检索
		if command == "search" {
			data := args[1]
			codenum := gojson.Json(data).Get("code").Tostring()
			if codenum != "" {
				//获取分页参数
				page, _ := strconv.Atoi(gojson.Json(data).Get("page").Tostring())
				pageSize, _ := strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
				data3 := product.FindGoodsByCode(codenum, page, pageSize)
				if data3 != nil {
					bytes, _ := json.Marshal(data3)
					fmt.Println(string(bytes))
				} else {
					map1 := make(map[string]interface{})
					bytes, _ := json.Marshal(map1)
					fmt.Println(string(bytes))
				}
			} else {
				page, _ := strconv.Atoi(gojson.Json(data).Get("page").Tostring())
				pageSize, _ := strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
				data3 := product.FindAll(page, pageSize)
				if data3 != nil {
					bytes, _ := json.Marshal(data3)
					fmt.Println(string(bytes))
				} else {
					map1 := make(map[string]interface{})
					bytes, _ := json.Marshal(map1)
					fmt.Println(string(bytes))
				}
			}
		}

		//商品上架
		if command == "add" {
			data := args[1]
			barcode := gojson.Json(data).Get("number").Tostring()
			rs := []rune(barcode)
			number := string(rs[0:13])
			lt := string(rs[13:len(barcode)])
			shelfId := gojson.Json(data).Get("shelfId").Tostring()
			amount, _ := strconv.Atoi(gojson.Json(data).Get("amount").Tostring())
			if lt == "" || len(lt) != 13{
				m1 := make(map[string]interface{})
				m1["errorcode"]="0"
				m1["errormsg"]="商品批次号不能为空或批次号不能小于13位"
				bytes, _ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}else {
				//判断货架Id是否存在
				data2 := shelf.FindById(shelfId)
				if data2 != nil {
					//判断是否是同一个商品上不同货架
					goods := product.FindGoodInfo(number,lt)
					if goods != nil && strconv.FormatInt(goods["shelf_id"].(int64),10) != shelfId{
						m1 := make(map[string]interface{})
						m1["errorcode"]="0"
						m1["errormsg"]="商品已经在其他货架存在不能进行此操作"
						bytes, _ := json.Marshal(m1)
						fmt.Println(string(bytes))
					}else {
						//判断是否存在该商品信息存在就加库存
						data3 := product.FindGoods(number, shelfId, lt)
						if data3 != nil {
							//更新库存
							count := data3["amount"].(int64)
							total := count + int64(amount)
							b := product.UpdateAmount(total, number, shelfId, lt)
							if b {
								map1 := make(map[string]interface{})
								bytes, _ := json.Marshal(map1)
								fmt.Println(string(bytes))
							} else {
								map1 := make(map[string]interface{})
								map1["errcode"] = "0"
								map1["errmsg"] = "商品上架失败"
								bytes, _ := json.Marshal(map1)
								fmt.Println(string(bytes))
							}
						} else {
							if product.InShelf(number, shelfId, amount, lt) {
								//商家成功返回列表
								map1 := make(map[string]interface{})
								bytes, _ := json.Marshal(map1)
								fmt.Println(string(bytes))
							} else {
								map1 := make(map[string]interface{})
								map1["errcode"] = "0"
								map1["errmsg"] = "商品上架失败"
								bytes, _ := json.Marshal(map1)
								fmt.Println(string(bytes))
							}
						}
					}
				}else {
					map1 := make(map[string]interface{})
					map1["errorcode"] = "0"
					map1["errormsg"] = "货架ID在系统中不存在"
					bytes, _ := json.Marshal(map1)
					fmt.Println(string(bytes))
				}
			}
		}

		//更新商品信息
		if command == "goodsInfo" {
			//data := args[1]
			////分页参数
			//page, _ := strconv.Atoi(gojson.Json(data).Get("page").Tostring())
			//pageSize, _ := strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
			data1 := product.FindGoodsInfo()
			if data1 != nil {
				//遍历数组
				for _, v := range data1 {
					productCode := v["sales_id"].(string)
					//根据id获取云端数据进行更新
					data2 := api.FindSalesGoods(productCode)
					//判断登录是否超时
						if len(data2) > 0 {
							var dat map[string]interface{}
							//获取云端返回数据进行更新
							bytes, _ := json.Marshal(data2)
							by3,_ := json.Marshal(data2)
							obj := gojson.Json(string(by3)).Get("obj").Tostring()
							if obj != "SessionTimeout" {
								if err := json.Unmarshal([]byte(string(bytes)), &dat); err == nil {
									if len(dat["obj"].([]interface{})) > 0 {
										mapTemp := (dat["obj"].([]interface{}))[0].(map[string]interface{})
										price := mapTemp["commoditySalesPrice"].(float64)
										name := mapTemp["commodityName"].(string)
										unit := mapTemp["commodityWeightUnit"].(string)
										weight := mapTemp["commodityWeight"].(float64)
										//进行本地更新操作
										product.UpdateGoods(productCode, name, price, weight, unit)
									}
								}
							}else {
								m1 := make(map[string]interface{})
								m1["errorcode"]="0"
								m1["errormsg"]="登录超时过期，请重新登录"
								bytes,_ := json.Marshal(m1)
								fmt.Println(string(bytes))
								return
							}
						}
				}
				m1 :=make(map[string]interface{})
				m1["code"]="200"
				m1["msg"]="刷新成功"
				bytes, _ := json.Marshal(m1)
				fmt.Println(string(bytes))
			} else {
				map1 := make(map[string]interface{})
				bytes, _ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}

		//根据商品条码查询商品信息
		if command == "barcode" {
			data := args[1]
			barcode := gojson.Json(data).Get("barcode").Tostring()
			//分页参数
			page :=1
			pageSize := 15
			//参数判断
			if len(barcode) >13 {
				data1 := sale_order.FindByNo(barcode,page,pageSize)
				if data1 != nil {
					bytes, _ := json.Marshal(data1)
					fmt.Println(string(bytes))
				} else {
					map1 := make(map[string]interface{})
					bytes, _ := json.Marshal(map1)
					fmt.Println(string(bytes))
				}
			}else {
				m1 := make(map[string]interface{})
				m1["errorcode"]="0"
				m1["errormsg"]="商品条码错误"
				by,_ := json.Marshal(m1)
				fmt.Println(string(by))
			}
		}
	},
}

func init() {
	RootCmd.AddCommand(saleGoodsCmd)
}
