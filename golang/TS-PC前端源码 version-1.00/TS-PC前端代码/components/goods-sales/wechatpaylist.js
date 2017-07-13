import React from 'react'
import {Table, Dropdown, Button, Menu, Icon, Modal,Input,Select} from 'antd'
import {TweenOneGroup} from 'rc-tween-one'
import {dateFormat} from '../../utils'
import styles from './scan-sales.css'

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
			title: '商户订单号',
			dataIndex: 'id',
			key: 'id',
		}, {
			title: '交易状态',
			dataIndex: 'status',
			key: 'status',
      render(text, record, index) {
        let str= text == 0 ? '未支付' : '已支付';

        return (<span>{str}</span>)
      }
		}, {
			title: '金额',
			dataIndex: 'total_price',
			key: 'total_price',
		}, {
			title: '支付完成时间',
			dataIndex: 'created_at',
			key: 'created_at',
      render(text, record, index) {
        let str=dateFormat(parseInt(text + '000'),'yyyy/MM/dd  hh:mm:ss');
        return (<span>{str}</span>)
      }
		}

	];
    const self = this;
    return (
    	<div className="styles">	      
	   
			<Table bordered columns={columns} dataSource={dataSource} loading={loading} onChange={this.pageChange.bind(this)} pagination={pagination} rowKey={record => record.id} />

	   </div>
    )
  }
}

export default Wechat
