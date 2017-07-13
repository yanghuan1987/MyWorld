import {init} from '../services/dashboard'
import {parse} from 'qs'
import { routerRedux } from 'dva/router'
import {message,Modal} from 'antd'

export default {
  namespace: 'dashboard',
  state: {
    login: false,
    loading: false,
    data:{}
  },
  subscriptions: {
    setup ({dispatch,history}) {

      history.listen(location => {
        if (location.pathname === '/'||location.pathname === '/dashboard') {
            dispatch({
              type: 'init',
              payload: {data:''}
            })
        }
      })
     
    }
  },
  effects: {
    *init ({ payload}, {call, put}) {
      yield put({ type: 'showLoading' });
    
      const data = yield call(init, parse(payload));
      
      yield put({
        type: 'initView',
        payload: {
            data:data
        }
      })
    }

  },
  reducers: {
     initView (state, action) {
      const {data} = action.payload;
      return {
        ...state,
        ...action.payload,
        data:data
      }
    },
  }
}
