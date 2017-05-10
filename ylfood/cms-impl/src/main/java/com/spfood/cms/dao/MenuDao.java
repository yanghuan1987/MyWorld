/**
 * 
 */
package com.spfood.cms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.cms.intf.domain.Menu;

/**
 *
 * Menu DAO
 *
 * @author yanghuan
 * @createTime 2017-02-04 08:24:48
 *
 */
public interface MenuDao extends BaseDao<Menu> {
	/**
	 * 批量更新菜单信息
	 * @param menuList
	 */
	public void updateList(List<Menu> menuList);

	/**
	 * 为B2C提供接口
	 *  @return 菜单的list集合
	 */
	public List<Menu> getMenuInfoForB2C();

	/**
	 * 修改所有子菜单状态
	 * @param menu 菜单对象,不为空
	 */
	void updateAllState(Menu menu);
}