package package_order

import (
	"cli/util/sqlite"
	"time"
	"strconv"
	"github.com/bitly/go-simplejson"
	"encoding/json"
	"strings"
)

/**
包裹订单，包含了多个包裹（package_goods)
*/
type PackageOrder struct {
	Id           string
	OrderNo      string //订单编号
	Type         int    //类型
	DeliverTime  string //配送时间
	Customer     string //客户名称
	Phone        string //客户电话
	Zone         string //收货区域
	Address      string //收货详细地址
	Verification string //提货码
	CreateTime   string //订单创建时间
	Status       int    //状态,-1 未上架 0 未取件 1 已取件
	SiteId       string //站点编号
}

func Store(entity *PackageOrder) bool {
	entity.Id = string(time.Now().Unix())
	entity.Status = 0
	const sql = "insert into t_package_order(id,order_no,type,deliver_time,customer,phone,zone,address,verification,create_time,status,site_id " +
		"values(?,?,?,?,?,?,?,?,?,?,?,?)"
	return sqlite.Insert(sql, entity.Id, entity.OrderNo, entity.Type, entity.DeliverTime,
		entity.Customer, entity.Phone, entity.Zone, entity.Address, entity.Verification,
		entity.CreateTime, entity.Status, entity.SiteId,
	)
}

/**
  保存订单包裹信息
 */
func StoreBatch(params string) bool {
	cn_json, _ := simplejson.NewJson([]byte(params))
	cn_obj, _ := cn_json.Get("obj").Array()
	for i, v := range cn_obj {
		di, _ := v.(map[string]interface{})
		Idd := strconv.FormatInt(time.Now().Unix(), 10)
		IDD, _ := strconv.Atoi(Idd)
		phone := ""
		zone := ""
		verification := ""
		customer := ""
		addr := ""
		//获取订单号用于判断订单是否存在
		orderNo := di["orderNo"].(string)
		data := QueryOrderByCode(orderNo)
		if len(data) == 0 {
			if !strings.Contains(orderNo, "ZTD") {
				Id := IDD + i
				//新增订单到订单包裹表中
				if di["phone"] != nil {
					phone = di["phone"].(string)
				}
				if di["zone"] != nil {
					zone = di["zone"].(string)
				}
				siteCode := di["siteCode"].(string)
				ctime, _ := (di["createTime"]).(json.Number)
				create_time, _ := strconv.ParseFloat(string(ctime), 64)
				createTime := int64(create_time) / 1000
				if di["verification"] != nil {
					verification = di["verification"].(string)
				}
				deliver, _ := (di["deliverTime"]).(json.Number)
				deliver_time, _ := strconv.ParseFloat(string(deliver), 64)
				deliverTime := int64(deliver_time) / 1000
				if di["receiver"] != nil {
					customer = di["receiver"].(string)
				}
				if di["addr"] != nil {
					addr = di["addr"].(string)
				}
				const sql = "insert into t_package_order (id,order_no,type,deliver_time,customer,phone,zone,address,verification,create_time,status,site_id) " +
					"values (?,?,?,?,?,?,?,?,?,?,?,?)"
				b := sqlite.Insert(sql, Id, orderNo, 1, deliverTime, customer, phone, zone, addr, verification, createTime, -1, siteCode)
				if !b {
					return false
				}
			}
		}
	}
	return true
}

func UpdateStatusByOrder(status int, code string) bool {
	const sql = "update t_package_order set status=? where order_no=? "
	return sqlite.UpdateOrDelete(sql, status, code)
}

/**
  根据提货码查询订单包裹
 */
func FindByCode(code string) (data []map[string]interface{}) {
	const sql = "SELECT  tpo.order_no AS order_no,tpo.phone AS phone,tpo.customer AS customer,tp.status AS status," +
		"tpo.create_time AS create_time,tp.number AS number,tic.name AS icename,tp.expire_time AS expire_time,ts.temperature AS temperature," +
		"ts.number AS shelfName FROM t_package_order tpo " +
		"LEFT JOIN t_packages tp ON tpo.order_no = tp.order_no " +
		"LEFT JOIN t_shelf ts ON ts.id = tp.shelf_id " +
		"LEFT JOIN t_icebox tic ON tic.id = ts.icebox_id " +
		"WHERE tpo.order_no = ? order by tpo.create_time desc"
	//根据提货查询获取订单号
	if len(FindOrderByCode(code)) > 0{
		data1 := FindOrderByCode(code)[0]
		if data1 != nil {
			//再根据订单号获取信息
			orderNo := data1["order_no"]
			data = sqlite.FindAll(sql, orderNo)
		}
	}
	return data
}

func FindPackageOrderList(page int, pageSize int) map[string]interface{} {
	offset := (page - 1) * pageSize
	query := "select * "
	sqlExceptSelect := " from t_package_order  order by create_time DESC"
	return sqlite.Paginate2(offset, pageSize, query, sqlExceptSelect, nil)
}

/**
   根据电话或者订单号检索
 */
func FindByOrderOrPhone(orderNo string, phone string, page int, pageSize int) (data map[string]interface{}) {
	offset := (page - 1) * pageSize
	query := "select *"
	sqlExceptSelect := " from t_package_order "
	if orderNo != "" || phone != "" {
		sqlExceptSelect += " where order_no = ? or phone = ?"
	}
	sqlExceptSelect += "  order by create_time DESC"
	data = sqlite.Paginate2(offset, pageSize, query, sqlExceptSelect, orderNo, phone)
	return data
}

/**
  丢弃确认
 */

func ConfirmiscarId(number string) bool {
	const sql = "update t_packages set status = 2 where number =?"
	return sqlite.UpdateOrDelete(sql, number)
}

func FindByOrderNo(orderNo string) map[string]interface{} {
	const sql = " select * from t_packages where order_no = ?"
	return sqlite.FindFirst(sql, orderNo)
}

/**
  刷新订单进行数据清理
 */
func DelOrders() bool {
	const sql = "delete from t_package_order"
	return sqlite.UpdateOrDelete(sql)
}

/**
   根据订单号查询包裹订单信息
 */
func QueryOrderByCode(orderNo string) map[string]interface{} {
	const sql = "select * from t_package_order where order_no = ?"
	return sqlite.FindFirst(sql, orderNo)
}

func GetInfo(orderNo string) []map[string]interface{} {
	const sql = "SELECT  tpo.order_no AS order_no,tpo.phone AS phone,tpo.customer AS customer,tpo.status AS status," +
		"tpo.create_time AS create_time,tp.number AS number,tic.name AS icename,tp.expire_time AS expire_time,ts.temperature AS temperature," +
		"ts.number AS shelfName FROM t_package_order tpo " +
		"LEFT JOIN t_packages tp ON tpo.order_no = tp.order_no " +
		"LEFT JOIN t_shelf ts ON ts.id = tp.shelf_id " +
		"LEFT JOIN t_icebox tic ON tic.id = ts.icebox_id " +
		"WHERE tpo.order_no = ?"
	return sqlite.FindAll(sql, orderNo)
}

func FindOrderByCode(code string) []map[string]interface{} {
	const sql = "select * from t_package_order where verification = ? order by create_time desc"
	return sqlite.FindAll(sql, code)

}
