/**
 * 
 */
package com.spfood.pms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.dao.CommodityCommentTypeDao;
import com.spfood.pms.intf.domain.CommodityCommentType;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.manager.CommodityCommentTypeManager;

/**
 * CommodityCommentType Manager
 *
 * @author yanghuan
 * @createTime 2017-04-13 16:33:53
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
	
	public List<CommodityCommentType> selectAllInfo() {
		return dao.selectAll();
	}
}
