import config from './config'
import fetch from 'dva/fetch';
import {remote} from 'electron'
const {cli} =remote.getGlobal('services')
const send=(url,options)=>{

  return new Promise((resolve, reject) => {
//	console.log(url+"====>"+options)
     cli.send(url,options.data).then(function(data){

      resolve(data)
    })
  });

};


export default async function request2 (url, options) {

    const data= await send(url,options);

    return data

}


