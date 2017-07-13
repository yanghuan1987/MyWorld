package cmd

import (
	"fmt"
	"github.com/spf13/cobra"
	"cli/model/icebox"
	"encoding/json"
	"github.com/widuu/gojson"
	"strconv"
)

/**
  冰柜
 */
var icevboxCmd = &cobra.Command{
	Use:"icebox",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
and usage of using your command. For example:
Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
		//新增冰柜
		command:=args[0]
		if command=="save"{
			data := args[1]
			m := icebox.Icebox{}
			json.Unmarshal([]byte(data),&m)
			if icebox.Store(&m) {
				m1 := make(map[string]interface{})
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}else {
				m2 := make(map[string]interface{})
				m2["errcode"] ="0"
				m2["errmsg"] = "新增冰柜失败"
				bytes,_ := json.Marshal(m2)
				fmt.Println(string(bytes))
			}
		}
		if command == "search"{
			//根据冰柜编号查询冰柜信息是
			num := args[1]
			number := gojson.Json(num).Get("number").Tostring()
			if number !="" {
				//获取分页参数
				page,_:= strconv.Atoi(gojson.Json(num).Get("page").Tostring())
				pageSize,_ :=  strconv.Atoi(gojson.Json(num).Get("pageSize").Tostring())
				data1 := icebox.FindByNum(number,page,pageSize)
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
				data1 := icebox.IceBoxList(page,pageSize)
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

		//批量删除
		if command == "batchdel"{
			no := args[1]
			nums := string(no)
			if icebox.DelIcebosByNum(nums) {
				m1 := make(map[string]interface{})
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}else {
				m1 := make(map[string]interface{})
				m1["errorCode"] ="110"
				m1["errorMsg"] = "删除冰柜信息失败"
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}
		}
		if command == "modify" {
			data := args[1]
			m := icebox.Icebox{}
			json.Unmarshal([]byte(data),&m)
			if icebox.ModifyIcebox(&m){
				map1 := make(map[string]interface{})
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}else {
				map1 := make(map[string]interface{})
				map1["errcode"] = "0"
				map1["errmsg"] = "编辑冰柜信息失败"
				bytes,_ := json.Marshal(map1)
				fmt.Println(string(bytes))
			}
		}

		if command == "query" {
			data := args[1]
			if len(data) != 0{
				page,_:= strconv.Atoi(gojson.Json(data).Get("page").Tostring())
				pageSize,_:= strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
				bytes,_:=json.Marshal(icebox.IceBoxList(page,pageSize))
				fmt.Println(string(bytes))
			}else {
				m1 := make(map[string]interface{})
				m1["errorcode"] ="0"
				m1["errormsg"] = "参数异常"
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}
		}
		//查询获取iceboxid
		if command =="iceboxid" {
			data := icebox.FindId()
			data1 := map[string]interface{}{
				"list":data,
			}
			if data != nil {
				bytes,_ := json.Marshal(data1)
				fmt.Println(string(bytes))
			}else {
				m1 := make(map[string]interface{})
				m1["errorcode"]="0"
				m1["errormsg"]="未查到冷物柜信息"
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}
		}
	},
}

func init(){
	RootCmd.AddCommand(icevboxCmd)
}