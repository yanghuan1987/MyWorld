import config from './config'
import fetch from 'dva/fetch';
import {remote} from 'electron'
const {cli} =remote.getGlobal('services')
const send=(url,options)=>{

  return new Promise((resolve, reject) => {
//	console.log(url+"====>"+options)
     cli.send(url,options.data).then(function(data){
     	console.log(data)
      resolve(data)
    })
  });

};
/**
 * Requests a URL, returning a promise.
 *
 * @param  {string} url       The URL we want to request
 * @param  {object} [options] The options we want to pass to "fetch"
 * @return {object}           An object containing either "data" or "err"
 */
export default async function request (url, options) {

    const data= await send(url,options);

    if(data.errcode!=undefined){

      const error = new Error(JSON.stringify(data));
      error.response = data;
      throw error;
    }if(data.errorcode!=undefined){

        let newData={}
        newData.errcode=data.errorcode
        newData.errmsg=data.errormsg

        const error = new Error(JSON.stringify(newData));
        error.response = newData;
        throw error;
    }else{

      return data
    }

}


