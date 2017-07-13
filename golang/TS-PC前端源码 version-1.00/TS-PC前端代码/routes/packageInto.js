import React, {	PropTypes} from 'react'
import {routerRedux} from 'dva/router'
import {connect} from 'dva'
import {Spin} from 'antd'
import PackageList from '../components/storage/package-list'
import PackageSearch from '../components/storage/package-search'

//订单包裹入柜
function Package({location,	dispatch,app,packageInto}) {
	const {list,loading,pagination,modalVisible} = packageInto;
	const stationModalProps = {
		visible: modalVisible,
		type: '',
		item: {},
		onOk(param) {
			dispatch({
				type: 'packageInto/update',
				payload: param
			})
		},
		onCancel() {
			dispatch({
				type: 'packageInto/hideModal'
			})
		}
	};
	const packageListProps = {
		dataSource: list,
		loading,
		pagination: pagination,
		onPageChange(query, page) {
			const {
				pathname
			} = location;
			dispatch(routerRedux.push({
				pathname: pathname,
				query: {
					query,
					page: page.current,
					pageSize: page.pageSize
				}
			}))
		},
		onQuerySelected(query, page) {
			dispatch({
				type: 'packageInto/query',
				payload: {
					query,
					page
				}
			})
		},
		onDeleteItem(codeNum) {
			dispatch({
				type: 'packageInto/delete',
				payload: codeNum
			})
		},
		onEditItem(item) {
			dispatch({
				type: 'packageInto/showModal',
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
		onSearch(params){
			dispatch({
				type: 'packageInto/search',
				payload: params
			})
		},

		onPackageIn(params){
			dispatch({
				type: 'packageInto/create',
				payload: {
					shelfId:params.shelfId,
					codeNum:params.codeNum,
					page:1,
					pageSize:10
				}
			})
		},
	}

	return( 
		<div className = 'content-inner' >
			<Spin tip = 'loading...' spinning = {loading} size = 'large' >
				<PackageSearch {...packageSearchProps}/> 
				<PackageList {...packageListProps}/> 
			</Spin> 
		</div>
	)
}

Package.propTypes = {
	packageInto: PropTypes.object,
	location: PropTypes.object,
	dispatch: PropTypes.func
};

function mapStateToProps({app,packageInto}) {
	return {app,packageInto	}
}
export default connect(mapStateToProps)(Package)