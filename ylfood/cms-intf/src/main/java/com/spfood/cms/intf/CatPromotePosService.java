package com.spfood.cms.intf;

import java.util.List;

import com.spfood.cms.intf.domain.CatPromotePos;

public interface CatPromotePosService {
	   /** 获取菜单信息(对B2C提供的接口)
	    * @param CatPromotePos 商品组对象，不能为空
	    * @pdOid 538b43e3-ff3a-4b99-a4fd-3746dd4553cc */
	   List<CatPromotePos> getCatPromotePosInfo(CatPromotePos catPromotePos);
	   /** 更新菜单信息
	    * 
	    * @param CatPromotePos 商品组对象，不能为空
	    * @pdOid 4c2f5cd1-13fa-409a-b68f-525e245a3f45 */
	   boolean updateOne(CatPromotePos catPromotePos);
	   /**新增菜单信息 
	    * @param catPromotePos 商品组对象，不能为空
	    * @pdOid 38808594-2d8f-4b07-bf6d-0fc035406d5f */
	   void addOne(CatPromotePos catPromotePos);
	   /**删除菜单信息 
	    * @param catPromotPos 商品组对象，不能为空
	    * @pdOid 2d1fb22c-b3a7-40b4-964b-c2e3b7d222f1 */
	   boolean deleteOne(CatPromotePos catPromotPos);
	   /** 排序重复检查
	    * 
	    * @param catPromotePos 商品组对象，不能为空
	    * @pdOid de5214af-681a-47eb-92f1-2ddb5ef28fff */
	   boolean selectBydisplayOrderIsHave(CatPromotePos catPromotePos);
	   /**
	    * 查询全部
	    */
	   public List<CatPromotePos> getCatPromotePosInfo();
}
