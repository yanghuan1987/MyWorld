/**
 * 
 */
package com.spfood.mms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.mms.intf.domain.MqMsgtemp;

/**
 *
 * MqMsgtemp DAO
 *
 * @author yanghuan
 * @createTime 2017-03-30 17:31:20
 *
 */
public interface MqMsgtempDao extends BaseDao<MqMsgtemp>{
	Boolean updateByCommodityCodeList(List<String> list);
	//MQ失败回滚手动
	Boolean updateRollBack(List<String> list);
}