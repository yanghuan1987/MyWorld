import React, { PropTypes } from 'react'
import { Form, Input,Select, InputNumber, Radio, Modal } from 'antd'
const FormItem = Form.Item

const formItemLayout = {
  labelCol: {
    span: 6
  },
  wrapperCol: {
    span: 14
  }
}

const modal = ({
  visible,
  type,
  roles,
  item = {},
  onOk,
  onCancel,
  form: {
    getFieldDecorator,
    validateFields,
    getFieldsValue
  }
}) => {

  function handleOk () {
    validateFields((errors) => {
      if (errors) {
        return
      }
      const data = {
        ...getFieldsValue(),
        key: item.key
      }
      onOk(data)
    })
  }

  const modalOpts = {
    title: `${type === 'create' ? '新建系统用户' : '修改系统用户'}`,
    visible,
    onOk: handleOk,
    onCancel,
    wrapClassName: 'vertical-center-modal'
  }
  const children = []

  const rolesLength=roles.length
  for (let i = 0; i < rolesLength; i++) {
    const obj=roles[i]
    children.push(<Select.Option key={obj.id}>{obj.name}</Select.Option>);
  }
 
  function handleChange(value){

  }
  return (
    <Modal {...modalOpts}>
      <Form horizontal>
        <FormItem label='姓名：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('name', {
            initialValue: item.name,
            rules: [
              {
                required: true,
                message: '姓名未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='用户名：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('account', {
            initialValue: item.account,
            rules: [
              {
                required: true,
                message: '用户名未填写'
              }
            ]
          })(<Input disabled={type=== 'create'?false:true}/>)}
        </FormItem>
        
        <FormItem label='密码：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('password', {
            rules: [
              {
                required: type=== 'create'?true:false,
                message: '密码未填写'
              }
            ]
          })(<Input type="password" />)}
        </FormItem>
        <FormItem label='选择角色：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('roleId', {
            initialValue: item.roles!=undefined&&item.roles.length>0?String(item.roles[0].id):''
            //initialValue: ["7","8"]
          })(
            <Select
              style={{ width: '100%' }}
              placeholder="请选择角色"
              onChange={handleChange}
            >
              {children}
            </Select>
          )}
        </FormItem>
      </Form>
    </Modal>
  )
}

modal.propTypes = {
  visible: PropTypes.any,
  form: PropTypes.object,
  item: PropTypes.object,
  onOk: PropTypes.func,
  onCancel: PropTypes.func
}

export default Form.create()(modal)
