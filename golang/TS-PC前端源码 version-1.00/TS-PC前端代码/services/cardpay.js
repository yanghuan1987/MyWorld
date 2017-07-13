import {
	request
} from '../utils'

const param = {
	data: [{
		"id": "1",
		"yinli_id": "1100598060646412-1-1",
		"order_id": "1493113123266",
		"name": "feik",
		"idcard": "20",
		"phone": "admin",
		"re_amount": "2017/6/16",
		"order_id": "qwwwwdwwdwdwdscs",
		"pay_time": "哈哈啊哈哈",
		"end":"支付完成",
		"opear": "qwwwwdwwdwdwdscs",
		"site_id": "哈哈啊哈哈"
	}, 
	{
		"id": "34",
		"yinli_id": "1100598060646412-1-1",
		"order_id": "1493113123266",
		"name": "feik",
		"idcard": "20",
		"phone": "admin",
		"re_amount": "2017/6/16",
		"order_id": "qwwwwdwwdwdwdscs",
		"pay_time": "哈哈啊哈哈",
		"end":"未完成",
		"opear": "qwwwwdwwdwdwdscs",
		"site_id": "哈哈啊哈哈"
	}],
	page: {
		"rows": 20,
		"current": 1
	}
}

export async function query(params) {
	// if(!!params.query) {
	// 	const arr = [];
	// 	for(var i = 0, len = param.data.length; i < len; i++) {
	// 		for(const key in param.data[i]) {
	// 			const str = param.data[i][key].toString();
	// 			if(str.indexOf(params.query) >= 0) {
	// 				arr.push(param.data[i]);
	// 				break;
	// 			}
	// 		}
	// 	}
	// 	return {
	// 		data: arr,
	// 		page: {
	// 			"rows": 20,
	// 			"current": 1
	// 		}
	// 	};
	// } else {
	// 	return param;
	// }
	console.log(params)
	return request('card/cardpayinfo', {
		data:params
	})
}

export async function queryState(params) {
	const arr = [];
	for(var i = 0, len = param.data.length; i < len; i++) {
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

	return request('card/cardpayinfo', {
		data:params
	})
}