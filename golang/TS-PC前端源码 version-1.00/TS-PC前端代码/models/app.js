import {login, userInfo, logout} from '../services/app'
import {parse} from 'qs'
import { routerRedux } from 'dva/router'
import {message,Modal} from 'antd'
import {remote} from 'electron'
const {cli} =remote.getGlobal('services')
export default {
  namespace: 'app',
  state: {
    login: false,
    loading: false,
    user: {
      name: '銀犁TS'
    },
    message:{},//通知信息
    loginButtonLoading: false,
    menuPopoverVisible: false,
    siderFold: localStorage.getItem('antdAdminSiderFold') === 'true',
    darkTheme: localStorage.getItem('antdAdminDarkTheme') !== 'false',
    isNavbar: document.body.clientWidth < 769,
    navOpenKeys: JSON.parse(localStorage.getItem('navOpenKeys') || '[]') //侧边栏菜单打开的keys
  },
  subscriptions: {
    setup ({dispatch,history}) {
      dispatch({type: 'queryUser'});
      window.onresize = function () {
        dispatch({type: 'changeNavbar'})
      }
    }
  },
  effects: {
    *login ({ payload}, {call, put}) {

      yield put({type: 'showLoading'});
      const data = yield call(login, parse(payload));
      if (data && data.errcode != 0) {
        if(data.needUpdate == 1){
            const modal = Modal.success({
              title: '有新的版本需要下载更新',
              maskClosable: false,
              okText:'立即更新',
              onOk:function(){
                cli.openLink(data.downUrl)
                
              }
            });
            yield put({type: 'hideLoading'});
            return
        }
      	sessionStorage.setItem("IS_LOGIN",1)
        yield put({
            type: 'loginSuccess',
            payload: {
              user: {
                name: data.name
              }
            }
        });
        /** 登录后刷新界面 */
        location.reload();
        let pathname=location.hash.split('?')[0].replace('#','');
        yield put(routerRedux.replace({
            pathname: pathname,
        }))
      } else {
        const modal = Modal.success({
          title: data.errmsg || '登录失败',
          // content: 'This modal will be destroyed after 1 second',
        });
        yield put({
          type: 'loginFail'
        })
      }
    },
    *queryUser ({ payload}, {call, put}) {
 			
 			let login=sessionStorage.getItem("IS_LOGIN")
 			
 			if(login=="1"){
 				 yield put({
          type: 'loginSuccess',
          payload: {
            user: {
              name: "銀犁TS"
            }
          }
        })
 			}
      /*yield put({type: 'showLoading'});
      const data = yield call(userInfo, parse(payload));
      if (!data.errcode) {
        yield put({
          type: 'loginSuccess',
          payload: {
            user: {
              name: data.name
            }
          }
        })
      }

      yield put({type: 'hideLoading'})*/
    },
    *logout ({  payload }, {call, put}) {
    	sessionStorage.removeItem("IS_LOGIN",1)
    	 yield put({
          type: 'logoutSuccess'
        })
    },
    *errmsg ({  payload }, {call, put}) {

      if(payload.indexOf("errcode")<0||JSON.parse(payload).errcode!=302){
          yield put({
              type: 'handleErrmsg',
              payload:payload
          })
      }else{
          yield put({
            type: 'loginFail',
          })
      }

    },
    *switchSider ({ payload}, {put}) {
      yield put({
        type: 'handleSwitchSider'
      })
    },
    *changeTheme ({ payload}, {put}) {
      yield put({
        type: 'handleChangeTheme'
      })
    },
    *changeNavbar ({ payload }, {put}) {
      if (document.body.clientWidth < 769) {
        yield put({type: 'showNavbar'})
      } else {
        yield put({type: 'hideNavbar'})
      }
    },
    *switchMenuPopver ({ payload }, {put}) {
      yield put({
        type: 'handleSwitchMenuPopver'
      })
    }
  },
  reducers: {
    handleErrmsg (state, action) {
      let message="网络请求失败";
      if(action.payload.indexOf("errcode")>0){
          let data=JSON.parse(action.payload);
          if(data.errcode!=302){
              message=data.errmsg
          }

      }
      message.error(message);
      return {
        ...state,
        loading:false,
        loginButtonLoading: false
      }
    },
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
        ...state,
        login: false
      }
    },
    loginFail (state,action) {

      return {
        ...state,
        ...action.payload,
        login: false,
        loading:false,
        loginButtonLoading: false
      }
    },
    showLoginButtonLoading (state) {
      return {
        ...state,
        loginButtonLoading: true
      }
    },
    hideLoginButtonLoading (state) {
      return {
        ...state,
        loginButtonLoading: false
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
    },
    handleSwitchSider (state) {
      localStorage.setItem('antdAdminSiderFold', !state.siderFold);
      return {
        ...state,
        siderFold: !state.siderFold
      }
    },
    handleChangeTheme (state) {
      localStorage.setItem('antdAdminDarkTheme', !state.darkTheme);
      return {
        ...state,
        darkTheme: !state.darkTheme
      }
    },
    showNavbar (state) {
      return {
        ...state,
        isNavbar: true
      }
    },
    hideNavbar (state) {
      return {
        ...state,
        isNavbar: false
      }
    },
    handleSwitchMenuPopver (state) {
      return {
        ...state,
        menuPopoverVisible: !state.menuPopoverVisible
      }
    },
    handleNavOpenKeys(state, action) {
      return {
        ...state,
        ...action.payload
      }
    }
  }
}
