/**
 * 
 */
package com.spfood.pms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.intf.domain.PmsCommodityMsgTemp;

/**
 *
 * PmsCommodityMsgTemp DAO
 *
 * @author yanghuan
 * @createTime 2017-03-30 17:31:20
 *
 */
public interface PmsCommodityMsgTempDao extends BaseDao<PmsCommodityMsgTemp>{
	Boolean updateByCommodityCodeList(List<String> list);
	//MQ失败回滚手动
	Boolean updateRollBack(List<String> list);
}