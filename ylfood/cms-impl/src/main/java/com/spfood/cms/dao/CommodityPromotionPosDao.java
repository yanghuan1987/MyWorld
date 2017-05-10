/**
 * 
 */
package com.spfood.cms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.cms.intf.domain.CommodityPromotionPos;

/**
 *
 * commodityPromotionPos DAO
 *
 * @author zhangpei
 * @createTime 2017-02-06 13:11:29
 *
 */
public interface CommodityPromotionPosDao extends BaseDao<CommodityPromotionPos>{
    /**
     * 查询推荐位列表及推荐商品
     * @param commodityPromotionPos
     * @return
     */
    public List<CommodityPromotionPos> getCommPromotePosList(CommodityPromotionPos commodityPromotionPos);
}