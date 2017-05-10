/**
 * 
 */
package com.spfood.pms.search.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.search.dao.CommodityCommentTypeDao;
import com.spfood.pms.search.intf.domain.CommodityCommentType;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.search.manager.CommodityCommentTypeManager;

/**
 * CommodityCommentType Manager
 *
 * @author zhangpei
 * @createTime 2017-04-13 13:19:20
 *
 */
@Service
public class CommodityCommentTypeManagerImpl extends BaseManagerImpl<CommodityCommentType> implements CommodityCommentTypeManager {

	@Autowired
	private CommodityCommentTypeDao dao;
	
	@Override
	protected BaseDao<CommodityCommentType> getBaseDao() {
		return dao;
	}

}
