// Copyright © 2017 NAME HERE <EMAIL ADDRESS>
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package cmd

import (
	"github.com/spf13/cobra"
	"cli/service/sale_order"
	"fmt"
	"encoding/json"
	"strconv"
	"github.com/widuu/gojson"
)

// saleOrderCmd represents the saleOrder command
var saleOrderCmd = &cobra.Command{
	Use:   "sale_order",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
and usage of using your command. For example:

Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
		command:=args[0]
		data:=args[1]
		params:=make(map[string]string)
		json.Unmarshal([]byte(data),&params)
		if command=="create"{
			//创建订单
			payType,_:=strconv.Atoi(params["payType"])
			m:=sale_order.Store(params["data"],payType )
			bytes,_ := json.Marshal(m)
			fmt.Println(string(bytes))
		}

		if command=="query"{
			ret:=sale_order.Search(params)
			bytes,_ := json.Marshal(ret)
			fmt.Println(string(bytes))
		}

		if command=="info"{
			ret:=sale_order.OrderInfo(params["orderId"])
			bytes,_ := json.Marshal(ret)
			fmt.Println(string(bytes))
		}

		//微信支付记录查询
		if command == "wxinfo" {
			data := args[1]
			keyname := gojson.Json(data).Get("keyname").Tostring()
			page,_:= strconv.Atoi(gojson.Json(data).Get("page").Tostring())
			pageSize,_ := strconv.Atoi(gojson.Json(data).Get("pageSize").Tostring())
			data1 := sale_order.OrderRecode(keyname,page,pageSize)
			bytes,_ := json.Marshal(data1)
			fmt.Println(string(bytes))
		}
	},
}

func init() {
	RootCmd.AddCommand(saleOrderCmd)
}
