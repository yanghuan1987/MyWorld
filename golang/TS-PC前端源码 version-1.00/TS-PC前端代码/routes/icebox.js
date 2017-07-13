import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import IceboxList from '../components/manager/iceboxlist'
import IceboxModal from '../components/manager/iceboxmodal'
import IceboxSearch from '../components/manager/icebox-search'

function Iceboxmanager ({location,dispatch,app,icebox}) {
	const {	list,loading,pagination,modalVisible,modalType,currentItem} = icebox;
	const iceboxModalProps = {
		visible: modalVisible,
		item: modalType === 'create' ? {} : currentItem,
    	type: modalType,
		onOk(param) {
			dispatch({
				type: `icebox/${modalType}`,
				payload: param
			})
		},
		onCancel() {
			dispatch({
				type: 'icebox/hideModal'
			})
		}
	};
	
	const iceboxListProps = {
		dataSource: list,
		loading,
		pagination: pagination,
		onPageChange(page) {
			const { query, pathname } = location
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
				type: 'icebox/queryState',
				payload: {
					query,
					page
				}
			})
		},
		onDeleteItem(id) {
			console.log(id);
			dispatch({
				type: 'icebox/delete',
				payload: id
			})
		},
		onEditItem(item) {
			dispatch({
				type: 'icebox/showModal',
				payload: {
					modalType: 'update',
					currentItem: item
				}
			})
		}
	};
	const iceboxSearchProps = {
		onSearch(iceboxNumber) {
			dispatch({
				type: 'icebox/search',
				payload: {
					number:iceboxNumber,
				}
			})
		},
		onAdd() {
			dispatch({
				type: 'icebox/showModal',
				payload: {
					modalType: 'create'
				}
			})
		}
	}
	const StationModalGen = () =>
		<IceboxModal { ...iceboxModalProps}/>;
	return( 
		<div className = 'content-inner' >
			<Spin tip = 'loading...' spinning = {loading} size = 'large' >
				<IceboxSearch { ...iceboxSearchProps}	/> 
				<IceboxList { ...iceboxListProps}	/> 
				<StationModalGen / >
			</Spin> 
		< /div >
	)
}
Iceboxmanager.propTypes = {
  icebox: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
};
function mapStateToProps({app,icebox}){
	return {app,icebox}
}

export default connect(mapStateToProps)(Iceboxmanager)