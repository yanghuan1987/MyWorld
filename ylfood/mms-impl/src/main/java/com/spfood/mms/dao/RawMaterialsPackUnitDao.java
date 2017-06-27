/**
 * 
 */
package com.spfood.mms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.mms.intf.domain.RawMaterialsPackUnit;

/**
 *
 * RawMaterialsPackUnit DAO
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:15:40
 *
 */
public interface RawMaterialsPackUnitDao extends BaseDao<RawMaterialsPackUnit>{
	/*
	 * 按产品code查询
	 * 
	 */
	List<RawMaterialsPackUnit> selectByRawMaterialsCode(String RawMaterialsCode);

}