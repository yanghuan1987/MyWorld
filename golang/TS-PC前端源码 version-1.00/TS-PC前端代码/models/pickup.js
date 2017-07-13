import { picked,search } from '../services/pickup'
import { parse } from 'qs'

export default {

  namespace: 'pickup',

  state: {
    list: [],
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
      /*history.listen(location => {
        if (location.pathname === '/dashboard/pickup') {
          dispatch({
            type: 'query',
            payload: location.query
          })
        }
      })*/
    }
  },

  effects: {
    *query ({ payload }, { call, put }) {
	      yield put({ type: 'showLoading' })
       
	      const data = yield call(picked, {orderNo:payload.orderNo});

        alert("操作成功")
        payload.fn()
	      yield put({ type: 'reload'});
    },
    *search ({ payload }, { call, put }) {
	      yield put({ type: 'showLoading' });
        location.query={
          ...payload
        }
	      const data = yield call(search, payload);
	      yield put({
	        type: 'querySuccess',
	        payload: {
	          list: data.list
	        }
	      })
    },
    *create ({ payload }, { call, put }) {
      yield put({ type: 'hideModal' });
      yield put({ type: 'showLoading' });
			
      const data = yield call(create, payload);
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    *update ({ payload }, { select, call, put }) {
      yield put({ type: 'hideModal' });
      yield put({ type: 'showLoading' });
	    const Id = yield select(({ pickup }) => pickup.currentItem.id);
	    const newIcebox = { ...payload, Id };
      const data = yield call(update, newIcebox);
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    *reload(action, { put, select }) {
      const pickup = yield select(({ pickup }) => pickup);
      yield put({ type: 'search', payload: {
          ...location.query
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
      const {list} = action.payload;
      return { ...state,
        list,
        loading: false
      }
    },
    clear (state, action) {
      return { ...state,
        list:[],
        loading: false
      }
    },
    showModal (state, action) {
      return { ...state, ...action.payload, modalVisible: true }
    },
    hideModal (state) {
      return { ...state, modalVisible: false }
    }
  }

}
