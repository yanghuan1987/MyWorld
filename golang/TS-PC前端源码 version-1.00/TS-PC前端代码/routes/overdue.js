import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import OverdueList from '../components/outtime/list'
import OverdueModal from '../components/outtime/modal'
import OverdueSearch from '../components/outtime/overdue-search'


//过期处理
function OutTime({ location, dispatch, app, overdue }) {
	const { list,overdueList,shelfId,loading, pagination, modalVisible } = overdue;
	const overdueModalProps = {
		visible: modalVisible,
		type: '',
		item: {},
		onOk(data){
			dispatch({
				type:'overdue/update',
				payload: data
			})
		},
		onCancel() {
			dispatch({
				type: 'overdue/hideModal'
			})
		}
	};
	const OverdueListProps = {
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
				type: 'overdue/delete',
				payload: id
			})
		},
		onEditItem(item) {
			dispatch({
				
				type: 'overdue/showModal',
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
	const overdueSearchProps = {
		overdueList,		  	
		onSearch(params) {
			location.query=params
			dispatch({
				type: 'overdue/search',
				payload: params
			})
		},
		onDiscard(number){
			dispatch({
				type: 'overdue/discard',
				payload: {
					number:number,
				}
			})
		},
	}
	const StationModalGen = () =>
		<OverdueModal {...overdueModalProps} />;
	return(
		<div className='content-inner'>
	      <Spin tip='loading...' spinning={loading} size='large'>
	     	<OverdueSearch { ...overdueSearchProps}	/> 
	        <OverdueList {...OverdueListProps} />
	        <StationModalGen />
	      </Spin>
	    </div>
	)
}

OutTime.propTypes = {
	overdue: PropTypes.object,
	location: PropTypes.object,
	dispatch: PropTypes.func
};

function mapStateToProps({ app, overdue }) {
	return { app, overdue }
}
export default connect(mapStateToProps)(OutTime)