/**
 * 
 */
package com.spfood.pms.search.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.search.dao.CommodityPropertyDao;
import com.spfood.pms.search.intf.domain.CommodityProperty;

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
	public List<CommodityProperty> selectPropertyByKeywords(
			List<String> keywords) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CommoditySqlIds.SELECT_BY_KEYWORDS), keywords);
		} catch (Exception e) {
			throw new PersistenceException("property.dao.selectPropertyByKeywords", e, null, getSqlName(CommoditySqlIds.SELECT_BY_KEYWORDS), keywords);
		}
	}


}
