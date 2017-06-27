package com.spfood.pms.search.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.pms.search.dao.CommodityAndCommentDao;
import com.spfood.pms.search.intf.domain.criteria.CommodityAndComment;

/**
 * CommodityDao 实现类
 * @author fengjunchao 
 *
 */
@Repository
public class CommodityAndCommentDaoImpl extends BaseDaoImpl<CommodityAndComment> implements CommodityAndCommentDao {

	@Override
	public List<CommodityAndComment> selectCommodityByCodelist(List<String> codeList) {
		try {
			if(codeList == null || codeList.size() < 1)return null;
			return sqlSessionTemplate.selectList(getSqlName(CommoditySqlIds.SELECTCOMMODITYACOMMENT_BY_CODELIST), codeList);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectCommodityByCodelist", e, null, getSqlName(CommoditySqlIds.SELECTCOMMODITYACOMMENT_BY_CODELIST), codeList);
		}
	}
	
	//根据ID的集合查询所有的商品
	public List<CommodityAndComment> selectListByIds(List<Long> idList) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectListByIdsForComment"), idList);
		} catch (Exception e) {
			throw new PersistenceException("kernel.dao.selectListByIds", e, null, getSqlName("selectListByIds"), idList);
		}
	}
}
