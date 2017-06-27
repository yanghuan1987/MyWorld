/**
 * 
 */
package com.spfood.mms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.mms.dao.ItemServiceForWmsDao;
import com.spfood.mms.intf.domain.criteria.ItemServiceForWms;

/**
 * @author Administrator
 *
 */
@Repository
public class ItemServiceForWmsDaoImpl  extends BaseDaoImpl<ItemServiceForWms> implements ItemServiceForWmsDao,SqlIds {
	
	//根据产品编码获取商品
	public ItemServiceForWms SelectByRawMaterialsCode(String productityCode) {
		try {
			return sqlSessionTemplate.selectOne(getSqlName(SELECT_BY_RAW_MATERIALS_CODE), productityCode);
		} catch (Exception e) {
			throw new PersistenceException("ItemServiceForWms.dao.selectByProductCode", e,getSqlName(SELECT_BY_RAW_MATERIALS_CODE), productityCode);
		}
	}
	

	/**
	 * 根据产品编码list获取商品
	 * @param commodityCode
	 * @return
	 */
	public List<ItemServiceForWms> SelectByRawMaterialsCodeList(List<String> list){
		try {
			return sqlSessionTemplate.selectList(getSqlName(SELECT_BY_RAW_MATERIALS_CODELIST), list);
		} catch (Exception e) {
			throw new PersistenceException("ItemServiceForWms.dao.SelectByProductCodeList", e,getSqlName(SELECT_BY_RAW_MATERIALS_CODELIST), list);
		}
	}
	
	@Override
	public List<ItemServiceForWms> sendDate() {
		try {
			return sqlSessionTemplate.selectList(getSqlName(SqlIds.SELECT_BY_COMMODITYCODE_FOR_MQ));
		} catch (Exception e) {
			throw new PersistenceException("ItemServiceForWms.dao.sendDate", e,getSqlName(SqlIds.SELECT_BY_COMMODITYCODE_FOR_MQ));
		}
	}
}
