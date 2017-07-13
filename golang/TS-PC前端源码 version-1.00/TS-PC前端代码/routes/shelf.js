import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import ShelfList from '../components/manager/shelflist'
import ShelfModal from '../components/manager/shelfmodal'
import ShelfSearch from '../components/manager/shelf-search'

//货架管理
function Shelfmanager({ location, dispatch, app, shelf }) {
	const {	list,iceboxList,loading,pagination,modalVisible,modalType,currentItem} = shelf;
	const shelfModalProps = {
		list:iceboxList,
		visible: modalVisible,
		item: modalType === 'create' ? {} : currentItem,
    	type: modalType,
			onOk(param) {
			dispatch({
				type: `shelf/${modalType}`,
				payload: param
			})
		},
		onCancel() {
			dispatch({
				type: 'shelf/hideModal'
			})
		},
		setFieldsValue(){
			dispatch({
				type:'shlef/listQuery'
			})

		}
	};
	const shelfListProps = {
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
		onDeleteItem(id) {
			dispatch({
				type: 'shelf/delete',
				payload: id
			})
		},
		onEditItem(item) {
			dispatch({
				type: 'shelf/showModal',
				payload: {
					modalType: 'update',
					currentItem: item
				}
			})
		}
	};
	const ShelfSearchProps = {
		iceboxList,
		onSearch(shelfNumber,iceboxId) {
			dispatch({
				type: 'shelf/search',
				payload: {
					shelfNumber: shelfNumber,
					iceboxId:iceboxId
				}
			})
		},
		onAdd() {
			dispatch({
				type: 'shelf/showModal',
				payload: {
					modalType: 'create'
				}
			})
		}
	}
	const ShelfModalGen = () =>
		<ShelfModal { ...shelfModalProps}/>;
	return( 
		<div className = 'content-inner' >
			<Spin tip = 'loading...' spinning = {loading} size = 'large' >
				<ShelfSearch { ...ShelfSearchProps}	/> 
				<ShelfList { ...shelfListProps}	/> 
				<ShelfModalGen / >
			</Spin> 
		</div >
	)
}

Shelfmanager.propTypes = {
	shelf: PropTypes.object,
	location: PropTypes.object,
	dispatch: PropTypes.func
};

function mapStateToProps({app,shelf}) {
	return {app,shelf}
}
export default connect(mapStateToProps)(Shelfmanager)
