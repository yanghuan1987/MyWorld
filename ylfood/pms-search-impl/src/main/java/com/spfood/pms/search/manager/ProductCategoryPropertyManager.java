/**
 * 
 */
package com.spfood.pms.search.manager;

import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.search.intf.domain.ProductCategoryProperty;

/**
 * ProductCategoryProperty Manager接口类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
public interface ProductCategoryPropertyManager extends BaseManager<ProductCategoryProperty> {

	/**
	 * 根据商品名称模糊查询商品属性
	 * @param commodityName
	 * @return
	 */
	public List<ProductCategoryProperty> selectPropertyByCommodityName(String commodityName);
}
