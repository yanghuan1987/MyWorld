package packages

import (
	"cli/model/packages"
	"cli/model/shelf"
)

/**
包裹入库(入柜)
 */
func Store(entity *packages.Packages) bool {

	return packages.Store(entity)
}


/**
  获取包裹列表
 */
//func FindPackageList() (data []map[string]interface{}){
//	data = packages.FindPackageList()
//	return  data
//}

/**
   根据条件检索包裹信息(过期处理也可用)
 */
func FindPackage(keywords string, status int,nowtime int,page int,pageSize int) (data map[string]interface{}){
	data = packages.FindPackage(keywords, status,nowtime,page,pageSize)
	return data
}

/**
   删除包裹信息（过期处理删除也可用）
 */
func DelPackage(number string) bool{
	return packages.DelPackage(number)
}

/**
  调整货架使用率
 */
func UpdateSpace(userate int ,shelfId string) bool{
	return  shelf.UpdateSpace(userate,shelfId)
}

/**
  库存移位
 */
func MovePackage(shelfId string,number string) bool{
	return  packages.MovePackage(shelfId,number)
}

