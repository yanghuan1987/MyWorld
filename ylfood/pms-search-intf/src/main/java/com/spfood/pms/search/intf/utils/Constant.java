package com.spfood.pms.search.intf.utils;

public class Constant {
	/**
	 * 排序类型
	 * 综合排序、价格、销量
	 */
	public enum OrderByCondition{
		comprehensive,priceToAsc,priceToDesc,salesToAsc,salesToDesc
	}
	
	/**
	 * 待审核，已通过审核，未通过审核，上架，下架，停售
	 *
	 */
	public enum CommodityState{
		onCheck,passCheck,unpassCheck,onShift,offShift,stop
	}
	
	/**
	 * 显示位置
	 *
	 */
	public enum CommodityShowPlace{
		toB,toC
	}
	
	/**
	 * 显示位置(检索条件）
	 *
	 */
	public enum CommodityShowPlaceForSelect{
		小B端,C端
	}
	
	//销量降序+价格升序 			comprehensive
	//价格升序+销量降序			
}
