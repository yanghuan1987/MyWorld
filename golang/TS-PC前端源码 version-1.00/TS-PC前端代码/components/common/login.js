import React, {PropTypes} from 'react'
import { Button, Row, Form, Input } from 'antd'
import config from '../../utils/config'
import styles from './login.less'

const FormItem = Form.Item;
const login = ({
  loginButtonLoading,
  onOk,
  form: {
    getFieldDecorator,
    validateFieldsAndScroll
  }
}) => {

  function handleOk () {
    validateFieldsAndScroll((errors, values) => {
      if (errors) {
        return
      }
      onOk(values)
    })
  }
  return (
    <div className={styles.form}>
      <div className={styles.logo}>
        
        <span>银犁TS</span>
      </div>
      <form>
        <FormItem hasFeedback>
          {getFieldDecorator('account', {
            rules: [
              {
                required: true,
                message: '请填写用户名'
              }
            ]
          })(<Input size='large' onPressEnter={handleOk} placeholder='用户名'  />)}
        </FormItem>
        <FormItem hasFeedback>
          {getFieldDecorator('password', {
            rules: [
              {
                required: true,
                message: '请填写密码'
              }
            ]
          })(<Input size='large' type='password' onPressEnter={handleOk} placeholder='密码'  />)}
        </FormItem>
        <FormItem hasFeedback>
          {getFieldDecorator('code', {
            rules: [
              {
                required: true,
                message: '自提点编号不能为空'
              }
            ]
          })(<Input size='large' onPressEnter={handleOk} placeholder='自提点编号'  />)}
        </FormItem>
        <Row>
          <Button type='primary' size='large' onClick={handleOk} loading={loginButtonLoading}>
            登录
          </Button>
        </Row>
        <p>
          <span>© 成都银犁</span>
        </p>
      </form>
    </div>
  )
}

login.propTypes = {
  form: PropTypes.object,
  loginButtonLoading: PropTypes.bool,
  onOk: PropTypes.func
}

export default Form.create()(login)
