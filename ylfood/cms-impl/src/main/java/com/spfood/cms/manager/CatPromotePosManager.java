/**
 * 
 */
package com.spfood.cms.manager;

import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.cms.intf.domain.CatPromotePos;

/**
 * CatPromotePos Manager
 *
 * @author yanghuan
 * @createTime 2017-02-07 10:01:48
 *
 */
public interface CatPromotePosManager extends BaseManager<CatPromotePos> {
	   /** 获取菜单信息
	    * 
	    * @pdOid 3db4d01f-ee23-4901-8275-bfb7cbcf064a */
	   List<CatPromotePos> getCatPromotePosInfo(CatPromotePos catPromotePos);
	   /** 更新菜单信息
	    * 
	    * @param CatPromotePos
	    * @pdOid 2caff14f-d112-495c-85f3-162665e91785 */
	   boolean updateOne(CatPromotePos catPromotePos);
	   /**新增菜单信息 
	    * @param catPromotePos
	    * @pdOid 4e838cde-10ff-4dba-a952-243a29af6cd6 */
	   void addOne(CatPromotePos catPromotePos);
	   /** 删除菜单信息
	    *  @param catPromotPos
	    * @pdOid c93c1cab-96a4-40cd-9f54-0797f9b56e74 */
	   boolean deleteOne(CatPromotePos catPromotePos);
	   
	   /** 排序重复检查
	    * 
	    * @param catPromotePos
	    * @pdOid de5214af-681a-47eb-92f1-2ddb5ef28fff */
	   boolean selectBydisplayOrderIsHave(CatPromotePos catPromotePos);
}
