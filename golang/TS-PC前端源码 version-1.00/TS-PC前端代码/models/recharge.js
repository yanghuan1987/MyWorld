import {bind,charge,queryInfo} from '../services/recharge'
import {parse} from 'qs'
import {routerRedux } from 'dva/router'
import {message,Modal} from 'antd'

export default {
  namespace: 'recharge',
  state: {
    loading: false,
    formValue: {
			cphone: '',
			amount: ''
		}
  },
  subscriptions: {
    	setup ({ dispatch, history }) {
      history.listen(location => {
        if (location.pathname === '/card/charge') {
        }
      })
    }
  },
  effects: {
    *charge ({ payload }, {call, put}) {
      yield put({type: 'showLoading'});
      const params = parse(payload.params);
      params.employeeId = '';
      params.account = '';
      params.siteCode = '';
      params.chgtype = '';
      const data = yield call(charge, params);
      
      if(data.code==200){
          Modal.success({
            title: data.msg
          });
          payload.fn()
          yield put({type: 'chargeSuccess'})
          
      }else{
           Modal.error({
            title: data.msg
          });
      }

    },
    *infoQuery({ payload }, {call, put}){
    	yield put({type: 'showLoading'});
    	const data = yield call(queryInfo, payload);

    	if(data.data && data.data[0]){
          yield put({type: 'querySuccess',payload: data})
      } else{
        yield put({type: 'chargeSuccess'});
      }     
    	
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
      return {...state,loading: false,formValue: data[0]}
      
    },
   chargeSuccess(state, action){     
      return {...state,loading: false,formValue:{}}
    }
  }
}
