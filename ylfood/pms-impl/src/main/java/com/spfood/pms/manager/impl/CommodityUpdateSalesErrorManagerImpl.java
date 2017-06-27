/**
 * 
 */
package com.spfood.pms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.dao.CommodityUpdateSalesErrorDao;
import com.spfood.pms.intf.domain.CommodityUpdateSalesError;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.manager.CommodityUpdateSalesErrorManager;

/**
 * CommodityUpdateSalesError Manager
 *
 * @author yanghuan
 * @createTime 2017-05-27 09:57:10
 *
 */
@Service
public class CommodityUpdateSalesErrorManagerImpl extends BaseManagerImpl<CommodityUpdateSalesError> implements CommodityUpdateSalesErrorManager {

	@Autowired
	private CommodityUpdateSalesErrorDao dao;
	
	@Override
	protected BaseDao<CommodityUpdateSalesError> getBaseDao() {
		return dao;
	}

}
