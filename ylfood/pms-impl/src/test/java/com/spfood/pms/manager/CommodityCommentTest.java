package com.spfood.pms.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.domain.CommodityComment;
import com.spfood.pms.intf.domain.CommodityCommentType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})

public class CommodityCommentTest {
	

	@Resource
	private CommodityCommentManager commodityCommentManager;
	
	@Resource
	private CommodityCommentTypeManager commodityCommentTypeManager;
	
	@Test
	public void selecComment() throws ParseException{
		PageInfo<CommodityComment> pageInfo = new PageInfo<CommodityComment>(1, 5);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dst="2017-4-1";
		String ded="2017-4-14";
		Date st = sdf.parse(dst);
		Date ed = sdf.parse(ded);
		List<Integer> orderList = new ArrayList<Integer>();
		orderList.add(10);
		orderList.add(5);
		orderList.add(12);
		CommodityComment commodityComment = new CommodityComment();
		commodityComment.setCommodityCode("YLC0000000025");
		commodityComment.setCommentContent("a");//评价关键字
		commodityComment.setOrderNo("3");//订单编号
		commodityComment.setCommentUsercode("a");//用户昵称
		commodityComment.setCommodityCommentDateSt(st);
		commodityComment.setCommodityCommentDateEd(ed);
		commodityComment.setOrderBy(13);
		PageInfo<CommodityComment> pageInfo2 = commodityCommentManager.selectCommodityCommentByPage(pageInfo, commodityComment);
		pageInfo2.isSelectCount();
	}
	
	@Test
	public void selecCount(){
		CommodityComment commodityComment = new CommodityComment();
		commodityComment.setCommodityCode("YLC0000000025");
		commodityComment.setCommentUsercode("aaa");//评价关键字
		commodityComment.setOrderNo("3434");//订单编号
		commodityComment.setCommentStatus(0);
		commodityCommentManager.selectCommodityCommentCount(commodityComment);
		commodityCommentManager.updateCommodityCommentCountStatus(commodityComment);
	}
	
	@Test
	public void selectTy(){
		List<CommodityCommentType> list = new ArrayList<CommodityCommentType>();
		list = commodityCommentTypeManager.selectAll();
		list.size();
	}
}
