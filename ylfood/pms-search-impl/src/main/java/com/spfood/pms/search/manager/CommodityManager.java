package com.spfood.pms.search.manager;

import java.util.HashMap;
import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.search.intf.domain.Commodity;
import com.spfood.pms.search.intf.domain.ProductCategoryProperty;
import com.spfood.pms.search.intf.domain.criteria.Parameters;
import com.spfood.pms.search.intf.utils.Constant.CommodityShowPlace;
import com.spfood.pms.search.intf.utils.Constant.CommodityState;
import com.spfood.pms.search.intf.utils.Constant.OrderByCondition;

/**
 * Commodity 接口
 * @author fengjunchao 2016-12-16
 *
 */
public interface CommodityManager extends BaseManager<Commodity> {

    /**
     * 根据产品编码获取商品
     * @param productCode
     * @return
     */
    public List<Commodity> selectCommodityByProduct(String productCode);
    
    /**
     * 根据商品编码获取商品
     * @param commodityCode
     * @return
     */
    public Commodity selectByCommodityCode(String commodityCode);
    
    /**
     * 根据编码集合查询商品集合
     * @param codeList
     * @return
     */
    public List<Commodity> selectCommodityByCodelist(List<String> codeList);
    
    /**
     * 根据品类编码获取品类属性集合
     * @return
     */
    public List<ProductCategoryProperty> selectCategoryPropertyByCategoryCode(String categoryCode);
    /**
     * 分页查询商品集合
     * pageInfo:分页参数，keywords:搜索关键字集合（支持多关键字检索），propertyMap:属性集合（k:属性名称，value:属性值），orderBy:排序规则
     * @return
     */
    public PageInfo<Commodity> selectCommodityListByPage(PageInfo<Commodity> pageInfo,List<String> keywords
            ,HashMap<String,String> propertyMap,OrderByCondition orderBy,CommodityState state,CommodityShowPlace showState);
    
    /**
     * 分页查询商品集合
     * pageInfo:分页参数，commodity:商品信息
     * @return
     */
    public PageInfo<Commodity> selectCommodityListByPageForApp(PageInfo<Commodity> pageInfo, Commodity commodity);
    
	/**
	 * 根据编码集合和商品状态查询商品集合
	 * @param codeList
	 * @return
	 */
	public List<Commodity> selectCommodityByCodelist(Parameters parameters);
}
