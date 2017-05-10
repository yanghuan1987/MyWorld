package com.spfood.pms.search.intf;

import java.util.HashMap;
import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.search.intf.domain.Commodity;
import com.spfood.pms.search.intf.domain.ProductCategory;
import com.spfood.pms.search.intf.domain.ProductCategoryProperty;
import com.spfood.pms.search.intf.utils.Constant.CommodityShowPlace;
import com.spfood.pms.search.intf.utils.Constant.CommodityShowPlaceForSelect;
import com.spfood.pms.search.intf.utils.Constant.CommodityState;
import com.spfood.pms.search.intf.utils.Constant.OrderByCondition;

/**
 * 商品服务接口
 * @author fengjunchao 2017-01-16
 *
 */
public interface CommoditySearchService {

	/**
	 * 根据产品获取该产品下的所有商品(仅包含商品的基本信息)
	 * @param product
	 * @return
	 */
	public List<Commodity> selectCommodityByProduct(String productCode);
	
	/**
	 * 根据商品ID获取商品信息
	 * @param commodityCode
	 * @return
	 */
	public Commodity selectOneByCommodityCode(String commodityCode);
	
	/**
	 * 根据商品编码获取品类及其父类集合
	 * 集合顺序为：一级、二级、三级
	 * @param commodityCode
	 * @return
	 */
	public List<ProductCategory> selectCategoryNameByCommodityCode(String commodityCode);
	
	/**
	 * 根据编码集合查询商品集合
	 * @param codeList
	 * @return
	 */
	public List<Commodity> selectCommodityByCodelist(List<String> codeList);
	
	/**
	 * 根据商品名称模糊查询商品品类集合
	 * @param commodityName
	 * @return
	 */
	public List<ProductCategory> selectCategoryByCommodityName(String commodityName);
	
	/**
	 * 根据商品名称模糊查询商品品类集合
	 * @param commodityName
	 * @return
	 */
	public List<ProductCategory> selectCategoryByKeywords(List<String> keywords,CommodityShowPlaceForSelect port);
	
	/**
	 * 根据商品名称模糊查询商品属性
	 * @param commodityName
	 * @return
	 */
	public List<ProductCategoryProperty> selectPropertyByCommodityName(String commodityName);
	
	/**
	 * 根据商品多关键字模糊查询商品属性
	 * @return
	 */
	public HashMap<String,List<String>> selectPropertyByKeywords(List<String> keywords);
	
	/**
	 * 分页查询商品集合
	 * pageInfo:分页参数，keywords:搜索关键字集合（支持多关键字检索），propertyMap:属性集合（k:属性名称，value:属性值），orderBy:排序规则
	 * state:商品状态0-待审核,1-已通过审核,2-未通过审核,3-上架,4-下架,5-停用
	 * @return
	 */
	public PageInfo<Commodity> selectCommodityListByPage(PageInfo<Commodity> pageInfo,List<String> keywords
			,HashMap<String,String> propertyMap,OrderByCondition orderBy,CommodityState state);
	
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
	public PageInfo<Commodity> selectCommodityListByPage(PageInfo<Commodity> pageInfo,List<String> keywords
			,HashMap<String,String> propertyMap,OrderByCondition orderBy,CommodityState state,CommodityShowPlace showState);
	
	/**
	 * 根据品类编码获取品类属性集合
	 * @return
	 */
	public List<ProductCategoryProperty> selectCategoryPropertyByCategoryCode(String categoryCode);
	
	
	/**
	 * 分页查询商品集合
	 * @param pageInfo  分页参数
	 * @param CateGroyCode  品类code
	 * @return
	 */
	public PageInfo<Commodity> selectCommodityListByPageForApp(PageInfo<Commodity> pageInfo,String CateGroyCode);
	
	/**
	 * 根据编码集合和商品状态查询商品集合
	 * @param codeList
	 * @return
	 */
	public List<Commodity> selectCommodityByCodelist(List<String> codeList,Integer commodityStatus);
}
