/**
 * 
 */
package com.spfood.pms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.dao.RawMaterialsPictureDao;
import com.spfood.pms.intf.domain.RawMaterialsPicture;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.manager.RawMaterialsPictureManager;

/**
 * RawMaterialsPicture Manager
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:18:40
 *
 */
@Service
public class RawMaterialsPictureManagerImpl extends BaseManagerImpl<RawMaterialsPicture> implements RawMaterialsPictureManager {

	@Autowired
	private RawMaterialsPictureDao dao;
	
	@Override
	protected BaseDao<RawMaterialsPicture> getBaseDao() {
		return dao;
	}

}
