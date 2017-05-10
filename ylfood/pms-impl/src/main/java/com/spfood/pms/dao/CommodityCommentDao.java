/**
 * 
 */
package com.spfood.pms.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.intf.domain.CommodityComment;

/**
 *
 * CommodityComment DAO
 *
 * @author yanghuan
 * @createTime 2017-04-13 16:03:10
 *
 */
public interface CommodityCommentDao extends BaseDao<CommodityComment>{
	
	/**
	 * 公共id数组集合查询评价
	 * @param idList
	 * @return
	 */
	public List<CommodityComment> selectListByIdsForComment(CommodityComment commodityComment);
	
    //查询该商品的评分数量
    public CommodityComment selectCommodityCommentCount(CommodityComment commodityComment);
    //更新评论是否显示
    public boolean updateCommodityCommentCountStatus(CommodityComment commodityComment);
}