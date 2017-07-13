package com.spfood.cms.intf;

import java.util.List;

import com.spfood.cms.intf.domain.PromotedCommodity;



public interface PromotedCommodityService {

    /**
     * 查询推荐商品
     * @param commPromotePos 推荐位ID
     * @return
     */
     List<PromotedCommodity> getPromotedCommoditys(String commPromotePos);
    
     /**
      * 修改推荐商品排序
      * @param commodityId 推荐商品ID
      * @param newOrder 新的排序数字
      * @return
      */
     boolean changeOrder(Long commodityId,Integer newOrder);
    
     /**
      * 新增推荐商品
      * @param promotedCommodity    推荐商品对象，不能为空
      * @return
      */
     PromotedCommodity add(PromotedCommodity promotedCommodity);
    
     /**
      * 修改推荐商品
      * @param promotedCommodity    推荐商品对象，不能为空
      * @return
      */
     boolean update(PromotedCommodity promotedCommodity);

     /**
      * 查询推荐商品
      * @param commodityCode 商品编码，不能为空
      * @return
      */
     PromotedCommodity selectByCommodityCode(String commodityCode);
     

}
