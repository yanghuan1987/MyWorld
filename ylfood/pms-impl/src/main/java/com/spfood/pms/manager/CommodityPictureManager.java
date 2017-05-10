/**
 * 
 */
package com.spfood.pms.manager;

import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.intf.domain.CommodityPicture;

/**
 * CommodityPicture Manager???
 *
 * @author fengjunchao
 * @createTime 2016-12-19 10:05:45
 *
 */
public interface CommodityPictureManager extends BaseManager<CommodityPicture> {

	/**
	 * 根据商品编码查询商品图片集合
	 * @param commodityCode
	 * @return
	 */
	public List<CommodityPicture> selectByCommodityCode(String commodityCode);
}
