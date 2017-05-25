package com.spfood.cms.intf;

import java.util.HashMap;
import java.util.List;

import com.spfood.cms.intf.domain.Menu;

public interface MenuService {

	/**
	 * 获取菜单信息
	 * @param menu  菜单对象,不为空
	 * @return 菜单list集合
	 */
	List<Menu> selectAll();

	/**
	 * 同步时插入数据
	 * @param hashMap
	 * @return 菜单list集合
	 */
	List<Menu> synchroCategoryInfo(HashMap<String, String> hashMap);

	/**
	 * 更新菜单信息
	 * @param menu 菜单对象,不为空
	 * @return boolean 布尔值
	 */
	boolean updateOne(Menu menu);

	/**
	 * 为B2C提供接口
	 * @return 菜单list集合
	 */
	public List<Menu> getMenuInfoForB2C();
	
	/**
	 * 通过菜单编码查询所有显示的子菜单
	 * @param menuCode 菜单编码
	 * @return menu对象,里面只封装了产品编码和名称
	 */
	List<Menu> getSonMenus(String menuCode);
}
