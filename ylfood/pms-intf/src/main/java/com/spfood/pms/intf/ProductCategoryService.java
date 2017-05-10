package com.spfood.pms.intf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductCategoryProperty;

/**
 * 品类服务接口
 * @author fengjunchao 2016-12-07
 *
 */
public interface ProductCategoryService {

	/**
	 * 查询指定ID的品类详细信息，包含品类下的属性信息及属性下的属性值信息
	 * @param id
	 * @return
	 */
	public ProductCategory selectById(Serializable id);
	
	/**
	 * 查询品类列表（品类本身级联查询）,一级分类所有信息
	 * @return
	 */
	public List<ProductCategory> selectFirstLevelCategoryAll();
	
	/**
	 * 添加品类信息（一、二级）
	 * @param productCategory
	 */
	public ProductCategory insertFirstLevelOrSecondLevelCategroy(ProductCategory productCategory);
	
	/**
	 * 更新品类信息(一、二级)
	 * @param productCategory
	 * @return
	 */
	public int updateFirstLevelOrSecondLevelCategroy(ProductCategory productCategory);
	
	/**
	 * 添加或修改三级品类信息
	 * @param productCategory 品类基本属性
	 * @param productCategoryPropertyList 品类属性集合
	 * @return
	 */
	public Long insertOrUpdateThirdLevelCategory(ProductCategory productCategory,List<ProductCategoryProperty> productCategoryPropertyList);
	
	/**
	 * 验证名称是否被使用
	 * @param productCategory
	 * @return
	 */
	public Boolean selectProductCategoryNameIsHave(ProductCategory productCategory);
	
	/**
	 * 验证编码是否被使用
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
	
	public HashMap<String,String> selectCategoryAlltoMap();
	
    /**
     * 分页查询评价统计
     * pageInfo:分页参数，CommodityComment:商品信息
     * @return
     */
    public PageInfo<ProductCategory> selectCommodityCommentByPage(PageInfo<ProductCategory> pageInfo, ProductCategory productCategory);
	
}
