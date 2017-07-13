package cmd

import (
	"fmt"
	"github.com/spf13/cobra"
	"cli/service/api"
	"encoding/json"
	"github.com/widuu/gojson"
)

// wxpayCmd represents the wxpay command
var wxpayCmd = &cobra.Command{
	Use:   "wxpay",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
and usage of using your command. For example:

Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
		command := args[0]
		if command == "pay" {
			//微信支付
			orderNo := args[1]
			code := gojson.Json(orderNo).Get("code").Tostring()
			data := api.WeChatPay(code)
			bytes,_ := json.Marshal(data)
			fmt.Println(string(bytes))
		}
		if command == "payInfo" {
			//支付信息查询
			code := args[1]
			orderNo := gojson.Json(code).Get("code").Tostring()
			data := api.WeChatPayInfo(orderNo)
			bytes, _ := json.Marshal(data)
			fmt.Println(string(bytes))
		}
	},
}

func init() {
	RootCmd.AddCommand(wxpayCmd)
}
