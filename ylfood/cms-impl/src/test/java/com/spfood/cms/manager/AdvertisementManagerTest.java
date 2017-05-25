package com.spfood.cms.manager;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.cms.intf.domain.Advertisement;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class AdvertisementManagerTest {

	@Resource
	private AdvertisementManager advertisementManager;
	
	
	/**
	 * 测试展示轮播图数据
	 */
	@Test
	@Rollback
	@Transactional
	public void testSelectList() throws Exception {
		Advertisement advertisement = new Advertisement();
		advertisement.setSlideAdsPosId("cms_slide_ads_pos_pc");
		List<Advertisement> list = advertisementManager.selectList(advertisement);
		if (list.size() == 0) {
			insert();
			List<Advertisement> list1 = advertisementManager.selectList(advertisement);
			// 拿到搜索框下所有的关键字
			int size = list1.size();
			for (int i = 0; i < size; i++) {
				Advertisement advertisement1 = list1.get(i);
				assertEquals("cms_slide_ads_pos_pc", advertisement1.getSlideAdsPosId());
				assertEquals("图片来源地址" + i, advertisement1.getImageUrl());
				assertEquals("点击图片访问地址" + i, advertisement1.getTargetUrl());
				assertEquals(i, advertisement1.getDisplayOrder().intValue());
			}
		} else {
			Advertisement advertisement2 = list.get(0);
			assertEquals("cms_slide_ads_pos_pc", advertisement2.getSlideAdsPosId());
		}
	}
	
	/**
	 * 测试修改轮播图
	 */
	@Test
	@Rollback
	@Transactional
	public void testUpdate() throws Exception {
		insert();
		List<Advertisement> list = advertisementManager.selectAll();
		Advertisement advertisement = list.get(0);
		advertisement.setSlideAdsPosId("cms_slide_ads_pos_pc");
		advertisement.setImageUrl("图片来源地址");
		advertisementManager.updateById(advertisement);
		// 验证修改后的数据
		List<Advertisement> updateList = advertisementManager.selectAll();
		Advertisement advertisement1 = updateList.get(0);
		assertEquals("图片来源地址", advertisement1.getImageUrl());
	}
	
	/**
	 * 测试删除轮播图
	 */
	@Test
	@Rollback
	@Transactional
	public void testDelete() throws Exception {
		insert();
		List<Advertisement> list = advertisementManager.selectAll();
		Advertisement advertisement = list.get(0);
		Long id = advertisement.getId();
		advertisementManager.deleteById(id);
		// 验证删除后的数据
		assertEquals(null, advertisementManager.selectById(id));
	}
	
	
	/**
	 * 向数据库插入数据
	 */
	public void insert() {
		for (int i = 0; i < 6; i++) {
			Advertisement advertisement = new Advertisement();
			advertisement.setSlideAdsPosId("cms_slide_ads_pos_pc");
			advertisement.setImageUrl("图片来源地址" + i);
			advertisement.setTargetUrl("点击图片访问地址" + i);
			advertisement.setDisplayOrder(i);
			advertisementManager.insert(advertisement);
		}
	}
	
}
