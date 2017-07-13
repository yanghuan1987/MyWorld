package site

import (
	"time"
	"cli/util/sqlite"
	"strconv"
)

/**
站点信息，只有一条数据
*/
type Site struct {
	Id        string //站点编码
	Name      string //站点名称
	CreatedAt string //创建时间
}

func Store(entity *Site) bool{
	entity.CreatedAt=strconv.FormatInt(time.Now().Unix(),10)
	const sql  = "insert into t_site(id,name,created_at) values(?,?,?)"
	return sqlite.Insert(sql,entity.Id,entity.Name,entity.CreatedAt)
}

/**
 查询站点信息
 */
func FindSite() map[string]interface{}{
	const sql   = "select * from t_site "
	data := sqlite.FindFirst(sql)
	return  data
}
