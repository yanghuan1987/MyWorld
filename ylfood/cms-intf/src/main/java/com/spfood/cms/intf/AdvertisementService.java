package com.spfood.cms.intf;

import com.spfood.cms.intf.domain.Advertisement;

public interface AdvertisementService {

	/**
	 * 插入轮播图
	 * @param advertisement 轮播图对象,不为空
	 * @return Advertisement  轮播图对象
	 */
	Advertisement insert(Advertisement advertisement);
	
	
	/**
	 * 编辑轮播图
	 * @param advertisement 轮播图对象,不为空
	 * @return boolean 布尔值
	 */
	boolean update(Advertisement advertisement);
	
	/**
	 * 删除轮播图
	 * @param id 轮播图id
	 * @return boolean 布尔值
	 */
	boolean delete(Long id);
}
