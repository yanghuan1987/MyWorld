/**
 * 
 */
package com.spfood.pms.search.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.search.dao.ProductCategoryPropertyDao;
import com.spfood.pms.search.intf.domain.ProductCategoryProperty;
import com.spfood.pms.search.manager.ProductCategoryPropertyManager;

/**
 * ProductCategoryProperty Manager实现类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
@Service
public class ProductCategoryPropertyManagerImpl extends BaseManagerImpl<ProductCategoryProperty> implements ProductCategoryPropertyManager {

	@Autowired
	private ProductCategoryPropertyDao productCategoryPropertyDao;
	
	@Override
	protected BaseDao<ProductCategoryProperty> getBaseDao() {
		return productCategoryPropertyDao;
	}

	@Override
	public List<ProductCategoryProperty> selectPropertyByCommodityName(
			String commodityName) {
		if(commodityName == null)
			return null;
		commodityName = commodityName.trim().replace("%", "").replace("'", "");
		commodityName = "%"+commodityName+"%";
		return productCategoryPropertyDao.selectPropertyByCommodityName(commodityName);
	}

}
