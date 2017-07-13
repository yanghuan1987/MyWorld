import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import Openlist from '../components/manager/open'
import OpenModal from '../components/manager/open-modal'

//开通银犁卡
function Open ({location,dispatch,app,open}) {
	const {loading,modalVisible} = open
	const openProps = {
		loading: loading,
		visible:modalVisible,
		type:'bind',
		onOk(params,type,fn){
			dispatch({type: 'open/bind', payload: {
				params,
				fn:fn
			}}
			)
		}
	};
	const openModalProps = {
				loading,

	};
	const openModalGen = () =>
		<OpenModal {...openModalProps} />;
	return(
		<Spin tip = 'loading...' spinning = {loading} size = 'large' >
			<Openlist {...openProps}/>
			<openModalGen />
		</Spin> 
	)
}

Open.propTypes = {
  open: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
};

function mapStateToProps({app,open}){
	return {app,open}
}

export default connect(mapStateToProps)(Open)