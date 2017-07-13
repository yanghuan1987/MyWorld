package com.spfood.cms.intf;

import java.util.List;

import com.spfood.cms.intf.domain.CommodityPromotionPos;
import com.spfood.cms.intf.domain.PromotedCommodity;

public interface CommodityPromotionPosService {

    /**
     * 通过ID查询推荐位信息
     * @param id    推荐位ID，不能为空
     * @return
     */
    CommodityPromotionPos selectById(Long id);
    
    /**
     * 所有推荐位
     * @return
     */
    List<CommodityPromotionPos> selectAll();
    
    /**
     * 推荐位及推荐商品(对B2C提供的接口)
     * @param name  推荐页面名称，不能为空
     * @return
     */
    List<PromotedCommodity> getCommPromotePosList(String name);
}
