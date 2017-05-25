package com.spfood.cms.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.cms.intf.domain.Menu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})

public class MeunManagerTest {
	
	@Autowired
	private MenuManager menuManager;
	
//	private JSONArray list;
//	
//	private JSONArray list2;
//
//	private JSONArray list3;
	
	private HashMap<String, String> hashMap;
	private HashMap<String, String> hashMap1;
	private HashMap<String, String> hashMap2;
	
	public void setDate(){
		/*
		list = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("name", "catName1");
		obj.put("Categorycode", "2001");
		obj.put("ParentCategorycode", "");
		list.add(obj);
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "catName2");
		obj1.put("Categorycode", "2001001");
		obj1.put("ParentCategorycode", "2001");
		list.add(obj1);
		JSONObject obj2 = new JSONObject();
		obj2.put("name", "catName3");
		obj2.put("Categorycode", "2001001001");
		obj2.put("ParentCategorycode", "2001001");
		list.add(obj2);
		

		list2 = new JSONArray();
		JSONObject obj3 = new JSONObject();
		obj3.put("name", "catNameup1");
		obj3.put("Categorycode", "2001");
		obj3.put("ParentCategorycode", "");
		list2.add(obj3);
		JSONObject obj4 = new JSONObject();
		obj4.put("name", "catNameup2");
		obj4.put("Categorycode", "2001001");
		obj4.put("ParentCategorycode", "2001");
		list2.add(obj4);
		JSONObject obj5 = new JSONObject();
		obj5.put("name", "catName3");
		obj5.put("Categorycode", "2001001001");
		obj5.put("ParentCategorycode", "2001001");
		list2.add(obj5);
		JSONObject obj6 = new JSONObject();
		obj6.put("name", "obj6");
		obj6.put("Categorycode", "3001");
		obj6.put("ParentCategorycode", "");
		list2.add(obj6);
		
		list3 = new JSONArray();
		JSONObject obj7 = new JSONObject();
		obj7.put("name", "obj6");
		obj7.put("Categorycode", "2001");
		obj7.put("ParentCategorycode", "");
		list3.add(obj7);
		JSONObject obj8 = new JSONObject();
		obj8.put("name", "obj7");
		obj8.put("Categorycode", "2001002");
		obj8.put("ParentCategorycode", "2001");
		list3.add(obj8);*/
		
		hashMap = new HashMap<String, String>();
		hashMap.put("2001", "catNameup1,");
		hashMap.put("2001001", "catName2,2001");
		hashMap.put("2001001001", "catName3,2001001");
		
		hashMap1 = new HashMap<String, String>();
		hashMap1.put("2001", "catNameup1a,");
		hashMap1.put("2001001", "catName2,2001");
		hashMap1.put("2001001001", "catName3a,2001001");
		hashMap1.put("3001", "catNameup4,");
		hashMap1.put("3001001", "catNameup5,3001");
		
		hashMap2 = new HashMap<String, String>();
		hashMap2.put("2001", "catNameup1b,");
		hashMap2.put("3001001001", "catNameup6,3001001");
		
		}
	
	@Test
	@Rollback
	@Transactional
	public void synchroCategoryInfo(){
		setDate();
		//初次同步
		menuManager.synchroCategoryInfo(hashMap);
		List<Menu> list = new ArrayList<Menu>();
		list = menuManager.selectAll();
		int check = 0;//计数器
		for (Menu menu : list) {
			if ("2001".equals(menu.getMenuCode())) {
				assertEquals("catNameup1",menu.getName());
				assertEquals("",menu.getParent());
				check++;
			}else if ("2001001".equals(menu.getMenuCode())) {
				assertEquals("catName2",menu.getName());
				assertEquals("2001",menu.getParent());
				check = check + 2;
			}else if ("2001001001".equals(menu.getMenuCode())) {
				assertEquals("catName3",menu.getName());
				assertEquals("2001001",menu.getParent());
				check = check + 3;
			}
		}
		assertEquals(6, check);//成功插入3条数据
		
		//第二次同步
		check = 0;//计数器初始化
		menuManager.synchroCategoryInfo(hashMap1);
		List<Menu> list2 = new ArrayList<Menu>();
		list2 = menuManager.selectAll();
		for (Menu menu : list2) {
			if ("2001".equals(menu.getMenuCode())) {
				assertEquals("catNameup1a",menu.getName());
				assertEquals("",menu.getParent());
				check++;
			}else if ("2001001".equals(menu.getMenuCode())) {
				assertEquals("catName2",menu.getName());
				assertEquals("2001",menu.getParent());
				check = check + 2;
			}else if ("2001001001".equals(menu.getMenuCode())) {
				assertEquals("catName3a",menu.getName());
				assertEquals("2001001",menu.getParent());
				check = check + 3;
			}else if ("3001".equals(menu.getMenuCode())) {
				assertEquals("catNameup4",menu.getName());
				assertEquals("",menu.getParent());
				check = check + 4;
			}else if ("3001001".equals(menu.getMenuCode())) {
				assertEquals("catNameup5",menu.getName());
				assertEquals("3001",menu.getParent());
				check = check + 5;
			}
		}
		assertEquals(15, check);//成功插入2条数据,更新2条数据

		//第三次同步
		check = 0;//计数器初始化
		menuManager.synchroCategoryInfo(hashMap2);
		List<Menu> list3 = new ArrayList<Menu>();
		list3 = menuManager.selectAll();
		for (Menu menu : list3) {
			if ("2001".equals(menu.getMenuCode())) {
				assertEquals("catNameup1b",menu.getName());
				assertEquals("",menu.getParent());
				check++;
			}else if ("2001001".equals(menu.getMenuCode())) {
				assertEquals("catName2",menu.getName());
				assertEquals("2001",menu.getParent());
				check = check + 2;
			}else if ("2001001001".equals(menu.getMenuCode())) {
				assertEquals("catName3a",menu.getName());
				assertEquals("2001001",menu.getParent());
				check = check + 3;
			}else if ("3001".equals(menu.getMenuCode())) {
				assertEquals("catNameup4",menu.getName());
				assertEquals("",menu.getParent());
				check = check + 4;
			}else if ("3001001".equals(menu.getMenuCode())) {
				assertEquals("catNameup5",menu.getName());
				assertEquals("3001",menu.getParent());
				check = check + 5;
			}else if ("3001001001".equals(menu.getMenuCode())) {
				assertEquals("catNameup6",menu.getName());
				assertEquals("3001001",menu.getParent());
				check = check + 6;
			}
		}
		assertEquals(21, check);//成功插入1条数据,更新1条数据
	}
	@Test
	@Rollback
	@Transactional
	public void updateone(){
		setDate();
		//初次同步
		menuManager.synchroCategoryInfo(hashMap);
		List<Menu> list = new ArrayList<Menu>();
		list = menuManager.selectAll();
		Menu menu2 = new Menu();
		for (Menu menu : list) {
			if ("2001".equals(menu.getMenuCode())) {
				menu2.setMenuCode(menu.getMenuCode());
				menu2.setName(menu.getName());
				menu2.setParent(menu.getParent());
				menu2.setDisplayOrder(1);
				menu2.setVisualable(1);
			}
		}
		int check = 0;
		menuManager.updateOne(menu2);
		List<Menu> list2 = new ArrayList<Menu>();
		list2 = menuManager.selectAll();
		for (Menu menu : list2) {
			if ("2001".equals(menu.getMenuCode())) {
				assertEquals(1,menu.getVisualable().intValue());
				assertEquals(1,menu.getDisplayOrder().intValue());
				check++;
			}
		}
		assertEquals(1,check);
	}
	
	@Test
	public void selectbySomethings() {

		Menu menu = new Menu();
		menu.setVisualable(1);
		List<Menu> list2 = new ArrayList<Menu>();
		list2 = menuManager.getMenuInfoForB2C();

		for (Menu menu2 : list2) {
			menu2.getMenuCode();
		}
	}
	
	/**
	 * 测试通过菜单编码获得子菜单
	 * @throws Exception
	 */
	@Test
	public void testGetSonMenus() throws Exception {
		String menuCode = "01001";
		List<Menu> sonMenus = menuManager.getSonMenus(menuCode);
		assertNotNull(sonMenus);
	}
	
}
