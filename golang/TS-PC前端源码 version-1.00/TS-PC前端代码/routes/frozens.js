import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import FrezonList from '../components/manager/frozen'
import FrezonModal from '../components/manager/frozen-modal'

//银犁卡充值
function FrezonOrActive ({location,dispatch,app,frozens}) {
	const {loading,modalVisible,formValue,pincard} = frozens
	const frezonListProps = {
		loading: loading,
		type: '0',
		formValue: formValue,
		onOk(params){
			dispatch({type: 'frozens/frozes', payload: params})
		},
		showDialog(params){
			dispatch({
				type: 'frozens/pincard',
				payload: params
			})
		},
		queryInfo(params){
			dispatch({type: 'frozens/infoQuery', payload: params})
		}
	}
	const frozenModalProps = {
		visible: modalVisible,
		formValue: pincard,
		onOk(param) {
			dispatch({
				type: 'frozens/confirm',
				payload: param
			})
		},
		onCancel() {
			dispatch({
				type: 'frozens/hideDailogModal'
			})
		}
	};
	const ShelfModalGen = () =>
		<FrezonModal { ...frozenModalProps}/>;
		return( 
			<div className = 'content-inner' >
				<Spin tip = 'loading...' spinning = {loading} size = 'large' >
					<FrezonList { ...frezonListProps}	/> 
					<ShelfModalGen / >
				</Spin> 
			</div >
		)
}

FrezonOrActive.propTypes = {
  frozens: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
};

function mapStateToProps({app,frozens}){
	return {app,frozens}
}

export default connect(mapStateToProps)(FrezonOrActive)