package com.spfood.pms.search.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.search.intf.domain.criteria.CommodityAndComment;
/**
 * CommodityAndCommentDao 接口类
 * @author Yanghuan 2016-12-16
 *
 */
public interface CommodityAndCommentDao extends BaseDao<CommodityAndComment>{
	/**
	 * 根据编码集合查询商品集合
	 * @param codeList
	 * @return
	 */
	public List<CommodityAndComment> selectCommodityByCodelist(List<String> codeList);

	/**
	 * 公共id数组集合查询商品列表
	 * @param idList
	 * @return
	 */
	public List<CommodityAndComment> selectListByIds(List<Long> idList);
}
