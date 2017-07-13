import {request} from '../utils'

export async function query(params) {
	return request('site/query', {
		data:{}
	})
}