import {frozen,cancel,queryInfo,confirm,pincard} from '../services/frozen'
import {parse} from 'qs'
import {routerRedux } from 'dva/router'
import {message} from 'antd'

export default {
  namespace: 'frozens',
  state: {
    loading: false,
    modalVisible: false,
    formValue: {},
    pincard:{},
  },
  subscriptions: {
    	setup ({ dispatch, history }) {
      history.listen(location => {
        if (location.pathname === '/card/frozen') {
      
        }
      })
    }
  },
  effects: {
    *frozes ({ payload }, {call, put}) {
      yield put({type: 'showLoading'});
      const data = yield call(frozen, parse(payload));
      if(data.code==200){
        alert("操作成功")
      }
	    yield put({ type: 'infoQuery', payload});
    },
    *cancel ({ payload }, {call, put}) {
	    yield put({type: 'hideDailogModal'})
      yield put({type: 'showLoading'});
      const data = yield call(cancel, parse(payload));
      yield put({type: 'hideLoading'})
    },
    *infoQuery({ payload }, {call, put}){
      yield put({type: 'showLoading'});

      const data = yield call(queryInfo, payload);
      yield put({type: 'querySuccess',payload:data
      })
    },
    *pincard({ payload }, {call, put}){
      yield put({type: 'showLoading'});
      const data = yield call(pincard, payload);
      data.status=payload.status
      data.availableBalance=payload.initialmoney
      data.idno=payload.idno
      yield put({type: 'showDialogModal',payload:{
         data:data
        }
      })
    },    
    *confirm({ payload }, {call, put}){
      yield put({type: 'showLoading'});
      const data = yield call(confirm, payload);
      if(data.code==200){
        alert("销卡成功")
      }
      yield put({type: 'hideLoading'});
     
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
    showDialogModal(state, action){
      const data = action.payload.data;
    	return{...state,loading: false,modalVisible: true,pincard:data}
    },
    hideDailogModal(state){
    	return {...state,loading: false,modalVisible: false}
    },
    querySuccess(state, action){     
      const data = action.payload.data;

      if(data&&data!=null && data[0]){
        return {...state,loading: false,formValue: data[0]}
      } else{
        return {...state,loading: false,formValue:{}}
      }
     
    }
  }
}
