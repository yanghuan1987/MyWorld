import {query,resetpwd,queryInfo} from '../services/resetpwd'
import {parse} from 'qs'
import {routerRedux } from 'dva/router'
import {message} from 'antd'

export default {
  namespace: 'resetpwd',
  state: {
    loading: false,
    formValue: {}
  },
  subscriptions: {
    	setup ({ dispatch, history }) {
      history.listen(location => {
        if (location.pathname === '/card/resetpwd') {
        }
      })
    }
  },
  effects: {
    *query ({ payload }, {call, put}) {
      yield put({type: 'showLoading'});
      const data = yield call(bind, parse(payload));
	    yield put({
	    	type: 'hideLoading'
	    })
    }, 
  
  *resetpwd({payload},{call,put}){
  	   yield put({type: 'showLoading'});
  
       const params = parse(payload.params);
      const data = yield call(resetpwd, params);
      alert("操作成功")
      payload.fn()
	    yield put({
	    	type: 'resetSuccess'
	    })
    },
    *infoQuery({payload},{call,put}){
      yield put({type: 'showLoading'});
      
      const data = yield call(queryInfo, payload);
      yield put({type: 'querySuccess',payload: {
          data,
          params:payload
        }
      })
    }
  },
  
  reducers: {
    showLoading (state) {
      return {
        ...state,
        loading: true
      }
    },
    hideLoading (state) {
      return {
        ...state,
        loading: false
      }
    },
    querySuccess(state, action){     
      const data = action.payload.data;

      if(data.data && data.data[0]){

        return {...state,loading: false,formValue: data.data[0]}
      } else{
        const params = action.payload.params;
        return {...state,loading: false,formValue:{...params}}
      }     
    },
    resetSuccess(state, action){     
      return {...state,loading: false,formValue:{}}
    }
  }
}
