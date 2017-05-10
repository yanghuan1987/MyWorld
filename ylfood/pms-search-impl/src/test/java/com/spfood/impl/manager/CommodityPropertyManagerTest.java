package com.spfood.impl.manager;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.pms.search.intf.domain.CommodityProperty;
import com.spfood.pms.search.manager.CommodityPropertyManager;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/local-spring-environment.xml","/spring-context.xml"})
public class CommodityPropertyManagerTest {

    @Resource
    private CommodityPropertyManager commodityPropertyManager;

    
    @Test
    @Rollback
    @Transactional
    public void selectPropertyByKeywords() {
        List<String> list=new ArrayList<String>();
        list.add("1");
        list.add("04");
        List<CommodityProperty> commodityProperties=commodityPropertyManager.selectPropertyByKeywords(list);
        assertTrue(commodityProperties.size()>=0);
    }
}
