import config from './config'
import menu from './menu'
import menu2 from './menu2'
import request from './command'
import request2 from './command2'
import math from './math'
import classnames from 'classnames'
import {color} from './theme'

// 连字符转驼峰
String.prototype.hyphenToHump = function () {
  return this.replace(/-(\w)/g, function () {
    return arguments[1].toUpperCase()
  })
};

// 驼峰转连字符
String.prototype.humpToHyphen = function () {
  return this.replace(/([A-Z])/g, '-$1').toLowerCase()
};


// 日期格式化
Date.prototype.format = function (format) {
  var o = {
    'M+': this.getMonth() + 1,
    'd+': this.getDate(),
    'h+': this.getHours(),
    'H+': this.getHours(),
    'm+': this.getMinutes(),
    's+': this.getSeconds(),
    'q+': Math.floor((this.getMonth() + 3) / 3),
    'S': this.getMilliseconds()
  };
  if (/(y+)/.test(format)) { format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length)) }
  for (var k in o) {
    if (new RegExp('(' + k + ')').test(format)) {
      format = format.replace(RegExp.$1, RegExp.$1.length === 1
        ? o[k]
        : ('00' + o[k]).substr(('' + o[k]).length))
    }
  }
  return format
};

 function dateFormat(time,fmt){

      if(!time) {
        return "";
      }
      var d = new Date(time);
      var o = {
        "M+": d.getMonth() + 1, //月份   
        "d+": d.getDate(), //日   
        "h+": d.getHours(), //小时   
        "m+": d.getMinutes(), //分   
        "s+": d.getSeconds(), //秒   
        "q+": Math.floor((d.getMonth() + 3) / 3), //季度   
        "S": d.getMilliseconds() //毫秒   
      };
      if(/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (d.getFullYear() + "").substr(4 - RegExp.$1.length));
      for(var k in o)
        if(new RegExp("(" + k + ")").test(fmt))
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
      return fmt;
}
module.exports = {
  config,
  menu,
  menu2,
  request,
  request2,
  math,
  color,
  classnames,
  dateFormat
};
