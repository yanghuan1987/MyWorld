import React, { PropTypes } from 'react'
import { Form, Input, InputNumber, Radio, Modal, Select } from 'antd'
const FormItem = Form.Item;

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
	function handleOk() {
		validateFields((errors) => {
			if(errors) {
				return
			}
			const data = {
				...getFieldsValue(),
				key: item.key
			};
			onOk(data)
		})
	}

	const modalOpts = {
		title: `${type === 'create' ? '新建站点' : '修改站点'}`,
		visible,
		onOk: handleOk,
		onCancel,
		wrapClassName: 'vertical-center-modal'
	};

	return(
		<Modal {...modalOpts}>
      <Form horizontal>
        <FormItem
          {...formItemLayout}
          label="站点类型："
          hasFeedback
        >
          {getFieldDecorator('type', {
            initialValue: item.type>0?item.type+"":"1",
            rules: [
              { required: true, message: '请选择站点类型' },
            ],
          })(
            <Select>
              <Select.Option value="1">国控站</Select.Option>
              <Select.Option value="2">省控站</Select.Option>
              <Select.Option value="3">市控站</Select.Option>
            </Select>
          )}
        </FormItem>
        <FormItem label='序号：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('id', {
            initialValue: item.id,
            rules: [
              {
                required: true,
                message: '序号未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='订单包裹编码：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('number', {
            initialValue: item.number,
            rules: [
              {
                required: true,
                message: '订单包裹编码未填写'
              }
            ]
          })(<Input />)}
        </FormItem>

        <FormItem label='货架ID：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('shelf_id', {
            initialValue: item.shelf_id,
            rules: [
              {
                required: true,
                message: '货架ID未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='货架编号：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('shelf_number', {
            initialValue: item.shelf_number,
            rules: [
              {
                required: true,
                message: '货架编号未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='货架温区：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('temperature', {
            initialValue: item.temperature,
            rules: [
              {
                required: true,
                message: '货架温区未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='上架人员：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('in_operator', {
            initialValue: item.in_operator,
            rules: [
              {
                required: true,
                message: '上架人员未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='取货人员：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('out_operator', {
            initialValue: item.out_operator,
            rules: [
              {
                required: true,
                message: '取货人员未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='取件时间：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('out_time', {
            initialValue: item.out_time,
            rules: [
              {
                required: true,
                message: '取件时间未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='上架时间：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('in_time', {
            initialValue: item.in_time,
            rules: [
              {
                required: true,
                message: '上架时间未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='过期时间：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('expire_time', {
            initialValue: item.expire_time,
            rules: [
              {
                required: true,
                message: '过期时间未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='包裹状态：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('status', {
            initialValue: item.status,
            rules: [
              {
                required: true,
                message: '包裹状态未填写'
              }
            ]
          })(<Input />)}
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