import React from 'react'
import { Table, Dropdown, Button, Menu, Icon, Modal, Input, Select } from 'antd'
import { TweenOneGroup } from 'rc-tween-one'
import styles from './into.css'

const confirm = Modal.confirm;
const Option = Select.Option;
const Search = Input.Search;
class list extends React.Component {
	constructor(props) {
		console.log(props);
		super(props);
		
		this.enterAnim = [{
			opacity: 0,
			x: 30,
			backgroundColor: '#fffeee',
			duration: 0
		}, {
			height: 0,
			duration: 200,
			type: 'from',
			delay: 250,
			ease: 'easeOutQuad',
			onComplete: this.onEnd
		}, {
			opacity: 1,
			x: 0,
			duration: 250,
			ease: 'easeOutQuad'
		}, {
			delay: 1000,
			backgroundColor: '#fff'
		}];
		this.leaveAnim = [{
			duration: 250,
			opacity: 0
		}, {
			height: 0,
			duration: 200,
			ease: 'easeOutQuad'
		}];
		const { current } = this.props.pagination;
		this.currentPage = current;
		this.newPage = current
	}

	getBodyWrapper = (body) => {
		// 切换分页去除动画;
		if(this.currentPage !== this.newPage) {
			this.currentPage = this.newPage
			return body
		}
		return(
			<TweenOneGroup component='tbody' className={body.props.className} enter={this.enterAnim} leave={this.leaveAnim} appear={false}>
        {body.props.children}
      </TweenOneGroup>
		)
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
	
	addList(){
		
//		console.log(this.refs.code.refs.input.value);
		console.log(this.refs.shelf_id.refs.input.value);
	}
	
	render() {
		const { loading, dataSource, pagination, onPageChange } = this.props;
		const rowSelection = {
			onChange: (selectedRowKeys, selectedRows) => {
				console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
			},
			getCheckboxProps: record => ({
				disabled: record.name === 'Disabled User', // Column configuration not to be checked
			}),
		};
		const columns = [{
			title: '序号',
			dataIndex: 'number',
			key: 'number',
		},
		{
			title: '商品编码',
			dataIndex: 'code',
			key: 'code',
		}, {
			title: '批次号',
			dataIndex: 'shelfNumber',
			key: 'shelfNumber',
		},{
			title: '货架ID',
			dataIndex: 'id',
			key: 'id',
		},{
			title: '上架人员',
			dataIndex: 'shelve',
			key: 'shelve',
		}, {
			title: '上架时间',
			dataIndex: 'shelfTime',
			key: 'shelfTime',
		}, {
			title: '商品数量',
			dataIndex: 'overdueTime',
			key: 'overdueTime',
		}, {
			title: '价格',
			dataIndex: 'packState',
			key: 'packState',
		}, {
			title: '重量',
			dataIndex: 'warmzone',
			key: 'warmzone',
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
		},, {
				title: '操作',
				dataIndex: 'opeartion',
				key: 'opeartion',
				render: (text, record) => {
					return(
						<Dropdown trigger={['click']} overlay={
		          		<Menu onClick={this.handleMenuClick.bind(null, record)}>
			            	<Menu.Item key='1'>编辑</Menu.Item>
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
		const self = this;
		return(
			<div> 
				<ul className={styles.topInput} >
					<li>
						<span>商品条码(编码+批次号):</span>
						<Input defaultValue="扫描或输入商品编码" />
					</li>
					<li>
						<span>新货架ID(13位)</span>
						<Input defaultValue="扫码或输入货架ID" />
					</li>
					<li>
						<Button type="primary">确认移除</Button>
					</li>
				</ul>
				<Search placeholder="这里输入关键词" style={{ width: 200 }} onSearch={value => console.log(value)}/>	
				
				<Table rowSelection={rowSelection} bordered columns={columns} dataSource={dataSource} loading={loading} onChange={this.pageChange.bind(this)} pagination={pagination} rowKey={record => record.id}/>
			</div>
		)
	}
}

export default list