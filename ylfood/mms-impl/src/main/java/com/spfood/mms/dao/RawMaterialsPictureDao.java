/**
 * 
 */
package com.spfood.mms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.mms.intf.domain.RawMaterialsPicture;

/**
 *
 * RawMaterialsPicture DAO
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:18:40
 *
 */
public interface RawMaterialsPictureDao extends BaseDao<RawMaterialsPicture>{

	/**
	 * 根据产品编码获取该产品的图片集合
	 * @param RawMaterialsCode
	 * @return
	 */
	public List<RawMaterialsPicture> getPictureByRawMaterialsCode(String RawMaterialsCode);


}