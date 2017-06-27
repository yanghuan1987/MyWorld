/**
 * 
 */
package com.spfood.mms.intf;

import java.util.List;

import com.spfood.mms.intf.domain.criteria.ItemServiceForWms;

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
	 * 按产品CODE检索
	 */
	public ItemServiceForWms SelectByRawMaterialsCode(String productityCode);

	/**
	 * 根据产品编码list获取商品
	 * @param commodityCode
	 * @return
	 */
	public List<ItemServiceForWms> SelectByRawMaterialsCodeList(List<String> list);
}
