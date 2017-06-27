package com.spfood.pms.search.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.search.intf.CommoditySearchService;
import com.spfood.pms.search.intf.domain.Commodity;
import com.spfood.pms.search.intf.domain.CommodityPicture;
import com.spfood.pms.search.intf.domain.CommodityProperty;
import com.spfood.pms.search.intf.domain.ProductCategory;
import com.spfood.pms.search.intf.domain.ProductCategoryProperty;
import com.spfood.pms.search.intf.domain.criteria.Parameters;
import com.spfood.pms.search.intf.utils.Constant.CommodityShowPlace;
import com.spfood.pms.search.intf.utils.Constant.CommodityShowPlaceForSelect;
import com.spfood.pms.search.intf.utils.Constant.CommodityState;
import com.spfood.pms.search.intf.utils.Constant.OrderByCondition;
import com.spfood.pms.search.manager.CommodityManager;
import com.spfood.pms.search.manager.CommodityPictureManager;
import com.spfood.pms.search.manager.CommodityPropertyManager;
import com.spfood.pms.search.manager.ProductCategoryManager;
import com.spfood.pms.search.manager.ProductCategoryPropertyManager;

/**
 * commodityService 实现
 * @author fengjunchao 2017-01-16
 *
 */
public class CommodityServiceImpl implements CommoditySearchService{
	
	private static final Log log = LogFactory.getLog(CommodityServiceImpl.class);
	
	@Autowired
	private CommodityManager commodityManager;
	@Autowired
	private CommodityPictureManager commodityPictureManager;
	@Autowired
	private ProductCategoryManager productCategoryManager;
	@Autowired
	private CommodityPropertyManager commodityPropertyManager;
	@Autowired
	private ProductCategoryPropertyManager productCategoryPropertyManager;
	
	/**
	 * 根据商品编码获取商品图片集合
	 * @param commodity
	 * @return
	 */
	public List<CommodityPicture> selectCommodityPictureByCommodityCode(Commodity commodity) {
		try {
			List<CommodityPicture> picList = new ArrayList<CommodityPicture>();
			CommodityPicture commodityPicture = new CommodityPicture();
			commodityPicture.setCommodity(commodity);
			//根据传入的图片对象所包含的商品信息查询出所有该商品的图片集合
			picList = commodityPictureManager.selectList(commodityPicture);
			log.info("selectCommodityPictureByCommodityCode success ,picture size:["+picList.size()+"]");
			return picList;
		} catch (Exception e) {
			log.error("selectCommodityPictureByCommodityCode fail!", e);
			return null;
		}
	}

	@Override
	public List<Commodity> selectCommodityByProduct(String productCode) {
		try {
			//根据产品编码获取商品集合
			List<Commodity> commodityList = commodityManager.selectCommodityByProduct(productCode);
			log.info("selectCommodityByProduct success ,commodityList size:["+commodityList.size()+"]");
			return commodityList;
		} catch (Exception e) {
			log.error("selectCommodityByProduct fail!", e);
			return null;
		}
	}

	@Override
	public Commodity selectOneByCommodityCode(String commodityCode) {
		try {
			if(commodityCode == null)
				return null;
			//获取商品的基本信息，包含属性信息
			Commodity commodity = commodityManager.selectByCommodityCode(commodityCode);
			if(commodity != null){
				log.info("selectOneByCommodityCode：["+commodityCode+"] get commodity");
				CommodityProperty p = new CommodityProperty();
				p.setCommdityPropertyName("规格");
				p.setCommdityPropertyValue(commodity.getProduct().getProductSpecificationValue()+
						commodity.getProduct().getProductSpecificationUnitFirst()+"/"+
						commodity.getProduct().getProductSpecificationUnitSecond());
				commodity.getCommodityPropertys().add(p);
				//获取商品的图片信息
				List<CommodityPicture> picList = selectCommodityPictureByCommodityCode(commodity);
				if(picList != null){
					commodity.setCommodityPictures(picList);
					commodity.setImageUrl(picList.get(0).getPictureUrl());
				}
			}
			return commodity;
		} catch (Exception e) {
			log.error("selectOneByCommodityCode fail!", e);
			return null;
		}
	}

	@Override
	public List<ProductCategory> selectCategoryByCommodityName(
			String commodityName) {
		return productCategoryManager.selectCategoryByCommodityName(commodityName);
	}

	@Override
	public List<ProductCategoryProperty> selectPropertyByCommodityName(
			String commodityName) {
		return productCategoryPropertyManager.selectPropertyByCommodityName(commodityName);
	}

	@Override
	public List<ProductCategoryProperty> selectCategoryPropertyByCategoryCode(
			String categoryCode) {
		if (null == categoryCode || "".equals(categoryCode)) {
			return null;
		}
		return commodityManager.selectCategoryPropertyByCategoryCode(categoryCode);
	}

	@Override
	public PageInfo<Commodity> selectCommodityListByPage(
			PageInfo<Commodity> pageInfo, List<String> keywords,
			HashMap<String, String> propertyMap, OrderByCondition orderBy,CommodityState state) {
		return commodityManager.selectCommodityListByPage(pageInfo, keywords, propertyMap, orderBy,state,null);
	}

	@Override
	public List<ProductCategory> selectCategoryByKeywords(List<String> keywords,CommodityShowPlaceForSelect port) {
		String portString = port.name();//转换具体参数
		return productCategoryManager.selectCategoryByKeywords(keywords,portString);
	}

	@Override
	public HashMap<String,List<String>> selectPropertyByKeywords(
			List<String> keywords) {
		List<CommodityProperty> list = commodityPropertyManager.selectPropertyByKeywords(keywords);
		HashMap<String,List<String>> map = new HashMap<String,List<String>>();
		if(list.size() > 0)
			for(CommodityProperty p : list){
				if(map.containsKey(p.getCommdityPropertyName()))
					map.get(p.getCommdityPropertyName()).add(p.getCommdityPropertyValue());
				else{
					List<String> l = new ArrayList<String>();
					l.add(p.getCommdityPropertyValue());
					map.put(p.getCommdityPropertyName(), l);
				}
			}
		return map;
	}

	@Override
	public List<Commodity> selectCommodityByCodelist(List<String> codeList) {
		return commodityManager.selectCommodityByCodelist(codeList);
	}

	@Override
	public List<ProductCategory> selectCategoryNameByCommodityCode(String commodityCode) {
		return productCategoryManager.selectCategoryNameByCommodityCode(commodityCode);
	}

	@Override
	public PageInfo<Commodity> selectCommodityListByPage(
			PageInfo<Commodity> pageInfo, List<String> keywords,
			HashMap<String, String> propertyMap, OrderByCondition orderBy,
			CommodityState state, CommodityShowPlace showState) {
		return commodityManager.selectCommodityListByPage(pageInfo, keywords, propertyMap, orderBy,state,showState);
	}

	@Override
	public PageInfo<Commodity> selectCommodityListByPageForApp(PageInfo<Commodity> pageInfo, String categoryCode) {
		
		Commodity commodity = new Commodity();
		ProductCategory category = new ProductCategory();
		category.setCategoryCode(categoryCode+"%");
		commodity.setCategory(category);
		commodity.setCommodityShowPlace("C端");
		commodity.setCommodityStatus(3);
		
		return commodityManager.selectCommodityListByPageForApp(pageInfo,commodity);
	}
	
	@Override
	public List<Commodity> selectCommodityByCodelist(List<String> codeList,Integer commodityStatus) {
		
		if (null == codeList || null == commodityStatus ||
				codeList.size() == 0 || commodityStatus.equals("")) {
			return null;
		}
		
		Parameters parameters = new Parameters();
		//商品编码
		parameters.setListParameters(codeList);
		//商品状态
		parameters.setIntegerParameters(commodityStatus);
		
		return commodityManager.selectCommodityByCodelist(parameters);
	}
	@Override
	public List<Commodity> selectCommodityPriceByCodelist(List<String> codeList) {
		return commodityManager.selectCommodityPriceByCodelist(codeList);
	}
}
