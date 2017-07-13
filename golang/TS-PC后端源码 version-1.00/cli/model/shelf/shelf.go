package shelf

import (
	"cli/util/sqlite"
	"strconv"
	"time"
)

/**
货架
*/
type Shelf struct {
	Id          string
	siteName    string //站点名称
	Number      string //编号
	IceboxId    string //所属冰柜
	Temperature string //温区
	Userate     int    //使用率
	Status      int    //状态，1=运行中
	CreatedAt    string //创建时间
	UpdatedAt   string //修改时间
}


/**
   新增货架信息
 */
func  Store(entity *Shelf) bool{
	//时间格式化
	entity.Id = strconv.FormatInt(time.Now().Unix(),10)+"000"
	entity.CreatedAt = strconv.FormatInt(time.Now().Unix(),10)
	entity.UpdatedAt = strconv.FormatInt(time.Now().Unix(),10)
	const  sql  = "insert into t_shelf (id,number,icebox_id,temperature,userate,status,created_at,updated_at) values(?,?,?,?,?,?,?,?)"
	return  sqlite.Insert(sql,entity.Id,entity.Number,entity.IceboxId,entity.Temperature,entity.Userate,entity.Status,entity.CreatedAt,entity.UpdatedAt)
}

/**
  根据货架编号查询货架信息
 */
func FindByNum(icename string,number string,page int ,pageSize int) map[string]interface{}{
	offset := (page-1)*pageSize
	query := "SELECT ts.*,ti.number AS iceboxnum,ti.site_id as siteCode "
	sqlExceptSelect := " FROM t_shelf ts LEFT JOIN t_icebox ti ON ts.icebox_id = ti.id WHERE  1=1 "
	if number != "" {
		sqlExceptSelect += " and ts.number= '" + number +"'"
	}
	if icename != "-1" {
		sqlExceptSelect +=" and ti.id = " + icename
	}
		sqlExceptSelect +=" order by ts.created_at desc"
	return  sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,nil)
}

/**
  删除货架信息
 */
func DelShelfByNum(numbers string) bool {
	const sql  =  "delete from t_shelf where number in ( ? )"
	return  sqlite.UpdateOrDelete(sql,numbers)
}

/**
  更新货架使用率
 */
func UpdateSpace(userate int ,shelfId string) bool{
	const sql  = "update t_shelf set userate = ? where id = ? "
	return  sqlite.UpdateOrDelete(sql,userate,shelfId)
}

func ModifyShelf(entity *Shelf) bool{
	shelfId := entity.Id
	entity.UpdatedAt = strconv.FormatInt(time.Now().Unix(),10)
	const sql  = "update t_shelf set number = ? ,icebox_id=?, temperature=?,userate=?,status=?,updated_at=? where id = ?"
	return  sqlite.UpdateOrDelete(sql,entity.Number,entity.IceboxId,entity.Temperature,entity.Userate,entity.Status,entity.UpdatedAt,shelfId)
}

/**
  查询列表
 */

func ShelfList(page int,pageSize int) map[string]interface{}{
	offset := (page-1)*pageSize
	query := "SELECT ts.*,ti.number AS iceboxnum,ti.site_id as siteCode "
	sqlExceptSelect := " FROM t_shelf ts LEFT JOIN t_icebox ti ON ts.icebox_id = ti.id order by ts.created_at desc "
	return sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,nil)
}


/**
  根据id查询货架信息
 */
func FindById(shefId string) map[string]interface{}{
	const  sql   = "select * from t_shelf where id = ?"
	return  sqlite.FindFirst(sql,shefId)
}

