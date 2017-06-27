/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.RawMaterialsPackUnitDao;
import com.spfood.pms.intf.domain.RawMaterialsPackUnit;

/**
 *
 * RawMaterialsPackUnit DAO
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:15:40
 *
 */
@Repository
public class RawMaterialsPackUnitDaoImpl extends BaseDaoImpl<RawMaterialsPackUnit> implements RawMaterialsPackUnitDao,RawMaterialsSqlIds {
	@Override
	public List<RawMaterialsPackUnit> selectByRawMaterialsCode(String RawMaterialsCode) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(SELECT_BY_RAW_MATERIALS_CODE), RawMaterialsCode);
		} catch (Exception e) {
			throw new PersistenceException("RawMaterialsPicture.dao.selectByRawMaterialsCode", e,getSqlName(SELECT_BY_RAW_MATERIALS_CODE), RawMaterialsCode.toString());
		}
	}
}
