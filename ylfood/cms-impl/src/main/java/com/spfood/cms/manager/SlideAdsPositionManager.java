/**
 * 
 */
package com.spfood.cms.manager;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.cms.intf.domain.SlideAdsPosition;

/**
 * SlideAdsPosition Manager
 *
 * @author SHIJIE
 * @createTime 2017-02-09 10:43:54
 *
 */
public interface SlideAdsPositionManager extends BaseManager<SlideAdsPosition> {

	/**
	 * 通过名称获得广告位
	 * @param name 传入的广告位名称
	 * @return SlideAdsPosition
	 */
	SlideAdsPosition selectOne(String name);
}
