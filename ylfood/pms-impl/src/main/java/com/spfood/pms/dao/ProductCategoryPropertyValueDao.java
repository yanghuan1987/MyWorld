/**
 * 
 */
package com.spfood.pms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.intf.domain.ProductCategoryPropertyValue;

/**
 *
 * ProductCategoryPropertyValue DAO接口类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
public interface ProductCategoryPropertyValueDao extends BaseDao<ProductCategoryPropertyValue>{
	/**
	 * 批量插入品类属性值
	 * @param ProductCategoryPropertyValue
	 */
	public void insertProductCategoryPropertyValueList(List<ProductCategoryPropertyValue> list);
}