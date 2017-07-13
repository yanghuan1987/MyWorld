import {request} from '../utils'


export async function picked(params) {
	
	return request('package_order/picked', {
		data:params
	})
	
}
export async function search(params) {
	
	return request('package_order/query', {
		data: params
	});
}
