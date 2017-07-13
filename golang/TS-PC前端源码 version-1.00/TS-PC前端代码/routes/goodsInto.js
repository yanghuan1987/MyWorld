import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import GoodsIntoList from '../components/goodsinto/list'
import GoodsIntoModal from '../components/goodsinto/modal'
import GoodsIntoSearch from '../components/goodsinto/search'

//调运商品入柜
function GoodsInto ({location,dispatch,app,goodsInto}) {
	const {list,loading,pagination,modalVisible,number,shelfId,amount} = goodsInto
	
	const goodsIntoModalProps = {
		visible: modalVisible,
		type: '',
		item: {},

		onOk(data){
			dispatch({
				type: 'goodsInto/update',
				payload: data
			})
		},
		onCancel() {
			dispatch({
				type: 'goodsInto/hideModal'
			})
		}
	};
	const goodsIntoListProps = {
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
		onQuerySelected(query, page) {
			dispatch({
				type: 'goodsInto/queryState',
				payload: {
					query,
					page
				}
			})
		},
		onDeleteItem(id) {
			dispatch({
				type: 'goodsInto/delete',
				payload: id
			})
		},
		onEditItem(item) {
			dispatch({
				type: 'goodsInto/showModal',
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
	const goodsIntoSearchProps = {
		number: number,
		shelfId: shelfId,
		amount: amount,
		onSearch(code){
			dispatch({
				type: 'goodsInto/search',
				payload: {
					code: code
				}
			})
		},
		onRefresh(){
			dispatch({
					type: 'goodsInto/update'
				})
		},
		onGoodsIn(params){
				dispatch({
						type: 'goodsInto/create',
						payload: {...params	}
					})
		},
		resetParams(){
			dispatch({
				type: 'goodsInto/reset'
			})
		}
	}
	const GoodsIntoModalGen = () =>
		<GoodsIntoModal {...goodsIntoModalProps} />;
	return(
		<div className='content-inner'>
	      <Spin tip='loading...' spinning={loading} size='large'>
	       <GoodsIntoSearch {...goodsIntoSearchProps} />
	        <GoodsIntoList {...goodsIntoListProps} />	       
	        <GoodsIntoModalGen />
	      </Spin>
	    </div>
	)
}
GoodsInto.propTypes = {
  GoodsInto: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
};

function mapStateToProps({app,goodsInto}){
	return {app,goodsInto}
}

export default connect(mapStateToProps)(GoodsInto)