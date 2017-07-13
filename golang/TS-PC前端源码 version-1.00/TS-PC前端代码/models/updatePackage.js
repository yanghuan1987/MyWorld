import { query,update,remove,search,reload} from '../services/updatePackage'
import { parse } from 'qs'

export default {

  namespace: 'updatePackage',

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
        if (location.pathname === '/update/updatePackage') {
          dispatch({
            type: 'updateReload',
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
      yield put({ type: 'hideModal' });
      yield put({ type: 'showLoading' });

     let params=parse(payload)
      if(params.page==undefined){
        params.page=1
      }
      if(params.pageSize==undefined){
        params.pageSize=10
      }
      const data = yield call(search, params);
       yield put({ type: 'querySuccess' ,payload: {
          list: data.list,
          pagination: {
            total: data.totalRows,
            current: parseInt(params.page)
          }
        }});
    },
    *update ({ payload }, { select, call, put }) {
      yield put({ type: 'hideModal' });
      yield put({ type: 'showLoading' });
//    const id = yield select(({ station }) => station.currentItem.id);
//    const newStation = { ...payload, id };

      const data = yield call(update, payload);
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
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
    *updateReload({ payload }, { call, put }){
    	yield put({ type: 'showLoading' });
      let params=parse(payload)
      if(params.page==undefined){
        params.page=1
      }
      if(params.pageSize==undefined){
        params.pageSize=10
      }
    	const data = yield call(reload,params);
      
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
    *reload(action, { put, select }) {
      const page = yield select(({ updatePackage }) => updatePackage.pagination);
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
        }
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
