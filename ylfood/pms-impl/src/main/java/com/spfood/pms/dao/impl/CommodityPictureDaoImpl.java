/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.dao.impl.SqlIds;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.CommodityPictureDao;
import com.spfood.pms.intf.domain.CommodityPicture;

/**
 *
 * CommodityPicture DAO
 *
 * @author fengjunchao
 * @createTime 2016-12-19 10:05:45
 *
 */
@Repository
public class CommodityPictureDaoImpl extends BaseDaoImpl<CommodityPicture> implements CommodityPictureDao {

	@Override
	public void insertPictureList(List<CommodityPicture> list) {
		try {
			sqlSessionTemplate.insert(getSqlName(ProductCategorySqlIds.INSERTLIST), list);
		} catch (Exception e) {
			throw new PersistenceException("commodityPicture.dao.insertPictureList", e,getSqlName(ProductCategorySqlIds.INSERTLIST), list);
		}
	}

	@Override
	public void deleteByCommodityCode(String commodityCode) {
		try {
			sqlSessionTemplate.delete(getSqlName(SqlIds.SQL_DELETE), commodityCode);
		} catch (Exception e) {
			throw new PersistenceException("commodityPicture.dao.deleteByCommodityCode", e, getSqlName(SqlIds.SQL_DELETE), commodityCode);
		}
	}

}
