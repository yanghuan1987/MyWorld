package com.spfood.pms.dao.impl;

public interface ProductCategorySqlIds {

	public String SELECT_BY_CATEGORYCODE = "selectByCategoryCode";
	public String SELECT_BY_CATEGORYNAME_ISHAVE = "selectByCategoryNameIsHave";
	public String SELECT_BY_CATEGORYCODE_ISHAVE = "selectByCategoryCodeIsHave";
	public String SELECTCHILDREN_BY_CATEGORYCODE = "selectCategoryChildren";
	public String INSERTLIST = "insertList";
	public String UPDATELIST = "updateList";
	public String INSRET_BY_COMMODITY = "insertByCommodity";
	public String SELECT_ALL = "selectAll";
	public String UPDATEBACK = "updateBack";
	public String SELECT_PAGE_IN_CATEGORY_ONE_FOR_COMMENT = "selectPageInCategoryOneForComment";
	public String SELECT_PAGE_IN_CATEGORY_TWO_FOR_COMMENT = "selectPageInCategoryTwoForComment";
	public String SELECT_PAGE_IN_CATEGORY_THREE_FOR_COMMENT = "selectPageInCategoryThreeForComment";
	public String SELECT_LIST_BY_IDS_ONE = "selectListByIdsOne";
	public String SELECT_LIST_BY_IDS_TWO = "selectListByIdsTwo";
	public String SELECT_LIST_BY_IDS_THREE = "selectListByIdsThree";
}
