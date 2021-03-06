/**
 * 
 */
package com.spfood.mms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.mms.dao.MqMsgtempDao;
import com.spfood.mms.intf.domain.MqMsgtemp;

/**
 *
 * MqMsgtemp DAO
 *
 * @author yanghuan
 * @createTime 2017-03-30 17:31:20
 *
 */
@Repository
public class MqMsgtempDaoImpl extends BaseDaoImpl<MqMsgtemp> implements MqMsgtempDao {
	
	
	@Override
	@Transactional
	public Boolean updateByCommodityCodeList(List<String> list) {
		try {
			sqlSessionTemplate.update(getSqlName(ProductCategorySqlIds.UPDATELIST), list);
		} catch (Exception e) {
			throw new PersistenceException("commodity.dao.updateByCommodityCodeList", e, getSqlName(ProductCategorySqlIds.UPDATELIST), list);
		}
		return true;
	}
	
	@Override
	@Transactional
	public Boolean updateRollBack(List<String> list) {
		try {
			sqlSessionTemplate.update(getSqlName(ProductCategorySqlIds.UPDATEBACK), list);
		} catch (Exception e) {
			throw new PersistenceException("commodity.dao.updateRollBack", e, getSqlName(ProductCategorySqlIds.UPDATEBACK), list);
		}
		return true;
	}
}
