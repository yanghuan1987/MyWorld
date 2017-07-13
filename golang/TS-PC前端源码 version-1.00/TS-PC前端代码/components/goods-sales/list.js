import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Button, Table, Input,Icon,Modal,InputNumber} from 'antd'
import styles from './scan-sales.css'

const Search = Input.Search;
const QRCode = require('qrcode.react');
class Scan extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			 modal1Visible: false,
			 boxVisible: false,
			 inputNumber: 1,
			 code: ''
		}
	}
	setModal1Visible(modal1Visible) {
	   this.setState({ modal1Visible });
	}
	handHide(){
		this.setState({
			boxVisible: false
		});
	}
	handShow(){
		const { onQueryProductInfo } = this.props
		onQueryProductInfo(this.state.code)
		this.setState({
			boxVisible: true
		});
	}
	inputNumberAdd(){
		let num = this.state.inputNumber + 1;
		this.setState({
			inputNumber: num
		});
	}
	inputNumberSub(){
		let num = this.state.inputNumber - 1;
		if(num == 0){
			num = 1;
		}
		this.setState({
			inputNumber: num
		});
	}
	changeCode(e){
		this.setState({
			code: e.target.value
		});
	}
	render(){
		let _class = this.state.boxVisible?styles.info:styles.none;
		let _class_foot = this.state.boxVisible?'':styles.none;
		const {productInfo} = this.props
		return(
			<div className="example-input">
				<div className={styles.leftadd}>
					<Button type="primary">充值清单</Button>
					<Input onChange={this.changeCode.bind(this)} value={this.state.code} size="large" placeholder="商品编码" style={{width:'200px',marginLeft:'20%'}}/>
					<Button onClick={this.handShow.bind(this)}>添加</Button>
					<div className={_class}>
						<p>{this.state.code} <span className={styles.close} onClick={this.handHide.bind(this)}>X</span></p>
						<p >{productInfo.productName}</p>
						<p style={{textAlign:'center'}}>
							<span className={styles.cols}>{this.state.inputNumber * productInfo.price}</span>
							<span className={styles.inputNum}>
								<Button type="primary" onClick={this.inputNumberSub.bind(this)}>-</Button>
								<InputNumber value={this.state.inputNumber}/>
								<Button type="primary" onClick={this.inputNumberAdd.bind(this)}>+</Button>
							</span>
						</p>
						<p>{productInfo.price}元/{productInfo.company}{productInfo.unit}</p>
					</div>
				</div>	
				<div className={styles.payright +" "+_class_foot}>
					<Button type="primary"  onClick={() => this.setModal1Visible(true)}>使用微信二维码支付</Button>
					<Button type="primary">使用银犁卡支付</Button>
					<p><span>总数量:</span><a>{this.state.inputNumber}</a></p>
					<p><span>总金额:</span><a>{this.state.inputNumber * productInfo.price}</a></p>
				</div>
				
		        <Modal
		          title="微信支付二维码"
		          visible={this.state.modal1Visible}
		          onOk={() => this.setModal1Visible(false)}
		          onCancel={() => this.setModal1Visible(false)}
		        >
		          <p style={{textAlign:'center'}}> <QRCode value="http://facebook.github.io/react/" /></p>
				  <ul className={styles.erweimapay} >
				  	<li >
				  		<span>订单号:</span>
				  		<Input style={{width:300}}/>
				  	</li>
				  	<li >
				  		<span>订单价格:</span>
				  		<Input style={{width:300}}/>
				  	</li>
				  	<li >
				  		<span>订单支付结果:</span>
				  		<Input style={{width:300}}/>
				  	</li>
				  </ul>
		        </Modal>
			</div>
		)	
	}

}

export default Scan