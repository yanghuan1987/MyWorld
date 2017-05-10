/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.dao.impl.SqlIds;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.ProductCategoryDao;
import com.spfood.pms.intf.domain.ProductCategory;

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
	public ProductCategory selectByCategoryCode(String categoryCode) {
		Assert.notNull(categoryCode);
		try {
			return sqlSessionTemplate.selectOne(getSqlName(ProductCategorySqlIds.SELECT_BY_CATEGORYCODE), categoryCode);
		} catch (Exception e) {
			throw new PersistenceException("productCategory.dao.selectByCategoryCode", e, getSqlName(ProductCategorySqlIds.SELECT_BY_CATEGORYCODE), categoryCode);
		}
	}

	@Override
	public Boolean selectProductCategoryNameIsHave(
			ProductCategory productCategory) {
		Assert.notNull(productCategory.getCategoryName());
		try {
			return sqlSessionTemplate.selectOne(getSqlName(ProductCategorySqlIds.SELECT_BY_CATEGORYNAME_ISHAVE), productCategory);
		} catch (Exception e) {
			throw new PersistenceException("productCategory.dao.selectProductCategoryNameIsHave", e, getSqlName(ProductCategorySqlIds.SELECT_BY_CATEGORYNAME_ISHAVE), productCategory);
		}
	}

	@Override
	public Boolean selectProductCategoryCodeIsHave(
			ProductCategory productCategory) {
		Assert.notNull(productCategory.getCategoryCode());
		try {
			return sqlSessionTemplate.selectOne(getSqlName(ProductCategorySqlIds.SELECT_BY_CATEGORYCODE_ISHAVE), productCategory);
		} catch (Exception e) {
			throw new PersistenceException("productCategory.dao.selectProductCategoryCodeIsHave", e, getSqlName(ProductCategorySqlIds.SELECT_BY_CATEGORYCODE_ISHAVE), productCategory);
		}
	}

	@Override
	public List<ProductCategory> selectCategoryChildrenByCode(
			String categoryCode) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(ProductCategorySqlIds.SELECTCHILDREN_BY_CATEGORYCODE),categoryCode);
		} catch (Exception e) {
			throw new PersistenceException("productCategory.dao.selectCategoryChildrenByCode", e,getSqlName(SqlIds.SQL_SELECT));
		}
	}

	@Override
	public List<ProductCategory> selectCategoryAll() {
		try {
			return sqlSessionTemplate.selectList(getSqlName(ProductCategorySqlIds.SELECT_ALL),null);
		} catch (Exception e) {
			throw new PersistenceException("productCategory.dao.selectCategoryAll", e, getSqlName(SqlIds.SQL_SELECT));
		}
	}
	//根据ID的集合查询所有的品类
	public List<ProductCategory> selectListByIds(String sqlId,List<Long> idList) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(sqlId), idList);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectListByIds", e, null, getSqlName(sqlId), idList);
		}
	}
}
