package com.spfood.pms.intf;

import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.domain.Commodity;
import com.spfood.pms.intf.domain.CommodityPicture;
import com.spfood.pms.intf.domain.ProductCategoryProperty;

/**
 * 品类服务接口
 * @author fengjunchao 2016-12-07
 *
 */
public interface CommodityService {

	/**
	 * 根据品类编码获取品类属性集合
	 * @param categoryCode
	 * @return
	 */
	public List<ProductCategoryProperty> selectCategoryPropertyByCategoryCode(String categoryCode);
	
	/**
	 * 获取商品列表
	 * @return
	 */
	public PageInfo<Commodity> selectCommodityAll(Commodity commodity,PageInfo<Commodity> pageInfo);
	/**
	 * 获取商品列表-详细查询
	 * @return
	 */
	public PageInfo<Commodity> selectCommodityDetial(Commodity commodity,PageInfo<Commodity> pageInfo);
	
	/**
	 * 根据品类模糊查询
	 * 获取商品列表
	 * @return
	 */
	public PageInfo<Commodity> selectCommodityByCategory(String categoryCode,Commodity commodity,PageInfo<Commodity> pageInfo);
	
	/**
	 * 根据产品获取该产品下的所有商品(仅包含商品的基本信息)
	 * @param product
	 * @return
	 */
	public List<Commodity> selectCommodityByProduct(String productCode);
	
	/**
	 * 单个商品上架
	 * @param id
	 * @return
	 */
	public Boolean updateCommodityStateUp(Long id);
	
	/**
	 * 单个商品下架
	 * @param id
	 * @return
	 */
	public Boolean updateCommodityStateDown(Long id);
	/**
	 * 单个商品删除
	 * @param id
	 * @return
	 */
	public Boolean updateCommodityStateDelete(Long id);
	/**
	 * 商品批量上架
	 * @param ids
	 * @return
	 */
	public Boolean updateCommodityStateUps(Long ids[]);
	
	/**
	 * 商品批量下架
	 * @param ids
	 * @return
	 */
	public Boolean updateCOmmodityStateDowns(Long ids[]);
	/**
	 * 商品批量删除
	 * @param id
	 * @return
	 */
	public Boolean updateCommodityStateDeletes(Long ids[]);
	
	/**
	 * 根据商品编码获取商品图片集合
	 * @return
	 */
	public List<CommodityPicture> selectCommodityPictureByCommodityCode(Commodity commodity);
	
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
	 * 根据商品ID获取商品信息
	 * @param commodityCode
	 * @return
	 */
	public Commodity selectOneByCommodityCode(String commodityCode);
	
	/**
	 * 根据品类查询
	 * 获取商品列表
	 * @return
	 */
	public List<Commodity> selectCommodityByCategory(String categoryCode);
	
	/**
	 * 商品编码集合获取商品信息
	 * @param list 商品编码集合
	 * @return
	 */
	public List<Commodity> selectListByCommodityCodeList(List<String> CommodityCodeList);
	

    /**
     * 分页查询评价统计
     * pageInfo:分页参数，CommodityComment:商品信息
     * @return
     */
    public PageInfo<Commodity> selectCommodityCommentByPage(PageInfo<Commodity> pageInfo, Commodity commodity);
}
