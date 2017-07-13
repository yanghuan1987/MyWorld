import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'

import StationList from '../components/manager/stationlist'
import StationModal from '../components/manager/stationmodal'

//站点管理
function Stationmanager ({location,dispatch,app,station}) {
	const {	list,loading,pagination,modalVisible} = station;
	const stationModalProps = {
		visible: modalVisible,
		type: '',
		item: {},
		onOk(param) {
			console.log(param)
			dispatch({
//				type: 'station/create',
				type: 'station/update',
				payload: param
			})
		},
		onCancel() {
			dispatch({
				type: 'station/hideModal'
			})
		}
	};
	
	const stationListProps = {
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
				type: 'station/queryState',
				payload: {
					query,
					page
				}
			})
		},
		onDeleteItem(id) {
			dispatch({
				type: 'station/delete',
				payload: id
			})
		},
		onEditItem(item) {
			dispatch({
				type: 'station/showModal',
				payload: {
					modalType: 'update',
					currentItem: item
				}
			})
		}
	};
	const iceboxSearchProps = {
		onAdd() {
			dispatch({
				type: 'station/showModal',
				payload: {
					modalType: 'create'
				}
			})
		}
	}
	const StationModalGen = () =>
		<StationModal { ...stationModalProps}/>;
	return( 
		<div className = 'content-inner' >
			<Spin tip = 'loading...' spinning = {loading} size = 'large' >
				<StationList { ...stationListProps}	/> 
				<StationModalGen / >
			</Spin> 
		< /div >
	)
}
Stationmanager.propTypes = {
  station: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
};
function mapStateToProps({app,station}){
	return {app,station}
}

export default connect(mapStateToProps)(Stationmanager)