/**
 * 
 */
package com.spfood.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.cms.dao.AdvertisementDao;
import com.spfood.cms.intf.domain.Advertisement;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.cms.manager.AdvertisementManager;

/**
 * Advertisement Manager
 *
 * @author SHIJIE
 * @createTime 2017-02-07 20:50:18
 *
 */
@Service
public class AdvertisementManagerImpl extends BaseManagerImpl<Advertisement> implements AdvertisementManager {

	@Autowired
	private AdvertisementDao dao;
	
	@Override
	protected BaseDao<Advertisement> getBaseDao() {
		return dao;
	}

}
