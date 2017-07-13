import {request} from '../utils'


export async function query (params) {
	
	return request('package_order/list', {
		data:params
	})
}

export async function search (params) {
	
	return request('packages/search', {
		data:params
	})
}
export async function remove (params) {
	
	return request('packages/delete', {
		data:params
	})
}

export async function discard (params) {
	
	return request('package_order/passed', {
		data:params
	})
}
