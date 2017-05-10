/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.ProductCategoryPropertyValueDao;
import com.spfood.pms.intf.domain.ProductCategoryPropertyValue;

/**
 *
 * ProductCategoryPropertyValue DAO实现类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
@Repository
public class ProductCategoryPropertyValueDaoImpl extends BaseDaoImpl<ProductCategoryPropertyValue> implements ProductCategoryPropertyValueDao {

	@Override
	public void insertProductCategoryPropertyValueList(
			List<ProductCategoryPropertyValue> list) {
		try {
			sqlSessionTemplate.insert(getSqlName(ProductCategorySqlIds.INSERTLIST), list);
		} catch (Exception e) {
			throw new PersistenceException("productCategoryPropertyValue.dao.insertProductCategoryPropertyValueList", e,getSqlName(ProductCategorySqlIds.INSERTLIST), list);
		}
	}
}
