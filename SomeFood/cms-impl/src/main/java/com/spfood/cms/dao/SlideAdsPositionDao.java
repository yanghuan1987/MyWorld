/**
 * 
 */
package com.spfood.cms.dao;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.cms.intf.domain.SlideAdsPosition;

/**
 *
 * SlideAdsPosition DAO
 *
 * @author SHIJIE
 * @createTime 2017-02-09 10:43:54
 *
 */
public interface SlideAdsPositionDao extends BaseDao<SlideAdsPosition>{
	
	/**
	 * 通过名称获得广告位
	 * @param name 传入的广告位名称
	 * @return SlideAdsPosition
	 */
	SlideAdsPosition selectOne(String name);
}