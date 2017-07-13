import { request } from '../utils'

export async function query(params) {
	console.log(params)
	return request('sale_goods/query', {
		data: params
	})
}

export async function update(params) {
	
	return request('sale_goods/goodsInfo',{
		data:params
	})
}

export async function remove(params) {
	
	return request('sale_goods/delete', {
		data: params
	})
}

export async function create(params) {
	
	return request('sale_goods/add', {
		data: params
	})
}

export async function search(params) {
	console.log("search")
	return request('sale_goods/search', {
		data: params
	})
}