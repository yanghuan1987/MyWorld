package com.spfood.impl.manager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.search.intf.domain.Commodity;
import com.spfood.pms.search.intf.domain.CommodityComment;
import com.spfood.pms.search.intf.utils.CommentGrade;
import com.spfood.pms.search.manager.CommodityCommentManager;
import com.spfood.pms.search.manager.CommodityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class CommodityCommentManagerTest {

	private static final Log log = LogFactory.getLog(CommodityCommentManagerTest.class);
	
	
    private List<CommodityComment> name() {
        List<CommodityComment> list=new ArrayList<CommodityComment>();
        
        CommodityComment comment=new CommodityComment();
        comment.setCommentTypeCode(3);
        comment.setCommentGrade(1);
        comment.setCommentUsercode("我我我");
        comment.setCommentContent("东西很好");
        comment.setOrderNo("订单编号1");
        comment.setCommodityCode("YLC0000000034");
        list.add(comment);
        CommodityComment comment1=new CommodityComment();
        comment1.setCommentTypeCode(3);
        comment1.setCommentGrade(4);
        comment1.setCommentUsercode("我我我");
        comment1.setCommentContent("东西很好");
        comment1.setOrderNo("订单编号2");
        comment1.setCommodityCode("YLC0000000054");
        list.add(comment1);
        CommodityComment comment2=new CommodityComment();
        comment2.setCommentTypeCode(3);
        comment2.setCommentGrade(5);
        comment2.setCommentUsercode("我我我");
        comment2.setCommentContent("东西很好");
        comment2.setOrderNo("订单编号3");
        comment2.setCommodityCode("YLC0000000063");
        list.add(comment2);
        
        return list;
    }
    @Resource
    private CommodityCommentManager commodityCommentManager;
    
    @Resource
    private CommodityManager commodityManager;
    
    /**
     * 测试新增
     */
    @Test
    @Rollback
    @Transactional
    public void add() {
        boolean b=false;
        List<CommodityComment> comments=name();
        Date date=new Date();
        List<String> list =new ArrayList<String>();//储存评价的商品编号
        
        //遍历评价集合
        for (CommodityComment commodityComment : comments) {
            //将当前时间作为评价时间
            commodityComment.setCommentTime(date);
            
            //将评价的商品编号添加到list
            list.add(commodityComment.getCommodityCode());
        }
        
        //通过商品编号集合查询商品集合
        List<Commodity> commodities=commodityManager.selectCommodityByCodelist(list);
        
        //将品类编码设置到评价对象中
        for (int i = 0; i < commodities.size(); i++) {
            comments.get(i).setCategoryCode(commodities.get(i).getCategory().getCategoryCode());
        }
        
        try {
            //执行批量新增
            commodityCommentManager.insertInBatch(comments);
            b=true;
        } catch (Exception e) {
            //返回false新增失败
            b=false;
        }
        assertEquals(true, b);
    }
    
    
    /**
     * 测试指定商品的总条数
     */
    @Test
	public void testSelectCommodityCommentCount() throws Exception {
    	String commodityCode = "YLC0000000025";
		commodityCommentManager.selectCommodityCommentCount(commodityCode);
	}
    
    /**
     * 测试指定商品的评价分页数据 
     */
    @Test
	public void testSelectCommodityCommentByPage() throws Exception {
    	String commodityCode = "YLC0000000112";
    	PageInfo<CommodityComment> pageInfo = commodityCommentManager.selectCommodityCommentByPage(commodityCode, 1, 2, null);
    	log.info(pageInfo.getPages());
    	log.info(pageInfo.getTotal());
    	List<CommodityComment> list = pageInfo.getResult();
    	log.info(list.size());
    	for (CommodityComment commodityComment : list) {
			log.info(commodityComment.getCommodityCode());
			log.info(commodityComment.getOrderNo());
			log.info(commodityComment.getCommentTime());
			log.info(commodityComment.getHeadUrl());
			log.info(commodityComment.getNickname());
		}
	}
    
    /**
     * 测试通过商品编码获得好评,中评,差评各自对应的数量
     */
    @Test
	public void testSelectCommodityCommentTypeCount() throws Exception {
    	String commodityCode = "YLC0000000025";
    	Map<String, Long> typeCount = commodityCommentManager.selectCommodityCommentTypeCount(commodityCode);
    	log.info(typeCount);
    	log.info("goodComment---->" + typeCount.get(CommentGrade.GOOD.getComment()));
    	log.info("mediumComment---->" + typeCount.get(CommentGrade.MEDIUM.getComment()));
    	log.info("badComment---->" + typeCount.get(CommentGrade.BAD.getComment()));
    	log.info("countComment---->" + typeCount.get(CommentGrade.COUNT.getComment()));
	}
    
    /**
     * 测试通过订单查询商品评价
     */
    @Test
	public void testSelectCommodityCommentByOrderNo() throws Exception {
    	String orderNo = "2323";
		List<CommodityComment> list = commodityCommentManager.selectCommodityCommentByOrderNo(orderNo);
		log.info("总条数:" + list.size());
		for (CommodityComment commodityComment : list) {
			log.info("商品评价:" + commodityComment.getCommentContent());
			log.info("商品编码:" + commodityComment.getCommodityCode());
			log.info("评价时间:" + commodityComment.getCommentTime());
			
		}
	}
    
    /**
     * 测试通过商品编码可变数组获得好评,中评,差评各自对应的数量还有总数
     */
    @Test
	public void testSelectGoodCommentPercent() throws Exception {
    	String[] commStrings = {"YLC0000000122"};
    	List<CommodityComment> list = commodityCommentManager.selectGoodCommentPercent(commStrings);
    	log.info(list);
    	for (CommodityComment commodityComment : list) {
    		log.info("goodComment---->" + commodityComment.getCommodityCode());
    		log.info("goodComment---->" + commodityComment.getGoodComment());
    		log.info("countComment---->" + commodityComment.getCountComment());
		}
	}
    
}
