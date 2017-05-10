package com.spfood.cms.intf;

import java.util.List;

import com.spfood.cms.intf.domain.SearchKeyword;

public interface SearchKeywordService {

	/**
	 * 新增关键字
	 * @param searchKeyword 关键字对象,不为空
	 * @return SearchKeyword 关键字对象
	 */
	SearchKeyword insert(SearchKeyword searchKeyword);
	
	/**
	 * 获取所有关键字
	 * @return 关键字集合
	 */
	List<SearchKeyword> selectAll();
	
	/**
	 * 修改关键字 
	 * @param searchKeyword 关键字对象,不为空
	 * @return boolean 布尔值
	 */
	boolean update(SearchKeyword searchKeyword);
	
}
