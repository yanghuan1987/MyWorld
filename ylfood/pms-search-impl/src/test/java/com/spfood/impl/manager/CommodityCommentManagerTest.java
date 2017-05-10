package com.spfood.impl.manager;

import static org.junit.Assert.*;

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
import com.spfood.pms.search.intf.domain.CommodityComment;
import com.spfood.pms.search.intf.utils.CommentGrade;
import com.spfood.pms.search.manager.CommodityCommentManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class CommodityCommentManagerTest {

	private static final Log log = LogFactory.getLog(CommodityCommentManagerTest.class);
	
    private List<CommodityComment> name() {
        List<CommodityComment> list=new ArrayList<CommodityComment>();
        for (int i = 0; i < 3; i++) {
            CommodityComment comment=new CommodityComment();
            comment.setCategoryCode("05");
            comment.setCommentGrade(i+1);
            comment.setCommentStatus(1);
            comment.setCommentTime(new Date());
            comment.setCommentTypeCode(3);
            comment.setCommentUsercode("我我我");
            comment.setCommentContent("东西很好");
            comment.setOrderNo("订单编号");
            comment.setCommodityCode("234");
            list.add(comment);
        }
        return list;
    }
    @Resource
    private CommodityCommentManager commodityCommentManager;
    
    /**
     * 测试新增
     */
    @Test
    @Rollback
    @Transactional
    public void add() {
        Long count=commodityCommentManager.selectCount();//新增前总记录数
        List<CommodityComment> list=name();//创建新增对象集合
        commodityCommentManager.insertInBatch(list);//执行新增
        Long count1=commodityCommentManager.selectCount();//新增后总记录数
        assertEquals(list.size(),count1-count);//断言新增新增后与新增前的记录数差等于新增对象的大小
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
    	String commodityCode = "YLC0000000025   ";
    	PageInfo<CommodityComment> pageInfo = commodityCommentManager.selectCommodityCommentByPage(commodityCode, 1, 5, CommentGrade.BAD);
    	log.info(pageInfo.getPages());
    	log.info(pageInfo.getTotal());
    	List<CommodityComment> list = pageInfo.getResult();
    	log.info(list.size());
    	for (CommodityComment commodityComment : list) {
			log.info(commodityComment.getCommodityCode());
			log.info(commodityComment.getOrderNo());
			log.info(commodityComment.getCommentTime());
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
			
		}
	}
    
}
