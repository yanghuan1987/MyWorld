import React, { PropTypes } from 'react'
import { Form,Input,Select, Button, Row, Col, Modal } from 'antd'
import SearchGroup from '../ui/search'
const Search = Input.Search;

class GoodsSearch extends React.Component {

	 constructor (props) {
    	super(props);
    	this.state={
    		number:'',
    		shelfId:'',
    		amount:''
		}
   }
	inputChange(key,e){
		const { value } = e.target;
		this.state[key]=value.replace(/\s+/g,'')
	}
	search(value){
		this.props.onSearch(value)
	}
	refresh(){

		this.props.onRefresh()
	}
	goodsIn(){
			if(this.state.number == ''){
				const modal = Modal.error({
		      content: '商品条码不能为空',
		    });
		    	setTimeout(() => modal.destroy(), 2000);
					return;
			}
			if(this.state.shelfId == ''){
				const modal = Modal.error({
		      content: '货架ID不能为空',
		    });
		    	setTimeout(() => modal.destroy(), 2000);
					return;
			}
			if(this.state.amount == ''){
				const modal = Modal.error({
		      content: '商品数量不能为空',
		    });
		    	setTimeout(() => modal.destroy(), 2000);
					return;
			}
			if(this.state.number.length<13){
				const modal = Modal.error({
		      content: '商品条码不正确',
		    });
		    return
			}
		this.props.onGoodsIn(this.state)
	}
	render(){
		 return (
  	
		    <Row gutter={24}>
				<Row gutter={24}>
		      <Col lg={4} md={12} sm={8} xs={24}>
							<Search	placeholder="输入商品编码"	style={{ width: 200,marginBottom:10}}	onSearch={value => this.search(value)}/>
						</Col>
					<Col lg={4} md={12} sm={8} xs={24}>
					</Col>
				</Row>
				<Row gutter={24}>
					<Col lg={8} md={8} sm={8} xs={24}>
						<span>商品条码(编号+批次)：</span>
						<Input id="shopCodeNumber" placeholder="" onChange={(e)=>this.inputChange('number',e)}/>
					</Col>
					<Col lg={8} md={8} sm={8} xs={24}>
						<span>货架ID(13位)：</span>
						<Input id="shopCodeId" placeholder="" onChange={(e)=>this.inputChange('shelfId',e)}/>
					</Col>
					<Col lg={8} md={8} sm={8} xs={24}>
						<span>商品数量：</span>
						<Input id="shopCodeCount" placeholder="" onChange={(e)=>this.inputChange('amount',e)}/>
					</Col>
				</Row>
				<Row gutter={24}>
					<Col lg={24} md={24} sm={24} xs={24}>
						<div style={{marginTop:10}}>
						<Button type="primary" onClick={()=>{this.goodsIn()}}>确认上架</Button>
    					<Button onClick={()=>{this.refresh()}}>更新商品信息</Button>
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
