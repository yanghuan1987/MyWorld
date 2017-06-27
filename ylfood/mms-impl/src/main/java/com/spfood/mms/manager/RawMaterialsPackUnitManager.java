/**
 * 
 */
package com.spfood.mms.manager;

import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.mms.intf.domain.RawMaterialsPackUnit;

/**
 * RawMaterialsPackUnit Manager
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:15:40
 *
 */
public interface RawMaterialsPackUnitManager extends BaseManager<RawMaterialsPackUnit> {
	
	/*
	 * 按产品code查询
	 * 
	 */
	List<RawMaterialsPackUnit> selectByRawMaterialsCode(String RawMaterialsCode);

}
