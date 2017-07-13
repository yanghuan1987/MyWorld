import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Button, Tabs, Table, Input, Select ,Icon} from 'antd'
import styles from './into.css'

const TabPane = Tabs.TabPane;
const Search = Input.Search;
const Option = Select.Option;

function callback(key) {
	console.log(key);
}

function Stockdis() {

	const columns = [{
			title: '序号',
			dataIndex: 'number',
			key: 'number',
		},
		{
			title: '订单包裹编码',
			dataIndex: 'code',
			key: 'code',
		}, {
			title: '货架ID',
			dataIndex: 'id',
			key: 'id',
		}, {
			title: '货架编号',
			dataIndex: 'shelfNumber',
			key: 'shelfNumber',
		}, {
			title: '货架温区',
			dataIndex: 'warmzone',
			key: 'warmzone',
		}, {
			title: '上架人员',
			dataIndex: 'shelve',
			key: 'shelve',
		}, {
			title: '取货人员',
			dataIndex: 'pickup',
			key: 'pickup',
		}, {
			title: '取件时间',
			dataIndex: 'fetchTime',
			key: 'fetchTime',
		}, {
			title: '上架时间',
			dataIndex: 'shelfTime',
			key: 'shelfTime',
		}, {
			title: '过期时间',
			dataIndex: 'overdueTime',
			key: 'overdueTime',
		}, {
			title: '包裹状态',
			dataIndex: 'packState',
			key: 'packState',
		}, {
			title: '操作',
			dataIndex: 'opeartion',
			key: 'opeartion',
			render: (text, record) => (
			    <span>
			    	<Button type="primary" style={{backgroundColor:'rgba(0,200,0,.5)',border:'none'}}><Icon type="edit" /></Button>
			        <span className="ant-divider" />
			        <Button type="primary" style={{border:'none'}}><Icon type="delete" /></Button>			       
			    </span>
			),
		}
	];

	function rowSelection(key) {
		console.log(key);
	}
	return(
		<div>
			<ul className={styles.topInput} >
				<li>
					<span>订单包裹编码(订单号-温区-序号):</span>
					<Input defaultValue="扫描或输入包裹编码" />
				</li>
				<li>
					<span className={styles.block}>调整货架空间:</span>	
					<Select defaultValue="保持原有" style={{ width: 120 }} >
						<Option value="jack">Jack</Option>
						<Option value="lucy">Lucy</Option>
						<Option value="disabled" disabled>Disabled</Option>
						<Option value="Yiminghe">yiminghe</Option>
					</Select>
				</li>
				<li>
					<Button type="primary">确认丢弃</Button>
				</li>
			</ul>
		<div>
			<Search placeholder="这里输入关键词" style={{ width: 200 }} onSearch={value => console.log(value)}/>
			<Select defaultValue="包裹状态" style={{width:120,marginLeft:'5px'}} >
				<Option value="jack">已丢弃</Option>
				<Option value="lucy">Lucy</Option>
			</Select>
			<Select defaultValue="是否过期" style={{width:120,marginLeft:'5px'}} >
				<Option value="jack">Jack</Option>
				<Option value="lucy">Lucy</Option>
			</Select>
			<Search style={{ width: 30,marginLeft:'2px',color:'blue' }}onSearch={value => console.log(value)}/>
			<Button><Icon type="download" /></Button>
		</div>
			<Table rowSelection={rowSelection} columns={columns} bordered />
			<Button type="primary"><Icon type="delete"/></Button>
	</div>
	)
}

export default Stockdis