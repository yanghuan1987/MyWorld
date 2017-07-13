package cmd

import (
	"github.com/spf13/cobra"
	"cli/model/shelf"
	"encoding/json"
	"fmt"
	"strconv"
	"github.com/widuu/gojson"
)

/**
  货架
 */
var shelfCmd = &cobra.Command{
	Use:"shelf",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
and usage of using your command. For example:
Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
		command := args[0]
		if command == "save"{
			//新增货架信息
			data := args[1]
			m := shelf.Shelf{}
			json.Unmarshal([]byte(data),&m)
			if shelf.Store(&m) {
				map1 := make(map[string]interface{})
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}else {
				map1 := make(map[string]interface{})
				map1["errorcode"] = "0"
				map1["errormsg"] = "新增货架失败"
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}

		if command == "search"{
			//根据货架编号查询货架信息
			num := args[1]
			number := gojson.Json(num).Get("number").Tostring()
			icename := gojson.Json(num).Get("icename").Tostring()
			//冷物柜名称
			if number != "" || icename != ""  {
				//获取分页参数
				page,_:= strconv.Atoi(gojson.Json(num).Get("page").Tostring())
				pageSize,_ :=  strconv.Atoi(gojson.Json(num).Get("pageSize").Tostring())
				data1 := shelf.FindByNum(icename,number,page,pageSize)
				if data1 != nil{
					bytes,_ := json.Marshal(data1)
					fmt.Println(string(bytes))
				}else {
					m1 := make(map[string]interface{})
					bytes,_ := json.Marshal(m1)
					fmt.Println(string(bytes))
				}
			}else {
				//获取分页参数
				page,_:= strconv.Atoi(gojson.Json(num).Get("page").Tostring())
				pageSize,_ :=  strconv.Atoi(gojson.Json(num).Get("pageSize").Tostring())
				data1 := shelf.ShelfList(page,pageSize)
				if data1 != nil{
					bytes,_ := json.Marshal(data1)
					fmt.Println(string(bytes))
				}else {
					m1 := make(map[string]interface{})
					bytes,_ := json.Marshal(m1)
					fmt.Println(string(bytes))
				}
			}

		}

		if command == "delete"{
			//删除冰柜信息
			no := args[1]
			score := string(no)
			if shelf.DelShelfByNum(score) {
				map1 := make(map[string]interface{})
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}else {
				map1 := make(map[string]interface{})
				map1["errorCode"] = "110"
				map1["errorMsg"] = "新增货架失败"
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}
		if command == "update"{
			//更新货架使用率
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
				map1["errorCode"] = "110"
				map1["errorMsg"] = "更新失败"
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}
		if command  == "modify" {
			data := args[1]
			m := shelf.Shelf{}
			json.Unmarshal([]byte(data),&m)
			if shelf.ModifyShelf(&m){
				map1 := make(map[string]interface{})
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}else {
				map1 := make(map[string]interface{})
				map1["errorCode"] = "110"
				map1["errorMsg"] = "编辑货架信息失败"
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}
		if command == "query" {
			data := args[1]
			page,_:= strconv.Atoi(gojson.Json(data).Get("page").Tostring())
			pageSize,_:= strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
			bytes,_ := json.Marshal(shelf.ShelfList(page,pageSize))
			fmt.Println(string(bytes))
		}
	},
}

func init(){
	RootCmd.AddCommand(shelfCmd)
}