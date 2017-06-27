/**
 * 
 */
package com.spfood.pms.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.dao.ItemServiceForFmsDao;
import com.spfood.pms.dao.PmsCommodityMsgTempDao;
import com.spfood.pms.intf.domain.criteria.ItemServiceForFms;
import com.spfood.pms.manager.ItemServiceForFmsManager;

/**
 * 物品信息列表服务 实现
 * 功能：同步物品新数据到WMS系统
 * @author yanghuan
 *
 */

@Service
public class ItemServiceForFmsManagerImpl extends BaseManagerImpl<ItemServiceForFms> implements ItemServiceForFmsManager{
	
	private static Logger logger = Logger.getLogger(ItemServiceForFmsManagerImpl.class);
	
	@Autowired
	private ItemServiceForFmsDao itemServiceForWmsDao;
	
	@Autowired
	private PmsCommodityMsgTempDao pmsCommodityMsgTempDat;
	
	@Override
	protected BaseDao<ItemServiceForFms> getBaseDao() {
		return itemServiceForWmsDao;
	}
	
	//取得消息内容
	@Transactional
	public List<ItemServiceForFms> sendDate(){
		logger.info("start to connect Datebase");
		List<ItemServiceForFms> list = new ArrayList<ItemServiceForFms>();
		//取得商品信息发送
		list = itemServiceForWmsDao.sendDate();
		if (null != list && list.size() != 0) {
			List<String> CommodityCode = new ArrayList<String>();
			for (ItemServiceForFms itemServiceForFms : list) {
				CommodityCode.add(itemServiceForFms.getItemCode());
			}
			//更新取得信息时间
			pmsCommodityMsgTempDat.updateByCommodityCodeList(CommodityCode);
		}
		return list;
	}
}
