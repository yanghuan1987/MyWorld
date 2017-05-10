/**
 * 
 */
package com.spfood.pms.manager;

import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductCategoryProperty;

/**
 * ProductCategory Manager接口类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
public interface ProductCategoryManager extends BaseManager<ProductCategory> {

	/**
	 * 添加或修改三级品类
	 * @param category
	 * @return
	 */
	public Long insertOrUpdateThirdLevelCategory(ProductCategory category,List<ProductCategoryProperty> list);
	
	/**
	 * 添加一、二级品类
	 * @param category
	 */
	public ProductCategory insertFirstLevelOrSecondLevelCategroy(ProductCategory category);
	
	/**
	 * 修改一、二级品类
	 * @param category
	 */
	public int updateFirstLevelOrSecondLevelCategroy(ProductCategory category);
	
	/**
	 * 查询品类名称是否被使用
	 * @param productCategory
	 * @return
	 */
	public Boolean selectProductCategoryNameIsHave(ProductCategory productCategory);
	
	/**
	 * 查询品类编码是否被占用
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
     * 分页查询评价统计
     * pageInfo:分页参数，CommodityComment:商品信息
     * @return
     */
    public PageInfo<ProductCategory> selectCommodityCommentByPage(PageInfo<ProductCategory> pageInfo, ProductCategory productCategory);
	
}
