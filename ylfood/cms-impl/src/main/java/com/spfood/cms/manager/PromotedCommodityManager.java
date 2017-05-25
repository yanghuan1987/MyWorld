/**
 * 
 */
package com.spfood.cms.manager;

import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.cms.intf.domain.PromotedCommodity;

/**
 * PromotedCommodity Manager
 *
 * @author zhangpei
 * @createTime 2017-02-04 14:04:57
 *
 */
public interface PromotedCommodityManager extends BaseManager<PromotedCommodity> {

    /**
     * 查看指定位置所有推荐商品
     * @param commPromotePos 推荐位置
     * @return
     */
    List<PromotedCommodity> getPromotedCommoditys(String commPromotePos);

    
    /**
     * 改变推荐商品展示顺序
     * @param commodityId   推荐商品ID
     * @param newOrder      新的顺序
     * @return
     */
    boolean changeOrder(Long commodityId,Integer newOrder);

    /**
     * 通过商品编码查询推荐商品
     * @param commodityCode
     * @return
     */
    PromotedCommodity selectByCommodityCode(String commodityCode);
    
}
