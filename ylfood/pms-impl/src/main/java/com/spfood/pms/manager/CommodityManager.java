package com.spfood.pms.manager;

import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.intf.domain.Commodity;
import com.spfood.pms.intf.domain.ProductCategoryProperty;

/**
 * Commodity 接口
 * @author fengjunchao 2016-12-16
 *
 */
public interface CommodityManager extends BaseManager<Commodity> {

	/**
	 * 分页查询商品集合
	 * rows 页数
	 * page 需要查询的数量
	 * orderBy 排序字段
	 * descOrAsc 排序规则
	 * con 其他组合条件
	 * @return
	 */
	public PageInfo<Commodity> selectCommodityListByPage(Commodity commodity,PageInfo<Commodity> pageInfo,String orderBy,String descOrAsc,String con);
	
	/**
	 * 分页查询商品集合
	 * rows 页数
	 * page 需要查询的数量
	 * categoryCode 品类编码
	 * @return
	 */
	public PageInfo<Commodity> selectCommodityListByPage(String categoryCode,Commodity commodity,PageInfo<Commodity> pageInfo);
	
	/**
	 * 修改商品状态
	 * @param ids
	 * @param state
	 * @return
	 */
	public Boolean updateCommodityState(Long ids[],Integer state);
	
	/**
	 * 根据品类编码获取品类属性集合
	 * @return
	 */
	public List<ProductCategoryProperty> selectCategoryPropertyByCategoryCode(String categoryCode);
	
	/**
	 * 新增商品
	 * @param commodity
	 */
	public void addCommodity(Commodity commodity);
	
	/**
	 * 修改商品
	 * @param commodity
	 */
	public void updateCommodity(Commodity commodity);
	
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
	 * 
	 * categoryCode 品类编码
	 * @return
	 */
	public List<Commodity> selectCommodityByCategory(Commodity commodity);
	
	/**
	 * 商品编码集合获取商品信息
	 * @param list 商品编码集合
	 * @return
	 */
	public List<Commodity> selectListByCommodityCodeList(List<String> CommodityCodeList);
	
	/**
	 * MQ取得商品信息
	 * @return
	 */
	public List<Commodity> sendDate();
    /**
     * 分页查询评价统计
     * pageInfo:分页参数，CommodityComment:商品信息
     * @return
     */
    public PageInfo<Commodity> selectCommodityCommentByPage(PageInfo<Commodity> pageInfo, Commodity commodity);
	
}
