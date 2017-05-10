/**
 * 
 */
package com.spfood.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.cms.dao.MenuDao;
import com.spfood.cms.intf.domain.Menu;

/**
 *
 * Menu DAO
 *
 * @author yanghuan
 * @createTime 2017-02-04 08:24:48
 *
 */
@Repository
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao, CmsSqlIds {

	/**
	 * 批量更新菜单信息
	 * @param menuList
	 * @return boolean 布尔值
	 */
	public void updateList(List<Menu> menuList) {
		try {
			sqlSessionTemplate.update(getSqlName(MENU_UPDATE_LIST), menuList);
		} catch (Exception e) {
			throw new PersistenceException("Menu.Dao.updateList", e,
					getSqlName(MENU_UPDATE_LIST));
		}
	}

	/**
	 * 为B2C提供接口
	 *  @return 菜单的list集合
	 */
	public List<Menu> getMenuInfoForB2C() {
		try {
			return sqlSessionTemplate
					.selectList(getSqlName(MENU_SELECT_FOR_B2C));
		} catch (Exception e) {
			throw new PersistenceException("Menu.Dao.getMenuInfoForB2C", e,
					getSqlName(MENU_SELECT_FOR_B2C));
		}
	}

	/**
	 * 修改所有子菜单状态
	 * @param menu 菜单对象,不为空
	 * @return boolean 布尔值
	 */
	@Override
	public void updateAllState(Menu menu) {
		try {
			sqlSessionTemplate.update(getSqlName(MENU_UPDATE_ALL_STATE), menu);
		} catch (Exception e) {
			throw new PersistenceException("Menu.Dao.updateAllState", e,
					getSqlName(MENU_UPDATE_ALL_STATE), menu);
		}
	}

}
