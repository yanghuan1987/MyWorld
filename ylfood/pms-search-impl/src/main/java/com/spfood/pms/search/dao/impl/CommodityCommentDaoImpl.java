/**
 * 
 */
package com.spfood.pms.search.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.search.dao.CommodityCommentDao;
import com.spfood.pms.search.intf.domain.CommodityComment;

/**
 *
 * CommodityComment DAO
 *
 * @author zhangpei
 * @createTime 2017-04-13 13:22:59
 *
 */
@Repository
public class CommodityCommentDaoImpl extends BaseDaoImpl<CommodityComment> implements CommodityCommentDao {

	
	/**
	 * 为B2C提供查询指定商品评价总条数接口
	 * @param commodityCode 商品编码
	 * @return 查询到的总条数
	 */
	@Override
	public Long selectCommodityCommentCount(String commodityCode) {
		try {
			return sqlSessionTemplate.selectOne(getSqlName(CategorySqlIds.SELECT_COUNT_BY_COMMODITYCODE), commodityCode);
		} catch (Exception e) {
			throw new PersistenceException("commodityComment.dao.selectCommodityCommentCount", e, getSqlName(CategorySqlIds.SELECT_COUNT_BY_COMMODITYCODE), commodityCode);
		}
	}

	
	/**
	 * 通过id集合获得评价集合
	 * @param ids id集合
	 * @return 评价集合
	 */
	@Override
	public List<CommodityComment> selectCommodityCommentByIds(List<Long> ids) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CategorySqlIds.SELECT_COMMENTS_BY_IDS), ids);
		} catch (Exception e) {
			throw new PersistenceException("commodityComment.dao.selectCommodityCommentByIds", e, getSqlName(CategorySqlIds.SELECT_COMMENTS_BY_IDS), ids);
		}
	}


	/**
	 * 通过 商品编码获得好评,中评,差评各自总条数
	 * @param commodityCode 商品编码
	 * @return 对应的map键值对集合  好评:条数
	 */
	@Override
	public List<Map<String, Object>> selectCommodityCommentTypeCount(
			String commodityCode) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CategorySqlIds.SELECT_TYPECOUNT_BY_COMMODITYCODE), commodityCode);
		} catch (Exception e) {
			throw new PersistenceException("commodityComment.dao.selectCommodityCommentTypeCount", e, getSqlName(CategorySqlIds.SELECT_TYPECOUNT_BY_COMMODITYCODE), commodityCode);
		}
	}


	/**
	 * B2C提供通过订单编码查询商品评价
	 * @param orderNo 订单编号
	 * @return 商品评价集合
	 */
	@Override
	public List<CommodityComment> selectCommodityCommentByOrderNo(String orderNo) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CategorySqlIds.SELECT_COMMENT_BY_ORDERNO), orderNo);
		} catch (Exception e) {
			throw new PersistenceException("commodityComment.dao.selectCommodityCommentByOrderNo", e, getSqlName(CategorySqlIds.SELECT_COMMENT_BY_ORDERNO), orderNo);
		}
	}

	
	/**
	 * 通过商品编码集合获得对应的好评数评价,总条数
	 * @param commodityCodes 商品编码可变数组
	 * @return 对应的商品评价集合
	 */
	@Override
	public List<CommodityComment> selectGoodCommentPercent(
			String... commodityCodes) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CategorySqlIds.SELECT_TYPECOUNT_BY_COMMODITYCODEARRAY), commodityCodes);
		} catch (Exception e) {
			throw new PersistenceException("commodityComment.dao.selectGoodCommentPercent", e, getSqlName(CategorySqlIds.SELECT_TYPECOUNT_BY_COMMODITYCODEARRAY), commodityCodes);
		}
	}

}
