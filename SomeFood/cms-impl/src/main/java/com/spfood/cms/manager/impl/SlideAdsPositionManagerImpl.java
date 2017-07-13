/**
 * 
 */
package com.spfood.cms.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.cms.dao.SlideAdsPositionDao;
import com.spfood.cms.intf.domain.SlideAdsPosition;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.cms.manager.SlideAdsPositionManager;

/**
 * SlideAdsPosition Manager
 *
 * @author SHIJIE
 * @createTime 2017-02-09 10:43:54
 *
 */
@Service
public class SlideAdsPositionManagerImpl extends BaseManagerImpl<SlideAdsPosition> implements SlideAdsPositionManager {

	private static final Log log = LogFactory.getLog(CatPromotePosManagerImpl.class);
	@Autowired
	private SlideAdsPositionDao dao;
	
	@Override
	protected BaseDao<SlideAdsPosition> getBaseDao() {
		return dao;
	}
	
	
	/**
	 * 通过名称获得广告位
	 * @param name 传入的广告位名称
	 * @return SlideAdsPosition
	 */
	@Override
	public SlideAdsPosition selectOne(String name) {
		log.info("Through advertising a name for advertising information");
		return dao.selectOne(name);
	}

}
