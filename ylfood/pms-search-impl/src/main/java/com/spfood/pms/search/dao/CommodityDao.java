package com.spfood.pms.search.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.pms.search.intf.domain.Commodity;
import com.spfood.pms.search.intf.domain.criteria.Parameters;
/**
 * CommodityDao 接口类
 * @author fengjunchao 2016-12-16
 *
 */
public interface CommodityDao extends BaseDao<Commodity>{

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
	 * 公共id数组集合查询商品列表
	 * @param idList
	 * @return
	 */
	public List<Commodity> selectListByIds(List<Long> idList);
	
	/**
	 * 根据编码集合查询商品集合
	 * @param codeList
	 * @return
	 */
	public List<Commodity> selectCommodityByCodelist(List<String> codeList);
	
	/**
	 * 根据编码集合和商品状态查询商品集合
	 * @param codeList
	 * @return
	 */
	public List<Commodity> selectCommodityByCodelist(Parameters parameters);
	/**
	 * 根据编码集合查询商品价格集合
	 * @param codeList
	 * @return
	 */
	public List<Commodity> selectCommodityPriceByCodelist(List<String> codeList);
}
