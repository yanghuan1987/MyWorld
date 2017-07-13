import { request } from '../utils'

export async function moduleQuery (params) {
  return request('/module/list', {
    method: 'get',
    data: params
  })
}
export async function query (params) {
  return request('/module/list', {
    method: 'get',
    data: params
  })
}
export async function create (params) {
  return request('/module/store', {
    method: 'post',
    data: params
  })
}

export async function remove (params) {
  return request('/module/remove', {
    method: 'post',
    data: params
  })
}

export async function update (params) {
  return request('/module/update', {
    method: 'post',
    data: params
  })
}
