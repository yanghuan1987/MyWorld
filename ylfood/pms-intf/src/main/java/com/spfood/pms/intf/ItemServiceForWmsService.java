/**
 * 
 */
package com.spfood.pms.intf;

import java.util.List;

import com.spfood.pms.intf.domain.criteria.ItemServiceForWms;

/**
 * WMS查询产品商品接口
 * @author yanghuan 2017-03-06
 *
 */
public interface ItemServiceForWmsService {
	/*
	 * 检索商品和产品表信息
	 */
	public List<ItemServiceForWms> SelectAllInfo();
	
	/*
	 * 按商品CODE检索
	 */
	public ItemServiceForWms SelectByCommodityCode(String commodityCode);
	/*
	 * 按产品CODE检索
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
}
