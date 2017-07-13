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
        <FormItem label='编号' hasFeedback {...formItemLayout}>
          {getFieldDecorator('Number', {
            initialValue: item.Number,
            rules: [
              {
                required: true,
                message: '编号未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='名称' hasFeedback {...formItemLayout}>
          {getFieldDecorator('Name', {
            initialValue: item.Name,
            rules: [
              {
                required: true,
                message: '名称未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='地址' hasFeedback {...formItemLayout}>
          {getFieldDecorator('shelfAmount', {
            initialValue: item.shelfAmount,
            rules: [
              {
                required: true,
                message: '地址未填写'
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