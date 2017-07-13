import { request } from '../utils'

export async function bind (params) {
	
  return request('card/bind', {
    method: 'post',
    data: params
  })
}

export async function charge (params) {
  return request('recharge/charge', {
    data: params
  })
}