import {request} from '../utils'

export async function query(params) {

	return {
		"data":[
			{	
				"id":"111",
				"sales_id":"21122",
				"shelf_id":"22112",
				"name":"adimg2",
				"lot": "D2342424",
				"in_operator":"1111111",
				"in_time":"温区一",	
				"status": 1,
				"price": 64.66,
				"expire_time": 64.66,
				"spec": "64.66",
				"unit": "米",
				"weight": 16.3,
				"created_at": 1693196152484,
				"updated_at": 1693196152484,
				"amount": 100
			},
		],
		"page":{
			"rows":30,
			"current":1
		}
	}
}
export async function remove(params) {
	alert("删除")
}
export async function update(params) {
	alert("编辑")
}