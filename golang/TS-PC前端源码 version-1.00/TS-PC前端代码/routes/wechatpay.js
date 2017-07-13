import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import WechatList from '../components/goods-sales/wechatpaylist'
import WechatModal from '../components/goods-sales/wechatpaymodal'
import WechatSearch from '../components/goods-sales/wechat-search'

//微信支付结果
function Wechatpay({ location, dispatch, app, wechatpay }) {
	const {	list,loading,pagination,modalVisible} = wechatpay;
	
	const wechatListProps = {
		dataSource: list,
		loading,
		pagination: pagination,
		onPageChange(page) {
			const {	query,pathname} = location;
			dispatch(routerRedux.push({
				pathname: pathname,
				query: {
					...query,
					page: page.current,
					pageSize: page.pageSize
				}
			}))
		},
		onQuerySelected(query, page) {
			dispatch({
				type: 'wechatpay/queryState',
				payload: {
					query,
					page
				}
			})
		},
		onDeleteItem(id) {
			dispatch({
				type: 'wechatpay/delete',
				payload: id
			})
		},
		onEditItem(item) {
			dispatch({
				type: 'wechatpay/showModal',
				payload: {
					modalType: 'update',
					currentItem: item
				}
			})
		},
				timeFormat(time,fmt){
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
	};
	const wechatSearchProps = {
		onSearch(params) {
			dispatch({
				type: 'wechatpay/search',
				payload: {
					...params
				}
			})
		}
	}
	return( 
		<div className='content-inner'>
			<Spin tip = 'loading...' spinning = {loading} size = 'large' >
					<WechatSearch { ...wechatSearchProps}	/> 
					<WechatList { ...wechatListProps}	/> 
			</Spin> 
			</div>
	)
}

Wechatpay.propTypes = {
	wechatpay: PropTypes.object,
	location: PropTypes.object,
	dispatch: PropTypes.func
};

function mapStateToProps({app,wechatpay}) {
	return {app,wechatpay	}
}
export default connect(mapStateToProps)(Wechatpay)
