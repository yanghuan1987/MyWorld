package com.spfood.cms.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.spfood.cms.intf.MenuService;
import com.spfood.cms.intf.domain.Menu;
import com.spfood.cms.manager.MenuManager;

public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuManager menuManager;

	/**
	 * 获取菜单信息
	 * @param menu 菜单对象,不为空
	 * @return 菜单list集合
	 */
	@Override
	public List<Menu> selectAll() {
		return menuManager.selectAll();
	}

	/**
	 * 同步时插入数据
	 * @param hashMap
	 * @return 菜单list集合
	 */
	@Override
	public List<Menu> synchroCategoryInfo(HashMap<String, String> hashMap) {
		return menuManager.synchroCategoryInfo(hashMap);
	}

	/**
	 * 更新菜单信息
	 * @param menu 菜单对象,不为空
	 * @return boolean 布尔值
	 */
	@Override
	public boolean updateOne(Menu Menu) {
		return menuManager.updateOne(Menu);
	}

	/**
	 * 为B2C提供接口
	 * @return 菜单list集合
	 */
	public List<Menu> getMenuInfoForB2C() {
		return menuManager.getMenuInfoForB2C();
	}
}
