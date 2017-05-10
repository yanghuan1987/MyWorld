/**
 * 
 */
package com.spfood.pms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.pms.intf.ItemServiceForWmsService;
import com.spfood.pms.intf.domain.criteria.ItemServiceForWms;
import com.spfood.pms.manager.ItemServiceForWmsManager;

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
	 * 按商品CODE检索
	 */
	public ItemServiceForWms SelectByCommodityCode (String commodityCode) {
		return itemServiceForWmsManager.SelectByCommodityCode(commodityCode);
	}
	/*
	 * 按产品CODE检索
	 */
	public ItemServiceForWms SelectByProductCode (String productityCode) {
		return itemServiceForWmsManager.SelectByProductCode(productityCode);
	}
	/**
	 * 根据商品编码list获取商品
	 * @param commodityCode
	 * @return
	 */
	public List<ItemServiceForWms> SelectByCommodityCodeList(List<String> list){
		return itemServiceForWmsManager.SelectByCommodityCodeList(list);
	};
	

	/**
	 * 根据产品编码list获取商品
	 * @param commodityCode
	 * @return
	 */
	public List<ItemServiceForWms> SelectByProductCodeList(List<String> list){
		return itemServiceForWmsManager.SelectByProductCodeList(list);
	};
}
