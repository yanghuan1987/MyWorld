package com.spfood.cms.manager;

import static org.junit.Assert.*;

import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.cms.intf.domain.CommodityPromotionPos;
import com.spfood.cms.intf.domain.PromotedCommodity;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class CommodityPromotionPosManagerTest {
    
    @Autowired
    private CommodityPromotionPosManager commodityPromotionPosManager;
    
    private CommodityPromotionPos creat(){
        CommodityPromotionPos commodityPromotionPos = new CommodityPromotionPos();
        commodityPromotionPos.setName("订单页");
        commodityPromotionPos.setMaxSlots(5);
        commodityPromotionPos.setMinSlots(5);
        return commodityPromotionPos;
    }
    
    
    /**
     * 新增推荐位
     */
    @Test
    @Rollback
    @Transactional
    public void add() {
        CommodityPromotionPos commodityPromotionPos =creat();
        commodityPromotionPosManager.insert(commodityPromotionPos);
        CommodityPromotionPos commodityPromotionPos1=commodityPromotionPosManager.selectById(commodityPromotionPos.getId());
        assertEquals(commodityPromotionPos1.getName(),commodityPromotionPos.getName());
    }
    
    /**
     * 编辑推荐位
     */
    @Test
    @Rollback
    @Transactional
    public void update() {
        CommodityPromotionPos commodityPromotionPos =creat();
        commodityPromotionPosManager.insert(commodityPromotionPos);
        CommodityPromotionPos commodityPromotionPos1=commodityPromotionPosManager.selectById(commodityPromotionPos.getId());
        commodityPromotionPos1.setName("改个名字");
        int row=commodityPromotionPosManager.updateByIdSelective(commodityPromotionPos);
        assertEquals(1,row);
    }
    
    
    
    /**
     * 查看全部
     */
    @Test
    @Rollback
    @Transactional
    public void selectAll() {
        List<CommodityPromotionPos> commodityPromotionPoses=commodityPromotionPosManager.selectAll();
        int count=commodityPromotionPoses.size();
        CommodityPromotionPos commodityPromotionPos =creat();
        commodityPromotionPosManager.insert(commodityPromotionPos);
        List<CommodityPromotionPos> commodityPromotionPoses1=commodityPromotionPosManager.selectAll();
        assertEquals(count+1,commodityPromotionPoses1.size());
    }
    
    @Test
    @Rollback
    @Transactional
    public void selectSize() {
        List<CommodityPromotionPos> commodityPromotionPoses=commodityPromotionPosManager.selectAll();
        int count=commodityPromotionPoses.size();
        CommodityPromotionPos commodityPromotionPos =creat();
        commodityPromotionPosManager.insert(commodityPromotionPos);
        List<CommodityPromotionPos> commodityPromotionPoses1=commodityPromotionPosManager.selectAll();
        assertEquals(count+1,commodityPromotionPoses1.size());
    }
    
    /**
     * 删除推荐位
     */
    @Test
    @Rollback
    @Transactional
    public void delete() {
        CommodityPromotionPos commodityPromotionPos =creat();
        commodityPromotionPosManager.insert(commodityPromotionPos);
        int row=commodityPromotionPosManager.deleteById(commodityPromotionPos.getId());
        assertEquals(1,row);
    }
    
    @Test
    @Rollback
    @Transactional
    public void delect() {
        CommodityPromotionPos commodityPromotionPos =creat();
        commodityPromotionPosManager.insert(commodityPromotionPos);
        CommodityPromotionPos commodityPromotionPos1 = commodityPromotionPosManager.selectById(commodityPromotionPos.getId());
        assertEquals(commodityPromotionPos1.getName(),commodityPromotionPos.getName());
    }
    @Test
    @Rollback
    @Transactional
    public void getCommPromotePosList() {
        CommodityPromotionPos commodityPromotionPos = new CommodityPromotionPos();
        commodityPromotionPos.setName("主页");
        List<PromotedCommodity> promotedCommodities=commodityPromotionPosManager.getCommPromotePosList(commodityPromotionPos);
        assertTrue(promotedCommodities.size()>0);
    }
    
}
