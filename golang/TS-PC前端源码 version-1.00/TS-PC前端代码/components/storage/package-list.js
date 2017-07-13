import React from 'react'
import { Table, Dropdown, Button, Menu, Icon, Modal, Input, Select } from 'antd'
import { TweenOneGroup } from 'rc-tween-one'
import styles from './into.css'
import {dateFormat} from '../../utils'

const confirm = Modal.confirm;
const Option = Select.Option;
const Search = Input.Search;
class list extends React.Component {
	constructor(props) {
		super(props);
		
		const { current } = this.props.pagination;
		this.currentPage = current;
		this.newPage = current;
		this.tableRowCheckedArr = [];
		this.querySate = "";
		this.queryExpireTime = "";
	}


	handleMenuClick = (record, e) => {
		const { onDeleteItem, onEditItem } = this.props
		if(e.key === '1') {
			onEditItem(record)
		} else if(e.key === '2') {
			confirm({
				title: '您确定要删除这条记录吗?',
				onOk() {
					onDeleteItem(record.number)
				}
			})
		}
	}

	onEnd = (e) => {
		e.target.style.height = 'auto'
	};

	async pageChange(pagination) {
		await this.props.onPageChange("", pagination);
		this.newPage = pagination.current
	}
	
	handleRemove(){
		const _this = this;
		const { onDeleteItem } = this.props
		confirm({
			title: '您确定要删除这条记录吗?',
			onOk() {
				onDeleteItem(_this.tableRowCheckedArr)
			}
		})
	}
	
	
	render() {
		const { loading, dataSource, pagination, onPageChange } = this.props;


		const columns = [{
				title: '序号',
				dataIndex: 'idx',
				key: 'idx',
				render(text, record, index) {
						return (<span>{index+1}</span>)
				}
			},
			{
				title: '订单包裹编码',
				dataIndex: 'number',
				key: 'number',
			}, {
				title: '货架ID',
				dataIndex: 'shelf_id',
				key: 'shelf_id',
			}, {
				title: '货架编号',
				dataIndex: 'shelfNo',
				key: 'shelfNo',
			}, {
				title: '货架温区',
				dataIndex: 'temperature',
				key: 'temperature',
			}, {
				title: '上架人员',
				dataIndex: 'in_operator',
				key: 'in_operator',
			}, {
				title: '取货人员',
				dataIndex: 'out_operator',
				key: 'out_operator',
			}, {
				title: '取件时间',
				dataIndex: 'out_time',
				key: 'out_time',
				render(text, record, index) {
						let str=dateFormat(parseInt(text + '000'),'yyyy/MM/dd  hh:mm:ss')
						return (<span>{str}</span>)
				}
			}, {
				title: '上架时间',
				dataIndex: 'in_time',
				key: 'in_time',
				render(text, record, index) {
						let str=dateFormat(parseInt(text + '000'),'yyyy/MM/dd  hh:mm:ss')
						return (<span>{str}</span>)
				}
			}, {
				title: '过期时间',
				dataIndex: 'expire_time',
				key: 'expire_time',
				render(text, record, index) {
						let str=dateFormat(parseInt(text + '000'),'yyyy/MM/dd  hh:mm:ss')
						return (<span>{str}</span>)
				}
			}, {
				title: '包裹状态',
				dataIndex: 'status',
				key: 'status',
				render(text, record, index) {
					let str= text == '0' ? '已上架' : text == '1' ? '已取件' : text == '2' ? '已丢弃' : ''
	
					return (<span>{str}</span>)
				}
			}, {
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
			}
		];
	
		return(
			<div className="styles">
				
	    		<Table  bordered columns={columns} dataSource={dataSource} loading={loading} onChange={this.pageChange.bind(this)} pagination={pagination} rowKey={record => record.id} />
	    		
	    	</div>
		)
	}
}

export default list