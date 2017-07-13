package cmd

import (
	"fmt"
	"github.com/spf13/cobra"
	"cli/model/site"
	"cli/model/icebox"
	"encoding/json"
)

// dashboardCmd represents the dashboard command
var dashboardCmd = &cobra.Command{
	Use:   "dashboard",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
and usage of using your command. For example:

Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
		//自提点信息
		site:=site.FindSite()
		//冷物柜总数
		icebox:=icebox.FindByCount()
		//上架货品数量

		//销码取件数量
		//丢弃货品数量

		data:=map[string]interface{}{
			"siteId":site["id"],
			"icebox":icebox,
		}
		bytes,_ := json.Marshal(data)
		fmt.Println(string(bytes))
	},
}

func init() {
	RootCmd.AddCommand(dashboardCmd)
}
