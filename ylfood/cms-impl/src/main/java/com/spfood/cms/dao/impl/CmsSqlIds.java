package com.spfood.cms.dao.impl;

public interface CmsSqlIds {
	
	
	// 商品组版面管理
	public static final String SELECT_BY_DISPLAYORDER_IS_HAVE = "selectBydisplayOrderIsHave";
	//菜单管理用SQL
	public static final String MENU_UPDATE_LIST = "updateList";//同步时更新List数组
	public static final String MENU_SELECT_FOR_B2C = "selecForB2C";//B2C接口
	public static final String MENU_UPDATE_ALL_STATE = "updateAllState";// 修改所有子菜单
	
	/**
	 * 通过菜单编码查询子菜单
	 */
	public static final String SELECT_MENUS_BY_MENUCODE = "selectSonMenusByMenuCode";
	
	
	// 广告位用SQL
	public static final String SLIDEADSPOSITION_SELECT_ONE = "selectByName";//检索表中所有信息
	// 推荐商品
	public static final String POS_SELECT_ALL="selectPosAndCommotidy";
}
