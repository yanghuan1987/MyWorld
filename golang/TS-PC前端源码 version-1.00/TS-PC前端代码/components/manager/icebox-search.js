import React, { PropTypes } from 'react'
import { Form,Input, Button, Row, Col } from 'antd'
import SearchGroup from '../ui/search'
const Search = Input.Search;

class IceboxSearch extends React.Component {
	 constructor (props) {
    	super(props);
    	this.state={
    		"iceboxNumber":"",
    	}
  }
	search(v){
		this.props.onSearch(v)
	}
	render(){
		const {onAdd} = this.props;
		  return (
		    <Row gutter={24}>
		      <Col lg={8} md={12} sm={16} xs={24} style={{marginBottom: 16}}>
		      	<Search defaultValue={this.state.iceboxNumber} onSearch={value =>this.search(value)} placeholder="输入冷物柜编号" style={{ width: 200 }}/>					
		      </Col>
		      <Col lg={{offset: 8, span: 8}} md={12} sm={8} xs={24} style={{marginBottom: 16, textAlign: 'right'}}>
		        <Button size='large' type='ghost' onClick={onAdd}>添加</Button>
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
