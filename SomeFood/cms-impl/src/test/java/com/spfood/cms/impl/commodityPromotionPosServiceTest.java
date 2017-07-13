package com.spfood.cms.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.cms.intf.CommodityPromotionPosService;
import com.spfood.cms.intf.domain.PromotedCommodity;
import com.spfood.cms.intf.utils.PromotedCommodityName;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class commodityPromotionPosServiceTest {

	@Autowired
	private CommodityPromotionPosService commodityPromotionPosService;
	
	@Test
    @Rollback
    @Transactional
    public void getCommPromotePosList() {
		String name = PromotedCommodityName.HOMEPAGE.getValue();
		List<PromotedCommodity> list = commodityPromotionPosService.getCommPromotePosList(name);
		System.out.println(list.get(0).getCommodityName());
		
	}
    
}
