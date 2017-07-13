import {request} from '../utils'

   export async function search(params) {
   	
   	return request('packages/search', {
   	  data: params
   	});
   }
export async function query(params) {
	return request('packages/query', {
	  data: params
	});
}

export async function remove(params) {
	
	return request('packages/delete', {
	  data: params
	})
}

export async function create(params) {
	
	return request('packages/save', {
	  data: params
	})
}