import { request } from '../utils'

export async function query (params) {
  return request('/manager/list', {
    method: 'get',
    data: params
  })
}

export async function create (params) {
  return request('/manager/store', {
    method: 'post',
    data: params
  })
}

export async function remove (params) {
  return request('/manager/remove', {
    method: 'post',
    data: params
  })
}

export async function update (params) {
  return request('/manager/update', {
    method: 'post',
    data: params
  })
}
