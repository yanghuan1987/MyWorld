/**
 * 
 */
package com.spfood.pms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.dao.CommodityCommentPictureDao;
import com.spfood.pms.intf.domain.CommodityCommentPicture;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.manager.CommodityCommentPictureManager;

/**
 * CommodityCommentPicture Manager
 *
 * @author yanghuan
 * @createTime 2017-06-14 16:25:36
 *
 */
@Service
public class CommodityCommentPictureManagerImpl extends BaseManagerImpl<CommodityCommentPicture> implements CommodityCommentPictureManager {

	@Autowired
	private CommodityCommentPictureDao dao;
	
	@Override
	protected BaseDao<CommodityCommentPicture> getBaseDao() {
		return dao;
	}

}
