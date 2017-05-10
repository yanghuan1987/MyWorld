/**
 * 
 */
package com.spfood.pms.search.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.search.intf.domain.CommodityProperty;

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
	 * 根据商品多关键字模糊查询商品属性
	 * @return
	 */
	public List<CommodityProperty> selectPropertyByKeywords(List<String> keywords);
}