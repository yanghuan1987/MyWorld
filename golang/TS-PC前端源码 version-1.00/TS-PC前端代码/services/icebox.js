import {request} from '../utils'

export async function create(params) {
		
	return request('icebox/save', {
		data:params
	})
}
export async function query(params) {

	return request('icebox/query', {
		data:params
	})
}
export async function search(params) {
	
	return request('icebox/search', {
		data:params
	})
}

export async function remove(params) {
	return request('icebox/delete', {
		data:params
	})
}

export async function update (params) {

	return request('icebox/modify', {
		data:params
	})
}