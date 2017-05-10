/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.ProductPropertyDao;
import com.spfood.pms.intf.domain.ProductProperty;

/**
 *
 * ProductProperty DAO实现类
 *
 * @author qin benzhong
 * @createTime 2016-12-06 14:19:51
 *
 */
@Repository
public class ProductPropertyDaoImpl extends BaseDaoImpl<ProductProperty> implements ProductPropertyDao {
	@Override
	public void insertInBatch(List<ProductProperty> entityList) {
		try {
			sqlSessionTemplate.insert(getSqlName("insertList"), entityList);
		} catch (Exception e) {
			throw new PersistenceException("productProperty.dao.insertInBatch", e,getSqlName("insertList"), entityList);
		}
	}

	@Override
	public List<ProductProperty> selectByProductCode(String productCode) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(ProductSqlIds.SELECT_BY_PRODUCTCODE), productCode);
		} catch (Exception e) {
			throw new PersistenceException("productProperty.dao.selectByProductCode", e,getSqlName(ProductSqlIds.SELECT_BY_PRODUCTCODE), productCode.toString());
		}
	}
}
