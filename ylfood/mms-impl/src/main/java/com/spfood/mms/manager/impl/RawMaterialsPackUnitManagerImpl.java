/**
 * 
 */
package com.spfood.mms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.mms.dao.RawMaterialsPackUnitDao;
import com.spfood.mms.intf.domain.RawMaterialsPackUnit;
import com.spfood.mms.manager.RawMaterialsPackUnitManager;

/**
 * RawMaterialsPackUnit Manager
 *
 * @author yanghuan
 * @createTime 2017-06-21 11:15:40
 *
 */
@Service
public class RawMaterialsPackUnitManagerImpl extends BaseManagerImpl<RawMaterialsPackUnit> implements RawMaterialsPackUnitManager {

	@Autowired
	private RawMaterialsPackUnitDao dao;
	
	@Override
	protected BaseDao<RawMaterialsPackUnit> getBaseDao() {
		return dao;
	}

	@Override
	public List<RawMaterialsPackUnit> selectByRawMaterialsCode(String RawMaterialsCode) {
		return dao.selectByRawMaterialsCode(RawMaterialsCode);
	}
}
