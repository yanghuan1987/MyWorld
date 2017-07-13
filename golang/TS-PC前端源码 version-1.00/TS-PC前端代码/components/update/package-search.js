import React, { PropTypes } from 'react'
import { Form,Input,Select, Button, Row, Col } from 'antd'
import SearchGroup from '../ui/search'
const Search = Input.Search;

class PackageSearch extends React.Component {

	 constructor (props) {
    	super(props);
    	
   }

	search(value){
		this.props.onSearch(value)
	}
	refresh(){
		this.props.onRefresh()
	}
	render(){
		 return (  	
		    <Row gutter={24}>
			    <Col lg={6} md={12} sm={8} xs={24}>
					<Search	placeholder="输入订单号或收货人手机号码"	style={{ width: 200,marginBottom:10}} onSearch={value => this.search(value)} />
				</Col>
				<Col lg={6} md={12} sm={8} xs={24}>
					<Button type="primary" onClick={()=>this.refresh()}>刷新订单</Button>
				</Col>
		    </Row>
		  )
	}
  
}

PackageSearch.propTypes = {
  form: PropTypes.object.isRequired,
  onSearch: PropTypes.func,
  onAdd: PropTypes.func,
  field: PropTypes.string,
  keyword: PropTypes.string
}

export default Form.create()(PackageSearch)
