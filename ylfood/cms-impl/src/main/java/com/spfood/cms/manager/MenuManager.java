/**
 * 
 */
package com.spfood.cms.manager;

import java.util.HashMap;
import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.cms.intf.domain.Menu;

/**
 * Menu Manager
 *
 * @author yanghuan
 * @createTime 2017-02-04 08:24:48
 *
 */
public interface MenuManager extends BaseManager<Menu> {

	/**
	 * 同步时插入数据
	 * @param hashMap  品类数据
	 * @return 菜单list集合
	 */
	List<Menu> synchroCategoryInfo(HashMap<String, String> hsahMap);

	/**
	 * 更新菜单信息
	 * @param menu 菜单对象,不为空 
	 * @return boolean 布尔值
	 */
	boolean updateOne(Menu meun);

	/**
	 * 为B2C提供接口
	 * @return 菜单list集合
	 */
	public List<Menu> getMenuInfoForB2C();

}
