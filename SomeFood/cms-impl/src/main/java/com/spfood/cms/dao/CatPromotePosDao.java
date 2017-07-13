/**
 * 
 */
package com.spfood.cms.dao;


import com.spfood.kernel.dao.BaseDao;
import com.spfood.cms.intf.domain.CatPromotePos;

/**
 *
 * CatPromotePos DAO
 *
 * @author yanghuan
 * @createTime 2017-02-07 10:01:48
 *
 */
public interface CatPromotePosDao extends BaseDao<CatPromotePos>{

	   /** 排序重复检查
	    * 
	    * @param catPromotePos
	    * @pdOid de5214af-681a-47eb-92f1-2ddb5ef28fff */
	   boolean selectBydisplayOrderIsHave(CatPromotePos catPromotePos);
}