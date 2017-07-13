import React, { PropTypes } from 'react'
import { Form,Input,Select, Button, Row, Col } from 'antd'
import SearchGroup from '../ui/search'
const Search = Input.Search;
const Option = Select.Option;

class ShelfSearch extends React.Component {

	 constructor (props) {
    	super(props);
    	this.state={
    		"shelfNumber":"",
    		"iceboxId": "-1",
    	}
   }
	iceboxSelect(){		
		console.log(this.props.iceboxList)
		return this.props.iceboxList.map(function(item){
			 return (
			 	<Option value={item.id} key={item.id}>{item.number}</Option>
			 )
		})
	}
	onChange(key,e){
		const { value } = e.target;
		this.state[key]=value 
	}
	handleSelectChange(key,value) {	 
	  this.state[key]=value
	}
	search(){
		const shelfNumber=this.state.shelfNumber
		const iceboxId=this.state.iceboxId
		this.props.onSearch(shelfNumber,iceboxId)
	}
	render(){
		const {onAdd}=this.props
		 return (
  	
		    <Row gutter={24}>
	      		<Col lg={24} md={24} sm={24} xs={24}>
		       		<Input defaultValue={this.state.shelfNumber} onChange={(e)=>this.onChange('shelfNumber',e)} placeholder="货架编号" style={{width:180}}/>
		       		<Select onChange={(value)=>this.handleSelectChange('iceboxId',value)} defaultValue="-1" style={{width:120,marginLeft:'10px'}} >
								<Option value="-1" key="-1">全部</Option>
								{this.iceboxSelect()}
							</Select>
					<Button type="primary" onClick={this.search.bind(this)} style={{marginLeft:10}}>搜索</Button>
		       	<Button size='large' type='ghost' onClick={onAdd} style={{marginBottom: 16, float: 'right'}}>添加</Button>
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
