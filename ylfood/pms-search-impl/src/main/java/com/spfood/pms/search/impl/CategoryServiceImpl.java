package com.spfood.pms.search.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.pms.search.intf.CategorySearchService;
import com.spfood.pms.search.intf.domain.ProductCategory;
import com.spfood.pms.search.manager.ProductCategoryManager;

/**
 * categoryService 实现
 * @author fengjunchao 2017-02-18
 *
 */
public class CategoryServiceImpl implements CategorySearchService{
	
	private static final Log log = LogFactory.getLog(CategoryServiceImpl.class);
	
	@Autowired
	private ProductCategoryManager productCategoryManager;

	@Override
	public List<ProductCategory> selectCategoryByCategoryCode(
			String categoryCode) {
		log.info("selectCategoryByCategoryCode by ["+categoryCode+"]");
		return productCategoryManager.selectCategoryByCategoryCode(categoryCode);
	}
	
	@Override
	public List<ProductCategory> selectCategoryChildrenByCode(
			String categoryCode) {
		log.info("selectCategoryByCategoryCode by ["+categoryCode+"]");
		return productCategoryManager.selectCategoryChildrenByCode(categoryCode);
	}
}
