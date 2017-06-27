/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.ItemServiceForFmsDao;
import com.spfood.pms.intf.domain.criteria.ItemServiceForFms;

/**
 * @author Administrator
 *
 */
@Repository
public class ItemServiceForFmsDaoImpl  extends BaseDaoImpl<ItemServiceForFms> implements ItemServiceForFmsDao,SqlIds {
	
	@Override
	public List<ItemServiceForFms> sendDate() {
		try {
			return sqlSessionTemplate.selectList(getSqlName(SELECT_BY_COMMODITYCODE_FOR_MQ));
		} catch (Exception e) {
			throw new PersistenceException("ItemServiceForWms.dao.sendDate", e,getSqlName(SELECT_BY_COMMODITYCODE_FOR_MQ));
		}
	}
}
