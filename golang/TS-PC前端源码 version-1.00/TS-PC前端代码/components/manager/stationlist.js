import React from 'react'
import {Table, Dropdown, Button, Menu, Icon, Modal,Input} from 'antd'
import {TweenOneGroup} from 'rc-tween-one'
import styles from './recharge.css'

const confirm = Modal.confirm;

class Station extends React.Component{
	constructor (props) {
    super(props);
    this.enterAnim = [
      {
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
      }
    ];
    this.leaveAnim = [
      {
        duration: 250,
        opacity: 0
      }, {
        height: 0,
        duration: 200,
        ease: 'easeOutQuad'
      }
    ];
    const {current} = this.props.pagination;
    this.currentPage = current;
    this.newPage = current
  }

  getBodyWrapper = (body) => {
    // 切换分页去除动画;
    if (this.currentPage !== this.newPage) {
      this.currentPage = this.newPage
      return body
    }
    return (
      <TweenOneGroup component='tbody' className={body.props.className} enter={this.enterAnim} leave={this.leaveAnim} appear={false}>
        {body.props.children}
      </TweenOneGroup>
    )
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
					title: '编号',
					dataIndex: 'id',
					key: 'id',
				}, {
					title: '名称',
					dataIndex: 'name',
					key: 'name',
				}, {
					title: '地址',
					dataIndex: 'shelfNumber',
					key: 'shelfNumber',
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

export default Station


			