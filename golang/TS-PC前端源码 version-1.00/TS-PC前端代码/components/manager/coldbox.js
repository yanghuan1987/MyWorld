import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Button, Tabs, Table, Input, Select ,Icon} from 'antd'
import styles from '../storage/into.css'

const TabPane = Tabs.TabPane;
const Search = Input.Search;
const Option = Select.Option;

function Coldbox() {
	const dataSource = [{
		key: '1',
		number: 1,
		code: 'DX012365489756',
		id: 12,
		shelfNumber: 'DX012365489756'
	}];
	const columns = [{
			title: '序号',
			dataIndex: 'number',
			key: 'number',
		},
		{
			title:'冷物柜编号',
			dataIndex: 'code',
			key: 'code',
		}, {
			title: '名称',
			dataIndex: 'id',
			key: 'id',
		}, {
			title: '所属站点编号',
			dataIndex: 'shelfNumber',
			key: 'shelfNumber',
		}, {
			title: '货架数',
			dataIndex: 'warmzone',
			key: 'warmzone',
		}, {
			title: '状态',
			dataIndex: 'fetchTime',
			key: 'fetchTime',
		}, {
			title: '温区1',
			dataIndex: 'shelfTime',
			key: 'shelfTime',
		}, {
			title: '温区2',
			dataIndex: 'overdueTime',
			key: 'overdueTime',
		},{
			title: '温区3',
			dataIndex: 'overdueTime',
			key: 'overdue',
		},{
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
			<Search placeholder="这里输入关键词" style={{ width: 200 }} onSearch={value => console.log(value)}/>
			<Table dataSource={dataSource} columns={columns} bordered />
			<Button type="primary">新增</Button>
			<Button type="primary"><Icon type="delete"/></Button>
	</div>
	)
}

export default Coldbox