package cmd

import (
	"github.com/spf13/cobra"
	"fmt"
	"cli/service/api"
	"github.com/widuu/gojson"
	"encoding/json"
	"cli/model/site"
	"strconv"
)

/**
  登陆
 */
const versionThis = float64(1.00)

var loginCmd = &cobra.Command{
	Use:   "login",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
and usage of using your command. For example:
Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
		command := args[0]
		if command =="login" {
			versionErr:=map[string]interface{}{
				"errcode":0,
				"errmsg":"版本号格式错误",
			}
			siteErr:=map[string]interface{}{
				"errcode":0,
				"errmsg":"站点编号验证失败",
			}
			passErr:=map[string]interface{}{
				"errcode":0,
				"errmsg":"用户名或密码错误",
			}
			data := args[1]
			username := gojson.Json(data).Get("account").Tostring()
			password := gojson.Json(data).Get("password").Tostring()
			siteCode := gojson.Json(data).Get("code").Tostring()
			//登录验证
			isLogin:=api.Login(username, password)
			if !isLogin{
				bytes,_ := json.Marshal(passErr)
				fmt.Println(string(bytes))
				return
			}
			//验证本地站点数据
			siteInfo:=site.FindSite()
			if siteInfo==nil{
				//远程验证站点数据，后保存
				siteRemote := api.VerificationSite(siteCode)
				if siteRemote["obj"]==false{
					bytes,_ := json.Marshal(siteErr)
					fmt.Println(string(bytes))
					return
				}
				//保存站点信息
				siteEntity:=site.Site{
					Id :siteCode,
					Name:siteCode,
				}

				site.Store(&siteEntity)
			}
			if siteInfo!=nil&&siteInfo["id"]!=siteCode{

				bytes,_ := json.Marshal(siteErr)
				fmt.Println(string(bytes))
				return
			}
			//检测版本信息
			versionInfo := api.CheckVersion()
			if versionInfo["errorcode"] != nil {
				bytes,_:= json.Marshal(versionInfo)
				fmt.Println(string(bytes))
			}
			bytes,_ := json.Marshal(versionInfo)
			versionString := versionInfo["obj"].(map[string]interface{})["version"].(string)
			downUrl := versionInfo["obj"].(map[string]interface{})["downloadUrl"].(string)
			version,err:=strconv.ParseFloat(versionString,64)
			if err!=nil{
				bytes,_ := json.Marshal(versionErr)
				fmt.Println(string(bytes))
				return
			}
			//返回登录后数据
			needUpdate:=0
			if versionThis>version{
				needUpdate=1
			}
				loginSuccess:=map[string]interface{}{
					"needUpdate":needUpdate,
					"downUrl":downUrl,
				}
				bytes,_ = json.Marshal(loginSuccess)
				fmt.Println(string(bytes))
				return
		}
	},
}

func init() {
	RootCmd.AddCommand(loginCmd)
}