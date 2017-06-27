/**
 * 
 */
package com.spfood.mms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.mms.intf.domain.RawMaterialsProperty;

/**
 *
 * RawMaterialsProperty DAO
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:20:09
 *
 */
public interface RawMaterialsPropertyDao extends BaseDao<RawMaterialsProperty>{

	/**
	 * 根据产品编码获取产品属性集合
	 * @param RawMaterialsCode
	 * @return
	 */
	public List<RawMaterialsProperty> selectByRawMaterialsCode(String RawMaterialsCode);


}