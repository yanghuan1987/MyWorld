/**
 * 
 */
package com.spfood.pms.manager;

import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.intf.domain.ProductPackUnit;

/**
 * ProductPackUnit Manager
 *
 * @author yanghuan
 * @createTime 2017-03-14 14:16:36
 *
 */
public interface ProductPackUnitManager extends BaseManager<ProductPackUnit> {
	
	/*
	 * 按产品code查询
	 * 
	 */
	List<ProductPackUnit> selectByProductCode(String productCode);
	
}
