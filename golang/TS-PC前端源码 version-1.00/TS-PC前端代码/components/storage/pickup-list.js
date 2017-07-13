import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Button,Table, Input,Icon} from 'antd'
import styles from './pickup.css'

const Search = Input.Search;
class PickupList extends React.Component{
	constructor(props){
		super(props);
		this.state = { 
			visible: false 
		}
	}
  	render () {
  		const { loading, dataSource,visible, confirmLoading, ModalText,timeFormat } = this.props;
  	
			const columns = [
			{
			  title: '收件人姓名',
			  dataIndex: 'customer',
			  key: 'customer',
			}, {
			  title: '下单时间',
			  dataIndex: 'create_time',
			  key: 'create_time',
			  render(text, record, index) {
			  		let str=timeFormat(parseInt(text + '000'),'yyyy/MM/dd  hh:mm:ss');
			  		return(
			  			<span>{str}</span>
			  		)
			  }
			}, {
			  title: '订单编号',
			  dataIndex: 'order_no',
			  key: 'order_no',
			}, {
			  title: '提货人电话',
			  dataIndex: 'phone',
			  key: 'phone',
			}, {
			  title: '过期时间',
			  dataIndex: 'expire_time',
			  key: 'expire_time',
			  render(text, record, index) {
			  		let str=timeFormat(parseInt(text + '000'),'yyyy/MM/dd  hh:mm:ss');
			  		return(
			  			<span>{str}</span>
			  		)
			  }
			},{
			  title: '订单包裹编号',
			  dataIndex: 'number',
			  key: 'number',
			}, {
			  title: '具体抽屉',
			  dataIndex: 'shelfName',
			  key: 'shelfName',
			}, {
			  title: '所处冷物柜',
			  dataIndex: 'icename',
			  key: 'icename',
			}, {
			  title: '温区',
			  dataIndex: 'temperature',
			  key: 'temperature',
			}, {
			  title: '包裹状态',
			  dataIndex: 'status',
			  key: 'status',
			  render(text, record, index) {

			  	if(text=='-1'){
			  		return (<span>待上架</span>)
			  	}
			  	if(text=='0'){
			  		return (<span>已上架</span>)
			  	}
			  	if(text=='1'){
			  		return (<span>已取件</span>)
			  	}
			  	if(text=='2'){
			  		return (<span>已丢弃</span>)
			  	}
			  }
			}];
	
		return(
			<div className="styles">		
				<Table columns={columns} dataSource={dataSource} loading={loading}  rowKey={record => record.id} />
			</div>		
			
		)
	}
}

export default PickupList