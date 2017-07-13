import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Card,Row,Col,Button, Table, Input,Icon,Modal,InputNumber} from 'antd'
import styles from './scan-sales.css'

const Search = Input.Search;
const QRCode = require('qrcode.react');
class Scan extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			"barcode":""
		}
	}
	onChange(key,e){
		const { value } = e.target;
		if(value == '' || value == undefined){
			const value = 1;
		}
		this.state[key]=value
	}
	onAdd(){
		if(this.state.goodsCode==""){
			alert("条形码不能为空")
			return;
		}
		document.getElementById("goods-code").value=""
		this.props.onAdd(this.state)

	}
	onClear(){
		document.getElementById("goods-code").value=""
		this.setState({
			"barcode":""
		})
		this.props.onClear()
	}
	onAmountChange(item,value){
		if(value == '' || value == undefined){
			value = 1;
		}
		value = Math.floor(value);
		item.amount=value
		this.props.onAmountChange(item)
	}
	onDelete(item){
		item.amount=0
		this.props.onAmountChange(item)
	}
	onPayment(type){

		if(this.props.dataSource.amount==0){
			alert("未添加商品")
			return
		}

		this.props.onPayment(type)
	}
	cardList(){
		const {list}=this.props.dataSource
		const _this=this

		return list.map(function(item){
			return(
				<Col span="12" key={item.id}>
					<Card title={item.name} bordered={true}>
						<a onClick={()=>_this.onDelete(item)} className={styles.close}>X</a>
						<h3 style={{marginBottom:10}}>商品单价：{item.price}元</h3>
						<div>
							库存量：{item.totalAmount}
						</div>
						<br/>
						<div>
							数量：
							<InputNumber size="large" min={1} defaultValue={1} max={item.totalAmount} value={item.amount} onChange={(v)=>_this.onAmountChange(item,v)} />
						</div>
					</Card>
					
				</Col>

			)
		})
	}
	render(){
		const {dataSource}=this.props
		return(
			<Row gutter={24}>
					<Col lg={18} md={18} sm={18} xs={24} style={{marginBottom:20}}>
						<Row gutter={24}>
							<Col lg={12} md={12} sm={12} xs={24} style={{marginBottom:20}}>
								<Input id="goods-code" placeholder="商品条码" onChange={(e)=>this.onChange('barcode',e)}/>
							</Col>
							<Col lg={12} md={12} sm={12} xs={24} style={{marginBottom:20}}>
								<Button type="primary" style={{marginRight:5}} onClick={()=>this.onAdd()}>添加</Button>
								<Button type="danger" onClick={()=>this.onClear()}>重置清单</Button>
							</Col>
						</Row>
						<Row gutter={24} style={{margin:-10}}>
							{this.cardList()}
						</Row>
			       </Col>
			       <Col lg={6} md={6} sm={6} xs={24} style={{marginBottom:20}}>
				   	<Button type="primary" style={{width:'100%',marginBottom:10}} onClick={()=>this.onPayment('wxpay')}>使用微信二维码支付</Button><br/>
						<Button type="primary" style={{width:'100%',marginBottom:10}} onClick={()=>this.onPayment('cardpay')}>使用银犁卡支付</Button>
					<p style={{fontSize:16}}>总数量：{dataSource.amount}</p>
					<p style={{fontSize:16}}>总金额：<font style={{color:'red'}}>{dataSource.totalPrice.toFixed(2)}</font></p>
				   </Col>
			</Row>
		)	
	}

}

export default Scan