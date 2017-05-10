package com.spfood.pms.search.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.dao.impl.SqlIds;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.search.dao.CommodityDao;
import com.spfood.pms.search.intf.domain.Commodity;
import com.spfood.pms.search.intf.domain.criteria.Parameters;

/**
 * CommodityDao 实现类
 * @author fengjunchao 
 *
 */
@Repository
public class CommodityDaoImpl extends BaseDaoImpl<Commodity> implements CommodityDao {

	@Override
	public List<Commodity> selectCommodityByProduct(String productCode) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CommoditySqlIds.SELECT_BY_PRODUCTCODE), productCode);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectListByPage", e, null, getSqlName(CommoditySqlIds.SELECT_BY_PRODUCTCODE), productCode);
		}
	}

	@Override
	public Commodity selectByCommodityCode(String commodityCode) {
		try {
			return sqlSessionTemplate.selectOne(getSqlName(SqlIds.SQL_SELECT_PRIMARY_KEY), commodityCode);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectOne", e, null, getSqlName(SqlIds.SQL_SELECT_PRIMARY_KEY), commodityCode.toString());
		}
	}
	
	//根据ID的集合查询所有的商品
	public List<Commodity> selectListByIds(List<Long> idList) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectListByIds"), idList);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectListByIds", e, null, getSqlName("selectListByIds"), idList);
		}
	}

	@Override
	public List<Commodity> selectCommodityByCodelist(List<String> codeList) {
		try {
			if(codeList == null || codeList.size() < 1)return null;
			return sqlSessionTemplate.selectList(getSqlName(CommoditySqlIds.SELECT_BY_CODELIST), codeList);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectCommodityByCodelist", e, null, getSqlName(CommoditySqlIds.SELECT_BY_CODELIST), codeList);
		}
	}

	@Override
	public List<Commodity> selectCommodityByCodelist(Parameters parameters) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CommoditySqlIds.SELECT_BY_CODELIST_AND_STATUS), parameters);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectCommodityByCodelist", e, null, getSqlName(CommoditySqlIds.SELECT_BY_CODELIST_AND_STATUS), parameters);
		}
	}
}
