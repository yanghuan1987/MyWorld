package sale_order_item

import (
	"strconv"
	"time"
	"cli/util/sqlite"
)

/**
销售订单明细
 */
type SaleOrderItem struct {
	Id          string
	SaleOrderId	string	//销售订单id
	SaleGoodsId string  //商品id
	Name        string  //商品名称
	Amount      int     //数量
	Price       float64 //单价
}

func Store(entity *SaleOrderItem) bool{
	entity.Id = strconv.FormatInt(time.Now().Unix(),10)
	const  sql = "isnert into t_sale_order_item (id,sale_order_id,sale_goods_id,name,amount,price) values (?,?,?,?,?,?)"
	return  sqlite.Insert(sql,entity.Id,entity.SaleOrderId,entity.SaleGoodsId,entity.Name,entity.Amount,entity.Price)
}

func StoreBatch(items []map[string]interface{}) bool{

	sql:="insert into t_sale_order_item(id,sale_order_id,sale_goods_id,amount,price) " +
		"values "
	str:=""
	for k,v:=range items{
		id:=strconv.FormatInt( time.Now().Unix(),10)+strconv.Itoa(k)
		str+=","
		str+="('"
		str+=id
		str+="','"
		str+=v["orderNo"].(string)
		str+="','"
		str+=v["goodsId"].(string)
		str+="',"
		str+=strconv.FormatInt(v["count"].(int64),10)
		str+=","
		str+=strconv.FormatFloat(v["actPrice"].(float64),'f', -1, 32)

		str+=")"
	}

	len:=len(str)
	str=str[1:len]
	sql+=str

	return sqlite.Insert(sql)
}