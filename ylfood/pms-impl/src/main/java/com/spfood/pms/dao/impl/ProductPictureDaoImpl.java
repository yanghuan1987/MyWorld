/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.dao.impl.SqlIds;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.ProductPictureDao;
import com.spfood.pms.intf.domain.ProductPicture;

/**
 *
 * ProductPicture DAO实现类
 *
 * @author qin benzhong
 * @createTime 2016-12-06 14:19:51
 *
 */
@Repository
public class ProductPictureDaoImpl extends BaseDaoImpl<ProductPicture> implements ProductPictureDao {

	@Override
	public void insertInBatch(List<ProductPicture> entityList) {
		try {
			sqlSessionTemplate.insert(getSqlName("insertList"), entityList);
		} catch (Exception e) {
			throw new PersistenceException("productPicture.dao.insertInBatch", e,getSqlName("insertList"), entityList);
		}
	}

	@Override
	public List<ProductPicture> getPictureByProductCode(String productCode) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(SqlIds.SQL_SELECT), productCode);
		} catch (Exception e) {
			throw new PersistenceException("productPicture.dao.getPictureByProductCode", e,getSqlName(SqlIds.SQL_SELECT), productCode.toString());
		}
	}
}
