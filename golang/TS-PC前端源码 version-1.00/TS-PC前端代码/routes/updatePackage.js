import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import PackageSearch from '../components/update/package-search'
import PackageList from '../components/update/packagelist'

//订单包裹更新
function UpdatePackage ({location,dispatch,app,updatePackage}) {
	const {list,loading,pagination,modalVisible} = updatePackage
	const packageListProps = {
	    dataSource: list,
	    loading,
	    pagination: pagination,
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
				type: 'updatePackage/delete',
				payload: id
			})
		},
		onEditItem(item) {
			dispatch({
				type: 'updatePackage/showModal',
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
 const packageSearchProps={
	 onSearch(keywords){
		 dispatch({
				type: 'updatePackage/search',
				payload: {
					keyname: keywords
				}
			})
	 },
	 onRefresh(){
		 dispatch({
			type: 'updatePackage/updateReload',
			payload:{
				dataType:0,
				page:1,
				pageSize:10
			}
		})
	 }
 }
	return(
		<div className='content-inner'>
	      	<Spin tip='loading...' spinning={loading} size='large'>
						<PackageSearch {...packageSearchProps}/>
						<PackageList {...packageListProps} />
					</Spin>
	    </div>
	)
}
	
UpdatePackage.propTypes = {
  updatePackage: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
};
function mapStateToProps({app,updatePackage}){
	return {app,updatePackage}
}

export default connect(mapStateToProps)(UpdatePackage)