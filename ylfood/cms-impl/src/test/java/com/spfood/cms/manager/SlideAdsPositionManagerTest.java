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
import com.spfood.cms.intf.domain.SlideAdsPosition;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class SlideAdsPositionManagerTest {

	@Resource
	private SlideAdsPositionManager slideAdsPositionManager;
	
	
	/**
	 * 测试获得banner数据
	 */
	@Test
	@Rollback
	@Transactional
	public void testSelectAll() throws Exception {
		List<SlideAdsPosition> list = slideAdsPositionManager.selectAll();
		if (list.size() == 0) {
			insert();
			List<SlideAdsPosition> list1 = slideAdsPositionManager.selectAll();
			// 拿到第一个元素
			SlideAdsPosition slideAdsPosition1 = list1.get(0);
			List<Advertisement> advertisements = slideAdsPosition1.getAdvertisements();
			// 拿到集合中的每一个图片对象
			for (Advertisement advertisement : advertisements) {
				assertEquals(slideAdsPosition1.getId(), advertisement.getSlideAdsPosId());
			}
		} else {
			SlideAdsPosition slideAdsPosition = list.get(0);
			List<Advertisement> advertisements1 = slideAdsPosition.getAdvertisements();
			for (Advertisement advertisement2 : advertisements1) {
				assertEquals(slideAdsPosition.getId(), advertisement2.getSlideAdsPosId());
				
			}
		}
	}
	
	
	/**
	 * 向数据库插入数据
	 */
	private void insert() {
		SlideAdsPosition slideAdsPosition = new SlideAdsPosition();
		slideAdsPosition.setName("第一个");
		slideAdsPosition.setMinSlot(0);
		slideAdsPositionManager.insert(slideAdsPosition);
	}
	
}
