package product

import (
	"cli/util/sqlite"
	"strconv"
	"time"
	"cli/model/cookie"
	"github.com/widuu/gojson"
	"encoding/json"
)


/**
前置仓商品
*/
type SaleGoods struct {
	Id         string
	SalesId    string  //销售id
	ShelfId    string  //货架id
	Name       string  //商品名称
	Lot        string  //批次
	InOperator string  //上架人员
	InTime     string  //上架时间
	Status     int     //商品状态
	Amount     int     //商品库存
	Price      float64 //商品价格
	ExpireTime string  //过期时间
	Spec       string  //规格
	Unit       string  //单位
	Weight     float64 //重量
	CreatedAt  string  //创建时间
	UpdatedAt  string  //更新时间
}

/**
  调运商品入柜
 */
func Store(entity *SaleGoods) bool {
	entity.Id = strconv.FormatInt(time.Now().Unix(),10)
	const sql = "insert into t_sale_goods(id,name) values(?,?)"
	return sqlite.Insert(sql, entity.Id, entity.Name)
}

/**
  将云端数据存入本地库（废弃）
 */
func  StoreBatch(entitys []SaleGoods) bool {
	sql := "insert into t_sale_goods (id,sales_id,shelf_id,name,lot," +
		"in_operator,in_time,status,amount,price,expire_time,spec,unit,weight,created_at,updated_at) values"
	str :=""
	for _,v := range entitys{
		str +=""
		str+="("
		str+=v.Id
		str+=","
		str+=v.SalesId
		str+=","
		str+=v.ShelfId
		str+=","
		str+=v.Name
		str+=","
		str+=v.Lot
		str+=","
		str+=v.InOperator
		str+=","
		str+=v.InTime
		str+=","
		str+=strconv.Itoa(v.Status)
		str+=","
		str+=strconv.Itoa(v.Amount)
		str+=","
		str+=strconv.Itoa(int(v.Price))
		str+=","
		str+=v.ExpireTime
		str+=","
		str+=v.Spec
		str+=","
		str+=v.Unit
		str+=","
		str+=strconv.Itoa(int(v.Weight))
		str+=","
		str+=v.CreatedAt
		str+=","
		str+=v.UpdatedAt
	}
	len:=len(str)
	str=str[1:len]
	sql+=str
	return sqlite.Insert(sql)
}

/**
 获取销售商品的信息
 */
func FindAll(page int,pageSize int) map[string]interface{} {
	offset := (page-1)*pageSize
	query := "SELECT tsg.*, ts.number AS shelfNo,tic.name AS icename"
	sqlExceptSelect  := "FROM t_sale_goods tsg LEFT JOIN t_shelf ts ON tsg.shelf_id = ts.id LEFT JOIN t_icebox tic ON ts.icebox_id = tic.id WHERE 1=1 order by tsg.in_time desc"
	return sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,nil)
}

/**
   删除销售商品
 */
func DelSaleGoods(id string) bool{
	const  sql = "delete from t_sale_goods where id =?"
	return sqlite.UpdateOrDelete(sql,id)
}

/**
   关键字检索
 */
func FindGoodsByCode(code string,page int,pageSize int) (m map[string]interface{}){
	offset := (page-1)*pageSize
	query := "SELECT tsg.*, ts.number AS shelfNo,tic.name AS icename "
	sqlExceptSelect := " FROM t_sale_goods tsg inner JOIN t_shelf ts ON tsg.shelf_id = ts.id inner JOIN t_icebox tic ON ts.icebox_id = tic.id WHERE tsg.sales_id = ?"
	m = sqlite.Paginate2(offset,pageSize,query,sqlExceptSelect,code)
	return  m
}

/**
   商品上架
 */

func InShelf(salesId string,shelfId string,amount int,lot string)  bool{
	Id := strconv.FormatInt(time.Now().Unix(),10)
	inTime := strconv.FormatInt(time.Now().Unix(),10)
	created_at := strconv.FormatInt(time.Now().Unix(),10)
	//获取当前操作人
	data := cookie.QueryCookie()
	bytes,_ := json.Marshal(data)
	user := gojson.Json(string(bytes)).Get("account").Tostring()//操作人
	const  sql   = "insert into t_sale_goods(id,sales_id,lot,shelf_id,amount,in_time,in_operator,created_at) values(?,?,?,?,?,?,?,?)"
	return  sqlite.UpdateOrDelete(sql,Id,salesId,lot,shelfId,amount,inTime,user,created_at)
}

/**
  扫码购买商品
 */
func SaleByBarCode(barcode string) (data map[string] interface{}){
	const  sql   = "select id,name,price,weight,unit where sales_id = ?"
	data = sqlite.FindFirst(sql,barcode)
	return  data
}

/**
  根据商品id更新商品数据
 */

func UpdateSaleGoods(entity []SaleGoods) bool{
	entity[0].UpdatedAt=strconv.FormatInt(time.Now().Unix(),10)
	sql := "update t_sale_goods set sales_id = ? ,name = ?,status = ?,amount = ?," +
		"price = ?,expire_time =?,spec =?,unit = ?,weight=?,update_at=? where id = ? "
	return  sqlite.UpdateOrDelete(sql,entity[0].SalesId,entity[0].Name,entity[0].Status,entity[0].Amount,
		entity[0].Price,entity[0].ExpireTime,entity[0].Spec,entity[0].Unit,entity[0].Weight,entity[0].UpdatedAt,entity[0].Id)
}

/**
  查询商品信息
 */

func FindGoodsInfo() []map[string]interface{}{
	const  sql   = "select * from t_sale_goods "
	return  sqlite.FindAll(sql)
}


func UpdateGoods(salesId string,name string ,price float64,weight float64,unit string) bool{
	updatedAt := strconv.FormatInt(time.Now().Unix(),10)
	const sql   = "update t_sale_goods set price =? ,name=? ,weight =? , unit =?,updated_at=? where sales_id = ?"
	return sqlite.UpdateOrDelete(sql,price,name,weight,unit,updatedAt,salesId)
}


func FindGoods(number string,shelfId string,lot string) map[string]interface{}{
	const sql = "select * from t_sale_goods where sales_id= ? and shelf_id = ? and lot = ? "
	return  sqlite.FindFirst(sql,number,shelfId,lot)
}

/**
  更新库存
 */

func UpdateAmount(amount int64,number string,shelfId string,lot string) bool {
	updateTime := strconv.FormatInt(time.Now().Unix(),10)
	const  sql  = "update t_sale_goods set amount =?,updated_at =?  where sales_id= ? and shelf_id = ? and lot = ?"
	return sqlite.UpdateOrDelete(sql,amount,updateTime,number,shelfId,lot)
}


func FindGoodInfo(number string,lot string) map[string]interface{}{
	const sql   = "select * from t_sale_goods where sales_id = ? and lot =?"
	return  sqlite.FindFirst(sql,number,lot)
}