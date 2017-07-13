import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import Stockdis from '../components/stockdis/list'
import StockdisSearch from '../components/stockdis/search'


//库存位移
function Stock({ location, dispatch, app, stockdis }) {
	const { list, loading, pagination, modalVisible } = stockdis;
	
	const StockProps = {
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
				type: 'stockdis/delete',
				payload: id
			})
		},
		onEditItem(item) {
			dispatch({
				type: 'stockdis/showModal',
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
	const searchProps={
		onSearch(params){
			dispatch({
					type: 'stockdis/search',
					payload: params
			})
		},

		onChangePosition(params){
			dispatch({
					type: 'stockdis/move',
					payload: params
				})
		},
	}

	return(
		<div className='content-inner'>
	      <Spin tip='loading...' spinning={loading} size='large'>
	        <StockdisSearch {...searchProps}/>
	        <Stockdis {...StockProps} />

	      </Spin>
	    </div>
	)
}

Stock.propTypes = {
	stockdis: PropTypes.object,
	location: PropTypes.object,
	dispatch: PropTypes.func
};

function mapStateToProps({ app, stockdis }) {
	return { app, stockdis }
}
export default connect(mapStateToProps)(Stock)