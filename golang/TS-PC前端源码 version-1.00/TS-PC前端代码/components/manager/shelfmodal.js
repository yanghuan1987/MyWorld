import React, { PropTypes } from 'react'
import { Form, Input, InputNumber, Radio, Modal, Select } from 'antd'
const FormItem = Form.Item;
const Option = Select.Option

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
  list,
	onCancel,
  setFieldsValue,
	form: {
		getFieldDecorator,
		validateFields,
		getFieldsValue,
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
      	data.Status = parseInt(data.Status);
			onOk(data)
		})
	}

  function handleSelectChange (value){
    setFieldsValue();
  };

  function getOptions(){
    return list.map(function(item){
      return <Option key={item.id} value={item.id}>{item.number}</Option>
    })
  }

	const modalOpts = {
		title: `${type === 'create' ? '新建货架' : '修改货架'}`,
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
            initialValue: item.number,
            rules: [
              {
                required: true,
                message: '编号未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label='所属冷物柜ID' hasFeedback {...formItemLayout}>
          {getFieldDecorator('IceboxId', {
            initialValue: item.icebox_id,
            rules: [
              {
                required: true,
                message: '所属冷物柜ID未填写'
              }
            ]
          })(
            <Select onChange={(value)=>{handleSelectChange(value)}} >
               {getOptions()}
            </Select>
          )}
        </FormItem>

        <FormItem label='温区' hasFeedback {...formItemLayout}>
          {getFieldDecorator('Temperature', {
            initialValue: item.temperature,
            rules: [
              {
                required: true,
                message: '温区未填写'
              }
            ]
          })(<Input />)}
        </FormItem>

        <FormItem {...formItemLayout}
          label="状态"
          hasFeedback
        >
          {getFieldDecorator('Status', {
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