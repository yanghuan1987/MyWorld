/**
 * 
 */
package com.spfood.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.cms.dao.SearchKeywordDao;
import com.spfood.cms.intf.domain.SearchKeyword;
import com.spfood.cms.manager.SearchKeywordManager;
import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;

/**
 * SearchKeyword Manager
 *
 * @author SHIJIE
 * @createTime 2017-01-22 14:44:57
 *
 */
@Service
public class SearchKeywordManagerImpl extends BaseManagerImpl<SearchKeyword> implements SearchKeywordManager {

	@Autowired
	private SearchKeywordDao dao;
	
	@Override
	protected BaseDao<SearchKeyword> getBaseDao() {
		return dao;
	}

}
