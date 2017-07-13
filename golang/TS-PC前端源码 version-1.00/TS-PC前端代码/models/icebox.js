import { query,create,update,search,remove } from '../services/icebox'
import { parse } from 'qs'

export default {

  namespace: 'icebox',

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
      history.listen(location => {
        if (location.pathname === '/stats/icebox') {
          dispatch({
            type: 'query',
            payload: location.query
          })
        }
      })
    }
  },

  effects: {
    *query ({ payload }, { call, put }) {
	      yield put({ type: 'showLoading' })
	      let params=parse(payload)
	      if(params.page==undefined){
	      	params.page=1
	      }
	      if(params.pageSize==undefined){
	      	params.pageSize=10
	      }
	      const data = yield call(query, params);
	   		
	      yield put({
	        type: 'querySuccess',
	        payload: {
	          list: data.list,
	          pagination: {
	            total: data.totalRows,
	            current: parseInt(params.page)
	          }
	        }
	      })
    },
    *search ({ payload }, { call, put }) {
	      yield put({ type: 'showLoading' });
	      let params=parse(payload)
	      if(params.page==undefined){
	      	params.page=1
	      }
	      if(params.pageSize==undefined){
	      	params.pageSize=10
	      }
	      const newParams = {  ...payload,...params };	
	      const data = yield call(search, parse(newParams));
	     
	      yield put({
	        type: 'querySuccess',
	        payload: {
	          list: data.list,
	          pagination: {
	            total: data.totalRows,
	            current: params.page
	          }
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

	    const Id = yield select(({ icebox }) => icebox.currentItem.id);
	    const newIcebox = { ...payload, Id };
    
      const data = yield call(update, newIcebox);
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    *delete({ payload }, { select, call, put }) {
      yield put({ type: 'hideModal' });
      yield put({ type: 'showLoading' });
     
      const data = yield call(remove, payload);
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    *reload(action, { put, select }) {
      const page = yield select(({ icebox }) => icebox.pagination);
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
      const {list, pagination} = action.payload;

      return { ...state,
        list,
        loading: false,
        pagination: {
          ...state.pagination,
          ...pagination
        }}
    },
    showModal (state, action) {
      return { ...state, ...action.payload, modalVisible: true }
    },
    hideModal (state) {
      return { ...state, modalVisible: false }
    }
  }

}
