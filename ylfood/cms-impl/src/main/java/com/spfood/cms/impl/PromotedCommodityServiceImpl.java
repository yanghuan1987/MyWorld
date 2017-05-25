package com.spfood.cms.impl;
import java.util.List;

import javax.annotation.Resource;

import com.spfood.basicservice.intf.BasicConfigurationService;
import com.spfood.cms.intf.PromotedCommodityService;
import com.spfood.cms.intf.domain.PromotedCommodity;
import com.spfood.cms.manager.PromotedCommodityManager;

public class PromotedCommodityServiceImpl implements PromotedCommodityService {

    @Resource
    private PromotedCommodityManager promotedCommodityManager;

    @Resource
    private BasicConfigurationService basicConfigurationService;
    
    /**
     * 指定推荐位的推荐商品列表
     * @param commPromotePos 推荐位编码，不能为空
     */
    @Override
    public List<PromotedCommodity> getPromotedCommoditys(String commPromotePos) {
        return promotedCommodityManager.getPromotedCommoditys(commPromotePos);
    }

    /**
     * 修改推荐商品排序
     * @param commodityId   要修改的推荐商品ID，不能为空
     * @param newOrder      新的排序位置，不能为空
     */
    @Override
    public boolean changeOrder(Long commodityId,Integer newOrder) {
        return promotedCommodityManager.changeOrder(commodityId, newOrder);
    }

    /**
     * 添加推荐商品
     * promotedCommodity    推荐商品对象，不能为空
     */
    @Override
    public PromotedCommodity add(PromotedCommodity promotedCommodity) {
        promotedCommodityManager.insert(promotedCommodity);
        return promotedCommodityManager.selectById(promotedCommodity.getId());
    }

    /**
     * 编辑推荐商品
     * @param promotedCommodity 推荐商品对象，不能为空
     */
    @Override
    public boolean update(PromotedCommodity promotedCommodity) {
        return promotedCommodityManager.updateById(promotedCommodity)==1?true:false;
    }
    /**
     * 通过商品编码查询推荐商品
     * @param commodityCode 推荐商品编码，不能为空
     */
    @Override
    public PromotedCommodity selectByCommodityCode(String commodityCode) {
        
        return promotedCommodityManager.selectByCommodityCode(commodityCode);
    }



    


}
