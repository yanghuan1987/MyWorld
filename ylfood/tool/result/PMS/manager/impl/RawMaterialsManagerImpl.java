/**
 * 
 */
package com.spfood.pms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.dao.RawMaterialsDao;
import com.spfood.pms.intf.domain.RawMaterials;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.manager.RawMaterialsManager;

/**
 * RawMaterials Manager
 *
 * @author yanghuan
 * @createTime 2017-06-21 15:18:16
 *
 */
@Service
public class RawMaterialsManagerImpl extends BaseManagerImpl<RawMaterials> implements RawMaterialsManager {

	@Autowired
	private RawMaterialsDao dao;
	
	@Override
	protected BaseDao<RawMaterials> getBaseDao() {
		return dao;
	}

}
