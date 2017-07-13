import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import Resetpwd from '../components/manager/resetpwd'

//银犁卡管理  修改密码
function Resetpassword ({location,dispatch,app,resetpwd}) {
	const {loading,formValue} = resetpwd
	const resetpwdProps = {
		loading: loading,
		formValue: formValue,
		onOk(params,fn){
			dispatch({
				type: 'resetpwd/resetpwd', 
				payload: {
					params,
					fn:fn
				}
			})
		},
		queryInfo(params){
			dispatch({type: 'resetpwd/infoQuery', payload: params})
		}
	}
	return(
		<Spin tip = 'loading...' spinning = {loading} size = 'large' >
			<Resetpwd {...resetpwdProps}/>
		</Spin> 
	)
}

Resetpassword.propTypes = {
  resetpwd: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
};

function mapStateToProps({app,resetpwd}){
	return {app,resetpwd}
}

export default connect(mapStateToProps)(Resetpassword)