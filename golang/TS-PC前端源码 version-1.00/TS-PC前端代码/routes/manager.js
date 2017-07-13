import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Spin } from 'antd'
import ManagerList from '../components/manager/list'
import ManagerSearch from '../components/manager/search'
import ManagerModal from '../components/manager/modal'

/**
 * 系统管理员信息列表
 */
function Manager ({ location, dispatch, app,manager }) {
  const {login}=app
  const {loading,list, pagination, currentItem, modalVisible, modalType,roles } = manager
  const { field, keyword } = location.query
  
  const managerModalProps = {
    item: modalType === 'create' ? {} : currentItem,
    type: modalType,
    roles:roles,
    visible: modalVisible,
    onOk (data) {
      dispatch({
        type: `manager/${modalType}`,
        payload: data
      })
    },
    onCancel () {
      dispatch({
        type: 'manager/hideModal'
      })
    }
  }

  const managerListProps = {
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
        type: 'manager/delete',
        payload: id
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'manager/edit',
        payload: {
          modalType: 'update',
          currentItem: item
        }
      })
    }
  }

  const managerSearchProps = {
    field,
    keyword,
    onSearch (fieldsValue) {
      fieldsValue.keyword.length ? dispatch(routerRedux.push({
        pathname: '/system/manager',
        query: {
          field: fieldsValue.field,
          keyword: fieldsValue.keyword
        }
      })) : dispatch(routerRedux.push({
        pathname: '/system/manager'
      }))
    },
    onAdd () {
      dispatch({
        type: 'manager/add',
        payload: {
          modalType: 'create'
        }
      })
    }
  }

  const ManagerModalGen = () =>
    <ManagerModal {...managerModalProps} />

  return (
    <div className='content-inner'>
      <Spin tip='loading...' spinning={loading} size='large'>
      <ManagerSearch {...managerSearchProps} />
      <ManagerList {...managerListProps} />
      <ManagerModalGen />
      </Spin>
    </div>
  )
}

Manager.propTypes = {
  manager: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func
}

function mapStateToProps ({ app,manager }) {
  return { app,manager }
}

export default connect(mapStateToProps)(Manager)
