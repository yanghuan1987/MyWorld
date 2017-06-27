/**
 * 
 */
package com.spfood.mms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.mms.dao.MqMsgtempDao;
import com.spfood.mms.intf.domain.MqMsgtemp;
import com.spfood.mms.manager.MqMsgtempManager;

/**
 * MqMsgtemp Manager
 *
 * @author yanghuan
 * @createTime 2017-03-30 17:31:20
 *
 */
@Service
public class MqMsgtempManagerImpl extends BaseManagerImpl<MqMsgtemp> implements MqMsgtempManager {

	@Autowired
	private MqMsgtempDao dao;
	
	@Override
	protected BaseDao<MqMsgtemp> getBaseDao() {
		return dao;
	}
	
	public 	Boolean updateRollBack(List<String> list){
		return dao.updateRollBack(list);
	}
}
