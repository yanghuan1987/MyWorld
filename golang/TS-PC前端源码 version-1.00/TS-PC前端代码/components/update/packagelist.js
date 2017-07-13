import React from 'react'
import { Table, Dropdown, Button, Menu, Icon, Modal, Input, Select } from 'antd'
import { TweenOneGroup } from 'rc-tween-one'
import {dateFormat} from '../../utils'
import styles from './into.css'

const confirm = Modal.confirm;
const Option = Select.Option;
const Search = Input.Search;
class list extends React.Component {
	constructor(props) {
		super(props);
	}

	

	handleMenuClick = (record, e) => {
		const { onDeleteItem, onEditItem } = this.props
		if(e.key === '1') {
			onEditItem(record)
		} else if(e.key === '2') {
			confirm({
				title: '您确定要删除这条记录吗?',
				onOk() {
					onDeleteItem(record.id)
				}
			})
		}
	}

	onEnd = (e) => {
		e.target.style.height = 'auto'
	};

	async pageChange(pagination) {
		await this.props.onPageChange(pagination);
		this.newPage = pagination.current
	}

	
	render() {
		const { loading, dataSource, pagination, onPageChange,timeFormat } = this.props;

		const columns = [{
				title: '序号',
				dataIndex: 'idx',
				key: 'idx',
				render(text, record, index) {
					return (<span>{index+1}</span>)
				}
			},
			{
				title: '订单编号',
				dataIndex: 'order_no',
				key: 'order_no',
			}, {
				title: '配送时间',
				dataIndex: 'deliver_time',
				key: 'deliver_time',
				render(text, record, index) {
						let str=dateFormat(parseInt(text + '000'),'yyyy/MM/dd  hh:mm:ss')
						return (<span>{str}</span>)
				}
			}, {
				title: '客户姓名',
				dataIndex: 'customer',
				key: 'customer',
			}, {
				title: '客户电话',
				dataIndex: 'phone',
				key: 'phone',
			}, {
				title: '收货区域',
				dataIndex: 'zone',
				key: 'zone',
			}, {
				title: '收货详细地址',
				dataIndex: 'address',
				key: 'address',
			}, {
				title: '提货码',
				dataIndex: 'verification',
				key: 'verification',
			}, {
				title: '订单创建时间',
				dataIndex: 'create_time',
				key: 'create_time',
				render(text, record, index) {
						let str=dateFormat(parseInt(text + '000'),'yyyy/MM/dd  hh:mm:ss')
						return (<span>{str}</span>)
				}
			}, {
				title: '自提点编号',
				dataIndex: 'site_id',
				key: 'site_id',
			},
		];
		
		return(
			<div>
	    		<Table columns={columns} dataSource={dataSource} loading={loading} onChange={this.pageChange.bind(this)} pagination={pagination} rowKey={record => record.id} />
	    	</div>
		)
	}
}

export default list