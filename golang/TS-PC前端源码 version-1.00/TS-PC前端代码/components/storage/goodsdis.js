import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Button, Tabs, Table, Input, Select, Icon } from 'antd'
import styles from './into.css'

const TabPane = Tabs.TabPane;
const Search = Input.Search;
const Option = Select.Option;

function Goodsdis() {
	const dataSource = [{
		key: '1',
		number: 1,
		code: 'DX012365489756',
		id: 12,
		shelfNumber: 'DX012365489756'
	}, {
		key: '2',
		number: 2,
		code: 'DZ012365489756',
		id: 15,
		shelfNumber: 'DX012365489756'
	}, {
		key: '3',
		number: 3,
		code: 'DD012365489756',
		id: 19,
		shelfNumber: 'DD012365489756'
	}];

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
		},  {
			title: '操作',
			dataIndex: 'opeartion',
			key: 'opeartion',
			render: (text, record) => (
				<span>
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
					<span>商品条码(编码+批次号):</span>
					<Input defaultValue="扫描或输入商品编码" />
				</li>
				<li>
					<span>新货架ID(13位)</span>
					<Input defaultValue="扫码或输入货架ID" />
				</li>
				<li>
					<Button type="primary">确认移动</Button>
				</li>
			</ul>
			<Search placeholder="这里输入关键词" style={{ width: 200 }} onSearch={value => console.log(value)}/>	
			
			<Table rowSelection={rowSelection} dataSource={dataSource} columns={columns} bordered />
			<Button type="primary"><Icon type="delete"/></Button>
		</div>
	)
}

export default Goodsdis