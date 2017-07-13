import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Form, Input, Button,Radio,Checkbox,Row,Col } from 'antd'
import styles from '../manager/recharge.css'
const FormItem = Form.Item;
const RadioGroup = Radio.Group;

const formItemLayout = {
	labelCol: {
		span: 6
	},
	wrapperCol: {
		span: 14
	}
};
const resetpwd = ({
	loading,
  onOk,
  formValue,
  queryInfo,
  form: {
    getFieldValue,
    getFieldDecorator,
    getFieldsValue,
    validateFieldsAndScroll,
    resetFields
  }
}) =>{
  let flag=0
	function handleOk(){

		validateFieldsAndScroll((errors, values) => {

      if (errors) {
        return
      }
      onOk({
        cardsyscode: values.cardsyscode,
        cpasswd: values.cpasswd,
      },function(){
            resetFields()
        })
    })
	}
  function queryCardInfo(){

    const formValuesss = getFieldsValue(['idno','cardsyscode'])
    if(formValuesss.idno || formValuesss.cardsyscode){
      queryInfo({
        cardsyscode: formValuesss.cardsyscode ? formValuesss.cardsyscode : '',
        idno:formValuesss.idno ? formValuesss.idno : ''
      })

      resetFields()
    }
  }
  function handleClear(){
    resetFields()
    queryInfo({
        cardsyscode:'',
        idno:'',
    })
  }
	function onChange(e){
		type = e.target.value
	} 	
	  return (
	  	<form>
	
        

       <FormItem label='手机号' hasFeedback {...formItemLayout}>
          {getFieldDecorator('cphone', {
            initialValue:formValue.cphone
          })(<Input disabled/>)}
        </FormItem>
        <FormItem label='身份证' hasFeedback {...formItemLayout}>
          {getFieldDecorator('idno', {
            rules: [
              {
                required: true,
                message: '请输入合法的身份证',
                pattern: /(^\d{15}$)|(^\d{17}([0-9]|X)$)/
              }
            ],validateTrigger: 'onBlur',
            initialValue:formValue.idno
          })(<Input onBlur={queryCardInfo} />)}
        </FormItem>
      <FormItem label='银犁卡号' hasFeedback {...formItemLayout}>
          {getFieldDecorator('cardsyscode', {
            rules: [
              {
                required: true,
                message: '银犁卡号未填写'
              }
            ],
            initialValue:formValue.cardsyscode
          })(<Input onBlur={queryCardInfo}/>)}
        </FormItem>
        <FormItem label='卡上余额' hasFeedback {...formItemLayout}>
          {getFieldDecorator('amount', {
            initialValue:formValue.initialmoney
          })(<Input disabled/>)}
        </FormItem>
        <FormItem label='当前状态' hasFeedback {...formItemLayout}>
          {getFieldDecorator('status', {
            initialValue:formValue.status == 0 ? '激活' : formValue.status == 1 ? '冻结' : formValue.status == 2 ? '销卡' : ''
          })(<Input disabled/>)}
        </FormItem>
           <FormItem label='新支付密码' hasFeedback {...formItemLayout}>
          {getFieldDecorator('cpasswd', {
            rules: [
              {
                required: true,
                message: '密码必须为6位数字',
                pattern: '^[0-9]{6}$',
                min: 6,
                max: 6
              }
            ],validateTrigger: 'onBlur'
          })(<Input type="password" placeholder="密码只能为6位（数字）"/>)}
        </FormItem>
        <FormItem label='确认密码' hasFeedback {...formItemLayout}>
          {getFieldDecorator('repwd', {
            rules: [
              {
                required: true,
                message: '确认密码必须为6位数字,且与密码一致',
                pattern:'^[0-9]{6}$',
                min: 6,
                max: 6,
                validator:function(rule, value, callback){

                  if(value){
                    if(value.length != 6){
                      callback('密码长度必须为6位数字')
                      return;
                    }
                    if(value != getFieldValue('cpasswd')){
                      callback('两次输入密码不一致');
                      return;
                    }
                    callback()
                  }else{
                    callback('确认密码必须为6位数字,且与密码一致');
                  }
                  
                }
              }
            ],validateTrigger: 'onBlur'
          })(<Input type="password" placeholder="请再次输入密码"/>)}
        </FormItem>
        <Row>	
        	<Col span={12} offset={6}>
          		<Button type='primary' size='large' style={{width:200}} onClick={handleOk} loading={loading}>确认修改 </Button>
          	</Col>
          	
              <Col span={12} offset={6}>
               <Button size='large' style={{width:200,marginTop:'1rem'}} onClick={handleClear} loading={loading}>清空表单 </Button>
             </Col>
           
        </Row>
      </form>

	  )
}


resetpwd.propTypes = {
  form: PropTypes.object,
  loading: PropTypes.bool,
  onOk: PropTypes.func
}

export default Form.create()(resetpwd)