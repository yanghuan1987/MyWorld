import React, { PropTypes } from 'react'
import { Form,Input, Button, Row, Col ,Select,Modal} from 'antd'
import SearchGroup from '../ui/search'
const Search = Input.Search;
const Option = Select.Option;

class OverdueSearch extends React.Component {
	 constructor (props) {
    	super(props);
    	this.state={
    		"shelfNumber":'',
    		"shelfId":'',
    		status:'-1',
    		keyname:'',
    		nowtime:'-1'
    	}
  }
	search(){		
		this.props.onSearch({
			status: this.state.status ? parseInt(this.state.status) : '-1',
			nowtime: this.state.nowtime == '1' ? parseInt(new Date().getTime()/1000) : '-1',
			keyname: this.state.keyname
		})
	}
	packageSelect(key,value) {
		this.state[key]=value
	}
	SelectChange(key,value) {
//		this.props.onSelectChange(this.state.shelfNumber,value)
	}
	discard(){
		if(this.state.shelfNumber == ''){
				const modal = Modal.error({
		      content: '订单包裹编码不能为空',
		    });
		    	setTimeout(() => modal.destroy(), 2000);
					return;
			}

		this.props.onDiscard(this.state.shelfNumber)
	}
	handleSelectChange(key,value){
		this.state[key]=value
	}
	inputChange(key,e){
		const { value } = e.target;
		this.state[key]=value.replace(/\s+/g,'')
	}
	render(){
		  return (		    
		    <Row gutter={24}>
		    <Row gutter={24}>
		    	<Col lg={24} md={24} sm={24} xs={24}>
		      	<Input onChange={(e)=>this.inputChange('keyname',e)} placeholder="订单包裹编码" style={{width:180}} />	
		      	<Select onChange={(value)=>this.handleSelectChange('status',value)} defaultValue="包裹状态" style={{width:120,marginLeft:10}} >
		      		<Option value="-1">全部</Option>
							<Option value="2">已丢弃</Option>
					    <Option value="0">已上架</Option>					    	
						</Select>
						<Select onChange={(value)=>this.handleSelectChange('nowtime',value)} defaultValue="是否过期" style={{width:120,marginLeft:10}} >
							<Option value="-1">全部</Option>
					   	<Option value="1">已过期</Option>
						</Select>
						<Button type="primary" onClick={this.search.bind(this)} style={{marginLeft:10}}>搜索</Button>				
		      </Col>		      
		     
		    </Row>
		    <Row gutter={24}  style={{marginTop:'15px'}}>
			  	<Col lg={24} md={24} sm={24} xs={24}>
			    	<span>订单包裹编码:</span>
			      	<Input id="shopCodeNumber" defaultValue={this.state.shelfNumber} onChange={(e)=>this.inputChange('shelfNumber',e)} placeholder="扫描或输入包裹编码" style={{ width: 200 }} />					
			    	<Button size='large' type='ghost' type="primary" onClick={this.discard.bind(this)}>确认丢弃</Button>
			    </Col>   
		    </Row>
		    </Row>
		  )
	}

}

OverdueSearch.propTypes = {
  form: PropTypes.object.isRequired,
  onSearch: PropTypes.func,
  field: PropTypes.string,
  keyword: PropTypes.string
}

export default Form.create()(OverdueSearch)
