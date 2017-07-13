package icebox

import (
	"cli/util/sqlite"
	"strings"
	"strconv"
	"time"
	"cli/model/site"
)

/**
冰柜
*/
type Icebox struct {
	Id           string
	SiteId       string //站点id
	Number       string //编号
	Name         string //名称
	ShelfAmount  int    //货架数量
	Status       int    //状态，1=运行中
	Temperature1 string //温区1
	Temperature2 string //温区2
	Temperature3 string //温区3
}

/**
 新增冰柜
 */
func  Store(entity *Icebox) bool {
	entity.Id = strconv.FormatInt(time.Now().Unix(),10)
	data := site.FindSite()
	entity.SiteId = data["id"].(string)
	const  sql   = "insert into t_icebox(id,site_id,number,name,shelf_amount,status,temperature1,temperature2,temperature3) values(?,?,?,?,?,?,?,?,?)"
	return  sqlite.Insert(sql,entity.Id,entity.SiteId,entity.Number,entity.Name,entity.ShelfAmount,entity.Status,entity.Temperature1,entity.Temperature2,entity.Temperature3)
}

/**
   根据冰柜编号查询冰柜信息
 */
func FindByNum(number string,page int,pageSize int) map[string]interface{} {
	params := number
	offset := (page-1)*pageSize
	query := "select * "
	sqlExceptSelect := " from t_icebox where number = ?"
	return  sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,params)
}

/**
  删除冰柜信息
 */
func DelIcebosByNum(number string) bool {
	const sql   = "delete from t_icebox where number = ? "
	return sqlite.UpdateOrDelete(sql,number)
}

/**
  批量删除
 */
func BatchDeleteByNum(nums string) bool{
	str := strings.Split(nums,",")
	const  sql = "delete from t_icebox where number in ( ? )"
	return  sqlite.UpdateOrDelete(sql,str)
}

func ModifyIcebox(entity *Icebox) bool{
	boxId := entity.Id
	const  sql   = "update t_icebox set site_id=?, number = ?,name=?,shelf_amount=?,status=?,temperature1=?,temperature2=?,temperature3=? where id =?"
	return  sqlite.UpdateOrDelete(sql,entity.SiteId,entity.Number,entity.Name,entity.ShelfAmount,entity.Status,entity.Temperature1,entity.Temperature2,entity.Temperature3,boxId)
}

/**
  查询冰柜列表
 */

func IceBoxList(page int ,pageSize int ) map[string]interface{}{
	offset := (page-1)*pageSize
	query := "select * "
	sqlExceptSelect := " from t_icebox "
	return sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,nil)
}

func FindByCount() int {
	const sql="select count(*) as count from t_icebox "
	return sqlite.Count(sql)
}

/**
  获取冷物柜ID
 */

func FindId()  []map[string]interface{}{
	siteCode := site.FindSite()
	siteId := siteCode["id"].(string)
	const  sql  =  "select * from t_icebox where site_id = ?"
	return sqlite.FindAll(sql,siteId)
}