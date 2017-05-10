/**
 * 
 */
package com.spfood.pms.search.manager;

import java.util.List;
import java.util.Map;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.search.intf.domain.CommodityComment;
import com.spfood.pms.search.intf.utils.CommentGrade;

/**
 * CommodityComment Manager
 *
 * @author zhangpei
 * @createTime 2017-04-13 13:22:59
 *
 */
public interface CommodityCommentManager extends BaseManager<CommodityComment> {

	
	/**
	 * 为B2C提供查询指定商品评价总条数接口
	 * @param commodityCode commodityCode
	 * @return 查询到的总条数
	 */
	Long selectCommodityCommentCount(String commodityCode);
	
	/**
	 * 为B2C提供查询指定商品评价的分页查询
	 * @param commodityCode commodityCode
	 * @param pageNum 页数
	 * @param pageSize 每页条数
	 * @return 分页对象
	 */
	PageInfo<CommodityComment> selectCommodityCommentByPage(String commodityCode, int pageNum, int pageSize, CommentGrade commentType);
	
	/**
	 * 通过 商品编码获得好评,中评,差评各自总条数
	 * @param commodityCode 商品编码
	 * @return 对应的map键值对集合  好评:条数
	 */
	Map<String, Long> selectCommodityCommentTypeCount(String commodityCode);
	
	/**
	 * B2C提供通过订单编码查询商品评价
	 * @param orderNo 订单编号
	 * @return 商品评价集合
	 */
	List<CommodityComment> selectCommodityCommentByOrderNo(String orderNo);
}
