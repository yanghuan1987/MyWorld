package com.spfood.pms.dao;

import java.util.List;
import java.util.Map;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.intf.domain.Commodity;
/**
 * CommodityDao 接口类
 * @author fengjunchao 2016-12-16
 *
 */
public interface CommodityDao extends BaseDao<Commodity>{

	/**
	 * 修改商品状态
	 * @param ids
	 * @param state
	 * @return
	 */
	public Boolean updateCommodityState(List<Map<String,Object>> list);
	
	/**
	 * 公共id数组集合查询商品列表
	 * @param idList
	 * @return
	 */
	public List<Commodity> selectListByIds(List<Long> idList);
	
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
	 * 公共id数组集合查询评价
	 * @param idList
	 * @return
	 */
	public List<Commodity> selectListByIdsForComment(List<Long> idList);
}
