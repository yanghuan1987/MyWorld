package com.spfood.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.cms.intf.SearchKeywordService;
import com.spfood.cms.intf.domain.SearchKeyword;
import com.spfood.cms.manager.SearchKeywordManager;

public class SearchKeywordServiceImpl implements SearchKeywordService {

	@Autowired
	private SearchKeywordManager searchKeywordManager;
	
	/**
	 * 获取所有关键字
	 * @return 关键字集合
	 */
	@Override
	public List<SearchKeyword> selectAll() {
		return searchKeywordManager.selectAll();
	}

	/**
	 * 修改关键字 
	 * @param searchKeyword 关键字对象,不为空
	 * @return boolean 布尔值
	 */
	@Override
	public boolean update(SearchKeyword searchKeyword) {
		int number = searchKeywordManager.updateById(searchKeyword);
		if (number > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 新增关键字
	 * @param searchKeyword 关键字对象,不为空
	 * @return SearchKeyword 关键字对象
	 */
	@Override
	public SearchKeyword insert(SearchKeyword searchKeyword) {
		searchKeywordManager.insert(searchKeyword);
		return searchKeyword;
	}
	
	
}
