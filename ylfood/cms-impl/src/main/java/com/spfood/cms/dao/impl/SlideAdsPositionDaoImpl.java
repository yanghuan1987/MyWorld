/**
 * 
 */
package com.spfood.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.cms.dao.SlideAdsPositionDao;
import com.spfood.cms.intf.domain.SlideAdsPosition;

/**
 *
 * SlideAdsPosition DAO
 *
 * @author SHIJIE
 * @createTime 2017-02-09 10:43:54
 *
 */
@Repository
public class SlideAdsPositionDaoImpl extends BaseDaoImpl<SlideAdsPosition> implements SlideAdsPositionDao {


	/**
	 * 通过名称获得广告位
	 * @param name 传入的广告位名称
	 * @return SlideAdsPosition
	 */
	@Override
	public SlideAdsPosition selectOne(String name) {
		try {
			return sqlSessionTemplate.selectOne(getSqlName(CmsSqlIds.SLIDEADSPOSITION_SELECT_ONE), name);
		} catch (Exception e) {
			throw new PersistenceException(
					"SlideAdsPosition.Dao.selectOne", e, getSqlName(CmsSqlIds.SLIDEADSPOSITION_SELECT_ONE), name);
		}
	}

}
