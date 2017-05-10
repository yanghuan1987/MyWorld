/**
 * 
 */
package com.spfood.pms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.intf.domain.ProductPackUnit;

/**
 *
 * ProductPackUnit DAO
 *
 * @author yanghuan
 * @createTime 2017-03-14 14:16:36
 *
 */
public interface ProductPackUnitDao extends BaseDao<ProductPackUnit>{
	/*
	 * 按产品code查询
	 * 
	 */
	List<ProductPackUnit> selectByProductCode(String productCode);
}