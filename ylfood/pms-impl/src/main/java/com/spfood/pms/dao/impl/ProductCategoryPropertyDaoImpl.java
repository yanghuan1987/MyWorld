/**
 * 
 */
package com.spfood.pms.dao.impl;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.dao.impl.SqlIds;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.ProductCategoryPropertyDao;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductCategoryProperty;

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
	public int deleteByProductCategory(ProductCategory category) {
		try {
			return sqlSessionTemplate.delete(getSqlName(SqlIds.SQL_DELETE),category);
		} catch (Exception e) {
			throw new PersistenceException(String.format("delete fail：%s", getSqlName(SqlIds.SQL_DELETE)), e);
		}
	}

}
