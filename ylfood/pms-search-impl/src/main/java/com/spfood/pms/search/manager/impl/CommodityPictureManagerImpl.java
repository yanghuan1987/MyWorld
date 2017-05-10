/**
 * 
 */
package com.spfood.pms.search.manager.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.search.dao.CommodityPictureDao;
import com.spfood.pms.search.intf.domain.CommodityPicture;
import com.spfood.pms.search.manager.CommodityPictureManager;

/**
 * CommodityPicture Manager
 *
 * @author fengjunchao
 * @createTime 2016-12-19 10:05:45
 *
 */
@Service
public class CommodityPictureManagerImpl extends BaseManagerImpl<CommodityPicture> implements CommodityPictureManager {

	@Autowired
	private CommodityPictureDao commodityPictureDao;
	
	@Override
	protected BaseDao<CommodityPicture> getBaseDao() {
		return commodityPictureDao;
	}

	

}
