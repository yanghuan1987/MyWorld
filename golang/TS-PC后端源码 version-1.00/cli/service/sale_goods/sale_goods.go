package product

import (
	saleGoodsModel "cli/model/sale_goods"
	//"cli/service/api"
)

/**
  从云端获取信息更新本地库
 */
//func UpdateGoods(code string) bool{
//	data := api.FindSalesGoods(code)
//	return saleGoodsModel.UpdateSaleGoods(data)
//}



/**
  调运商品入柜
 */
func Store(data *saleGoodsModel.SaleGoods) bool{
	b:=saleGoodsModel.Store(data)
	return b
}

/**
   获取销售商品信息
 */
func GoodsInfo() []map[string]interface{}{
	return  saleGoodsModel.FindGoodsInfo()
}

/**
  删除销售商品
 */
func DelGoodsByLot(lot string) bool{
	return saleGoodsModel.DelSaleGoods(lot)
}

/**
  关键字检索
 */

//func FindGoodsByCode(code string) (data map[string]interface{}){
//	data = saleGoodsModel.FindGoodsByCode(code)
//	return data
//}

/**
   商品上架
 */
func InShelf(salesId string,shelfId string,amount int,lot string) bool{
	return  saleGoodsModel.InShelf(salesId,shelfId,amount,lot)
}

/**
   扫码销售
 */

func SaleByBarCode(barcode string) (data map[string]interface{}){

	return  data
}
