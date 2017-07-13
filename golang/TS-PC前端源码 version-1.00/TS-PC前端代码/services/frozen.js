import { request,request2 } from '../utils'

export async function frozen (params) {

  return request('card/frozen', {
    data: params
  })
}

export async function cancel (params) {

  	return request('card/frozen', {
    data: params
  })
}

export async function queryInfo(params){

	return  request('card/query',{
		data: params
	})
}


export async function pincard(params){

	return  request('card/pincard',{
		data: params
	})
}

// export async function confirm(params){
// 	return  request('card/confirm',{
// 		data: params
// 	})
// }

export async function confirm(params){
	console.log(params)
	return  request2('card/confirm',{
		data: params
	})
}




