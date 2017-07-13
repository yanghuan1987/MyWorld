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

const frezoneOrActive = ({
	loading,
  	onOk,
  	type,
  	showDialog,
    formValue,
    queryInfo,
  	form: {
    getFieldDecorator,
    getFieldsValue,
    setFieldsValue,
    validateFieldsAndScroll,
    resetFields
  }
}) =>{

	function handleOk(params){
		validateFieldsAndScroll((errors, values) => {
	      if (errors) {
	        return
	      }
        if(formValue.status!=0&&formValue.status!=1){
          alert('销卡状态无法进行此操作')
          return
        }

	      onOk({
	      	cardsyscode: values.cardsyscode,
	      	status: values.cardStatus
	      })
    	})
	}
	function showDialogModal(){
		showDialog(formValue);
	}
	function onChange(e){
		type = e.target.value
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
                //pattern:/(^\d{15}$)|(^\d{17}([0-9]|X)$)/
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
            initialValue: formValue.cardsyscode
          })(<Input onBlur={queryCardInfo}/>)}
        </FormItem>
        <FormItem label='姓名' hasFeedback {...formItemLayout}>
          {getFieldDecorator('cname', {
            initialValue:formValue.cname
          })(<Input disabled/>)}
        </FormItem>
         <FormItem label='剩余金额' hasFeedback {...formItemLayout}>
          {getFieldDecorator('initialmoney', {
            initialValue:formValue.initialmoney
          })(<Input disabled/>)}
        </FormItem>	
        <FormItem label='当前状态' hasFeedback {...formItemLayout}>
          {getFieldDecorator('status', {
            initialValue:formValue.status == 0 ? '激活' : formValue.status == 1 ? '冻结' : formValue.status == 2 ? '销卡' : ''
          })(<Input disabled/>)}
        </FormItem>	
        <FormItem label='卡状态' hasFeedback {...formItemLayout}>
         {getFieldDecorator('cardStatus',{
          initialValue:"0",
         })(
            <RadioGroup>
                <Radio value='0'>激活</Radio>
                <Radio value='1'>冻结</Radio>
            </RadioGroup>
          )}
      
        </FormItem>

        <Row>	
        	<Col span={12} offset={6}>
          		<Button type='primary' size='large' style={{width:200}} onClick={handleOk} loading={loading} disabled={!formValue.cphone}>保存内容 </Button>
          	</Col>
          	<Col span={12} offset={6}>
          		<Button size='large' style={{width:200,marginTop:'1rem'}} onClick={showDialogModal} loading={loading}>进行销卡处理 </Button>
          	</Col>
             <Col span={12} offset={6}>
               <Button size='large' style={{width:200,marginTop:'1rem'}} onClick={handleClear} loading={loading}>清空表单 </Button>
             </Col>
        </Row>
      </form>	  	
	)
}
frezoneOrActive.propTypes = {
  form: PropTypes.object,
  loading: PropTypes.bool,
  onOk: PropTypes.func
}

export default Form.create()(frezoneOrActive)