import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import Recharge from '../components/manager/recharge'

//银犁卡充值
function Rechargecard ({location,dispatch,app,recharge}) {
	const {loading,formValue} = recharge
	const rechargeProps = {
		loading: loading,
		type:'bind',
		formValue: formValue,
		free: 50,
		queryInfo(params){
			dispatch({type: 'recharge/infoQuery', payload: params})
		},
		onOk(params,fn){
			dispatch({type: 'recharge/charge', payload: {
				params,
				fn:fn
			}
			})
		}
		
	}
	return(
		<Spin tip = 'loading...' spinning = {loading} size = 'large' >
			<Recharge {...rechargeProps}/>
		</Spin> 
	)
}

Rechargecard.propTypes = {
  recharge: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
};

function mapStateToProps({app,recharge}){
	return {app,recharge}
}

export default connect(mapStateToProps)(Rechargecard)