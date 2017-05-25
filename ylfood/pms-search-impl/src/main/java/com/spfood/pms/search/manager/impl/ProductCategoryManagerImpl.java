/**
 * 
 */
package com.spfood.pms.search.manager.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.search.dao.CommodityDao;
import com.spfood.pms.search.dao.ProductCategoryDao;
import com.spfood.pms.search.intf.domain.ProductCategory;
import com.spfood.pms.search.manager.ProductCategoryManager;

/**
 * ProductCategory Manager实现类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
@Service
public class ProductCategoryManagerImpl extends BaseManagerImpl<ProductCategory> implements ProductCategoryManager {

	@Resource
	private ProductCategoryDao productCategoryDao;
	@Resource
	private CommodityDao commodityDao;
	private static final Log log = LogFactory.getLog(ProductCategoryManagerImpl.class);

	@Override
	protected BaseDao<ProductCategory> getBaseDao() {
		return productCategoryDao;
	}


	@Override
	public List<ProductCategory> selectCategoryByCommodityName(
			String commodityName) {
		if(commodityName == null)
			return null;
		commodityName = commodityName.trim().replace("%", "").replace("'", "");
		commodityName = "%"+commodityName+"%";
		return productCategoryDao.selectCategoryByCommodityName(commodityName);
	}


	@Override
	public List<ProductCategory> selectCategoryByKeywords(List<String> keywords,String port) {
		if(keywords == null || keywords.size() < 1)
			return null;
		List<String> list = new ArrayList<String>();
		for(String str : keywords){
			str = str.trim().replace("%", "").replace("'", "");
			if(str.equals(""))
				continue;
			list.add(str);
		}
		if(list.size() < 1)
			return null;
		ProductCategory productCategory = new ProductCategory();
		productCategory.setKeywords(keywords);
		productCategory.setPort(port);
		return productCategoryDao.selectCategoryByKeywords(productCategory);
	}


	@Override
	public List<ProductCategory> selectCategoryNameByCommodityCode(String commodityCode) {
		try {
			ProductCategory thirdCategory = productCategoryDao.selectCategoryByCommodityCode(commodityCode);
			ProductCategory secondCategory = productCategoryDao.selectCategoryByCategoryCode(thirdCategory.getParentCategory().getCategoryCode());
			ProductCategory firstCategory = productCategoryDao.selectCategoryByCategoryCode(secondCategory.getParentCategory().getCategoryCode());
			List<ProductCategory> list = new LinkedList<ProductCategory>();
			list.add(firstCategory);
			list.add(secondCategory);
			list.add(thirdCategory);
			return list;
		} catch (Exception e) {
			log.error("search productCategory fail.commodityCode:["+commodityCode+"]", e);
			return null;
		}
	}


	@Override
	public List<ProductCategory> selectCategoryByCategoryCode(
			String categoryCode) {
		try {
			ProductCategory category = productCategoryDao.selectCategoryByCategoryCode(categoryCode);
			ProductCategory secondCategory = null;
			ProductCategory firstCategory = null;
			if(category.getParentCategory() != null && category.getParentCategory().getCategoryCode() != null){
				secondCategory = productCategoryDao.selectCategoryByCategoryCode(category.getParentCategory().getCategoryCode());
				if(secondCategory.getParentCategory() != null && secondCategory.getParentCategory().getCategoryCode() != null)
					firstCategory = productCategoryDao.selectCategoryByCategoryCode(secondCategory.getParentCategory().getCategoryCode());
			}
			List<ProductCategory> list = new LinkedList<ProductCategory>();
			if(firstCategory != null)
				list.add(firstCategory);
			if(secondCategory != null)
				list.add(secondCategory);
			if(category != null)
				list.add(category);
			return list;
		} catch (Exception e) {
			log.error("search productCategory fail.categoryCode:["+categoryCode+"]", e);
			return null;
		}
		
	}

	@Override
	public List<ProductCategory> selectCategoryChildrenByCode(
			String categoryCode) {
		return productCategoryDao.selectCategoryChildrenByCode(categoryCode);
	}
}
