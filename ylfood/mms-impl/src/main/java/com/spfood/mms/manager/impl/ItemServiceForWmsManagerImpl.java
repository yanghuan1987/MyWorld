/**
 * 
 */
package com.spfood.mms.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.mms.dao.ItemServiceForWmsDao;
import com.spfood.mms.dao.MqMsgtempDao;
import com.spfood.mms.intf.domain.criteria.ItemServiceForWms;
import com.spfood.mms.manager.ItemServiceForWmsManager;

/**
 * 物品信息列表服务 实现
 * 功能：同步物品新数据到WMS系统
 * @author yanghuan
 *
 */

@Service
public class ItemServiceForWmsManagerImpl extends BaseManagerImpl<ItemServiceForWms> implements ItemServiceForWmsManager{
	
	private static Logger logger = Logger.getLogger(ItemServiceForWmsManagerImpl.class);
	
	@Autowired
	private ItemServiceForWmsDao itemServiceForWmsDao;
	
	@Autowired
	private MqMsgtempDao mqMsgtempDat;
	
	@Override
	protected BaseDao<ItemServiceForWms> getBaseDao() {
		return itemServiceForWmsDao;
	}
	
	/*
	 * 检索商品和产品表信息
	 */
	public List<ItemServiceForWms> SelectAllInfo () {
		logger.info("Statr to select table, class is"+ ItemServiceForWmsManagerImpl.class);
		return itemServiceForWmsDao.selectAll();
	}
	/*
	 * 按产品CODE检索
	 */
	public ItemServiceForWms SelectByRawMaterialsCode (String productityCode) {
		logger.info("Statr to select Producttable, class is"+ ItemServiceForWmsManagerImpl.class);
		return itemServiceForWmsDao.SelectByRawMaterialsCode(productityCode);
	}
	/**
	 * 根据产品编码list获取商品
	 * @param commodityCode
	 * @return
	 */
	public List<ItemServiceForWms> SelectByRawMaterialsCodeList(List<String> list){
		logger.info("Statr to select Producttable, class is"+ ItemServiceForWmsManagerImpl.class);
		return itemServiceForWmsDao.SelectByRawMaterialsCodeList(list);
		
	};
	
	//取得消息内容
	@Transactional
	public List<ItemServiceForWms> sendDate(){
		
		List<ItemServiceForWms> list = new ArrayList<ItemServiceForWms>();
		//取得商品信息发送
		list = itemServiceForWmsDao.sendDate();
		if (null != list && list.size() != 0) {
			List<String> CommodityCode = new ArrayList<String>();
			for (ItemServiceForWms itemServiceForWms : list) {
				CommodityCode.add(itemServiceForWms.getItemCode());
			}
			//更新取得信息时间
			mqMsgtempDat.updateByCommodityCodeList(CommodityCode);
		}
		return list;
	}
}
