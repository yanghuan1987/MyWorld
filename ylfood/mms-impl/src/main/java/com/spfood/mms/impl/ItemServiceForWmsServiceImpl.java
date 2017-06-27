/**
 * 
 */
package com.spfood.mms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.mms.intf.ItemServiceForWmsService;
import com.spfood.mms.intf.domain.criteria.ItemServiceForWms;
import com.spfood.mms.manager.ItemServiceForWmsManager;

/**
 * WMS查询产品商品接口实现
 * @author yanghuan 2017-03-06
 *
 */
public class ItemServiceForWmsServiceImpl implements ItemServiceForWmsService{
	
	@Autowired
	private ItemServiceForWmsManager itemServiceForWmsManager;
	/*
	 * 检索商品和产品表信息
	 */
	public List<ItemServiceForWms> SelectAllInfo(){
		return itemServiceForWmsManager.SelectAllInfo();
	}; 
	
	/*
	 * 按产品CODE检索
	 */
	public ItemServiceForWms SelectByRawMaterialsCode (String productityCode) {
		return itemServiceForWmsManager.SelectByRawMaterialsCode(productityCode);
	}

	/**
	 * 根据产品编码list获取商品
	 * @param commodityCode
	 * @return
	 */
	public List<ItemServiceForWms> SelectByRawMaterialsCodeList(List<String> list){
		return itemServiceForWmsManager.SelectByRawMaterialsCodeList(list);
	};
}
