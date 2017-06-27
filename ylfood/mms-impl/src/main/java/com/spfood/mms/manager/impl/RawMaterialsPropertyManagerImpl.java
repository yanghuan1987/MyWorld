/**
 * 
 */
package com.spfood.mms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.mms.dao.RawMaterialsPropertyDao;
import com.spfood.mms.intf.domain.RawMaterialsProperty;
import com.spfood.mms.manager.RawMaterialsPropertyManager;

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
