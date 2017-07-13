import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import PickupList from '../components/storage/pickup-list'
import PickupSearch from '../components/storage/pickup-search'

function CustomerPickup({ location, dispatch, app, pickup }) {
	const { list, loading, pagination, modalVisible } = pickup;
	const overdueModalProps = {
		visible: modalVisible,
		type: '',
		item: {},
		onOk(data){
			dispatch({
				type:'pickup/update',
				payload: data
			})
		},
		onCancel() {
			dispatch({
				type: 'pickup/hideModal'
			})
		}
	};
	const pickupListProps = {
		dataSource: list,
		loading,
		pagination: pagination,
		onPageChange(page) {
			const { query, pathname } = location;
			dispatch(routerRedux.push({
				pathname: pathname,
				query: {
					...query,
					page: page.current,
					pageSize: page.pageSize
				}
			}))
		},
		onDeleteItem(id) {
			dispatch({
				type: 'pickup/delete',
				payload: id
			})
		},
		onEditItem(item) {
			dispatch({
				type: 'pickup/showModal',
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
	const pickupSearchProps = {
		onSearch(code) {
			dispatch({
				type: 'pickup/search',
				payload: {
					code: code,
				}
			})
		},
		onClear(){
			dispatch({
				type: 'pickup/clear',
				payload: {}
			})
		},
		OnpickupEnd(fn) {
			if(list==undefined||list.length==0){
				alert("无可操作对象")
				return
			}
			dispatch({
				type: 'pickup/query',
				payload:{
					orderNo:list[0].order_no,
					fn:fn
				}
			})
		}
	}

	return(
		<div className='content-inner'>
	      <Spin tip='loading...' spinning={loading} size='large'>
	     	<PickupSearch { ...pickupSearchProps}	/> 
	        <PickupList {...pickupListProps} />
	      </Spin>
	    </div>
	)
}

CustomerPickup.propTypes = {
	pickup: PropTypes.object,
	location: PropTypes.object,
	dispatch: PropTypes.func
};

function mapStateToProps({ app, pickup }) {
	return { app, pickup }
}
export default connect(mapStateToProps)(CustomerPickup)