package com.spfood.mms.intf.utils;

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
	
	//销量降序+价格升序 			comprehensive
	//价格升序+销量降序			
	/**
	 * 排序类型
	 */
	public enum OrderByComment{
		ExpressGoodCountAsc(1,"配送好评数升序"),
		ExpressGoodCountDesc(2,"配送好评数降序"),
		ExpressBadCountAsc(3,"配送差评数升序"),
		ExpressBadCountDesc(4,"配送差评数降序"),
		PackageGoodCountAsc(5,"包装好评数升序"),
		PackageGoodCountDesc(6,"包装好评数降序"),
		PackageBadCountAsc(7,"包装差评数升序"),
		PackageBadCountDesc(8,"包装差评数降序"),
		CommodityGoodCountAsc(9,"商品好评数升序"),
		CommodityGoodCountDesc(10,"商品好评数降序"),
		CommodityBadCountAsc(11,"商品差评数升序"),
		CommodityBadCountDesc(12,"商品差评数降序"),
		ExpressGradeAsc(13,"配送评价降序"),
		ExpressGradeDesc(14,"配送评价降序"),
		PackageGradeAsc(15,"包装评价降序"),
		PackageGradeDesc(16,"包装评价降序"),
		CommodityGradeAsc(17,"商品评价降序"),
		CommodityGradeDesc(18,"商品评价降序");
		private int value;
		private String name;
		private OrderByComment (int value,String name) {
			this.value = value;
			this.name = name;
		}
		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	}
	
	/**
	 * 排序类型
	 */
	public enum CategoryCodeSize{
		categorycodelevelone(2,"一级品类2位编号"),
		categorycodeleveltwo(5,"二级品类5位编号"),
		categorycodelevelthree(8,"三级品类8位编号");
		private int value;
		private String name;
		private CategoryCodeSize (int value,String name) {
			this.value = value;
			this.name = name;
		}
		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	}
	
	/**
	 * 排序类型
	 */
	public enum CommentLevel{
		goodComment(3,"好评"),
		middleComment(2,"中评"),
		badComment(1,"差评");
		private int value;
		private String name;
		private CommentLevel (int value,String name) {
			this.value = value;
			this.name = name;
		}
		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	}

	/**
	 * 业务类型
	 */
	public enum itemType{
		commodity(0,"商品"),
		product(1,"产品"),
		category(2,"品类"),
		rawMaterials(3,"原料");
		private int value;
		private String name;
		private itemType (int value,String name) {
			this.value = value;
			this.name = name;
		}
		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	}
	
	/**
	 * 待审核，已通过审核，未通过审核，上架，下架，停售，已删除
	 *
	 */
	public enum CommodityStateDetial{
		onCheck(0,"待审核"),
		passCheck(1,"已通过审核"),
		unpassCheck(2,"未通过审核"),
		onShift(3,"上架"),
		offShift(4,"下架"),
		stop(5,"停售"),
		deleted(6,"已删除");
		private int value;
		private String name;
		private CommodityStateDetial (int value,String name) {
			this.value = value;
			this.name = name;
		}
		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	}
	/**
	 * 商品销售数量更新错误状态
	 */
	public enum errorStatus{
		wating(0,"未处理"),
		finish(1,"处理完");
		private int value;
		private String name;
		private errorStatus (int value,String name) {
			this.value = value;
			this.name = name;
		}
		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	}

	 


	

}
