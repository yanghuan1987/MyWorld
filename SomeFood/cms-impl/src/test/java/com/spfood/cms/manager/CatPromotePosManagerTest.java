package com.spfood.cms.manager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.cms.intf.domain.CatPromotePos;
import com.spfood.cms.intf.domain.CatPromotedCommodity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})

public class CatPromotePosManagerTest {
	
	@Resource
	private CatPromotePosManager catPromotePosManager;
	
	private CatPromotePos cPos1;
	private CatPromotePos cPos2;
	
	public void setDate(){
		//INSERT商品组1
		cPos1 = new CatPromotePos();
		List<CatPromotedCommodity> list1 = new ArrayList<CatPromotedCommodity>();
		cPos1.setCategoryName("name");
		cPos1.setImageUrl("url");
		cPos1.setDisplayOrder(98);
		cPos1.setAdvert("广告词");
		//INSERT明细1
		CatPromotedCommodity commodity1 = new CatPromotedCommodity();
		commodity1.setCommodityCode("1111");
		commodity1.setCommodityImgUrl("2222");
		commodity1.setCommodityName("3333");
		commodity1.setCommoditySpec("4444");
		commodity1.setProductCode("5555");
		commodity1.setDisplayOrder(98);
		list1.add(commodity1);
		//INSERT明细2
		CatPromotedCommodity commodity2 = new CatPromotedCommodity();
		commodity2.setCommodityCode("6666");
		commodity2.setCommodityImgUrl("7777");
		commodity2.setCommodityName("8888");
		commodity2.setCommoditySpec("9999");
		commodity2.setProductCode("0000");
		commodity2.setDisplayOrder(99);
		list1.add(commodity2);
		cPos1.setCatPromotedCommodityList(list1);
		
		
		}
	
//	@Test
//	public void creatDate(){
//		for (int j = 0; j < 4; j++) {
//			//INSERT商品组1
//			cPos1 = new CatPromotePos();
//			List<CatPromotedCommodity> list = new ArrayList<CatPromotedCommodity>();
//			cPos1.setCategoryName("name"+j);
//			cPos1.setImageUrl("url"+j);
//			cPos1.setDisplayOrder(j+1);
//			for (int i = 0; i < 8; i++) {
//				CatPromotedCommodity commodity = new CatPromotedCommodity();
//				commodity.setCommodityCode("aaaa"+i);
//				commodity.setCommodityImgUrl("bbbb"+i);
//				commodity.setCommodityName("cccc"+i);
//				commodity.setCommoditySpec("dddd"+i);
//				commodity.setProductCode("eeee"+i);
//				commodity.setDisplayOrder(i+1);
//				list.add(commodity);
//			}
//			cPos1.setCatPromotedCommodityList(list);
//			catPromotePosManager.addOne(cPos1);
//		}
//	}
	
	
	@Test
	@Rollback
	@Transactional
	public void insert(){
		setDate();
		//初次同步
		catPromotePosManager.addOne(cPos1);
		List<CatPromotePos> list = new ArrayList<CatPromotePos>();
		CatPromotePos catPromotePosn = new CatPromotePos();
		list = catPromotePosManager.getCatPromotePosInfo(catPromotePosn);

		int i =0;
		for (CatPromotePos catPromotePos : list) {
			if ("name".equals(catPromotePos.getCategoryName())) {
				assertEquals("url",catPromotePos.getImageUrl());
				assertEquals(98,catPromotePos.getDisplayOrder().intValue());
				for (CatPromotedCommodity commodity : catPromotePos.getCatPromotedCommodityList()) {
					if ("1111".equals(commodity.getCommodityCode())) {
						assertEquals(catPromotePos.getId(),commodity.getCatPromotePosId());
						assertEquals("2222",commodity.getCommodityImgUrl());
						assertEquals("3333",commodity.getCommodityName());
						assertEquals("4444",commodity.getCommoditySpec());
						assertEquals("5555",commodity.getProductCode());
						assertEquals(98,commodity.getDisplayOrder().intValue());
						i++;
					}
					if ("6666".equals(commodity.getCommodityCode())) {
						assertEquals(catPromotePos.getId(),commodity.getCatPromotePosId());
						assertEquals("7777",commodity.getCommodityImgUrl());
						assertEquals("8888",commodity.getCommodityName());
						assertEquals("9999",commodity.getCommoditySpec());
						assertEquals("0000",commodity.getProductCode());
						assertEquals(99,commodity.getDisplayOrder().intValue());
						i++;
					}
				}
			}
		}

		assertEquals(2,i);
	}
	
	@Test
	@Rollback
	@Transactional
	public void update(){
		setDate();
		catPromotePosManager.addOne(cPos1);
		List<CatPromotePos> listA = new ArrayList<CatPromotePos>();
		listA = catPromotePosManager.getCatPromotePosInfo(cPos1);
		for (CatPromotePos catPromotePos : listA) {
			cPos2 = catPromotePos;
			//UPDATE商品组1
			List<CatPromotedCommodity> list2 = catPromotePos.getCatPromotedCommodityList();
			cPos2.setCategoryName("nameu");
			cPos2.setImageUrl("urlu");
			cPos2.setDisplayOrder(9);
			for (CatPromotedCommodity catPromotedCommodity : list2) {
				if ("1111".equals(catPromotedCommodity.getCommodityCode())) {
					//UPDATE明细1
					catPromotedCommodity.setCommodityCode("a");
					catPromotedCommodity.setCommodityImgUrl("b");
					catPromotedCommodity.setCommodityName("c");
					catPromotedCommodity.setCommoditySpec("d");
					catPromotedCommodity.setProductCode("e");
					catPromotedCommodity.setDisplayOrder(9);
				}else if ("6666".equals(catPromotedCommodity.getCommodityCode())) {
					//UPDATE明细2
					catPromotedCommodity.setCommodityCode("f");
					catPromotedCommodity.setCommodityImgUrl("g");
					catPromotedCommodity.setCommodityName("h");
					catPromotedCommodity.setCommoditySpec("j");
					catPromotedCommodity.setProductCode("k");
					catPromotedCommodity.setDisplayOrder(8);
				}
			}
		}

		catPromotePosManager.updateOne(cPos2);
		List<CatPromotePos> list = new ArrayList<CatPromotePos>();
		list = catPromotePosManager.getCatPromotePosInfo(cPos2);

		int i =0;
		for (CatPromotePos catPromotePos : list) {
			if("nameu".equals(catPromotePos.getCategoryName())){
					assertEquals("urlu",catPromotePos.getImageUrl());
					assertEquals(9,catPromotePos.getDisplayOrder().intValue());
					i++;
					for (CatPromotedCommodity commodity : catPromotePos.getCatPromotedCommodityList()) {
						if ("a".equals(commodity.getCommodityCode())) {
							assertEquals(catPromotePos.getId(),commodity.getCatPromotePosId());
							assertEquals("b",commodity.getCommodityImgUrl());
							assertEquals("c",commodity.getCommodityName());
							assertEquals("d",commodity.getCommoditySpec());
							assertEquals("e",commodity.getProductCode());
							assertEquals(9,commodity.getDisplayOrder().intValue());
							i++;
						}
						if ("f".equals(commodity.getCommodityCode())) {
							assertEquals(catPromotePos.getId(),commodity.getCatPromotePosId());
							assertEquals("g",commodity.getCommodityImgUrl());
							assertEquals("h",commodity.getCommodityName());
							assertEquals("j",commodity.getCommoditySpec());
							assertEquals("k",commodity.getProductCode());
							assertEquals(8,commodity.getDisplayOrder().intValue());
							i++;
						}
					}
				}
			}

		assertEquals(3,i);
		
		boolean b = catPromotePosManager.selectBydisplayOrderIsHave(cPos2);
		
		assertEquals(false,b);
	}
	
	@Test
	public void delete(){
		setDate();
		catPromotePosManager.addOne(cPos1);
		List<CatPromotePos> listA = new ArrayList<CatPromotePos>();
		CatPromotePos caPos = new CatPromotePos();
		listA = catPromotePosManager.getCatPromotePosInfo(caPos);
		for (CatPromotePos catPromotePos : listA) {
			cPos2 = catPromotePos;
		}
		assertEquals(1,catPromotePosManager.selectCount(cPos2).intValue());
		catPromotePosManager.deleteOne(cPos2);
		catPromotePosManager.selectCount(cPos2);
		assertEquals(0,catPromotePosManager.selectCount(cPos2).intValue());
		
	}
	
	@Test
	public void select(){
		List<CatPromotePos> listA = new ArrayList<CatPromotePos>();
		CatPromotePos caPos = new CatPromotePos();
		Long aLong = 30l;
		caPos.setId(aLong);
		listA = catPromotePosManager.getCatPromotePosInfo(caPos);

		for (CatPromotePos catPromotePos : listA) {
			cPos2 = catPromotePos;
		}
	}
}
