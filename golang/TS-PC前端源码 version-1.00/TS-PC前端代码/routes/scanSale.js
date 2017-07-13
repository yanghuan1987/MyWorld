import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import {Spin} from 'antd'
import ScanSaleList from '../components/goods-sales/scansale-list'
import PaymentModal from '../components/goods-sales/payment-modal'
function Scansales ({location,dispatch,app,scanSale}) {
	const {loading,dataSource,orderInfo,paymentType,paymentVisible} = scanSale;

	const scanSaleProps = {
		dataSource,
		onAdd(params){
			dispatch({
				type: 'scanSale/add',
				payload: {
					...params
				}
			})
		},
		onAmountChange(params){
			dispatch({
				type: 'scanSale/amountChange',
				payload: {
					item:params
				}
			})
		},
		onClear(){
			dispatch({
				type: 'scanSale/clear'
			})
		},
		onPayment(type){
			let i="";
			let p="";
			let a="";
			let s="";
			dataSource.list.map(function(item,index){
				if(index==0){
					i+=item.id
					p+=item.price
					a+=item.amount
					s+=item.sales_id
				}else{
					i+=","+item.id
					p+=","+item.price
					a+=","+item.amount
					s+=","+item.sales_id
				}
			})
			let paymentType=0
			if(type=='cardpay'){
				paymentType=1
			}
			let parmas={
				"paymentType":paymentType,
				"data":i+"|"+p+"|"+a+"|"+s
			}
			dispatch({
				type: `scanSale/payment`,
				payload: {
					...parmas
				}
			})
		}
	};
	const paymentModalProps={
		visible: paymentVisible,
		type:paymentType,
		item:orderInfo,
		onCancel(needClear) {
			dispatch({
				type: 'scanSale/paymentHide'
			})
			if(needClear){
				dispatch({
					type: 'scanSale/clear'
				})
			}
			
		},
		onOk(param) {
			
			dispatch({
				type: 'scanSale/cardpay',
				payload: param
			})
		}
	}
	const PaymentModalGen = () =>
		<PaymentModal { ...paymentModalProps}/>;
	return(
		<div className='content-inner'>
			<Spin tip="Loading..." spinning = {loading} size='large'>
				<ScanSaleList {...scanSaleProps}/>
				<PaymentModalGen />
			</Spin>
		</div>
	)
}

Scansales.propTypes = {
	scanSale: PropTypes.object,
	location: PropTypes.object,
	dispatch: PropTypes.func
};

function mapStateToProps({app,scanSale}){
	return {app,scanSale}
}

export default connect(mapStateToProps)(Scansales)