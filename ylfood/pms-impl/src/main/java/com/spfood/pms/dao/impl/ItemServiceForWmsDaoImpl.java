/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.ItemServiceForWmsDao;
import com.spfood.pms.intf.domain.criteria.ItemServiceForWms;

/**
 * @author Administrator
 *
 */
@Repository
public class ItemServiceForWmsDaoImpl  extends BaseDaoImpl<ItemServiceForWms> implements ItemServiceForWmsDao,CommoditySqlIds {
	//根据商品编码获取商品
	public ItemServiceForWms SelectByCommodityCode(String commodityCode) {
		try {
			return sqlSessionTemplate.selectOne(getSqlName(SELECT_BY_COMMODITYCODE), commodityCode);
		} catch (Exception e) {
			throw new PersistenceException("ItemServiceForWms.dao.selectByCommodityCode", e,getSqlName(SELECT_BY_COMMODITYCODE), commodityCode);
		}
	}
	
	//根据产品编码获取商品
	public ItemServiceForWms SelectByProductCode(String productityCode) {
		try {
			return sqlSessionTemplate.selectOne(getSqlName(SELECT_BY_PRODUCTCODE), productityCode);
		} catch (Exception e) {
			throw new PersistenceException("ItemServiceForWms.dao.selectByProductCode", e,getSqlName(SELECT_BY_PRODUCTCODE), productityCode);
		}
	}
	
	/**
	 * 根据商品编码list获取商品
	 * @param commodityCode
	 * @return
	 */
	public List<ItemServiceForWms> SelectByCommodityCodeList(List<String> list){
		try {
			return sqlSessionTemplate.selectList(getSqlName(SELECT_BY_COMMODITYCODELIST), list);
		} catch (Exception e) {
			throw new PersistenceException("ItemServiceForWms.dao.SelectByCommodityCodeList", e,getSqlName(SELECT_BY_COMMODITYCODELIST), list);
		}
	}
	

	/**
	 * 根据产品编码list获取商品
	 * @param commodityCode
	 * @return
	 */
	public List<ItemServiceForWms> SelectByProductCodeList(List<String> list){
		try {
			return sqlSessionTemplate.selectList(getSqlName(SELECT_BY_PRODUCTCODELIST), list);
		} catch (Exception e) {
			throw new PersistenceException("ItemServiceForWms.dao.SelectByProductCodeList", e,getSqlName(SELECT_BY_PRODUCTCODELIST), list);
		}
	}
	
	@Override
	public List<ItemServiceForWms> sendDate() {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CommoditySqlIds.SELECT_BY_COMMODITYCODE_FOR_MQ));
		} catch (Exception e) {
			throw new PersistenceException("ItemServiceForWms.dao.sendDate", e,getSqlName(CommoditySqlIds.SELECT_BY_COMMODITYCODE_FOR_MQ));
		}
	}
}
