package com.spfood.cms.intf;

import java.util.List;

import com.spfood.cms.intf.domain.SlideAdsPosition;
import com.spfood.cms.intf.utils.SlideAdsPositionName;

public interface SlideAdsPositionService {

	
	/**
	 * 获得所有广告位
	 * @return 所有广告位集合
	 */
	List<SlideAdsPosition> selectAll();
	
	/**
	 *  为B2C提供接口,通过名称获得广告位
	 * @param slideAdsPositionName 传入的广告位名称,枚举
	 * @return SlideAdsPosition
	 */
	SlideAdsPosition selectOne(SlideAdsPositionName slideAdsPositionName);
}
