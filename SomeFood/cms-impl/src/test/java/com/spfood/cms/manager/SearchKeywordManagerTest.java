package com.spfood.cms.manager;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.cms.intf.domain.SearchKeyword;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class SearchKeywordManagerTest {

	@Resource
	private SearchKeywordManager searchKeywordManager;
	
	
	/**
	 * 测试展示关键字数据
	 */
	@Test
	@Rollback
	@Transactional
	public void testSelectAll() throws Exception {
		List<SearchKeyword> list = searchKeywordManager.selectAll();
		if (list.size() == 0) {
			insert();
			List<SearchKeyword> list1 = searchKeywordManager.selectAll();
			// 拿到搜索框中的关键字
			SearchKeyword searchKeyword = list1.get(0);
			assertEquals("搜索框", searchKeyword.getKeyword());
			assertEquals(false, searchKeyword.getIsDefault());
			assertEquals(0, searchKeyword.getDisplayOrder().intValue());
			
			// 拿到搜索框下所有的关键字
			int size = list1.size();
			for (int i = 1; i < size; i++) {
				SearchKeyword keyword = list1.get(i);
				assertEquals("关键字" + i, keyword.getKeyword());
				assertEquals(false, keyword.getIsDefault());
				assertEquals(i, keyword.getDisplayOrder().intValue());
			}
		} else {
			SearchKeyword searchKeyword = list.get(0);
			assertEquals(true, searchKeyword.getIsDefault());
		}
	}
	
	/**
	 * 测试修改关键字
	 */
	@Test
	@Rollback
	@Transactional
	public void testUpdate() throws Exception {
		insert();
		List<SearchKeyword> list = searchKeywordManager.selectAll();
		SearchKeyword searchKeyword = list.get(0);
		searchKeyword.setKeyword("精品五花肉");
		searchKeyword.setIsDefault(true);
		searchKeywordManager.updateById(searchKeyword);
		// 验证修改后的数据
		List<SearchKeyword> updateList = searchKeywordManager.selectAll();
		SearchKeyword keyword = updateList.get(0);
		assertEquals("精品五花肉", keyword.getKeyword());
	}
	
	
	/**
	 * 向数据库插入数据
	 */
	private void insert() {
		SearchKeyword searchKeyword = new SearchKeyword();
		searchKeyword.setKeyword("搜索框");
		searchKeyword.setIsDefault(true);
		searchKeyword.setDisplayOrder(0);
		searchKeywordManager.insert(searchKeyword);
		for (int i = 1; i < 9; i++) {
			SearchKeyword keyword = new SearchKeyword();
			keyword.setKeyword("关键字" + i);
			keyword.setIsDefault(false);
			keyword.setDisplayOrder(i);
			searchKeywordManager.insert(keyword);
		}
		
	}
	
}
