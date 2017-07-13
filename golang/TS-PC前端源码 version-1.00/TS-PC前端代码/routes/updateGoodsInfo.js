import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import GoodsSearch from '../components/update/goods-search'
import UpdateInfoList from '../components/update/goodsinfolist'


//商品信息更新
function GoodsInfo ({location,dispatch,app,updateGoodsInfo}) {
	const {list,loading,pagination,modalVisible} = updateGoodsInfo

	const updateListProps = {
	    dataSource: list,
	    loading,
	    pagination: pagination,
	    onOk(data){
				dispatch({
					type: 'updateGoodsInfo/update',
					payload: data
				})
			},
			onCancel() {
				dispatch({
					type: 'updateGoodsInfo/hideModal'
				})
			},
	    onPageChange (page) {
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
				type: 'updateGoodsInfo/delete',
				payload: id
			})
		},
		onEditItem(item) {
			dispatch({
				type: 'updateGoodsInfo/showModal',
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
	}
	const goodsSearchProps={
			onSearch(code){
				dispatch({
						type: 'updateGoodsInfo/search',
						payload: {
							code: code
						}
					})
			},
		onRefresh(){
			dispatch({
					type: 'updateGoodsInfo/update'
				})
		},
		onGoodsIn(params){
				dispatch({
						type: 'updateGoodsInfo/create',
						payload: {...params}
					})
			},
	}

	return(
		<div className='content-inner'>
	      <Spin tip='loading...' spinning={loading} size='large'>
					<GoodsSearch {...goodsSearchProps}/>
	        <UpdateInfoList {...updateListProps} />
	      </Spin>
	    </div>
	)
}

GoodsInfo.propTypes = {
  updateGoodsInfo: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
};

function mapStateToProps({app,updateGoodsInfo}){
	return {app,updateGoodsInfo}
}

export default connect(mapStateToProps)(GoodsInfo)