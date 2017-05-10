package com.spfood.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.cms.intf.SlideAdsPositionService;
import com.spfood.cms.intf.domain.SlideAdsPosition;
import com.spfood.cms.intf.utils.SlideAdsPositionName;
import com.spfood.cms.manager.SlideAdsPositionManager;

public class SlideAdsPositionServiceImpl implements SlideAdsPositionService {

	@Autowired
	private SlideAdsPositionManager slideAdsPositionManager;
	
	
	/**
	 * 获得所有广告位
	 * @return 所有广告位集合
	 */
	@Override
	public List<SlideAdsPosition> selectAll() {
		return slideAdsPositionManager.selectAll();
	}


	/**
	 *  为B2C提供接口,通过名称获得广告位
	 * @param slideAdsPositionName 传入的广告位名称,枚举
	 * @return SlideAdsPosition
	 */
	@Override
	public SlideAdsPosition selectOne(SlideAdsPositionName slideAdsPositionName) {
		return slideAdsPositionManager.selectOne(slideAdsPositionName.getValue());
	}

}
