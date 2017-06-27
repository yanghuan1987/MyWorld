/**
 * 
 */
package com.spfood.pms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.dao.RawMaterialsPropertyDao;
import com.spfood.pms.intf.domain.RawMaterialsProperty;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.manager.RawMaterialsPropertyManager;

/**
 * RawMaterialsProperty Manager
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:22:11
 *
 */
@Service
public class RawMaterialsPropertyManagerImpl extends BaseManagerImpl<RawMaterialsProperty> implements RawMaterialsPropertyManager {

	@Autowired
	private RawMaterialsPropertyDao dao;
	
	@Override
	protected BaseDao<RawMaterialsProperty> getBaseDao() {
		return dao;
	}

}
