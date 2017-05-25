package com.spfood.cms.manager;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.cms.intf.domain.PromotedCommodity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class PromotedCodityManagerTest {

    @Autowired
    private PromotedCommodityManager promotedCommodityManager;
    
    private PromotedCommodity create() {
        PromotedCommodity promotedCommodity=new PromotedCommodity();
        promotedCommodity.setCommodityCode("123");
        promotedCommodity.setCommodityName("商品名称");
        promotedCommodity.setCommPromotePos("cms_commodity_pos_homePage");
        promotedCommodity.setProductCode("456");
        promotedCommodity.setCommoditySpec("商品规格");
        promotedCommodity.setImageUrl("图片地址");
        promotedCommodity.setDisplayOrder(1);
        return promotedCommodity;
    }
    /**
     * 添加推荐商品
     */
    @Test
    @Rollback
    @Transactional
    public void add() {
       PromotedCommodity promotedCommodity=create();
       promotedCommodityManager.insert(promotedCommodity);
       PromotedCommodity promotedCommodity1=promotedCommodityManager.selectById(promotedCommodity.getId());
       assertEquals(promotedCommodity1.getCommodityName(),promotedCommodity.getCommodityName());
    }
    
    /**
     * 查看指定页面的推荐商品
     */
    @Test
    @Rollback
    @Transactional
    public void getPromotedCommoditys(){
        PromotedCommodity promotedCommodity=create();
        promotedCommodityManager.insert(promotedCommodity);
        List<PromotedCommodity> promotedCommoditys=promotedCommodityManager.getPromotedCommoditys(promotedCommodity.getCommPromotePos());
        int j=0;
        for(int i=0;i<promotedCommoditys.size();i++){
            if(promotedCommodity.getId().equals(promotedCommoditys.get(i).getId())){
                j=i;
            }
        }
        assertEquals("商品名称",promotedCommoditys.get(j).getCommodityName());
    }
    
    /**
     * 通过ID查询
     */
    @Test
    @Rollback
    @Transactional
    public void selectById() {
        PromotedCommodity promotedCommodity=create();
        promotedCommodityManager.insert(promotedCommodity);
        PromotedCommodity promotedCommodity1=promotedCommodityManager.selectById(promotedCommodity.getId());
        assertEquals(promotedCommodity1.getCommodityName(),promotedCommodity.getCommodityName());
    }
    
    /**
     * 修改顺序
     */
    @Test
    @Rollback
    @Transactional
    public void changOrder() {
        PromotedCommodity promotedCommodity=create();
        promotedCommodityManager.insert(promotedCommodity);
        boolean isSuccess=promotedCommodityManager.changeOrder(promotedCommodity.getId(), 5);
        assertEquals(true,isSuccess);
    }
    
    /**
     * 修改推荐商品
     */
    @Test
    @Rollback
    @Transactional
    public void update() {
        PromotedCommodity promotedCommodity=create();
        promotedCommodityManager.insert(promotedCommodity);
        PromotedCommodity promotedCommodity1=promotedCommodityManager.selectById(promotedCommodity.getId());
        promotedCommodity1.setCommodityName("新商品名称");
        int row=promotedCommodityManager.updateByIdSelective(promotedCommodity1);
        assertEquals(1,row);
    }
    
    
}
