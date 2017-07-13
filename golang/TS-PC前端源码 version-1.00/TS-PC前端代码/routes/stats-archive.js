import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import StatsArchiveList from '../components/stats/archive/list'
import StatsArchiveHeader from '../components/stats/archive/header'
import StatsArchiveModal from '../components/stats/archive/modal'

/**
 * 站点信息列表
 */
function StatsArchive ({ location, dispatch, app,statsArchive }) {
  const {login}=app;
  const {list,list1,stations, numbers, currentItem, modalVisible, dataType,loading } = statsArchive;
  const { field, keyword } = location.query;
  let hasAirQuality=true
  if(list1.air_quality!=undefined){
      hasAirQuality=list1.air_quality.length>0
  }

  const statsArchiveListProps = {
    dataSource: list,
    dataSource1: list1,
    dataType,
    loading,
    numbers
  };

  const statsArchiveHeaderProps = {
    field,
    hasAirQuality:hasAirQuality,
    stations,
    onSearch (fieldsValue) {
      dispatch({
        type: 'statsArchive/query',
        payload:fieldsValue
      })
    },
    onDataTypeChange(value){
        dispatch({
          type: 'statsArchive/dataTypeChange',
          payload: {
            dataType: value
          }
        })
    }
  };

  return (
    <div className='content-inner'>
      <Spin tip='loading...' spinning={loading} size='large'>
      <StatsArchiveHeader {...statsArchiveHeaderProps} />
      <StatsArchiveList {...statsArchiveListProps} />
      </Spin>
    </div>
  )
}

StatsArchive.propTypes = {
  statsArchive: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
};

function mapStateToProps ({ app,statsArchive }) {
  return { app,statsArchive }
}

export default connect(mapStateToProps)(StatsArchive)
