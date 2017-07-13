import { request } from '../utils'

export async function login (params) {
  return request('login/login', {
    method: 'post',
    data: params
  })
}

export async function logout (params) {
  return request('login/loginOut ', {
    method: 'post',
    data: params
  })
}

export async function userInfo (params) {
  return request('/manager/info', {
    method: 'get',
    data: params
  })
}
