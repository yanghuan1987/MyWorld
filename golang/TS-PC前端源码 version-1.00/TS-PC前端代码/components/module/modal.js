import React, { PropTypes } from 'react'
import { Form,Select, Input, InputNumber, Radio, Modal } from 'antd'
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
    title: `${type === 'create' ? '新建权限' : '修改权限'}`,
    visible,
    maskClosable:false,
    onOk: handleOk,
    onCancel,
    wrapClassName: 'vertical-center-modal'
  }

  return (
    <Modal {...modalOpts}>
      <Form horizontal>
        <FormItem label='名称：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('name', {
            initialValue: item.name,
            rules: [
              {
                required: true,
                message: '名称未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='路由：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('path', {
            initialValue: item.path,
            rules: [
              {
                required: true,
                message: '路由未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        
        <FormItem label='类型' hasFeedback {...formItemLayout}>
          {getFieldDecorator('type', {
            initialValue:item.type==2?"2":"1",
            rules: [
              {
                required: true,
                message: '请选择类型'
              }
            ]
          })(
            <Select>
              <Select.Option value="1">URL</Select.Option>
              <Select.Option value="2">BUTTON</Select.Option>
            </Select>
          )}
        </FormItem>
        <FormItem label='key：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('moduleKey', {
            initialValue: item.moduleKey,
            rules: [
              {
                required: true,
                message: 'key未填写'
              }
            ]
          })(<Input />)}
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
