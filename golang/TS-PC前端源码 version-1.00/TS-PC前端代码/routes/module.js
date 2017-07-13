import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import ModuleList from '../components/module/list'
import ModuleSearch from '../components/module/search'
import ModuleModal from '../components/module/modal'

/**
 * 站点信息列表
 */
function Module ({ location, dispatch, app,module }) {
  const {login}=app
  const {loading,list, pagination, currentItem, modalVisible, modalType } = module
  const { field, keyword } = location.query
  
  const moduleModalProps = {
    item: modalType === 'create' ? {} : currentItem,
    type: modalType,
    visible: modalVisible,
    onOk (data) {
      dispatch({
        type: `module/${modalType}`,
        payload: data
      })
    },
    onCancel () {
      dispatch({
        type: 'module/hideModal'
      })
    }
  }

  const moduleListProps = {
    dataSource: list,
    loading,
    pagination: pagination,
    onPageChange (page) {
      
      const { query, pathname } = location
      dispatch(routerRedux.push({
        pathname: pathname,
        query: {
          ...query,
          page: page.current,
          pageSize: page.pageSize
        }
      }))
    },
    onDeleteItem (id) {
      dispatch({
        type: 'module/delete',
        payload: id
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'module/showModal',
        payload: {
          modalType: 'update',
          currentItem: item
        }
      })
    }
  }

  const moduleSearchProps = {
    field,
    keyword,
    onSearch (fieldsValue) {
      fieldsValue.keyword.length ? dispatch(routerRedux.push({
        pathname: '/system/module',
        query: {
          field: fieldsValue.field,
          keyword: fieldsValue.keyword
        }
      })) : dispatch(routerRedux.push({
        pathname: '/system/module'
      }))
    },
    onAdd () {
      dispatch({
        type: 'module/showModal',
        payload: {
          modalType: 'create'
        }
      })
    }
  }

  const ModuleModalGen = () =>
    <ModuleModal {...moduleModalProps} />

  return (
    <div className='content-inner'>
      <Spin tip='loading...' spinning={loading} size='large'>
      <ModuleSearch {...moduleSearchProps} />
      <ModuleList {...moduleListProps} />
      <ModuleModalGen />
      </Spin>
    </div>
  )
}

Module.propTypes = {
  module: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
}

function mapStateToProps ({ app,module }) {
  return { app,module }
}

export default connect(mapStateToProps)(Module)
