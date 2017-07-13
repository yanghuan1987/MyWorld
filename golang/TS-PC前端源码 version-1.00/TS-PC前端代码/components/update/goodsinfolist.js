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
		
	const { current } = this.props.pagination;
	this.currentPage = current;
	this.newPage = current
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
	
	handelSearch(){
		const { onQuerySelected } = this.props
		onQuerySelected({
			state: this.queryState,
			expire_time: this.queryExpireTime
		},{current: 1,pageSize: 10})
	}
	handleChange(value){
		this.querySate = value;
	}
	handleChange2(value){
		this.queryExpireTime = value;
	}	
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
			title: '商品编码',
			dataIndex: 'sales_id',
			key: 'sales_id',
		}, {
			title: '批次号',
			dataIndex: 'lot',
			key: 'lot',
		},{
			title: '货架id',
			dataIndex: 'shelf_id',
			key: 'shelf_id',
		},{
			title: '上架人员',
			dataIndex: 'in_operator',
			key: 'in_operator',
		}, {
			title: '上架时间',
			dataIndex: 'in_time',
			key: 'in_time',
			render(text, record, index) {
						let str=dateFormat(parseInt(text + '000'),'yyyy/MM/dd  hh:mm:ss')
						return (<span>{str}</span>)
				}
		}, {
			title: '商品数量',
			dataIndex: 'amount',
			key: 'amount',
		}, {
			title: '价格',
			dataIndex: 'price',
			key: 'price',
		}, {
			title: '重量',
			dataIndex: 'weight',
			key: 'weight',
		}, {
			title: '商品名称',
			dataIndex: 'name',
			key: 'name',
		}, {
			title: '商品规格',
			dataIndex: 'spec',
			key: 'spec',
		},  {
			title: '商品单位',
			dataIndex: 'unit',
			key: 'unit',
		},{
			title: '操作',
			dataIndex: 'opeartion',
			key: 'opeartion',
			render: (text, record) => {
				return(
					<Dropdown trigger={['click']} overlay={
	          		<Menu onClick={this.handleMenuClick.bind(null, record)}>

		            	<Menu.Item key='2'>删除</Menu.Item>
	          		</Menu>}>
		            <Button style={{ border: 'none' }}>
		              <Icon style={{ marginRight: 2 }} type='bars' />
		              <Icon type='down' />
		            </Button>
	          </Dropdown>
				)
			}
		}];
		const self = this;
		return(
			<div> 
				<Table columns={columns} dataSource={dataSource} loading={loading} onChange={this.pageChange.bind(this)} pagination={pagination} rowKey={record => record.id}/>
			</div>
		)
	}
}

export default list