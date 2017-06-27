package com.spfood.pms.search.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.search.intf.CommodityAndCommentService;
import com.spfood.pms.search.intf.domain.ProductCategory;
import com.spfood.pms.search.intf.domain.criteria.CommodityAndComment;
import com.spfood.pms.search.intf.utils.Constant.CommodityShowPlace;
import com.spfood.pms.search.intf.utils.Constant.CommodityState;
import com.spfood.pms.search.intf.utils.Constant.OrderByCondition;
import com.spfood.pms.search.manager.CommodityAndCommentManager;

/**
 * commodityService 实现
 * @author fengjunchao 2017-01-16
 *
 */
public class CommodityAndCommentServiceImpl implements CommodityAndCommentService{
	
	@Autowired
	private CommodityAndCommentManager CommodityAndCommentManager;
	
	@Override
	public List<CommodityAndComment> selectCommodityByCodelist(List<String> codeList) {
		return CommodityAndCommentManager.selectCommodityByCodelist(codeList);
	}
	@Override
	public PageInfo<CommodityAndComment> selectCommodityListByPage(
			PageInfo<CommodityAndComment> pageInfo, List<String> keywords,
			HashMap<String, String> propertyMap, OrderByCondition orderBy,
			CommodityState state, CommodityShowPlace showState) {
		return CommodityAndCommentManager.selectCommodityListByPage(pageInfo, keywords, propertyMap, orderBy,state,showState);
	}
	
	@Override
	public PageInfo<CommodityAndComment> selectCommodityListByPageForApp(PageInfo<CommodityAndComment> pageInfo, String categoryCode) {
		
		CommodityAndComment commodity = new CommodityAndComment();
		ProductCategory category = new ProductCategory();
		category.setCategoryCode(categoryCode+"%");
		commodity.setCategory(category);
		commodity.setCommodityShowPlace("C端");
		commodity.setCommodityStatus(3);
		
		return CommodityAndCommentManager.selectCommodityListByPageForApp(pageInfo,commodity);
	}
}
