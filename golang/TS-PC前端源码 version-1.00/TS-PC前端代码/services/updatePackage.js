import {request} from '../utils'

export async function query(params) {
	
	return request('package_order/query', {
		data:params
	})
}
export async function search(params) {
	
	return request('package_order/search', {
		data:params
	})
}
export async function remove (params) {

	return request('packages/delete', {
		data:params
	})
}

export async function reload (params) {

	return request('package_order/refresh', {
		data:params
	})
}