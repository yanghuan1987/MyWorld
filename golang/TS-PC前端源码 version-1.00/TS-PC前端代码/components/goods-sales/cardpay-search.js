import React, { PropTypes } from 'react'
import { Form,Input,Select, Button, Row, Col } from 'antd'
import SearchGroup from '../ui/search'
const Search = Input.Search;

class ShelfSearch extends React.Component {

	 constructor (props) {
    	super(props);
    	this.state={
    		"keyname":"",
    		"status":'',
    	}
   }

	
	onChange(key,e){
		 const { value } = e.target;

		this.state[key]=value
	}
	handleSelectChange(key,value) {
	  
	  this.state[key]=value
	}
	search(){
		this.props.onSearch(this.state)
	}
	render(){
		const {onAdd}=this.props
		 return (
		    <Row gutter={24}>
		    	<Col lg={24} md={24} sm={24} xs={24} style={{marginBottom:20}}>
			       	<Input onChange={(e)=>this.onChange('keyname',e)} placeholder="订单号" style={{width:180}}/>						 
{			    //     <Select onChange={(value)=>this.handleSelectChange('status',value)} defaultValue="-1" style={{width:120,marginLeft:'10px'}} >
							// 	<Option value="-1">全部</Option>
							// 	<Option value="0">未支付</Option>
							// 	<Option value="1">已支付</Option>
							// </Select>
}
					<Button type="primary" onClick={this.search.bind(this)} style={{marginLeft:'10px'}}>搜索</Button>
				</Col>
		    </Row>
		  )
	}
  
}

ShelfSearch.propTypes = {
  form: PropTypes.object.isRequired,
  onSearch: PropTypes.func,
  onAdd: PropTypes.func,
  field: PropTypes.string,
  keyword: PropTypes.string
}

export default Form.create()(ShelfSearch)
