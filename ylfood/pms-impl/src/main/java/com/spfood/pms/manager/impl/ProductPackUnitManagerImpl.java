/**
 * 
 */
package com.spfood.pms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.dao.ProductPackUnitDao;
import com.spfood.pms.intf.domain.ProductPackUnit;
import com.spfood.pms.manager.ProductPackUnitManager;

/**
 * ProductPackUnit Manager
 *
 * @author yanghuan
 * @createTime 2017-03-14 14:16:36
 *
 */
@Service
public class ProductPackUnitManagerImpl extends BaseManagerImpl<ProductPackUnit> implements ProductPackUnitManager {

	@Autowired
	private ProductPackUnitDao dao;
	
	@Override
	protected BaseDao<ProductPackUnit> getBaseDao() {
		return dao;
	}

	@Override
	public List<ProductPackUnit> selectByProductCode(String productCode) {
		return dao.selectByProductCode(productCode);
	}
}
