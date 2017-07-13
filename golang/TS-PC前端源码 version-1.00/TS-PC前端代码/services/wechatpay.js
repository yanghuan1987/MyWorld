import {
	request
} from '../utils'

export async function query(params) {

	
		return request('sale_order/wxinfo', {
		data:params
	})
}

export async function queryState(params) {
	const arr = [];
	for(var i = 0, len = param.data.length;i<len;i++) {
		if(param.data[i].status == params.query.state || param.data[i].expire_time == params.query.expire_time) {
			arr.push(param.data[i]);
		}
	}
	return {
		data: arr,
		page: {
			"rows": 10,
			"current": 1
		}
	};
}


export async function search(params) {
	
	return request('sale_order/wxinfo', {
		data:params
	})
}