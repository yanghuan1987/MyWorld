package com.spfood.pms.search.intf;

import java.util.HashMap;
import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.search.intf.domain.criteria.CommodityAndComment;
import com.spfood.pms.search.intf.utils.Constant.CommodityShowPlace;
import com.spfood.pms.search.intf.utils.Constant.CommodityState;
import com.spfood.pms.search.intf.utils.Constant.OrderByCondition;

/**
 * 商品服务接口
 * @author fengjunchao 2017-01-16
 *
 */
public interface CommodityAndCommentService {
	/**
	 * 根据编码集合查询商品集合
	 * @param codeList
	 * @return
	 */
	public List<CommodityAndComment> selectCommodityByCodelist(List<String> codeList);
	/**
	 * 分页查询商品集合
	 * @param pageInfo  分页参数
	 * @param keywords  搜索关键字集合
	 * @param propertyMap  属性集合
	 * @param orderBy  排序规则
	 * @param state    商品状态
	 * @param showState 显示客户端
	 * @return
	 */
	public PageInfo<CommodityAndComment> selectCommodityListByPage(PageInfo<CommodityAndComment> pageInfo,List<String> keywords
			,HashMap<String,String> propertyMap,OrderByCondition orderBy,CommodityState state,CommodityShowPlace showState);
	/**
	 * 分页查询商品集合
	 * @param pageInfo  分页参数
	 * @param CateGroyCode  品类code
	 * @return
	 */
	public PageInfo<CommodityAndComment> selectCommodityListByPageForApp(PageInfo<CommodityAndComment> pageInfo,String CateGroyCode);
}
