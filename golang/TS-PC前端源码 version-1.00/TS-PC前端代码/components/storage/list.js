import React from 'react'
import { Table, Dropdown, Button, Menu, Icon, Modal, Input, Select } from 'antd'
import { TweenOneGroup } from 'rc-tween-one'
import styles from './into.css'

const confirm = Modal.confirm;
const Option = Select.Option;
const Search = Input.Search;
class list extends React.Component {
	constructor(props) {
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
		this.newPage = current;
		this.tableRowCheckedArr = [];
		this.querySate = "";
		this.queryExpireTime = "";
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
					onDeleteItem([record.id])
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
	
	addList(){
		console.log(this.refs.code.refs.input.value);
		console.log(this.refs.shelf_id.refs.input.value);
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
	
	queryList(param){
		const { onPageChange } = this.props
		onPageChange(param,{current: 1,pageSize: 10})
	}
	handelSearch(){
		const { onQuerySelected } = this.props
		onQuerySelected({
			state: this.querySate,
			expire_time: this.queryExpireTime
		},{current: 1,pageSize: 10})
	}
	handleChange(value){
		this.querySate = value;
	}
	handleChange2(value){
		this.queryExpireTime = value;
	}
	render() {
		const { loading, dataSource, pagination, onPageChange } = this.props;
		const rowSelection = {
			onChange: (selectedRowKeys, selectedRows) => {
				this.tableRowCheckedArr = selectedRowKeys;
				console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
			},
			getCheckboxProps: record => ({
				disabled: record.name === 'Disabled User', // Column configuration not to be checked
			}),
		};
		const columns = [{
				title: '序号',
				dataIndex: 'id',
				key: 'id',
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
				dataIndex: 'shelf_number',
				key: 'shelf_number',
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
			}, {
				title: '上架时间',
				dataIndex: 'in_time',
				key: 'in_time',
			}, {
				title: '过期时间',
				dataIndex: 'expire_time',
				key: 'expire_time',
			}, {
				title: '包裹状态',
				dataIndex: 'status',
				key: 'status',
			}, {
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
		//调整货架空间
		const optionsArr = [{
			text: 'jack1',
			id: '1'
		}, {
			text: 'jack2',
			id: '2'
		}, {
			text: 'jack3',
			id: '3'
		}]
		var ops = [];
		optionsArr.map((item,i)=>{
			ops.push(<Option key={i} value={item.id}>{item.text}</Option>)
		})
		//包裹状态
		const statusArr = [{
			text: '已签收',
			id: '1'
		}, {
			text: '未签收',
			id: '2'
		}, {
			text: '派件中',
			id: '3'
		}]
		var status = [];
		statusArr.map((item,i)=>{
			status.push(<Option key={i} value={item.id}>{item.text}</Option>)
		})
		//是否过期
		const outtimeArr = [{
			text: '已过期',
			id: '1'
		}, {
			text: '未过期',
			id: '2'
		}]
		var times = [];
		outtimeArr.map((item,i)=>{
			times.push(<Option key={i} value={item.id}>{item.text}</Option>)
		})
		return(
			<div className="styles">
				<ul className={styles.topInput} >
					<li>
						<span>订单包裹编码(订单号-温区-序号):</span>
						<Input placeholder="扫描或输入包裹编码" ref="code"/>
					</li>
					<li>
						<span>货架ID(13位)</span>
						<Input placeholder="扫码或输入货架ID" ref="shelf_id"/>
					</li>
					<li>
						<span className={styles.block}>调整货架空间</span>	
						<Select defaultValue="保持原有" style={{ width: 120 }} >
							{ops}
						</Select>
					</li>
					<li>
						<Button type="primary" onClick={this.addList.bind(this)}>确认入柜</Button>
					</li>
				</ul>
				<Search placeholder="这里输入关键词" style={{ width: 200 }} onSearch={value => this.queryList(value)}/>
				<Select placeholder="请选择" style={{ width: 120 }} onChange={this.handleChange.bind(this)}>
					{status}
				</Select>
				<Select placeholder="请选择" style={{ width: 120 }} onChange={this.handleChange2.bind(this)}>
					{times}
				</Select>
				<Button onClick={this.handelSearch.bind(this)}><Icon type="search" /></Button>
				<Button ><Icon type="download" /></Button>
	    		<Table rowSelection={rowSelection} bordered columns={columns} dataSource={dataSource} loading={loading} onChange={this.pageChange.bind(this)} pagination={pagination} rowKey={record => record.id} />
	    		<Button onClick={this.handleRemove.bind(this)}><Icon type="delete" /></Button>
	    	</div>
		)
	}
}

export default list