/**
 * 
 */
package com.spfood.mms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.mms.dao.ProductCategoryPropertyValueDao;
import com.spfood.mms.intf.domain.ProductCategoryPropertyValue;
import com.spfood.mms.manager.ProductCategoryPropertyValueManager;

/**
 * ProductCategoryPropertyValue Manager实现类
 *
 * @author fengjunchao
 * @createTime 2016-12-06 14:30:35
 *
 */
@Service
public class ProductCategoryPropertyValueManagerImpl extends BaseManagerImpl<ProductCategoryPropertyValue> implements ProductCategoryPropertyValueManager {

	@Autowired
	private ProductCategoryPropertyValueDao productCategoryPropertyValueDao;
	
	@Override
	protected BaseDao<ProductCategoryPropertyValue> getBaseDao() {
		return productCategoryPropertyValueDao;
	}

}
