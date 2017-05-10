/**
 * 
 */
package com.spfood.pms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.dao.ProductCategoryPropertyDao;
import com.spfood.pms.intf.domain.ProductCategoryProperty;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.manager.ProductCategoryPropertyManager;

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

}
