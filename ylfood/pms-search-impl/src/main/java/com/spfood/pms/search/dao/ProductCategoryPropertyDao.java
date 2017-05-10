/**
 * 
 */
package com.spfood.pms.search.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.search.intf.domain.ProductCategoryProperty;

/**
 *
 * ProductCategoryProperty DAO接口类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
public interface ProductCategoryPropertyDao extends BaseDao<ProductCategoryProperty>{

	/**
	 * 根据商品名称模糊查询商品属性
	 * @param commodityName
	 * @return
	 */
	public List<ProductCategoryProperty> selectPropertyByCommodityName(String commodityName);
	
	/**
	 * 根据品类编码获取品类属性
	 * @param categoryCode
	 * @return
	 */
	public List<ProductCategoryProperty> selectCategoryProperty(String categoryCode);
}