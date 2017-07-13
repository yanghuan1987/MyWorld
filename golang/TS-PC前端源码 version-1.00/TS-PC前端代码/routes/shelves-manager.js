import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import Shelves from '../components/manager/shelves'

function Shelvesmanager ({location}) {
	return(
		<Shelves />
	)
}

function mapStateToProps({}){
	return {}
}

export default connect(mapStateToProps)(Shelvesmanager)