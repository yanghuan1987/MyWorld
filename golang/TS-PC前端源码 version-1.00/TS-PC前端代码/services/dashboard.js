import {request} from '../utils'

export async function init(params) {
 	return request('dashboard/init', {
		data:params
	})
}