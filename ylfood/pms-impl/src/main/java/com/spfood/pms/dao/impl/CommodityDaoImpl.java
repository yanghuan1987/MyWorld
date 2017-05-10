package com.spfood.pms.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.dao.impl.SqlIds;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.CommodityDao;
import com.spfood.pms.intf.domain.Commodity;

/**
 * CommodityDao 实现类
 * @author fengjunchao 
 *
 */
@Repository
public class CommodityDaoImpl extends BaseDaoImpl<Commodity> implements CommodityDao {

	@Override
	@Transactional
	public Boolean updateCommodityState(List<Map<String,Object>> list) {
		try {
			sqlSessionTemplate.update(getSqlName(ProductCategorySqlIds.UPDATELIST), list);
		} catch (Exception e) {
			throw new PersistenceException("commodity.dao.updateCommodityState", e, getSqlName(ProductCategorySqlIds.UPDATELIST), list);
		}
		return true;
	}
	
	//根据ID的集合查询所有的商品
	public List<Commodity> selectListByIds(List<Long> idList) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectListByIds"), idList);
		} catch (Exception e) {
			throw new PersistenceException("commodity.dao.selectListByIds", e,getSqlName("selectListByIds"), idList);
		}
	}

	@Override
	public List<Commodity> selectCommodityByProduct(String productCode) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CommoditySqlIds.SELECT_BY_PRODUCTCODE), productCode);
		} catch (Exception e) {
			throw new PersistenceException("commodity.dao.selectCommodityByProduct", e,getSqlName(CommoditySqlIds.SELECT_BY_PRODUCTCODE), productCode);
		}
	}

	@Override
	public Commodity selectByCommodityCode(String commodityCode) {
		try {
			return sqlSessionTemplate.selectOne(getSqlName(SqlIds.SQL_SELECT_PRIMARY_KEY), commodityCode);
		} catch (Exception e) {
			throw new PersistenceException("commodity.dao.selectByCommodityCode", e,getSqlName(SqlIds.SQL_SELECT_PRIMARY_KEY), commodityCode.toString());
		}
	}

	@Override
	public List<Commodity> selectCommodityByCategory(Commodity commodity) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CommoditySqlIds.SELECT_BY_CATEGORY_FOR_PRICE), commodity);
		} catch (Exception e) {
			throw new PersistenceException("commodity.dao.selectCommodityByCategory", e,getSqlName(CommoditySqlIds.SELECT_BY_CATEGORY_FOR_PRICE), commodity);
		}
	}
	
	@Override
	public List<Commodity> selectListByCommodityCodeList(List<String> CommodityCodeList) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CommoditySqlIds.SELECT_BY_COMMODITYCODELIST_COMMODITY), CommodityCodeList);
		} catch (Exception e) {
			throw new PersistenceException("commodity.dao.selectListByCommodityCodeList", e,getSqlName(CommoditySqlIds.SELECT_BY_COMMODITYCODELIST_COMMODITY), CommodityCodeList);
		}
	}
	
	@Override
	public List<Commodity> sendDate() {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CommoditySqlIds.SELECT_BY_COMMODITYCODE_FOR_MQ));
		} catch (Exception e) {
			throw new PersistenceException("commodity.dao.sendDate", e,getSqlName(CommoditySqlIds.SELECT_BY_COMMODITYCODE_FOR_MQ));
		}
	}
	
	//根据ID的集合查询所有的商品评价
	public List<Commodity> selectListByIdsForComment(List<Long> idList) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CommoditySqlIds.SELECT_PAGE_IN_COMMODITY_FOR_COMMENT_DETAIL), idList);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectListByIds", e, null, getSqlName(CommoditySqlIds.SELECT_PAGE_IN_COMMODITY_FOR_COMMENT_DETAIL), idList);
		}
	}
}
