import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Form, Input, Button,Radio,Row,Col} from 'antd'
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
const recharge = ({
	loading,
	onOk,
	free,
	queryInfo,
	formValue,
	form: {
	    getFieldDecorator,
	    getFieldsValue,
	    validateFieldsAndScroll,
	    resetFields
	}
}) =>{

	function handleOk(params){
		validateFieldsAndScroll((errors, values) => {
	      if (errors) {
	        return
	      }

	      onOk({
	      	cardsyscode: values.cardsyscode,
	      	free: values.free
	      },function(){
	      		resetFields()
	      })
	    })
	}
	function handleCheckValue(){
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

	return (
	  	<form>
	  	<FormItem label='手机号' hasFeedback {...formItemLayout}>
          {getFieldDecorator('cphone', {
            initialValue:formValue.cphone
          })(<Input disabled/>)}
        </FormItem>
        <FormItem label='身份证' hasFeedback {...formItemLayout}>
          {getFieldDecorator('idno', {
          	initialValue:formValue.idno,
            rules: [
              {
                required: true,
                message: '请输入合法的身份证',
                pattern: /(^\d{15}$)|(^\d{17}([0-9]|X)$)/
              }
            ],validateTrigger: 'onBlur'
          })(<Input onBlur={handleCheckValue} />)}
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
          })(<Input onBlur={handleCheckValue}/>)}
        </FormItem>
        <FormItem label='卡上余额' hasFeedback {...formItemLayout}>
          {getFieldDecorator('initialmoney',{
            initialValue:formValue.initialmoney
          })(<Input disabled/>)}
        </FormItem>
        <FormItem label='当前状态' hasFeedback {...formItemLayout}>
          {getFieldDecorator('status',{
            initialValue:formValue.status == 0 ? '激活' : formValue.status == 1 ? '冻结' : formValue.status == 2 ? '销卡': ''
          })(<Input disabled/>)}
        </FormItem>
        	
        <FormItem label='充值金额' hasFeedback {...formItemLayout}>
         {getFieldDecorator('free',{
         	initialValue:"50",
         })(
            <RadioGroup>
              <Radio value="50">50</Radio>
              <Radio value="100">100</Radio>
              <Radio value="200">200</Radio>
              <Radio value="300">300</Radio>
              <Radio value="400">400</Radio>
              <Radio value="500">500</Radio>
            </RadioGroup>
          )}
			
        </FormItem>
        <Row>	
        	<Col span={12} offset={6}>
          		<Button type='primary' size='large' style={{width:200}} onClick={handleOk} loading={loading} disabled={formValue.status == 2}>确认充值 </Button>
          	</Col>
          	 <Col span={12} offset={6}>
               <Button size='large' style={{width:200,marginTop:'1rem'}} onClick={handleClear} loading={loading}>清空表单 </Button>
             </Col>
        </Row>
      </form>
	  )
}
recharge.propTypes = {
  form: PropTypes.object,
  loading: PropTypes.bool,
  onOk: PropTypes.func
}

export default Form.create()(recharge)