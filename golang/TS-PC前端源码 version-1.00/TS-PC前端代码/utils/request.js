import config from './config'
import fetch from 'dva/fetch';


const $=require('jquery')


function checkStatus(response) {
  if (response.status >= 200 && response.status < 300) {
    return response;
  }

  const error = new Error(response.status);
  error.response = response;
  throw error;
}
const ajax=(url,options)=>{
  return new Promise((resolve, reject) => {
      $.ajax({
        url:config.host+url,
        type:options.method|| 'get',
        cache:false,
        data:options.data || {},
        dataType: 'JSON',
        xhrFields: {
           withCredentials: true
        },
        crossDomain: true,
    }).done((data)=>{

        resolve(data)
    }).fail(()=>{
        resolve({"errcode":"request","errmsg":"网络请求失败"})
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

    const data= await ajax(url,options);
    if(data.errcode){
      const error = new Error(JSON.stringify(data));
      error.response = data;
      throw error;
    }else{
      return data
    }

}
/*
export default async function request(url, options) {

  if(options.method=="post"){
     var headers={

      }
      //如果不使用URLSearchParams，参数将以payload的形式发送
      var formData  = new URLSearchParams();
      var params=options.data;
      for(name in params) {
        formData.set(name, params[name]);
      }
      options.headers=headers;
      options.body=formData;
  }
  //fetch的get方法不支持将参数放入body
  if(options.method=="get"){

    if(url.indexOf("?")<0){
      url+="?";
    }
    url+="t="+new Date().getTime();
    var params=options.data;
    for(name in params) {
        url+="&"+name+"="+params[name];
    }
  }
  options.credentials="include";

  const response = await fetch(config.host+url, options);

  checkStatus(response);

  const data = await response.json();
  if(data.errcode){
    const error = new Error(JSON.stringify(data));
    error.response = response;
    throw error;
  }

  return data;
}
*/
