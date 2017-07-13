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
			onOk(data, type)
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
        <FormItem label='站点编号：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('code', {
            initialValue: item.code,
            rules: [
              {
                required: true,
                message: '站点编号未填写'
              }
            ]
          })(<Input />)}
        </FormItem>

        <FormItem label='经度：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('lng', {
            initialValue: item.lng,
            rules: [
              {
                required: true,
                message: '经度未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='纬度：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('lat', {
            initialValue: item.lat,
            rules: [
              {
                required: true,
                message: '纬度未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='排序：' hasFeedback {...formItemLayout}>
          {getFieldDecorator('sort', {
            initialValue: item.sort
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