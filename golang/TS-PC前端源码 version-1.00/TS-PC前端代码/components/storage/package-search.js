import React, { PropTypes } from 'react'
import { Form,Input,Select, Button, Row, Col,Modal } from 'antd'
import SearchGroup from '../ui/search'
const Search = Input.Search;
const Option = Select.Option;

class GoodsSearch extends React.Component {
	 constructor (props) {
    	super(props);
    	this.state={
			status: '',
			nowtime: '',
			keyname: '',
			codeNum: '',
			shelfId:'',
			replace:''
		}
   }
	inputChange(key,e){
		const { value } = e.target;
		this.state[key]=value
	}
	search(){
		this.props.onSearch({
			status: this.state.status ? parseInt(this.state.status) : '-1',
			nowtime: this.state.nowtime == 1 ? parseInt(new Date().getTime()/1000) : '-1',
			keyname: this.state.keyname
		})
	}
	refresh(){
		this.props.onRefresh()
	}
	packageIn(){	
		if(this.state.codeNum == ''){
			const modal = Modal.error({
	      content: '订单包裹编码不能为空',
	    });
	    	setTimeout(() => modal.destroy(), 2000);
				return;
		}
	
		if(this.state.shelfId == ''){
			const modal  = Modal.error({
				content:'货架ID不能为空'
			})
				setTimeout(()=>modal.destroy(),2000);
				return
		}
		
		let d=this.state.codeNum.split("-");
		if(d.length!=3){
			const modal = Modal.error({
	      content: '订单包裹编码格式不正确',
	    });
	    	setTimeout(() => modal.destroy(), 2000);
				return;
		}
		console.log(123999999)
		this.props.onPackageIn(this.state)
	}
	handleSelectChange(key,value) {	  
	  this.state[key]=value.replace(/\s+/g,'')
	}
		render(){

		 return (  	
		    <Row gutter={24}>
				<Row gutter={24}>
		        <Col lg={24} md={24} sm={24} xs={24}>
					<Search	placeholder="扫描或输入包裹编码" style={{ width: 200,marginBottom:10}} 
								onChange={value => {this.inputChange('keyname',value)}} onSearch={value => this.search(value)} />

					<Select onChange={(value)=>this.handleSelectChange('status',value)} defaultValue="包裹状态" style={{width:120,marginLeft:10}}>
						<option value="-1">全部</option>
						<option value="0">已上架</option>
						<option value="1">已取件</option>
						<option value="2">已丢弃</option>
					</Select>						
					<Select onChange={(value)=>this.handleSelectChange('nowtime',value)} defaultValue="是否过期" style={{width:120,marginLeft:10}}>
						<option value="-1">全部</option>
						<option value="1">已过期</option>
					</Select>						
					<Button type="primary" onClick={this.search.bind(this)} style={{width:120,marginLeft:10}}>搜索</Button>
		      	</Col>
				</Row>
				<Row gutter={24}>
					<Col lg={8} md={8} sm={8} xs={24}>
						<span>订单包裹编码(订单号-温区-序号)：</span>
						<Input id="shopCodeNumber" placeholder="扫描或输入包裹编码" onChange={(e)=>this.inputChange('codeNum',e)}/>
					</Col>
					<Col lg={8} md={8} sm={8} xs={24}>
						<span>货架ID：</span>
						<Input id="shopCodeId" placeholder="扫描或输入货架ID" onChange={(e)=>this.inputChange('shelfId',e)}/>
					</Col>
				</Row>
				<Row gutter={24}>
					<Col lg={24} md={24} sm={24} xs={24}>
						<div style={{marginTop:10}}>
						<Button type="primary" onClick={()=>{this.packageIn()}}>确认入柜</Button>
						</div>
					</Col>
				</Row>
		    </Row>
			
		  )
	}
  
}

GoodsSearch.propTypes = {
  form: PropTypes.object.isRequired,
  onSearch: PropTypes.func,
  onAdd: PropTypes.func,
  field: PropTypes.string,
  keyword: PropTypes.string
}

export default Form.create()(GoodsSearch)
