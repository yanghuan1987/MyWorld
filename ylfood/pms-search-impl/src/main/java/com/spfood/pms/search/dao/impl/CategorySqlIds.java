package com.spfood.pms.search.dao.impl;

public interface CategorySqlIds {

	public String SELECT_BY_COMMODITYNAME = "selectByCommodityName";
	public String SELECT_BY_KEYWORDS = "selectByKeywords";
	public String SELECT_BY_COMMODITYCODE = "selectByCommodityCode";
	public String SELECT_BY_CATEGORYCODE = "selectByCategoryCode";
	public String SELECTCHILDREN_BY_CATEGORYCODE = "selectCategoryChildren";
	public String SELECTNAME_BY_CATEGORYCODELIST = "selectCategoryName";
	
	/**
	 * 查询指定商品总条数
	 */
	public String SELECT_COUNT_BY_COMMODITYCODE = "selectCommodityCount";
	
	/**
	 * 通过id集合获得评价集合
	 */
	public String SELECT_COMMENTS_BY_IDS = "selectCommentByIds";
	
	/**
	 * 通过商品编码查询对应的好评,中评,差评总数
	 */
	public String SELECT_TYPECOUNT_BY_COMMODITYCODE = "selectCommentTypeCount";
	
	/**
	 * 通过订单查询商品评价
	 */
	public String SELECT_COMMENT_BY_ORDERNO = "selectCommentByOrderNo";
	
	/**
	 * 通过商品编码查询对应的好评,中评,差评总数
	 */
	public String SELECT_TYPECOUNT_BY_COMMODITYCODEARRAY = "selectGoodCommentPercent";
}
