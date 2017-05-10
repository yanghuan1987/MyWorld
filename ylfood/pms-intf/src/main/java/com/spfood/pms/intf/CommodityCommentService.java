/**
 * 
 */
package com.spfood.pms.intf;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.domain.CommodityComment;

/**
 * 评价服务
 * @author Administrator
 *
 * @author yanghuan
 * @createTime 2017-04-13 16:03:10
 */
public interface CommodityCommentService {

    /**
     * 分页查询评价统计
     * pageInfo:分页参数，CommodityComment:商品评价信息
     * @return
     */
    public PageInfo<CommodityComment> selectCommodityCommentByPage(PageInfo<CommodityComment> pageInfo, CommodityComment commodityComment);
    
    //查询该商品的评分数量
    public CommodityComment selectCommodityCommentCount(CommodityComment commodityComment);
    //更新评论是否显示
    public boolean updateCommodityCommentCountStatus(CommodityComment commodityComment);
}
