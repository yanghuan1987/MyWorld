/**
 * 
 */
package com.spfood.mms.dao;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.mms.intf.domain.ProductCategory;
import com.spfood.mms.intf.domain.ProductCategoryProperty;

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
	 * 删除指定品类下的属性
	 * @param id--品类主键
	 * @return
	 */
	public int deleteByProductCategory(ProductCategory category);
	
}