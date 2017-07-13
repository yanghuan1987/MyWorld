import { query,remove,create,search} from '../services/packageInto'
import { parse } from 'qs'

export default {

  namespace: 'packageInto',

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
        if (location.pathname === '/packages/packageInto') {
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
      const data = yield call(query, parse(params));
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
	      const data = yield call(search, params);	   
	      yield put({
	        type: 'querySuccess',
	        payload: {
	          list: data.list,
            pagination:{
              total: data.totalRows,
              current: parseInt(params.page)
            }
	        }
	      })
    },
    *update ({ payload }, { select, call, put }) {
      yield put({ type: 'hideModal' });
      yield put({ type: 'showLoading' });
      const id = yield select(({ packageInto }) => packageInto.currentItem.id);

      const newStation = { ...payload, id };
      const data = yield call(update, newStation);
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    *create ({ payload }, { call, put }) {
      yield put({ type: 'hideModal' });
      yield put({ type: 'showLoading' });

      const data = yield call(create, payload);
      if(data.errcode == 0){
        alert(data.errmsg || '入柜失败');
      }else{
        alert("入柜成功")
        document.getElementById('shopCodeNumber').value = '';
        document.getElementById('shopCodeId').value = '';
      }
      yield put({ type: 'reload' });
    },
    
    *'delete' ({ payload }, { call, put }) {
      yield put({ type: 'showLoading' });
      const data = yield call(remove, { codeNum: payload });
      if (!data.errcode) {

        yield put({ type: 'reload' });
      }else{

        yield put({ type: 'hideLoading' })
      }
    },
    *reload(action, { put, select }) {
      const page = yield select(({ packageInto }) => packageInto.pagination);
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




	