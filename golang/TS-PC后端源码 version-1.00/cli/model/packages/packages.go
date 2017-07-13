package packages

import (
	"cli/util/sqlite"
	"strconv"
	"time"
	"cli/model/cookie"
	"encoding/json"
	"github.com/widuu/gojson"
	"cli/service/api"
)

/**
包裹
*/
type Packages struct {
	Id          string
	Number     string //包裹编码
	OrderNo     string //所属订单号
	ShelfId     string //货架id
	InOperator  string //上架人员
	InTime      string //上架时间
	OutOperator string //取货人员
	OutTime     string //取货时间
	Status      int    //商品状态 0正在使用  1已取件  2已丢弃
	ExpireTime  string //过期时间
	CreatedAt   string //创建时间
	UpdatedAt   string //更新时间
}

/**
  包裹入库
 */
func Store(entity *Packages) bool {
	entity.Id = strconv.FormatInt(time.Now().Unix(),10)
	entity.InTime = strconv.FormatInt(time.Now().Unix(),10)
	const sql = "insert into t_packages(id,number,order_no,shelf_id,in_operator," +
		"in_time,out_operator,out_time,status,expire_time,created_at,updated_at) values(?,?,?,?,?,?,?,?,?,?,?,?)"
	return sqlite.Insert(sql, entity.Id, entity.Number, entity.OrderNo, entity.ShelfId,
		entity.InOperator, entity.InTime, entity.OutOperator, entity.OutTime, entity.Status, entity.ExpireTime,
		entity.CreatedAt, entity.UpdatedAt)
}

/**
  根据订单号查询包裹
 */
func FindByOrder(orderNo string) (data []map[string]interface{}) {
	const sql = "select * from t_packages where order_no=?"
	data = sqlite.FindAll(sql, orderNo)
	return data
}

/**
   修改包裹状态
 */
func UpdateStatusByOrder(status int, orderNo string) bool {
	user,_ := json.Marshal(cookie.QueryCookie())
	username := gojson.Json(string(user)).Get("account").Tostring()
	outTIme := strconv.FormatInt(time.Now().Unix(),10)
	const sql = "update t_packages set status=?,out_operator=?,out_time= ? where order_no=? "
	return sqlite.UpdateOrDelete(sql, status,username,outTIme,orderNo)
}

/**
   查找包裹列表
 */
func FindPackageList(page int ,pageSize int ) map[string]interface{}{
	offset := (page-1)*pageSize
	query := "SELECT  tp.*, ts.temperature AS temperature,ts.Number as shelfNo "
	sqlExceptSelect := " FROM t_packages tp INNER JOIN t_shelf ts  ON tp.shelf_id = ts.id  order by tp.in_time DESC"
	return sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,nil)
}

/**
   根据条件检索信息
 */
func FindPackage(keywords string,status int,nowtime int,page int,pageSize int)(data map[string]interface{}){
	offset := (page-1)*pageSize
	query := "SELECT  tp.*, ts.temperature AS temperature,ts.Number as shelfNo"
	sqlExceptSelect := " FROM t_packages tp LEFT JOIN t_shelf ts  ON tp.shelf_id = ts.id WHERE 1=1"
	if keywords != "" {
		sqlExceptSelect += " and tp.number like '%"+keywords+"%'"
	}
	if status != int(-1) {
		sqlExceptSelect += " and tp.status = " + strconv.Itoa(status)
	}
	if nowtime != int(-1) {
		sqlExceptSelect += " and tp.expire_time < " + strconv.Itoa(nowtime)
	}
	sqlExceptSelect += " order by tp.in_time DESC"
	data = sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,nil)
	return  data
}

/**
  删除包裹信息
 */
func DelPackage(number string) bool {
	const sql  = "delete from t_packages where number = ? "
	return  sqlite.UpdateOrDelete(sql,number)
}

/**
   库存移位
 */
func  MovePackage(shelfId string,number string) bool{
	const  sql   = "update t_packages set shelf_id = ? where number = ?"
	return  sqlite.UpdateOrDelete(sql,shelfId,number)
}


/**
  包裹入柜
 */

func InPackage(number string,shelfId string,orderNo string) bool{
	Id := strconv.FormatInt(time.Now().Unix(),10)
	user,_ := json.Marshal(cookie.QueryCookie())
	InOperator := gojson.Json(string(user)).Get("account").Tostring()
	InTime := strconv.FormatInt(time.Now().Unix(),10)
	t := time.Now()
	ExpireTime := time.Date(t.Year(), t.Month(), t.Day(), 0, 0, 0, 0, t.Location()).Unix() + 22*60*60//过期时间默认当晚10点
	CreatedAt := strconv.FormatInt(time.Now().Unix(),10)
	UpdatedAt := strconv.FormatInt(time.Now().Unix(),10)
	status := "0"
	const  sql   = "insert into t_packages (id,number,order_no,shelf_id,in_operator,in_time,status,expire_time,created_at,updated_at) values (?,?,?,?,?,?,?,?,?,?)"
	b := sqlite.Insert(sql,Id,number,orderNo,shelfId,InOperator,InTime,status,ExpireTime,CreatedAt,UpdatedAt)
	if b {
		//更新包裹订单状态为上架
		b1 := UpdateStatus(orderNo)
		if b1 {
			return  true
		}
	}
	return true
}

func FindPackageListPassed(page int ,pageSize int ) map[string]interface{}{
	offset := (page-1)*pageSize
	query := "SELECT  tp.*, ts.temperature AS temperature,ts.Number as shelfNo "
	sqlExceptSelect := " FROM t_packages tp LEFT JOIN t_shelf ts  ON tp.shelf_id = ts.id  where tp.status = 2"
	return sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,nil)
}

func QueryByNum(number string) map[string]interface{}{
	const sql   ="select * from t_packages where status = -1 and number = ?"
	return  sqlite.FindFirst(sql,number)
}


/**
  状态轮询
 */
func Polling() bool{
	//获取包裹中的未同步订单
	data := FindPackagesSync()
	for _,v := range data {
		//上架未同步的
		if v["status"].(int) == 0 {
			//更新云端状态
			dat := api.UpOrderStatus(v["order_no"].(string), "5")
			by,_ := json.Marshal(dat)
			if gojson.Json(string(by)).Get("obj").Tostring() !="修改订单状态失败" {
				//更新本地同步状态
				UpdateOrderIsSync(v["order_no"].(string))
			}
		}
		//取件未同步
		if v["status"].(int) == 1 {
			//更新云端状态
			dat := api.UpOrderStatus(v["order_no"].(string), "6")
			by,_ := json.Marshal(dat)
			if gojson.Json(string(by)).Get("obj").Tostring() !="修改订单状态失败" {
				//更新本地同步状态
				UpdateOrderIsSync(v["order_no"].(string))
			}
		}
		//丢弃未同步
		if v["status"].(int) == 2 {
			//更新云端状态
			dat := api.UpOrderStatus(v["order_no"].(string), "7")
			by,_ := json.Marshal(dat)
			if gojson.Json(string(by)).Get("obj").Tostring() !="修改订单状态失败" {
				//更新本地同步状态
				UpdateOrderIsSync(v["order_no"].(string))
			}
		}
	}
	return true
}

/**
  查询未同步的订单信息
 */
func FindPackagesSync() []map[string]interface{}{
	const  sql  ="select * from t_packages where id_sync = 1"
	return  sqlite.FindAll(sql)
}


func UpdateOrderIsSync(orderNo string) bool {
	//更新订单包裹的同步状态
	const sql = "update t_package_order set is_sync=0 where order_no = ?"
	b := sqlite.UpdateOrDelete(sql,orderNo)
	if !b {
		return  false
	}else {
		//更新包裹的同步状态
		const  sql1   = "update t_packages set is_sync =0 where order_no = ?"
		b1 :=sqlite.UpdateOrDelete(sql1,orderNo)
		if b1{
			return true
		}
	}
	return true
}

func FindByNum(number string) (data map[string]interface{}) {
	const sql = "select * from t_packages where number=?"
	data = sqlite.FindFirst(sql, number)
	return data
}


func UpdateStatus(orderNo string) bool{
	const  sql   = "update t_package_order set status =0 where order_no = ?"
	return  sqlite.UpdateOrDelete(sql,orderNo)
}