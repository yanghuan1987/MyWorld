/**
 * 
 */
package com.spfood.pms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.intf.domain.ProductProperty;

/**
 *
 * ProductProperty DAO接口类
 *
 * @author qin benzhong
 * @createTime 2016-12-06 14:19:51
 *
 */
public interface ProductPropertyDao extends BaseDao<ProductProperty>{

	/**
	 * 根据产品编码获取产品属性集合
	 * @param productCode
	 * @return
	 */
	public List<ProductProperty> selectByProductCode(String productCode);
}