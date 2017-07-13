import {request} from '../utils'

export async function move(params) {
	
	return request('packages/move', {
	  data: params
	})

}
export async function search(params) {
	
	return request('packages/search', {
		data:params
	})
}
export async function remove(params) {
	
	return request('packages/delete', {
		data:params
	})
}

