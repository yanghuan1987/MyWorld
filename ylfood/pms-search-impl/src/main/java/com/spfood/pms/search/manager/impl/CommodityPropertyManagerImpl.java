/**
 * 
 */
package com.spfood.pms.search.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.search.dao.CommodityPropertyDao;
import com.spfood.pms.search.intf.domain.CommodityProperty;
import com.spfood.pms.search.manager.CommodityPropertyManager;

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

	@Override
	public List<CommodityProperty> selectPropertyByKeywords(
			List<String> keywords) {
		return commodityPropertyDao.selectPropertyByKeywords(keywords);
	}

}
