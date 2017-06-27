/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.PmsCommodityMsgTempDao;
import com.spfood.pms.intf.domain.PmsCommodityMsgTemp;

/**
 *
 * PmsCommodityMsgTemp DAO
 *
 * @author yanghuan
 * @createTime 2017-03-30 17:31:20
 *
 */
@Repository
public class PmsCommodityMsgTempDaoImpl extends BaseDaoImpl<PmsCommodityMsgTemp> implements PmsCommodityMsgTempDao,SqlIds {
	
	
	@Override
	@Transactional
	public Boolean updateByCommodityCodeList(List<String> list) {
		try {
			sqlSessionTemplate.update(getSqlName(UPDATELIST), list);
		} catch (Exception e) {
			throw new PersistenceException("commodity.dao.updateByCommodityCodeList", e, getSqlName(UPDATELIST), list);
		}
		return true;
	}
	
	@Override
	@Transactional
	public Boolean updateRollBack(List<String> list) {
		try {
			sqlSessionTemplate.update(getSqlName(UPDATEBACK), list);
		} catch (Exception e) {
			throw new PersistenceException("commodity.dao.updateRollBack", e, getSqlName(UPDATEBACK), list);
		}
		return true;
	}
}
