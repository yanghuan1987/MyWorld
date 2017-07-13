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
		      data.Status=parseInt(data.status)
      		data.ShelfAmount = parseInt(data.ShelfAmount);
				onOk(data)
			})
	}

	const modalOpts = {
		title: `${type === 'create' ? '新建冷物柜' : '修改冷物柜'}`,
		visible,
		onOk: handleOk,
		onCancel,
		wrapClassName: 'vertical-center-modal'
	};

	return(
	<Modal {...modalOpts}>
      <Form>      
        <FormItem label='编号' hasFeedback {...formItemLayout}>  
          {getFieldDecorator('Number', {
            initialValue: item.number,
            rules: [
              {
                required: true,
                message: '编号未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='名称' hasFeedback {...formItemLayout}>
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
        <FormItem style={{display: 'none'}} label='所属站点编号' hasFeedback {...formItemLayout}>
          {getFieldDecorator('SiteId', {
            initialValue: item.site_id
          })(<Input />)}
        </FormItem>
        <FormItem label='货架数量' hasFeedback {...formItemLayout}>
          {getFieldDecorator('ShelfAmount', {
            initialValue: item.shelf_amount,
            rules: [
              {
                required: true,
                message: '货架数量必须是正整数',
                pattern: /^\+?[1-9][0-9]*$/
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="状态"
          hasFeedback
        >
          {getFieldDecorator('status', {
            initialValue: item.status, 
            rules: [
              { required: true, message: '请选择状态' },
            ],
          })(
            <Select>
              <Select.Option value="0">未运行</Select.Option>
              <Select.Option value="1">运行中</Select.Option>
            </Select>
          )}
        </FormItem>
       
        <FormItem label='温区1' hasFeedback {...formItemLayout}>
          {getFieldDecorator('Temperature1', {
            initialValue: item.temperature1,
            rules: [
              {
                required: true,
                message: '温区1未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='温区2' hasFeedback {...formItemLayout}>
          {getFieldDecorator('Temperature2', {
            initialValue: item.temperature2,
            rules: [
              {
                required: true,
                message: '温区2未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='温区3' hasFeedback {...formItemLayout}>
          {getFieldDecorator('Temperature3', {
            initialValue: item.temperature3,
            rules: [
              {
                required: true,
                message: '温区3未填写'
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