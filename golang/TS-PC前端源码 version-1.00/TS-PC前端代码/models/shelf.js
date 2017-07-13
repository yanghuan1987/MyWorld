import { query,create,remove,update,iceboxQuery,search,listQuery } from '../services/shelf'

import { parse } from 'qs'

export default {

  namespace: 'shelf',

  state: {
    list: [],
    iceboxList:[],
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
        if (location.pathname === '/stats/shelf') {
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
      yield put({ type: 'showLoading' });
      let params=parse(payload)
      if(params.page==undefined){
      	params.page=1
      }
      if(params.pageSize==undefined){
      	params.pageSize=10
      }
      const iceboxData=yield call(iceboxQuery,{page:1,pageSize:10000})
      const data = yield call(query, parse(params));
      
      yield put({
        type: 'querySuccess',
        payload: {
          list: data.list,
          iceboxList:iceboxData.list,
          pagination: {
            total: data.totalRows,
            current: parseInt(params.page)
          }
        }
      })
    },
    
    *listQuery ({ payload }, { call, put }) {
      yield put({ type: 'showLoading' });
      const data = yield call(create, payload);
      
      yield put({ type: 'query' })
    },

    *create ({ payload }, { call, put }) {
      yield put({ type: 'hideModal' });
      yield put({ type: 'showLoading' });
			
      const data = yield call(create, payload);
      console.log(data)
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
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
        const iceboxData=yield call(iceboxQuery,{page:1,pageSize:10000})
	      const data=yield call(search, {
          number: payload.shelfNumber,
          icename:payload.iceboxId,
          page: params.page,
          pageSize: params.pageSize})
	      yield put({
	        type: 'querySuccess',
	        payload: {
	          list: data.list,
            iceboxList:iceboxData.list,
	          pagination: {
	          	current: parseInt(params.page),
	          	total: data.totalRows
	          }
	        }
	      })
    },
	*'delete' ({ payload }, { call, put }) {
      yield put({ type: 'showLoading' });
      const data = yield call(remove, { id: payload });
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    *update ({ payload }, { select, call, put }) {
      yield put({ type: 'hideModal' });
      yield put({ type: 'showLoading' });
      const Id = yield select(({ shelf }) => shelf.currentItem.id);
      const newShelf = { ...payload, Id };
      const data = yield call(update, parse(newShelf));
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    *reload(action, { put, select }) {
      const page = yield select(({ shelf }) => shelf.pagination);
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
      const {list,iceboxList, pagination} = action.payload;
			if(pagination && pagination.current){
				pagination.current = parseInt(pagination.current)
			}
      return { ...state,
        list,
        iceboxList,
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
