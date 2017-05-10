/**
 * 
 */
package com.spfood.pms.dao;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.intf.domain.Commodity;
import com.spfood.pms.intf.domain.CommodityProperty;

/**
 *
 * CommodityProperty DAO
 *
 * @author fengjunchao
 * @createTime 2016-12-19 10:05:45
 *
 */
public interface CommodityPropertyDao extends BaseDao<CommodityProperty>{

	/**
	 * 新增商品属性
	 * @param commodity
	 */
	public void insertByCommodity(Commodity commodity);
}