import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Form, Input, Button,Radio,Checkbox,Row,Col,Icon,Modal } from 'antd'
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
const openlist = ({
	loading,
  onOk,
  type,
  form: {
    getFieldValue,
    getFieldDecorator,
    validateFieldsAndScroll,
    resetFields,
  }
}) =>{
	function handleOk(params){
    let _this=this
		validateFieldsAndScroll((errors, values) => {
      if (errors) {
        return
      }
      onOk(values,type,function(){
        resetFields();
      })
    })
	}
	function onChange(e){
		type = e.target.value
	} 

  function handleCancel(){

  }
	return (
	  	<form>
	  	<FormItem label='银犁卡号' hasFeedback {...formItemLayout} >
          {getFieldDecorator('cardsyscode', {
            rules: [
              {
                required: true,
                message: '请输入10位正确的银犁卡号',
                min:10,
                max:10
              }
            ],validateTrigger: 'onBlur'
          })(<Input placeholder="请将银犁卡放到数据采集器上或手动输入"  />)}
        </FormItem>
       <FormItem label='卡号信息' hasFeedback {...formItemLayout}>
          {getFieldDecorator('Cname', {
            rules: [
              {
                required: true,
                message: '卡号信息未填写'
              }
            ]
          })(<Input placeholder="开卡人名称" />)}
        </FormItem>
        <FormItem label='联系电话' hasFeedback {...formItemLayout}>
          {getFieldDecorator('Cphone', {
            rules: [
              {
                required: true,
                message: '请输入合法的手机号码 ',
                pattern:'^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$'
              }
            ],validateTrigger: 'onBlur'
          })(<Input placeholder="银犁卡开卡人电话"/>)}
        </FormItem>
        <FormItem label='身份证' hasFeedback {...formItemLayout}>
          {getFieldDecorator('Idno', {
            rules: [
              {
                required: true,
                message: '请输入合法的身份证',
                pattern: /(^\d{15}$)|(^\d{17}([0-9]|X)$)/
              }
            ],validateTrigger: 'onBlur'
          })(<Input placeholder="开卡人身份证，一张身份证开一张卡"/>)}
        </FormItem>
        <FormItem label='设置密码' hasFeedback {...formItemLayout}>
          {getFieldDecorator('Cpasswd', {
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
                validator(rule, value, callback){
                  if(value==undefined||value.length != 6){
                    callback('密码长度必须为6位数字')
                    return;
                  }
                  if(value != getFieldValue('Cpasswd')){
                    callback('两次输入密码不一致');
                    return;
                  }
                  callback();
                }
              }
            ],validateTrigger: 'onBlur'
          })(<Input type="password" placeholder="请再次输入密码"/>)}
        </FormItem>
        <Row>	
        	<Col span={12} offset={6}>
          		<Button type='primary' size='large' style={{width:200}} onClick={handleOk} loading={loading}>确认开卡 </Button>
          	</Col>
          	{
            //   <Col span={12} offset={6}>
            //   <Button size='large' style={{width:200,marginTop:'1rem'}} onClick={handleCancel} loading={loading}>取消 </Button>
            // </Col>
            }
        </Row>
      </form>
	  	
	)
}


openlist.propTypes = {
  form: PropTypes.object,
  loading: PropTypes.bool,
  onOk: PropTypes.func
}

export default Form.create()(openlist)