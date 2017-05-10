/**
 * 
 */
package com.spfood.pms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.intf.domain.CommodityPicture;

/**
 *
 * CommodityPicture DAO
 *
 * @author fengjunchao
 * @createTime 2016-12-19 10:05:45
 *
 */
public interface CommodityPictureDao extends BaseDao<CommodityPicture>{

	public void insertPictureList(List<CommodityPicture> list);
	
	public void deleteByCommodityCode(String commodityCode);
}