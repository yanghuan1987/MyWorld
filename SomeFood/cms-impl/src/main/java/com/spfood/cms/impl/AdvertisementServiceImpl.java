package com.spfood.cms.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.cms.intf.AdvertisementService;
import com.spfood.cms.intf.domain.Advertisement;
import com.spfood.cms.manager.AdvertisementManager;

public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private AdvertisementManager advertisementManager;
	
	
	/**
	 * 编辑轮播图
	 * @param advertisement 轮播图对象,不为空
	 * @return boolean 布尔值
	 */
	@Override
	public boolean update(Advertisement advertisement) {
		int number = advertisementManager.updateById(advertisement);
		if (number > 0) {
			return true;	
		} else {
			return false;
		}
	}

	/**
	 * 插入轮播图
	 * @param advertisement 轮播图对象,不为空
	 * @return Advertisement  轮播图对象
	 */
	@Override
	public Advertisement insert(Advertisement advertisement) {
		advertisementManager.insert(advertisement);
		return advertisement;
	}

	
	/**
	 * 删除轮播图
	 * @param id 轮播图id
	 * @return boolean 布尔值
	 */
	@Override
	public boolean delete(Long id) {
		int number = advertisementManager.deleteById(id);
		if (number > 0) {
			return true;	
		} else {
			return false;
		}
	}
	
}
