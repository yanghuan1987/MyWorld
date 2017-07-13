import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import CardList from '../components/goods-sales/cardpaylist'
import CardModal from '../components/goods-sales/cardpaymodal'
import CardSearch from '../components/goods-sales/cardpay-search'

//银犁卡支付结果
function Cardpay({ location, dispatch, app, cardpay }) {
	const {	list,loading,pagination,modalVisible} = cardpay;
	const cardModalProps = {
		visible: modalVisible,
		type: '',
		item: {},
		onOk(param) {
			dispatch({
			type: 'cardpay/update',
				payload: param
			})
		},
		onCancel() {
			dispatch({
				type: 'cardpay/hideModal'
			})
		}
	};
	const cardListProps = {
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
		onQuerySelected(query, page) {
			dispatch({
				type: 'cardpay/queryState',
				payload: {
					query,
					page
				}
			})
		},
		onDeleteItem(id) {
			dispatch({
				type: 'cardpay/delete',
				payload: id
			})
		},
		onEditItem(item) {
			dispatch({
				type: 'cardpay/showModal',
				payload: {
					modalType: 'update',
					currentItem: item
				}
			})
		}
	};
	const CardSearchProps = {
		onSearch(params) {
			dispatch({
				type: 'cardpay/search',
				payload: {
					...params
				}
			})
		}
	}

	return( 
		<div className = 'content-inner' >
			<Spin tip = 'loading...' spinning = {loading} size = 'large' >
				<CardSearch { ...CardSearchProps}	/> 
				<CardList { ...cardListProps}	/> 
			</Spin> 
		</div >
	)
}

Cardpay.propTypes = {
	cardpay: PropTypes.object,
	location: PropTypes.object,
	dispatch: PropTypes.func
};

function mapStateToProps({app,cardpay}) {
	return {app,cardpay	}
}
export default connect(mapStateToProps)(Cardpay)
