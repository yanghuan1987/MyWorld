package package_order

import (
	packageOrder "cli/model/package_order"
	"cli/service/api"
	"cli/model/packages"
	"encoding/json"
	"cli/util/sqlite"
	"github.com/widuu/gojson"
)

/**
同步云端包裹订单到本地
 */
func SyncCloudData(siteId string,dateType int,dateType1 int) map[string]interface {}{
	data1 := api.FindPackageOrderList(siteId,dateType1)
	if data1 != nil{
		by,_ := json.Marshal(data1)
		if  gojson.Json(string(by)).Get("obj").Tostring() == "SessionTimeout" {
			m1 := make(map[string]interface{})
			m1["code"]="10000"
			m1["msg"]="登录超时，请重新登录"
			return  m1
		}else {
			b := packageOrder.StoreBatch(string(by))
			if b {
				data := api.FindPackageOrderList(siteId,dateType)
				bytes,_:= json.Marshal(data)
				packageOrder.StoreBatch(string(bytes))
			}
			m1 := make(map[string]interface{})
			m1["code"]="100"
			m1["msg"]="刷新成功"
			return  m1
		}
	}
	m2 := make(map[string]interface{})
	m2["code"]="0"
	m2["msg"]="刷新失败"
	return m2
}

/**
   根据提货码获取客户包裹信息
 */

func PackageInfo(code string) (data []map[string]interface{}) {
	data = packageOrder.FindByCode(code)
	return data
}

/**
用户取件
 */
func Picked(orderNo string) bool {
	data := packageOrder.FindByOrderNo(orderNo)
	if data == nil {
		return false
	}else {
		b1 := packageOrder.UpdateStatusByOrder(1, orderNo)
		if b1 == false {
			return false
		}
		//更改包裹状态
		b2 := packages.UpdateStatusByOrder(1, orderNo)
		if !b2 {
			packageOrder.UpdateStatusByOrder(0, orderNo)
			return false
		}
	}
	return true

}

/**
   根据订单号或者电话号码检索订单包裹信息
 */
func GetPackageOrder(orderNo string, phone string,page int,pageSize int) (data map[string]interface{}) {
	data = packageOrder.FindByOrderOrPhone(orderNo, phone,page,pageSize)
	return data
}

/**
  获取本地数据列表
 */
func FindPackageOrderList(page int ,pageSize int ) map[string]interface{}{
	data := packageOrder.FindPackageOrderList(page,pageSize)
	return  data
}

/**
  过期处理确认
  param:number(包裹编号)
 */
func ConfirmiscardD(number string) bool {
	b1 := packageOrder.ConfirmiscarId(number)
	if !b1 {
		return false
	}
	return true
}

func GetInfo(orderNo string) []map[string]interface{}{
	data := packageOrder.GetInfo(orderNo)
	return data
}

/**
  更新同步状态信息
 */

func UpdateSync(orderNo string) bool {
	//更新订单包裹的同步状态
	const sql = "update t_package_order set is_sync=1 where order_no = ?"
	b := sqlite.UpdateOrDelete(sql,orderNo)
	if !b {
		return  false
	}else {
		//更新包裹的同步状态
		const  sql1   = "update t_packages set is_sync =1 where order_no = ?"
		b1 :=sqlite.UpdateOrDelete(sql1,orderNo)
		if b1{
			return true
		}
	}
	return true
}


func UpdateOrderSync(orderNo string) bool {
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


func FindByOrder(orderNo string) map[string]interface{}{
	const  sql   =" select * from t_packages where order_no = ?"
	return  sqlite.FindFirst(sql,orderNo)
}



func FindPackage(number string) map[string]interface{}{
	const  sql   = "select * from t_packages where number = ?"
	return  sqlite.FindFirst(sql,number)
}