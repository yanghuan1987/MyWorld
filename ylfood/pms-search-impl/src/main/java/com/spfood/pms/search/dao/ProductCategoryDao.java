/**
 * 
 */
package com.spfood.pms.search.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.search.intf.domain.ProductCategory;

/**
 *
 * ProductCategory DAO接口类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
public interface ProductCategoryDao extends BaseDao<ProductCategory>{

	/**
	 * 根据商品名称模糊查询商品品类集合
	 * @param commodityName
	 * @return
	 */
	public List<ProductCategory> selectCategoryByCommodityName(String commodityName);
	/**
	 * 根据商品名称模糊查询商品品类集合
	 * @param commodityName
	 * @return
	 */
	public List<ProductCategory> selectCategoryByKeywords(ProductCategory productCategory);
	
	/**
	 * 根据商品编码查询品类
	 * @param commodityCode
	 * @return
	 */
	public ProductCategory selectCategoryByCommodityCode(String commodityCode);
	
	/**
	 * 根据品类编码查询品类
	 * @param categoryCode
	 * @return
	 */
	public ProductCategory selectCategoryByCategoryCode(String categoryCode);
	
	/**
	 * 根据编码查询子类
	 * @param categoryCode
	 * @return
	 */
	public List<ProductCategory> selectCategoryChildrenByCode(String categoryCode);
}