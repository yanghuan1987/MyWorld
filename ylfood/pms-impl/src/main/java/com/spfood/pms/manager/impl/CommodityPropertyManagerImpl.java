/**
 * 
 */
package com.spfood.pms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.dao.CommodityPropertyDao;
import com.spfood.pms.intf.domain.CommodityProperty;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.manager.CommodityPropertyManager;

/**
 * CommodityProperty Manager
 *
 * @author fengjunchao
 * @createTime 2016-12-19 10:05:45
 *
 */
@Service
public class CommodityPropertyManagerImpl extends BaseManagerImpl<CommodityProperty> implements CommodityPropertyManager {

	@Autowired
	private CommodityPropertyDao commodityPropertyDao;
	
	@Override
	protected BaseDao<CommodityProperty> getBaseDao() {
		return commodityPropertyDao;
	}

}
