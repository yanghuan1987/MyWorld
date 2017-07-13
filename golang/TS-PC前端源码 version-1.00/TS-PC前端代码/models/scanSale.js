import { query,queryInfo,payment,cardpay } from '../services/scanSale'
import { parse } from 'qs'

export default {

  namespace: 'scanSale',

  state: {
    dataSource:{
      list: [],
      amount:0,
      totalPrice:0,
    },
    orderInfo:{},
    loading: false,
    currentItem: {},
    paymentVisible:false,
    paymentType:0,
    modalVisible: false,
    modalType: 'create',
    pagination: {
      showSizeChanger: true,
      showQuickJumper: true,
      showTotal: total => `共 ${total} 条`,
      current: 1,
      total: null
    },
  },
	
  subscriptions: {
    setup ({ dispatch, history }) {
      
    }
  },

  effects: {
    *query ({ payload }, { call, put }) {
      const data = yield call(query, parse(payload));
      yield put({
        type: 'querySuccess',
        payload: {
          productInfo: data
        }
      })
    },
    *add ({ payload }, { call, put }) {

      //根据商品条形码查询数据
      const data = yield call(queryInfo, parse(payload));
      let item={}
      if(data.list.length>0){
        item=data.list[0]
        if(parseInt(item.amount)<=0){
          alert("商品库存不足")
          return
        }
        item.totalAmount=parseInt(item.amount)
        item.amount=1
      }else{
        alert("商品不存在")
        return
      }

      yield put({
        type: 'queryAdd',
        payload: {
          item: item
        }
      })
    },

    *payment ({ payload }, { call, put }) {
      yield put({ type: 'showLoading' });
      const data = yield call(payment, parse(payload));
      yield put({
        type: 'paymentShow',
        payload: {
          orderInfo: data,
          paymentType:payload.paymentType
        }
      })
    },
     *cardpay ({ payload }, { call, put }) {
      
      yield put({ type: 'paymentHide' });
      yield put({ type: 'showLoading' });
      const data = yield call(cardpay, parse(payload));

      alert("操作成功")
       yield put({ type: 'clear' });
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
      const {productInfo} = action.payload;
      return { productInfo,loading: false}
    },
    queryAdd (state, action) {
      const {item} = action.payload;
      //原数据是否存在该商品，存在则数量加1
      const {dataSource}=state
      let isRepeat=false
      dataSource.list.map(function(obj){
        if(obj.id==item.id){
          obj.amount++
          isRepeat=true
        }
      })
      if(!isRepeat){
        dataSource.list.push(item)
      }
      dataSource.amount++
      dataSource.totalPrice+=parseFloat(item.price)
      return { dataSource,loading: false}
    },
    amountChange (state, action) {

      const {dataSource}=state
      //dataSource自动会变更，去掉amount=0的item
      //重新计算价格和数量

      let amount=0;
      let price=0;
      let removeIndex=-1
      dataSource.list.map(function(obj,index){
   
        if(obj.amount==0){
          removeIndex=index
        }else{
          amount+=obj.amount
          price+=obj.amount*obj.price
        }
                
      })
      if(removeIndex>-1){

        dataSource.list.splice(removeIndex,1)
      }
      
      dataSource.amount=amount
      dataSource.totalPrice=price
      return { dataSource,loading: false}
    },
    clear (state, action) {
      let dataSource={
        list: [],
        amount:0,
        totalPrice:0,
      }
      return {dataSource,loading: false}
    },
    
    paymentShow (state, action) {

      return { ...state, ...action.payload,loading: false, paymentVisible: true }
    },
    paymentHide (state, action) {

      return { ...state, paymentVisible: false }
    },
    showModal (state, action) {
      return { ...state, ...action.payload, modalVisible: true }
    },
    hideModal (state) {
      return { ...state, modalVisible: false }
    }
  }

}
