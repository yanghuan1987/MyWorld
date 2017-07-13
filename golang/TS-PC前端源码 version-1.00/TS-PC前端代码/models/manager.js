import { create, remove, update, query } from '../services/manager'
import { roleQuery } from '../services/role'
import { parse } from 'qs'

export default {

  namespace: 'manager',

  state: {
    list: [],
    roles:[],
    loading: false,
    currentItem: {},
    modalVisible: false,
    modalType: 'create',
    pagination: {
      showSizeChanger: true,
      showQuickJumper: true,
      showTotal: total => `共 ${total} 条`,
      current: 1,
      total: null
    }
  },

  subscriptions: {
    setup ({ dispatch, history }) {
      
      history.listen(location => {
        if (location.pathname === '/system/manager') {
          dispatch({
            type: 'query',
            payload: location.query
          })
        }
      })
    }
  },

  effects: {
    *add ({ payload }, { call, put }) {
      yield put({ type: 'showLoading' })

      const data = yield call(roleQuery, {pageSize:100})
      
      yield put({
        type: 'showModal',
        payload: {
          roles: data.data,
          ...payload
        }
      })
    },
    *edit ({ payload }, { call, put }) {
      yield put({ type: 'showLoading' })
      const data = yield call(roleQuery,  {pageSize:100})
      
      yield put({
        type: 'showModal',
        payload: {
          roles: data.data,
          ...payload
        }
      })
    },
    *query ({ payload }, { call, put }) {
      yield put({ type: 'showLoading' })
      const data = yield call(query, parse(payload))
      yield put({
        type: 'querySuccess',
        payload: {
          list: data.data,
          pagination: {
            total: data.page.rows,
            current: data.page.current
          }
        }
      })
    },
    *'delete' ({ payload }, { call, put }) {
      yield put({ type: 'showLoading' })
      const data = yield call(remove, { id: payload })
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    *create ({ payload }, { call, put }) {
      yield put({ type: 'hideModal' })
      yield put({ type: 'showLoading' })

      const data = yield call(create, payload)
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    *update ({ payload }, { select, call, put }) {
      yield put({ type: 'hideModal' })
      yield put({ type: 'showLoading' })
      const id = yield select(({ manager }) => manager.currentItem.id)

      const newManager = { ...payload, id }
      const data = yield call(update, newManager)
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    *reload(action, { put, select }) {
      const page = yield select(({ manager }) => manager.pagination)
      yield put({ type: 'query', payload: { 
          page:page.current
       } });
    },
  },

  reducers: {

    showLoading (state) {
      return { ...state, loading: true }
    },
    hideLoading (state) {
      return { ...state, loading: false }
    },
    querySuccess (state, action) {
      const {list, pagination} = action.payload
      return { ...state,
        list,
        loading: false,
        pagination: {
          ...state.pagination,
          ...pagination
        }}
    },
    showModal (state, action) {
      return { ...state, 
        loading: false,
        ...action.payload, 
        modalVisible: true 
      }
    },
    hideModal (state) {
      return { ...state, modalVisible: false }
    }
  }

}
