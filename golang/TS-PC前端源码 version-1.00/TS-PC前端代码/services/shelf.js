import {request} from '../utils'

export async function create(params) {
	
 return request('shelf/save', {
		data:params
	})
}

export async function iceboxQuery(params) {
	return request('icebox/query', {
		data:params
	})
}

export async function query (params) {
	return request('shelf/query', {
		data:params
	})
}

export async function search (params) {
	
	return request('shelf/search', {
		data:params
	});
}

export async function update (params) {
	
	return request('shelf/modify', {
		data:params
	})
}

export async function listQuery (params) {
	
	return request(' icebox/ iceboxid', {
		data:params
	})
}