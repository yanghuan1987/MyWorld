/**
 * 
 */
package com.spfood.mms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.mms.dao.ProductCategoryPropertyDao;
import com.spfood.mms.intf.domain.ProductCategoryProperty;
import com.spfood.mms.manager.ProductCategoryPropertyManager;

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
