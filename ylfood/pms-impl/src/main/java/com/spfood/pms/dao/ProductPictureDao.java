/**
 * 
 */
package com.spfood.pms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.intf.domain.ProductPicture;

/**
 *
 * ProductPicture DAO接口类
 *
 * @author qin benzhong
 * @createTime 2016-12-06 14:19:51
 *
 */
public interface ProductPictureDao extends BaseDao<ProductPicture>{

	/**
	 * 根据产品编码获取该产品的图片集合
	 * @param productCode
	 * @return
	 */
	public List<ProductPicture> getPictureByProductCode(String productCode);
}