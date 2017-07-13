import { request } from '../utils'

//export async function bind (params) {
//	console.log(params);
//return request('card/bind', {
//  data: params
//})
//}

export async function charge (params) {
	
  return request('card/charge', {
    data: params
  })
}

export async function queryInfo(params){
		
	return  request('card/query',{
		data: params
	})
}
