import React, { PropTypes } from 'react'
import { Form, Input, InputNumber, Radio, Modal, Select,Icon } from 'antd'
import { orderStatus } from '../../services/scanSale'

const FormItem = Form.Item;
const QRCode = require('qrcode.react');
var timer

const formItemLayout = {
	labelCol: {
		span: 6
	},
	wrapperCol: {
		span: 14
	}
};

class PaymentModal extends React.Component {
  constructor (props) {
    	super(props);
    	this.state={
    		"paymentStatus":9
    	}
   }
   handleOk() {
    if(this.props.type==0){
      this.props.onCancel()
      return
    }
		this.props.form.validateFields((errors) => {
			if(errors) {
				return
			}
			const data = {
				...this.props.form.getFieldsValue()
			};
			this.props.onOk(data)
		})
	}
  handleCancle(needClear){
    if(timer!=undefined){
      clearInterval(timer)
    }
    this.props.onCancel(needClear)
  }  
  async checkOrderStatus(orderId){
      if(orderId==undefined){
        return
      }
      let repeat=0
      let success=false
      let _this=this
      timer=setInterval(function(){

        if(success||repeat>=20){
          clearInterval(timer)

          if(success&&repeat>0){
 
            _this.setState({
              "paymentStatus":1
            })            
            setTimeout(function(){
                _this.handleCancle(true)
            },2000)
          }
          if(!success&&repeat>0){
            _this.setState({
              "paymentStatus":0
            })

          }
          return
        }
        orderStatus(orderId,_this.props.type).then(function(data){
            repeat++
            if(data.errcode==undefined){
              success=true
            }
         })
      },3500)      
  }
  renderPaymentStatus(){
     if(this.state.paymentStatus==0){
       return (
         <div style={{textAlign:"center",fontSize:16,marginBottom:20,color:'red',fontWeight:'bold'}}>
           <Icon type="close-circle" />&nbsp;支付失败</div>
       )
     }
     if(this.state.paymentStatus==1){
       return (
         <div style={{textAlign:"center",fontSize:16,marginBottom:20,color:'green',fontWeight:'bold'}}>
           <Icon type="check-circle" />&nbsp;支付成功</div>
       )
     }
     if(this.state.paymentStatus==9){
       return (
         <div style={{textAlign:"center",fontSize:16,marginBottom:20,fontWeight:'bold'}}>
           <Icon type="question-circle" />&nbsp;等待支付</div>
       )
     }
  }
  renderPage(){
    const _this=this
    const {item,visible,type}=this.props

    if(item==undefined||item.orderNo==undefined){
      return(<div></div>)
    }
    const { getFieldDecorator } = this.props.form
      if(visible==false){
        return
      }
     if(type==0){
      if(this.state.paymentStatus==9){
           //2s后开始轮询
           setTimeout(function() {
            _this.checkOrderStatus(item.orderNo)
          }, 2000)  
      }
      
       
       return (
         <Form>
          {this.renderPaymentStatus()}
         <div style={{textAlign:'center',marginBottom:20}}>
            <QRCode value={item.code==undefined?"00":item.code} size={200}/>
         </div>
          <FormItem label='订单编号：' hasFeedback {...formItemLayout}>
            {getFieldDecorator('orderNo', {
              initialValue: item.orderNo
            })(<Input type="text" disabled/>)}
          </FormItem>
          <FormItem label='商品数量：' hasFeedback {...formItemLayout}>
            {getFieldDecorator('totalAmount', {
              initialValue: item.totalAmount
              })(<Input disabled/>)}
          </FormItem>

          <FormItem label='订单价格：' hasFeedback {...formItemLayout}>
            {getFieldDecorator('totalPrice', {
              initialValue: item.totalPrice
            })(<Input disabled/>)}
          </FormItem>
 
      </Form>
       )
     }
     return (
       <Form horizontal>
       
         <FormItem label='银犁卡号：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('cardno', {
            initialValue: '',
            rules: [
              {
                required: true,
                message: '银犁卡号不能为空'
              }
            ]
          })(<Input />)}
        </FormItem>
         <FormItem label='银犁卡密码：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('password', {
            initialValue: '',
            rules: [
              {
                required: true,
                message: '银犁卡密码不能为空'
              }
            ]
          })(<Input type="password" />)}
        </FormItem>
        <FormItem label='订单编号：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('orderNo', {
            initialValue: item.orderNo
          })(<Input disabled/>)}
        </FormItem>
        <FormItem label='商品数量：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('totalAmount', {
            initialValue: item.totalAmount
            })(<Input disabled/>)}
        </FormItem>

        <FormItem label='订单价格：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('totalPrice', {
            initialValue: item.totalPrice
          })(<Input disabled/>)}
        </FormItem>

      </Form>
     )
  }
  componentWillUnmount(){
      clearInterval(timer)
  }
  render(){
    const key=new Date().getTime()
    const {type,visible}=this.props
    const modalOpts = {
      title: `${type === 0 ? '微信支付' : '银犁卡支付'}`,
      visible,
      onOk: this.handleOk.bind(this),
      onCancel:this.handleCancle.bind(this),
      wrapClassName: 'vertical-center-modal',
      maskClosable:false,
    };
    return(
      <Modal {...modalOpts}>
        {this.renderPage()}
    </Modal>
    )
  }
}

PaymentModal.propTypes = {
	visible: PropTypes.any,
	form: PropTypes.object,
	item: PropTypes.object,
	onOk: PropTypes.func,
	onCancel: PropTypes.func
};

export default Form.create()(PaymentModal)