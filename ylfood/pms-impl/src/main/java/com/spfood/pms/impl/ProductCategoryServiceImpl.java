package com.spfood.pms.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.ProductCategoryService;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductCategoryProperty;
import com.spfood.pms.manager.ProductCategoryManager;
/**
 * ProductCategoryService 实现类
 * @author fengjunchao 2016-12-23
 *
 */
public class ProductCategoryServiceImpl implements ProductCategoryService{
	
	@Autowired
	private ProductCategoryManager productCategoryManager;

	@Override
	public ProductCategory selectById(Serializable id) {
		return productCategoryManager.selectById(id);
	}

	@Override
	public List<ProductCategory> selectFirstLevelCategoryAll() {
		return productCategoryManager.selectAll();
	}

	@Override
	public ProductCategory insertFirstLevelOrSecondLevelCategroy(ProductCategory productCategory) {
		return productCategoryManager.insertFirstLevelOrSecondLevelCategroy(productCategory);
	}

	@Override
	public int updateFirstLevelOrSecondLevelCategroy(ProductCategory productCategory) {
		return productCategoryManager.updateFirstLevelOrSecondLevelCategroy(productCategory);
	}

	@Override
	public Long insertOrUpdateThirdLevelCategory(ProductCategory productCategory,
			List<ProductCategoryProperty> productCategoryPropertyList) {
		return productCategoryManager.insertOrUpdateThirdLevelCategory(productCategory, productCategoryPropertyList);
	}

	@Override
	public Boolean selectProductCategoryNameIsHave(
			ProductCategory productCategory) {
		return productCategoryManager.selectProductCategoryNameIsHave(productCategory);
	}

	@Override
	public Boolean selectProductCategoryCodeIsHave(
			ProductCategory productCategory) {
		return productCategoryManager.selectProductCategoryCodeIsHave(productCategory);
	}

	@Override
	public List<ProductCategory> selectCategoryChildrenByCode(
			String categoryCode) {
		return productCategoryManager.selectCategoryChildrenByCode(categoryCode);
	}

	@Override
	public List<ProductCategory> selectCategoryAll() {
		return productCategoryManager.selectCategoryAll();
	}

	@Override
	public HashMap<String, String> selectCategoryAlltoMap() {
		//定义map
		HashMap<String,String> map = new HashMap<String, String>();
		//遍历获取的品类信息
		for(ProductCategory category:productCategoryManager.selectCategoryAll()){
			//map存儲怕品类信息   k:品类编码，value:品类编码+逗号+父类编码（categoryCode+,+pcategoryCode）
			map.put(category.getCategoryCode(),
					category.getCategoryName()+","+
					(category.getParentCategory()==null?"":category.getParentCategory().getCategoryCode()));
		}
		return map;
	}

    @Override
    public PageInfo<ProductCategory> selectCommodityCommentByPage(PageInfo<ProductCategory> pageInfo, ProductCategory productCategory) {
    	return productCategoryManager.selectCommodityCommentByPage(pageInfo, productCategory);
    }
}
