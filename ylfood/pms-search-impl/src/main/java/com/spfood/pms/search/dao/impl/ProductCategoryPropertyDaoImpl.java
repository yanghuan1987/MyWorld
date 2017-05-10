/**
 * 
 */
package com.spfood.pms.search.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.search.dao.ProductCategoryPropertyDao;
import com.spfood.pms.search.intf.domain.ProductCategoryProperty;

/**
 *
 * ProductCategoryProperty DAO实现类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
@Repository
public class ProductCategoryPropertyDaoImpl extends BaseDaoImpl<ProductCategoryProperty> implements ProductCategoryPropertyDao {

	@Override
	public List<ProductCategoryProperty> selectPropertyByCommodityName(
			String commodityName) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CategoryPropertySqlIds.SELECT_BY_COMMODITYNAME), commodityName);
		} catch (Exception e) {
			throw new PersistenceException("property.dao.selectPropertyByCommodityName", e, null, getSqlName(CategoryPropertySqlIds.SELECT_BY_COMMODITYNAME), commodityName);
		}
	}

	@Override
	public List<ProductCategoryProperty> selectCategoryProperty(
			String categoryCode) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CategoryPropertySqlIds.SELECT_BY_CATEGORYCODE), categoryCode);
		} catch (Exception e) {
			throw new PersistenceException("property.dao.selectCategoryProperty", e, null, getSqlName(CategoryPropertySqlIds.SELECT_BY_CATEGORYCODE), categoryCode);
		}
	}
}
