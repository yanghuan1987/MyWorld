/**
 * 
 */
package com.spfood.cms.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.cms.dao.MenuDao;
import com.spfood.cms.intf.domain.Menu;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.cms.manager.MenuManager;

/**
 * Menu Manager
 *
 * @author yanghuan
 * @createTime 2017-02-04 08:24:48
 *
 */
@Service
public class MenuManagerImpl extends BaseManagerImpl<Menu> implements MenuManager {

	private static final Log log = LogFactory.getLog(MenuManagerImpl.class);
	
	@Autowired
	private MenuDao dao;
	
	@Override
	protected BaseDao<Menu> getBaseDao() {
		return dao;
	}


	/**
	 * 同步时插入数据
	 * @param hashMap 品类数据
	 * @return 菜单list集合
	 */
	@Transactional
	public List<Menu> synchroCategoryInfo(HashMap<String,String> hashMap) {
		// 接受查询所有的结果
		List<Menu> reslutlist = new ArrayList<Menu>();
		// 需要添加数据的集合
		List<Menu> addList = new ArrayList<Menu>();
		// 需要更新数据的集合
		List<Menu> upList = new ArrayList<Menu>();
		reslutlist = selectAll(); // 查询所有菜单赋值给集合
		HashMap<String,String> resulthashMap = new HashMap<String, String>(); // 创建map用来放菜单编码,和父菜单编码
		log.info("Before the synchronization for all the goods information, "
				+ "need to be synchronized category information, a total of "+hashMap.size()+",Article "+reslutlist.size()+" the existing data");
		if (reslutlist.size() != 0) { // 集合不为空,把菜单编码,和父菜单编码放入创建的map中
			//CMS数据生成hashmap
			for (Menu menu : reslutlist) { // 循环放入菜单编码,和父菜单编码到map中
				String nameParentString = menu.getName()+","+menu.getParent();
				resulthashMap.put(menu.getMenuCode(), nameParentString);
			}
			for (String str : hashMap.keySet()) { // 品类信息放入list集合
				if(resulthashMap.containsKey(str)){ // 如果map中存在此信息就放入更新数据的集合
					if(!resulthashMap.get(str).equals(hashMap.get(str))){ // resulthashMap中存在且在hashMap不存在
						// 拼接需要的key
						String strName = hashMap.get(str).substring(0, hashMap.get(str).indexOf(","));
						Menu menuUp = new Menu(); // 初始化menu对象并设置值
						menuUp.setMenuCode(str);
						menuUp.setName(strName);
						upList.add(menuUp); // 添加到需要更新数据的集合
					}
				}else { // 不存在就直接放入添加数据的集合
					String strName = hashMap.get(str).substring(0, hashMap.get(str).indexOf(","));
					String strParentCode =  hashMap.get(str).substring(hashMap.get(str).indexOf(",")+1,hashMap.get(str).length());
					Menu menuAdd = new Menu();
					menuAdd.setMenuCode(str);
					menuAdd.setName(strName);
					menuAdd.setParent(strParentCode);
					addList.add(menuAdd); // 添加到需要新增数据集合
				}
			}
		} else { // 没有拿到菜单数据就添加到新增数据集合
			for (String str : hashMap.keySet()) {
				String strName = hashMap.get(str).substring(0, hashMap.get(str).indexOf(","));
				String strParentCode =  hashMap.get(str).substring(hashMap.get(str).indexOf(",")+1,hashMap.get(str).length());
				Menu menuAdd = new Menu();
				menuAdd.setMenuCode(str);
				menuAdd.setName(strName);
				menuAdd.setParent(strParentCode);
				addList.add(menuAdd); // 添加到需要新增数据集合
			}
		}
		// 集合不为空更新数据到数据库
		if (addList.size() != 0) {
			log.info("New data synchronization");
			insertInBatch(addList);
		}
		// 集合为空插入修改数据到数据库中
		if (upList.size() != 0) {
			log.info("Synchronous update data");
			dao.updateList(upList);
		}
		//操作结束后重新查询数据
		log.info("Retrieve all the data in the table and return");
		return selectAll();
	}

	/**
	 * 更新菜单信息
	 * @param menu 菜单对象,不为空 
	 * @return boolean 布尔值
	 */
	@Transactional
	public boolean updateOne(Menu menu) {
		log.info("Update the menu in the page editor");
		// 更新数据
		updateById(menu);
		Integer visualable = menu.getVisualable();
		// 如果修改后菜单的状态为不可见,就把所有子菜单设置为不可见
		if (visualable == 0) {
			dao.updateAllState(menu);
		}
		return true;
	}

	/**
	 * 为B2C提供接口
	 * @return 菜单list集合
	 */
	public List<Menu> getMenuInfoForB2C() {
		log.info("B2CCall interface to get the menu data");
		return dao.getMenuInfoForB2C();
	}
	
}
