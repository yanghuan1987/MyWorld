import React from 'react'
import {Table, Dropdown, Button, Menu, Icon, Modal,Input,Select} from 'antd'
import {TweenOneGroup} from 'rc-tween-one'
import styles from './recharge.css'

const confirm = Modal.confirm;
const select = Select.Option;
const Search = Input.Search;

class Wechat extends React.Component {
  constructor (props) {
    super(props);
    
    const {current} = this.props.pagination;
    this.currentPage = current;
    this.newPage = current
  }

  
  handleMenuClick = (record, e) => {

    const {onDeleteItem, onEditItem} = this.props
    if (e.key === '1') {
      onEditItem(record)
    } else if (e.key === '2') {
      confirm({
        title: '您确定要删除这条记录吗?',
        onOk () {
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
  render () {
    const {loading, dataSource, pagination, onPageChange} = this.props;

    const columns = [
			{
				title:'货架ID',
				dataIndex: 'id',
				key: 'id',
			}, {
				title: '货架编号',
				dataIndex: 'number',
				key: 'number',
			}, {
				title: '所属冷物柜编号',
				dataIndex: 'iceboxnum',
				key: 'iceboxnum',
			}, {
				title: '所属站点编号',
				dataIndex: 'siteCode',
				key: 'siteCode',
			}, {
				title: '温区',
				dataIndex: 'temperature',
				key: 'temperature',
			}, 

			 {
				title: '状态',
				dataIndex: 'status',
				key: 'status',
				render(text, record, index) {
	        let str= text == 0 ? '未运行' : '运行中';

	        return (<span>{str}</span>)
	      }
			},{
			title: '操作',
			dataIndex: 'opeartion',
			key: 'opeartion',
			render: (text, record) => {
				return(
					<Dropdown trigger={['click']} overlay={
		          	<Menu onClick={this.handleMenuClick.bind(null, record)}>
			           	<Menu.Item key='1'>编辑</Menu.Item>

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
    return (
    	<div >	      

			<Table columns={columns} dataSource={dataSource} loading={loading} onChange={this.pageChange.bind(this)} pagination={pagination} rowKey={record => record.id} />

	   </div>
    )
  }
}

export default Wechat
