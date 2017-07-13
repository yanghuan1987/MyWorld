import React from 'react'
import {Table, Dropdown, Button, Menu, Icon, Modal,Input,Select} from 'antd'
import {TweenOneGroup} from 'rc-tween-one'
import styles from './scan-sales.css'
import {dateFormat} from '../../utils'

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
    const {loading, dataSource, pagination, onPageChange,timeFormat} = this.props;

   const columns = [{
			title: '序号',
			dataIndex: 'idx',
			key: 'idx',
			render(text, record, index) {
						return (<span>{index+1}</span>)
				}
		},
		{
			title:'银犁卡号',
			dataIndex: 'card_no',
			key: 'card_no',
		}, {
			title: '用户名',
			dataIndex: 'cname',
			key: 'cname',
		}, {
			title: '身份证号',
			dataIndex: 'id_no',
			key: 'id_no',
		}, {
			title: '电话',
			dataIndex: 'phone',
			key: 'phone',
		}, {
			title: '支付前剩余金额',
			dataIndex: 'old_money',
			key: 'old_money',
		}, {
			title: '订单金额',
			dataIndex: 'total_price',
			key: 'total_price',
		}, {
			title: '订单号',
			dataIndex: 'order_no',
			key: 'order_no',
		},{
			title: '支付时间',
			dataIndex: 'pay_time',
			key: 'pay_time',
			render(text, record, index) {
				let str=dateFormat(parseInt(text + '000'),'yyyy/MM/dd  hh:mm:ss');
				return (<span>{str}</span>)
			}
		},{
			title: '支付结果',
			dataIndex: 'pay_status',
			key: 'pay_status',
			render(text, record, index) {
				let str= text == 0 ? '未支付' : '已支付';

				return (<span>{str}</span>)
			}
		},{
			title: '操作人',
			dataIndex: 'account',
			key: 'account',
		},{
			title: '站点id',
			dataIndex: 'site_code',
			key: 'site_code',
		}
	];
    const self = this;
    return (
    	<div>	      
		   
				<Table bordered columns={columns} dataSource={dataSource} loading={loading} onChange={this.pageChange.bind(this)} pagination={pagination} rowKey={record => record.id} />

	   </div>
    )
  }
}

export default Wechat
