import React, { PropTypes } from 'react'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Button, Tabs, Table, Input, Select ,Icon,Modal} from 'antd'
import styles from './into.css'

const TabPane = Tabs.TabPane;
const Search = Input.Search;
const Option = Select.Option;
const confirm = Modal.confirm;


class Into extends React.Component{
	constructor(props){
		super(props);
		this.state = { 
			visible: false 
		}
	}
	showModal = () => {
	    this.setState({
	      visible: true,
	    });
	  }
	hideModal = () => {
	    this.setState({
	      visible: false,
	    });
	  }
	confirm() {
	  Modal.confirm({
	    title: 'Confirm',
	    content: 'Bla bla ...',
	    okText: '确认',
	    cancelText: '取消',
	  });
	}
  	render () {
  		const { visible, confirmLoading, ModalText } = this.state;
  		const rowSelection = {
		  onChange: (selectedRowKeys, selectedRows) => {
		    console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
		  },
		  getCheckboxProps: record => ({
		    disabled: record.name === 'Disabled User',    // Column configuration not to be checked
		  }),
		};
	
		const dataSource = [
		{
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
		}];
	
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
				        <Button type="primary" style={{border:'none'}} onClick={this.showModal}><Icon type="delete" /></Button>			       
				    </span>
				),
			}
		];
	
		return(
			<div>
				<ul className={styles.topInput} >
					<li>
						<span>订单包裹编码(订单号-温区-序号):</span>
						<Input defaultValue="扫描或输入包裹编码" />
					</li>
					<li>
						<span>货架ID(13位)</span>
						<Input defaultValue="扫码或输入货架ID" />
					</li>
					<li>
						<span className={styles.block}>调整货架空间</span>	
						<Select defaultValue="保持原有" style={{ width: 120 }} >
							<Option value="jack">Jack</Option>
							<Option value="lucy">Lucy</Option>
							<Option value="disabled" disabled>Disabled</Option>
							<Option value="Yiminghe">yiminghe</Option>
						</Select>
					</li>
					<li>
						<Button type="primary">确认入柜</Button>
					</li>
				</ul>
				<div>
					<Search placeholder="这里输入关键词" style={{ width: 200 }} onSearch={value => console.log(value)}/>
						<Select defaultValue="包裹状态" style={{width:120,marginLeft:'5px'}} >
							<Option value="jack">Jack</Option>
							<Option value="lucy">Lucy</Option>
						</Select>
						<Select defaultValue="是否过期" style={{width:120,marginLeft:'5px'}} >
							<Option value="jack">Jack</Option>
							<Option value="lucy">Lucy</Option>
					</Select>
					<Search style={{ width: 30,marginLeft:'2px',color:'blue' }}onSearch={value => console.log(value)}/>
				</div>
					<Table  rowSelection={rowSelection} dataSource={dataSource} columns={columns} bordered />
					<Button type="primary" onClick={this.showModal}><Icon type="delete" /></Button>
					
				<Modal 
					visible={this.state.visible} 
					onOk={this.hideModal} 
					onCancel={this.hideModal}
			        okText="确认"
			        cancelText="取消">
			       <p>确定要删除吗?</p>
		        </Modal>
			</div>	
			
			
		)
	}
}

export default Into