/**
 * 
 */
package com.spfood.mms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.mms.intf.domain.ProductCategory;

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
	 * 根据品类编码获取指定品类
	 * @return
	 */
	public ProductCategory selectByCategoryCode(String categoryCode);
	
	/**
	 * 根据品类查询该品类名称是否被其他占用
	 * @param productCategory
	 * @return
	 */
	public Boolean selectProductCategoryNameIsHave(ProductCategory productCategory);
	
	
	/**
	 * 根据品类查询改品类编码是否已被使用
	 * @param productCategory
	 * @return
	 */
	public Boolean selectProductCategoryCodeIsHave(ProductCategory productCategory);
	
	/**
	 * 根据编码查询子类
	 * @param categoryCode
	 * @return
	 */
	public List<ProductCategory> selectCategoryChildrenByCode(String categoryCode);
	/**
	 * 获取所有品类信息
	 * @return
	 */
	public List<ProductCategory> selectCategoryAll();
	
	/**
	 * 公共id数组集合查询品类评价
	 * @param idList
	 * @return
	 */
	public List<ProductCategory> selectListByIds(String sqlid,List<Long> idList);
}