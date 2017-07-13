import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import GoodsdisList from '../components/goodsdis/list'
import GoodsdisModal from '../components/goodsdis/modal'

//商品位移
function Goodsdisplament({ location, dispatch, app, goodsDis }) {
	const { list, loading, pagination, modalVisible } = goodsDis;
	
	const goodsDisModalProps = {
		visible: modalVisible,
		type: '',
		item: {},
		onOk(data){
			console.log(1232312)
			dispatch({
				type: 'goodsDis/update',
				payload: data
			})
		},
		onCancel() {
			dispatch({
				type: 'goodsDis/hideModal'
			})
		}
	};
	
	const GoodsdisListProps = {
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
				type: 'goodsDis/delete',
				payload: id
			})
		},
		onEditItem(item) {
			dispatch({
				type: 'goodsDis/showModal',
				payload: {
					modalType: 'update',
					currentItem: item
				}
			})
		}
	};
	const GoodsIntoModalGen = () =>
		<GoodsdisModal {...goodsDisModalProps} />;
	return(
		<div className='content-inner'>
	      <Spin tip='loading...' spinning={loading} size='large'>
	        <GoodsdisList {...GoodsdisListProps} />
	        <GoodsIntoModalGen />
	      </Spin>
	    </div>
	)
}
Goodsdisplament.propTypes = {
  goodsDis: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
};

function mapStateToProps({app,goodsDis}){
	return {app,goodsDis}
}

export default connect(mapStateToProps)(Goodsdisplament)