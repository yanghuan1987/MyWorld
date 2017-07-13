/**
 * 
 */
package com.spfood.cms.manager;

import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.cms.intf.domain.CommodityPromotionPos;
import com.spfood.cms.intf.domain.PromotedCommodity;

/**
 * commodityPromotionPos Manager
 *
 * @author zhangpei
 * @createTime 2017-02-06 13:11:29
 *
 */
public interface CommodityPromotionPosManager extends BaseManager<CommodityPromotionPos> {

    /**
     * 查询推荐位及推荐商品
     * @param commodityPromotionPos
     * @return
     */
    List<PromotedCommodity> getCommPromotePosList(CommodityPromotionPos commodityPromotionPos);
}
