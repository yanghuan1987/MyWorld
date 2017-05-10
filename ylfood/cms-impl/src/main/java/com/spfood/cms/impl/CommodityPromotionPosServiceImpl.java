package com.spfood.cms.impl;

import java.util.List;





import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.spfood.cms.intf.CommodityPromotionPosService;
import com.spfood.cms.intf.domain.CommodityPromotionPos;
import com.spfood.cms.intf.domain.PromotedCommodity;
import com.spfood.cms.manager.CommodityPromotionPosManager;

public class CommodityPromotionPosServiceImpl implements CommodityPromotionPosService{

    @Resource
    private CommodityPromotionPosManager commodityPromotionPosManager;
    
    private static final Log log = LogFactory.getLog(CommodityPromotionPosServiceImpl.class);
    /**
     * 通过ID查询推荐位
     * @param id 推荐位ID，不能为空
     */
    
    @Override
    public CommodityPromotionPos selectById(Long id) {
        return commodityPromotionPosManager.selectById(id);
    }
    /**
     * 查询所有推荐位
     */
    @Override
    public List<CommodityPromotionPos> selectAll() {
        
        return commodityPromotionPosManager.selectAll();
    }
    /**
     * 查询推荐位及推荐商品
     * @param name 推荐页面名称，不能为空
     */
    @Override
    public List<PromotedCommodity> getCommPromotePosList(String name) {
        log.info("getCommPromotePosList for B2C start");
        CommodityPromotionPos commodityPromotionPos=new CommodityPromotionPos();
        commodityPromotionPos.setName(name);
        return commodityPromotionPosManager.getCommPromotePosList(commodityPromotionPos);
    }

}
