import { query,remove,update,search,move} from '../services/stockdis'
import { parse } from 'qs'
import {message,Modal} from 'antd'
export default {

  namespace: 'stockdis',

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
    	console.log(history)
      history.listen(location => {
        if (location.pathname === '/packages/stockdis') {
           const { query, pathname } = location;
          dispatch({
            type: 'search',
            payload: {
              ...query,
              keyname: "",
              nowtime: -1,
              status: -1
            }
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
    *move({payload},{call,put}){
    	yield put({type:'showLoading'});
    	
    	const data = yield call(move,payload);
       Modal.success({
            title: '操作成功'
          });
      document.getElementById('shopCodeNumber').value = '';
      document.getElementById('shopCodeId').value = '';
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
      if(params.keyname==undefined){
        params.keyname=''
      }
      if(params.nowtime==undefined){
        params.nowtime='-1'
      }
      if(params.status==undefined){
        params.status='-1'
      }

      const newParams = { ...payload, ...params };

      const data = yield call(search, newParams);
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
  
    *delete ({ payload }, { call, put }) {
      yield put({ type: 'showLoading' });
      const data = yield call(remove, { codeNum: payload });
      if (!data.errcode) {
        yield put({ type: 'reload' });
      }else{
        yield put({ type: 'hideLoading' })
      }
    },
    
    *reload(action, { put, select }) {
      const page = yield select(({ stockdis }) => stockdis.pagination);
      yield put({ type: 'search', payload: {
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




	