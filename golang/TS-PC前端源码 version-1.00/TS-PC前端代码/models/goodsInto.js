import { create, remove, update, query,search } from '../services/goodsInto'
import { parse } from 'qs'

export default {
  namespace: 'goodsInto',

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
        if (location.pathname === '/packages/goodsInto') {
          dispatch({
            type: 'search',
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
    *'delete' ({ payload }, { call, put }) {
      yield put({ type: 'showLoading' });
      const data = yield call(remove, { id: payload });
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    *create ({ payload }, { call, put }) {
      yield put({ type: 'hideModal' });
      yield put({ type: 'showLoading' });
      const params = parse(payload);
			params.page = 1;
			params.pageSize = 10;

      const data = yield call(create, params);

      if(data.errcode == 0){
        alert(data.errmsg || '上架失败');
      }else{
        alert("上架成功")
        document.getElementById('shopCodeNumber').value = '';
        document.getElementById('shopCodeId').value = '';
        document.getElementById('shopCodeCount').value = '';
      }

      yield put({ type: 'reload' });
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
          pagination: {
            total: data.totalRows,
            current: parseInt(params.page)
          }
        }
      })
    },
    *update ({ payload }, { select, call, put }) {
      yield put({ type: 'hideModal' });
//    const id = yield select(({ goodsInto }) => goodsInto.currentItem.id);
//    const newStation = { ...payload, id };
      const params =  parse(payload);
      params.page = 1;
      params.pageSize = 10;
      
      console.log(params)
      const data = yield call(update,params);
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    *reload(action, { put, select }) {
      const page = yield select(({ goodsInto }) => goodsInto.pagination);
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
