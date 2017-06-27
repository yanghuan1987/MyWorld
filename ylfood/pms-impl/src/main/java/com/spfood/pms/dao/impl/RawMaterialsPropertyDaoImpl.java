/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.RawMaterialsPropertyDao;
import com.spfood.pms.intf.domain.RawMaterialsProperty;

/**
 *
 * RawMaterialsProperty DAO
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:20:09
 *
 */
@Repository
public class RawMaterialsPropertyDaoImpl extends BaseDaoImpl<RawMaterialsProperty> implements RawMaterialsPropertyDao,RawMaterialsSqlIds {
	@Override
	public void insertInBatch(List<RawMaterialsProperty> entityList) {
		try {
			sqlSessionTemplate.insert(getSqlName("insertList"), entityList);
		} catch (Exception e) {
			throw new PersistenceException("RawMaterialsProperty.dao.insertInBatch", e,getSqlName("insertList"), entityList);
		}
	}

	@Override
	public List<RawMaterialsProperty> selectByRawMaterialsCode(String RawMaterialsCode) {
		try {
			return sqlSessionTemplate.selectList(SELECT_BY_RAW_MATERIALS_CODE);
		} catch (Exception e) {
			throw new PersistenceException("RawMaterialsProperty.dao.selectByRawMaterialsCode", e,getSqlName(SELECT_BY_RAW_MATERIALS_CODE), RawMaterialsCode.toString());
		}
	}


}
