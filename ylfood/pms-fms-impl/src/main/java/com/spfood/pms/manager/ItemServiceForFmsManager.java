/**
 * 
 */
package com.spfood.pms.manager;

import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.intf.domain.criteria.ItemServiceForFms;

/**
 * 物品信息列表服务 接口类
 * 功能：同步物品新数据到WMS系统
 * @author yanghuan
 *
 */
public interface ItemServiceForFmsManager extends BaseManager<ItemServiceForFms>{
	/**
	 * MQ取得商品信息
	 * @return
	 */
	public List<ItemServiceForFms> sendDate();
}
