import dva from 'dva';
import {message} from 'antd';
import './main-dev.html';
import {remote} from 'electron'
const {cli} =remote.getGlobal('services')
// 1. Initialize      
const app = dva({
  onError(e) {

     if(!e.message){
         return;
     }

     if(app._models[2]!=undefined){
        let len=app._models.length
        for(var i=0;i<len;i++){
            if(i>1){
                const m=app._models[i].namespace
                app._store.dispatch({type: m+'/hideLoading'})
            }
        }   
        
        app._store.dispatch({type: 'app/hideLoading'})
     }
     console.log(e)
     let data=JSON.parse(e.message);
     message.error(data.errmsg);
     if(data.errmsg=='SessionTimeout'||data.errcode==403){
        app._store.dispatch({type: 'app/logout'})
     }
     //app._store.dispatch({type: 'app/errmsg',payload: e.message})
  },
});

// 2. Plugins
// app.use({});

// 3. Model
app.model(require('./models/app'))

// 4. Router
app.router(require('./router'));

// 5. Start
app.start('#root');

cli.upload();
