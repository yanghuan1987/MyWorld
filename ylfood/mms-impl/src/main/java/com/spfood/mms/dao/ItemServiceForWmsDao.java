package com.spfood.mms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.mms.intf.domain.criteria.ItemServiceForWms;

/**
 * 物品信息列表服务 接口类
 * 功能：同步物品新数据到WMS系统
 * @author yanghuan
 *
 */

public interface ItemServiceForWmsDao extends BaseDao<ItemServiceForWms>{

	/**
	 * 根据产品编码获取商品
	 * @param commodityCode
	 * @return
	 */
	public ItemServiceForWms SelectByRawMaterialsCode(String productityCode);

	/**
	 * 根据产品编码list获取商品
	 * @param commodityCode
	 * @return
	 */
	public List<ItemServiceForWms> SelectByRawMaterialsCodeList(List<String> list);
	
	/**
	 * MQ取得商品信息
	 * @return
	 */
	public List<ItemServiceForWms> sendDate();
}
