/**
 * 
 */
package com.spfood.cms.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.PersistenceException;
import com.spfood.cms.dao.CatPromotePosDao;
import com.spfood.cms.intf.domain.CatPromotePos;

/**
 *
 * CatPromotePos DAO
 *
 * @author yanghuan
 * @createTime 2017-02-07 10:01:48
 *
 */
@Repository
public class CatPromotePosDaoImpl extends BaseDaoImpl<CatPromotePos> implements
		CatPromotePosDao, CmsSqlIds {


	   /** 排序重复检查
	    * 
	    * @param catPromotePos
	    * @pdOid de5214af-681a-47eb-92f1-2ddb5ef28fff */
	public boolean selectBydisplayOrderIsHave(CatPromotePos catPromotePos) {
		Assert.notNull(catPromotePos.getDisplayOrder());
		try {
		  //执行查询
			return sqlSessionTemplate.selectOne(
					getSqlName(SELECT_BY_DISPLAYORDER_IS_HAVE),
					catPromotePos);
		} catch (Exception e) {
			throw new PersistenceException("CatPromotePos.Dao.selectBydisplayOrderIsHave", e,
					getSqlName(SELECT_BY_DISPLAYORDER_IS_HAVE),
					catPromotePos);
		}
	}
}
