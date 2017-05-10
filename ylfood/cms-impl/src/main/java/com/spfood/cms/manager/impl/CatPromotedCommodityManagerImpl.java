/**
 * 
 */
package com.spfood.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.cms.dao.CatPromotedCommodityDao;
import com.spfood.cms.intf.domain.CatPromotedCommodity;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.cms.manager.CatPromotedCommodityManager;

/**
 * CatPromotedCommodity Manager
 *
 * @author yanghuan
 * @createTime 2017-02-03 11:46:38
 *
 */
@Service
public class CatPromotedCommodityManagerImpl extends BaseManagerImpl<CatPromotedCommodity> implements CatPromotedCommodityManager {

	@Autowired
	private CatPromotedCommodityDao dao;
	
	@Override
	protected BaseDao<CatPromotedCommodity> getBaseDao() {
		return dao;
	}

}
