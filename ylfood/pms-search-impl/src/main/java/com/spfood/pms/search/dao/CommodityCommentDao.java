/**
 * 
 */
package com.spfood.pms.search.dao;

import java.util.List;
import java.util.Map;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.search.intf.domain.CommodityComment;

/**
 *
 * CommodityComment DAO
 *
 * @author zhangpei
 * @createTime 2017-04-13 13:22:59
 *
 */
public interface CommodityCommentDao extends BaseDao<CommodityComment>{

	/**
	 * 为B2C提供查询指定商品评价总条数接口
	 * @param commodityCode 商品编码
	 * @return 查询到的总条数
	 */
	Long selectCommodityCommentCount(String commodityCode);

	/**
	 * 通过id集合获得评价集合
	 * @param ids id集合
	 * @return 评价集合
	 */
	List<CommodityComment> selectCommodityCommentByIds(List<Long> ids);
	
	/**
	 * 通过 商品编码获得好评,中评,差评各自总条数
	 * @param commodityCode 商品编码
	 * @return 对应的map键值对集合  好评:条数
	 */
	List<Map<String, Object>> selectCommodityCommentTypeCount(String commodityCode);
	
	/**
	 * B2C提供通过订单编码查询商品评价
	 * @param orderNo 订单编号
	 * @return 商品评价集合
	 */
	List<CommodityComment> selectCommodityCommentByOrderNo(String orderNo);
}