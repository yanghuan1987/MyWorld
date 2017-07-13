import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import Station from '../components/manager/station'

function Stationmanager ({location}) {
	return(
		<Station />
	)
}

function mapStateToProps({}){
	return {}
}

export default connect(mapStateToProps)(Stationmanager)