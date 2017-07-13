import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import Coldbox from '../components/manager/coldbox'

function Coldboxmanager ({location}) {
	return(
		<Coldbox />
	)
}

function mapStateToProps({}){
	return {}
}

export default connect(mapStateToProps)(Coldboxmanager)