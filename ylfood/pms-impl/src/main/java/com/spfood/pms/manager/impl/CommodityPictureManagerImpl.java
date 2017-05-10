/**
 * 
 */
package com.spfood.pms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.dao.CommodityPictureDao;
import com.spfood.pms.intf.domain.Commodity;
import com.spfood.pms.intf.domain.CommodityPicture;
import com.spfood.pms.manager.CommodityPictureManager;

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

	@Override
	public List<CommodityPicture> selectByCommodityCode(String commodityCode) {
		Commodity commodity = new Commodity();
		commodity.setCommodityCode(commodityCode);
		CommodityPicture commodityPicture = new CommodityPicture();
		commodityPicture.setCommodity(commodity);
		return commodityPictureDao.selectList(commodityPicture);
	}

}
