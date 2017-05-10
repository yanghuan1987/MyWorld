package com.spfood.pms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.intf.domain.criteria.ItemServiceForWms;

/**
 * 物品信息列表服务 接口类
 * 功能：同步物品新数据到WMS系统
 * @author yanghuan
 *
 */

public interface ItemServiceForWmsDao extends BaseDao<ItemServiceForWms>{
	/**
	 * 根据商品编码获取商品
	 * @param commodityCode
	 * @return
	 */
	public ItemServiceForWms SelectByCommodityCode(String commodityCode);
	

	/**
	 * 根据产品编码获取商品
	 * @param commodityCode
	 * @return
	 */
	public ItemServiceForWms SelectByProductCode(String productityCode);
	/**
	 * 根据商品编码list获取商品
	 * @param commodityCode
	 * @return
	 */
	public List<ItemServiceForWms> SelectByCommodityCodeList(List<String> list);
	

	/**
	 * 根据产品编码list获取商品
	 * @param commodityCode
	 * @return
	 */
	public List<ItemServiceForWms> SelectByProductCodeList(List<String> list);
	
	/**
	 * MQ取得商品信息
	 * @return
	 */
	public List<ItemServiceForWms> sendDate();
}
