import {login, userInfo, logout} from '../services/app'
import {parse} from 'qs'
import { routerRedux } from 'dva/router'

export default {
  namespace: 'common',
  state: {
    loading: false,
    loginButtonLoading: false
  },
  subscriptions: {
    setup ({dispatch}) {
      
    }
  },
  effects: {
    *login ({
      payload
    }, {call, put}) {
      yield put({type: 'showLoginButtonLoading'})
      const data = yield call(login, parse(payload))
      if (!data.errcode) {
        location.href="#/dashboard";
      } else {
        yield put({
          type: 'loginFail',
          payload: {
          }})
      }
    }
  },
  reducers: {
    loginSuccess (state, action) {
      return {
        ...state,
        ...action.payload,
        login: true,
        loginButtonLoading: false
      }
    },
    logoutSuccess (state) {
      return {
        ...state
      }
    },
    loginFail (state,action) {
      return {
        ...state,
        ...action.payload,
        loginButtonLoading: false
      }
    },
    showLoginButtonLoading (state) {
      return {
        ...state,
        loginButtonLoading: true
      }
    },
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
    }
  }
}
