import {bind,charge} from '../services/open'
import {parse} from 'qs'
import {routerRedux } from 'dva/router'
import {message,Modal} from 'antd'

export default {
  namespace: 'open',

  state: {
    loading: false,
    modalVisible:false
  },
  subscriptions: {
    	setup ({ dispatch, history }) {
      history.listen(location => {
        if (location.pathname === '/card/open') {
        }
      })
    }
  },
  effects: {
    *bind ({ payload }, {call, put}) {
      yield put({type: 'showLoading'});
      const data = yield call(bind, 
      	{customer: parse(payload.params),cardDTO:{factory:'',operatorcode:'',operatorname:'',Validity:''}}

      );
	    yield put({type: 'hideLoading'})
      if(data.code==200){
          Modal.success({
            title: data.msg
          });
          payload.fn()
      }else{
           Modal.error({
            title: data.msg
          });
      }
      
    },
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
    showModal (state, action) {
      return { ...state, ...action.payload, modalVisible: true }
    },
    hideModal (state) {
      return { ...state, modalVisible: false }
    }
  }
}
