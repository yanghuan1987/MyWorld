/**
 * 
 */
package com.spfood.pms.dao.impl;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.CommodityPropertyDao;
import com.spfood.pms.intf.domain.Commodity;
import com.spfood.pms.intf.domain.CommodityProperty;

/**
 *
 * CommodityProperty DAO
 *
 * @author fengjunchao
 * @createTime 2016-12-19 10:05:45
 *
 */
@Repository
public class CommodityPropertyDaoImpl extends BaseDaoImpl<CommodityProperty> implements CommodityPropertyDao {

	@Override
	public void insertByCommodity(Commodity commodity) {
		try {
			sqlSessionTemplate.insert(getSqlName(ProductCategorySqlIds.INSRET_BY_COMMODITY), commodity);
		} catch (Exception e) {
			throw new PersistenceException("commodityProperty.dao.insertByCommodity", e, getSqlName(ProductCategorySqlIds.INSRET_BY_COMMODITY), commodity);
		}
	}

}
