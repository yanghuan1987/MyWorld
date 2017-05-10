/**
 * 
 */
package com.spfood.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.dao.CommodityCommentDao;
import com.spfood.pms.intf.domain.CommodityComment;

/**
 *
 * CommodityComment DAO
 *
 * @author yanghuan
 * @createTime 2017-04-13 16:03:10
 *
 */
@Repository
public class CommodityCommentDaoImpl extends BaseDaoImpl<CommodityComment> implements CommodityCommentDao {
	
	//根据ID的集合查询所有的商品评价
	public List<CommodityComment> selectListByIdsForComment(CommodityComment commodityComment) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(CommoditySqlIds.SELECT_PAGE_IN_COMMODITYCOMMENT_DETAIL), commodityComment);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectListByIds", e, null, getSqlName(CommoditySqlIds.SELECT_PAGE_IN_COMMODITYCOMMENT_DETAIL), commodityComment);
		}
	}
    
    //查询该商品的评分数量
    public CommodityComment selectCommodityCommentCount(CommodityComment commodityComment){
		try {
			return sqlSessionTemplate.selectOne(getSqlName(CommoditySqlIds.SELECT_COMMODITYCOMMENT_COUNT), commodityComment);
		} catch (Exception e) {
			throw new PersistenceException("dao.CommodityCommentDaoImpl.selectCommodityCommentCount", e, null, getSqlName(CommoditySqlIds.SELECT_COMMODITYCOMMENT_COUNT), commodityComment);
		}
	}
    //更新评论是否显示
    public boolean updateCommodityCommentCountStatus(CommodityComment commodityComment){
		try {
			int count = sqlSessionTemplate.update(getSqlName(CommoditySqlIds.UPDATE_COMMODITYCOMMENT_STATUS), commodityComment);
			if (count > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			throw new PersistenceException("dao.CommodityCommentDaoImpl.updateCommodityCommentCountStatus", e, null, getSqlName(CommoditySqlIds.UPDATE_COMMODITYCOMMENT_STATUS), commodityComment);
		}
	}
}
