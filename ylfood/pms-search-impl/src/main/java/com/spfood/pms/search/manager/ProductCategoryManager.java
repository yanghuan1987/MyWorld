/**
 * 
 */
package com.spfood.pms.search.manager;

import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.search.intf.domain.ProductCategory;

/**
 * ProductCategory Manager接口类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
public interface ProductCategoryManager extends BaseManager<ProductCategory> {

	/**
	 * 根据商品名称模糊查询商品品类集合
	 * @param commodityName
	 * @return
	 */
	public List<ProductCategory> selectCategoryByCommodityName(String commodityName);
	
	/**
	 * 根据商品名称模糊查询商品品类集合
	 * @param keywords
	 * @param port
	 * @return
	 */
	public List<ProductCategory> selectCategoryByKeywords(List<String> keywords,String port);
	
	/**
	 * 根据商品编码获取品类及其父类名称集合
	 * 集合顺序为：一级、二级、三级
	 * @param commodityCode
	 * @return
	 */
	public List<ProductCategory> selectCategoryNameByCommodityCode(String commodityCode);
	
	/**
	 * 根据品类编码获取品类及其父类集合
	 * 集合顺序为：一级、二级、三级
	 * @param commodityCode
	 * @return
	 */
	public List<ProductCategory> selectCategoryByCategoryCode(String categoryCode);
	
	/**
	 * 根据编码查询子类
	 * @param categoryCode
	 * @return
	 */
	public List<ProductCategory> selectCategoryChildrenByCode(String categoryCode);

	/**
	 * 根据编码集合查询名称
	 * @param List<String》>
	 * @return
	 */
	public List<ProductCategory> selectCategoryNameByCodeList(List<String> categoryCode);
	
}
