import { request,request2 } from '../utils'

export async function query(params) {
	return request('sale_goods/barcode', {
	  method: 'get',
	  data: params
	})
}

export async function queryInfo(params) {

	
	return request('sale_goods/barcode', {
	  method: 'get',
	  data: params
	})
}

export async function orderStatus(orderId,type) {

	if(type==0){
		return request2('sale_order/info', {
			data:{
				orderId:orderId
			}
		})
	}
	
	
}
export async function payment(params) {

	let newData={}
	newData.payType=params.paymentType+""
	newData.data=params.data

	return request('sale_order/create', {
			method: 'get',
	  	data: newData
	})

}
export async function cardpay(params) {

	let newData={}
	newData.ordercode =params.orderNo
	newData.cardsyscode =params.cardno
	newData.free=params.totalPrice
	newData.cPasswd=params.password

		return request('card/pay', {
				method: 'post',
		  	data: newData
		})

}