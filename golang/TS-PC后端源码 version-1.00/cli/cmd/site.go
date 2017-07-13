package cmd

import (
	"fmt"
	"github.com/spf13/cobra"
	"cli/model/site"
	"encoding/json"
)

// siteCmd represents the site command
var siteCmd = &cobra.Command{
	Use:   "site",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
and usage of using your command. For example:
Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
			command := args[0]
		if command == "save"{
			data:=args[1]
			m:=site.Site{}
			json.Unmarshal([]byte(data),&m)
			if site.Store(&m){
				m1 := make(map[string]interface{})
				bytes,_ := json.Marshal(m1)
				fmt.Println(string(bytes))
			}else{
				m1 := make(map[string]interface{})
				bytes,_:= json.Marshal(m1)
				m1["errcode"]="0"
				m1["errmsg"]="操作失败"
				fmt.Println(string(bytes))
			}
		}
		if command == "query" {
			data := site.FindSite()
			if data != nil {
				bytes,_:= json.Marshal(data)
				fmt.Print(string(bytes))
			}else {
				m1 := make(map[string]interface{})
				bytes,_:= json.Marshal(m1)
				fmt.Println(string(bytes))
			}
		}
	},
}

func init() {
	RootCmd.AddCommand(siteCmd)
}
