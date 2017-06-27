/**
 * 
 */
package com.spfood.mms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.dao.impl.SqlIds;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.mms.dao.RawMaterialsPictureDao;
import com.spfood.mms.intf.domain.RawMaterialsPicture;

/**
 *
 * RawMaterialsPicture DAO
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:18:40
 *
 */
@Repository
public class RawMaterialsPictureDaoImpl extends BaseDaoImpl<RawMaterialsPicture> implements RawMaterialsPictureDao {

	@Override
	public void insertInBatch(List<RawMaterialsPicture> entityList) {
		try {
			sqlSessionTemplate.insert(getSqlName("insertList"), entityList);
		} catch (Exception e) {
			throw new PersistenceException("RawMaterialsPicture.dao.insertInBatch", e,getSqlName("insertList"), entityList);
		}
	}

	@Override
	public List<RawMaterialsPicture> getPictureByRawMaterialsCode(String RawMaterialsCode) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(SqlIds.SQL_SELECT), RawMaterialsCode);
		} catch (Exception e) {
			throw new PersistenceException("RawMaterialsPicture.dao.getPictureByRawMaterialsCode", e,getSqlName(SqlIds.SQL_SELECT), RawMaterialsCode.toString());
		}
	}


}
