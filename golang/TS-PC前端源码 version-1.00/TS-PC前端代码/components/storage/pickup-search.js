import React, { PropTypes } from 'react'
import { Form,Input, Button, Row, Col,Modal } from 'antd'
import SearchGroup from '../ui/search'
const Search = Input.Search;
const FormItem = Form.Item;



class IceboxSearch extends React.Component {
	 constructor (props) {
    	super(props);
    	this.state={
    		code: '',
    	}
  	}
	onChange(key,e){
		const { value } = e.target;
		this.state[key] = value;
	}
	search(){
			if(this.state.code == ''){
				const modal = Modal.error({
		      content: '提货码不能为空',
		    });
		    	setTimeout(() => modal.destroy(), 2000);
					return;
			}

		const code =this.state.code
		this.props.onSearch(code.trim())
	}
	reset(){
		this.props.form.resetFields();
		this.props.onClear()
	}
	pickupEnd(){
		let _this=this
		this.props.OnpickupEnd(function(){
			_this.props.form.resetFields();
			_this.setState({
				code: ''
			})
		});
	}
	render(){
	  const {code} = this.props;
	  const { getFieldDecorator } = this.props.form;
	  const formItemLayout = {
			labelCol: { span: 5 },
			wrapperCol: { span: 19 }
		};
	  return (
	    <Row gutter={24}>
		    <Col lg={6} md={12} sm={16} xs={24}  id="pick-code-input"  style={{marginBottom: 16}}>
		      	<FormItem {...formItemLayout} label={`提货码:`}>
		            {getFieldDecorator(`code`)(
			      		<Input onChange={(e)=>this.onChange('code',e)} placeholder="点击输入提货码" style={{ width: 200 }}/>	
		            )}
		         </FormItem>
		    </Col>
		    <Col lg={6} md={12} sm={16} xs={24} style={{marginBottom: 16}}>
		      	<Button size='large'  type="primary" onClick={this.reset.bind(this)}>清除</Button>
		      	<Button size='large'  onClick={this.search.bind(this)}  type="primary" style={{marginLeft:'5px'}}>检索</Button>
		    </Col>
		    <Col lg={6} md={12} sm={8} xs={24} style={{marginBottom: 16, textAlign: 'right'}}>
		        <Button size='large' type="primary" onClick={()=>{this.pickupEnd()}}>取件完成</Button>
		    </Col>
	    </Row>
	  )
	}

}

IceboxSearch.propTypes = {
  form: PropTypes.object.isRequired,
  onSearch: PropTypes.func,
  onAdd: PropTypes.func,
  field: PropTypes.string,
  keyword: PropTypes.string
}

export default Form.create()(IceboxSearch)
