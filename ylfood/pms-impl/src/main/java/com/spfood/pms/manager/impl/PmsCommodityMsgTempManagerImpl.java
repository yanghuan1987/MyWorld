/**
 * 
 */
package com.spfood.pms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.dao.PmsCommodityMsgTempDao;
import com.spfood.pms.intf.domain.PmsCommodityMsgTemp;
import com.spfood.pms.manager.PmsCommodityMsgTempManager;

/**
 * PmsCommodityMsgTemp Manager
 *
 * @author yanghuan
 * @createTime 2017-03-30 17:31:20
 *
 */
@Service
public class PmsCommodityMsgTempManagerImpl extends BaseManagerImpl<PmsCommodityMsgTemp> implements PmsCommodityMsgTempManager {

	@Autowired
	private PmsCommodityMsgTempDao dao;
	
	@Override
	protected BaseDao<PmsCommodityMsgTemp> getBaseDao() {
		return dao;
	}
	
	public 	Boolean updateRollBack(List<String> list){
		return dao.updateRollBack(list);
	}
}
