/**
 * 
 */
package com.spfood.pms.search.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.search.dao.ProductCategoryDao;
import com.spfood.pms.search.intf.domain.ProductCategory;

/**
 *
 * ProductCategory DAO实现类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
@Repository
public class ProductCategoryDaoImpl extends BaseDaoImpl<ProductCategory> implements ProductCategoryDao {

	@Override
	public List<ProductCategory> selectCategoryByCommodityName(
			String commodityName) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CategorySqlIds.SELECT_BY_COMMODITYNAME), commodityName);
		} catch (Exception e) {
			throw new PersistenceException("category.dao.selectCategoryByCommodityName", e, null, getSqlName(CategorySqlIds.SELECT_BY_COMMODITYNAME), commodityName);
		}
	}

	@Override
	public List<ProductCategory> selectCategoryByKeywords(ProductCategory productCategory) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CategorySqlIds.SELECT_BY_KEYWORDS), productCategory);
		} catch (Exception e) {
			throw new PersistenceException("category.dao.selectCategoryByKeywords", e, null, getSqlName(CategorySqlIds.SELECT_BY_KEYWORDS), productCategory);
		}
	}

	@Override
	public ProductCategory selectCategoryByCommodityCode(String commodityCode) {
		try {
			return sqlSessionTemplate.selectOne(getSqlName(CategorySqlIds.SELECT_BY_COMMODITYCODE), commodityCode);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectOne", e, null, getSqlName(CategorySqlIds.SELECT_BY_COMMODITYCODE), commodityCode.toString());
		}
	}

	@Override
	public ProductCategory selectCategoryByCategoryCode(String categoryCode) {
		try {
			return sqlSessionTemplate.selectOne(getSqlName(CategorySqlIds.SELECT_BY_CATEGORYCODE), categoryCode);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectOne", e, null, getSqlName(CategorySqlIds.SELECT_BY_CATEGORYCODE), categoryCode.toString());
		}
	}
	

	@Override
	public List<ProductCategory> selectCategoryChildrenByCode(
			String categoryCode) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CategorySqlIds.SELECTCHILDREN_BY_CATEGORYCODE),categoryCode);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectCategoryChildrenByCode", e,getSqlName(CategorySqlIds.SELECTCHILDREN_BY_CATEGORYCODE));
		}
	}

	@Override
	public List<ProductCategory> selectCategoryNameByCodeList(List<String> categoryCode) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CategorySqlIds.SELECTNAME_BY_CATEGORYCODELIST),categoryCode);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectCategoryNameByCodeList", e,getSqlName(CategorySqlIds.SELECTNAME_BY_CATEGORYCODELIST));
		}
	}
}
