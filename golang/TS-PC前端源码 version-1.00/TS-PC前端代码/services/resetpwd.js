import { request } from '../utils'

export async function resetpwd (params) {
	
  return request('card/resetpwd', {
    method: 'post',
    data: params
  })
}

export async function queryInfo(params){
	
	return  request('card/query',{
		data: params
	})
}
