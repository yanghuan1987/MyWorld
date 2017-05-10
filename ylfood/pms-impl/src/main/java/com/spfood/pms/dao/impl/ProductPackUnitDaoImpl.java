/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.ProductPackUnitDao;
import com.spfood.pms.intf.domain.ProductPackUnit;

/**
 *
 * ProductPackUnit DAO
 *
 * @author yanghuan
 * @createTime 2017-03-14 14:16:36
 *
 */
@Repository
public class ProductPackUnitDaoImpl extends BaseDaoImpl<ProductPackUnit> implements ProductPackUnitDao,ProductSqlIds {
	@Override
	public List<ProductPackUnit> selectByProductCode(String productCode) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(SELECT_BY_PRODUCTCODE), productCode);
		} catch (Exception e) {
			throw new PersistenceException("productPicture.dao.selectByProductCode", e,getSqlName(SELECT_BY_PRODUCTCODE), productCode.toString());
		}
	}
}
