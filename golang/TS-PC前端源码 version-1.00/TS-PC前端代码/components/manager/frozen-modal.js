import React, { PropTypes } from 'react'
import { Form, Input, InputNumber, Radio, Modal, Select,Row,Col,Button } from 'antd'
const FormItem = Form.Item;
import { confirm } from '../../services/frozen'

const formItemLayout = {
  labelCol: {
    span: 6
  },
  wrapperCol: {
    span: 14
  }
};

const modal = ({
  visible,
  loading,
  type,
  item = {},
  onOk,
  formValue,
  onCancel,
  form: {
    getFieldDecorator,
    validateFields,
    getFieldsValue
  }
}) => {
  function handleOk() {
    validateFields((errors) => {
      const data = {
        ...getFieldsValue(),
        key: item.key
      };
      // onOk({
      //   cardsyscode: data.cardsyscode
      // })
      handleConfirm({
        cardsyscode:data.cardsyscode
      })
    })
  }
  
  function handleCancel(){
    onCancel();
  }

  function handleConfirm(params){
      confirm(params).then(function(data){
        console.log(data);
        if(data.code=='200'){
          alert('银犁卡注销成功')
        }
        if(data.errcode==undefined){
          alert('银犁卡注销失败')
        }
      })
  }


  const modalOpts = {
    title:'银犁卡状态处理',
    visible,
    onOk: handleOk,
    onCancel: handleCancel,
    wrapClassName: 'vertical-center-modal'
  };

  return(
  <Modal {...modalOpts}>
      <Form horizontal>      
        <FormItem label='银犁卡号' hasFeedback {...formItemLayout}>
          {getFieldDecorator('cardsyscode', {
            initialValue: formValue.cardsyscode
          })(<Input disabled/>)}
        </FormItem>
        <FormItem label='姓名' hasFeedback {...formItemLayout}>
          {getFieldDecorator('cname', {
              initialValue: formValue.cname
          })(<Input disabled/>)}
        </FormItem>
            <FormItem label='身份证' hasFeedback {...formItemLayout}>
          {getFieldDecorator('idno', {
            initialValue: formValue.idno
          })(<Input disabled/>)}
        </FormItem>
        <FormItem label='剩余金额' hasFeedback {...formItemLayout}>
          {getFieldDecorator('initialmoney', {
            initialValue: formValue.availableBalance
          })(<Input disabled/>)}
        </FormItem>
        <FormItem label='当前状态' hasFeedback {...formItemLayout}>
          {getFieldDecorator('status', {
            initialValue: formValue.status == 0 ? '激活' : formValue.status == 1 ? '冻结' : '销卡'
          })(<Input disabled/>)}
        </FormItem>
        <FormItem>
        <div>
           <h2>若销卡将退金额<span>&yen;{formValue.refund==""?0:formValue.refund}</span></h2>
         <h3>{'1.累计充值金额-累计的消费金额>0,允许退款'}</h3>
         <h3>{'2.累计充值金额-累计的消费金额<=0,则不退款'}</h3>
         <h3>{'3.退款只退充值金额,平台赠送金额不参与计算'}</h3>
      </div>
      </FormItem>
    </Form>
    </Modal>
  )
};

modal.propTypes = {
  visible: PropTypes.any,
  form: PropTypes.object,
  item: PropTypes.object,
  onOk: PropTypes.func,
  onCancel: PropTypes.func
};

export default Form.create()(modal)