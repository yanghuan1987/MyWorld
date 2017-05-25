package com.spfood.pms.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.crm.customer.domain.Customer;
import com.spfood.crm.customer.service.CustomerService;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.intf.CommodityService;
import com.spfood.pms.intf.domain.Commodity;
import com.spfood.pms.intf.domain.CommodityPicture;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductCategoryProperty;
import com.spfood.pms.manager.CommodityManager;
import com.spfood.pms.manager.CommodityPictureManager;

/**
 * commodityService 实现
 * @author fengjunchao 2016-12-19
 *
 */
public class CommodityServiceImpl implements CommodityService{
	
	private static final Log log = LogFactory.getLog(CommodityServiceImpl.class);
	
	@Autowired
	private CommodityManager commodityManager;
	@Autowired
	private CommodityPictureManager commodityPictureManager;

    @Autowired 
    private CustomerService customerService;
    
	@Override
	public List<ProductCategoryProperty> selectCategoryPropertyByCategoryCode(
			String categoryCode) {
		return commodityManager.selectCategoryPropertyByCategoryCode(categoryCode);
	}

	@Override
	public Boolean updateCommodityStateUp(Long id) {
		Long ids[] = {id};
		log.info("commodityCode：["+id+"]to up");
		return commodityManager.updateCommodityState(ids, 3);
	}

	@Override
	public Boolean updateCommodityStateDown(Long id) {
		Long ids[] = {id};
		log.info("commodityCode：["+id+"]to down");
		return commodityManager.updateCommodityState(ids, 4);
	}
	@Override
	public Boolean updateCommodityStateDelete(Long id) {
		Long ids[] = {id};
		log.info("commodityCode：["+id+"]to delete");
		return commodityManager.updateCommodityState(ids, 6);
	}
	@Override
	public Boolean updateCommodityStateDeletes(Long[] ids) {
		return commodityManager.updateCommodityState(ids, 6);
	}
	@Override
	public Boolean updateCommodityStateUps(Long[] ids) {
		return commodityManager.updateCommodityState(ids, 3);
	}

	@Override
	public Boolean updateCOmmodityStateDowns(Long[] ids) {
		return commodityManager.updateCommodityState(ids, 4);
	}

	@Override
	public PageInfo<Commodity> selectCommodityAll(Commodity commodity,PageInfo<Commodity> pageInfo) {
		return commodityManager.selectCommodityListByPage(commodity,pageInfo,null,null,null);
	}
	@Override
	public PageInfo<Commodity> selectCommodityDetial(Commodity commodity,PageInfo<Commodity> pageInfo) {
		return commodityManager.selectCommodityListByPage(commodity,pageInfo);
	}
	@Override
	public List<CommodityPicture> selectCommodityPictureByCommodityCode(Commodity commodity) {
		CommodityPicture commodityPicture = new CommodityPicture();
		commodityPicture.setCommodity(commodity);
		return commodityPictureManager.selectList(commodityPicture);
	}

	@Override
	public void addCommodity(Commodity commodity) {
		commodityManager.addCommodity(commodity);
	}

	@Override
	public void updateCommodity(Commodity commodity) {
		commodityManager.updateCommodity(commodity);
	}

	@Override
	public List<Commodity> selectCommodityByProduct(String productCode) {
		return commodityManager.selectCommodityByProduct(productCode);
	}

	@Override
	public Commodity selectOneByCommodityCode(String commodityCode) {
		//获取商品的基本信息，包含属性信息
		Commodity commodity = commodityManager.selectByCommodityCode(commodityCode);
		if(commodity != null){
			log.info("selectOneByCommodityCode：["+commodityCode+"] get commodity");
			//获取商品的图片信息
			List<CommodityPicture> picList = selectCommodityPictureByCommodityCode(commodity);
			if(picList != null)
				commodity.setCommodityPictures(picList);
		}
		return commodity;
	}

	@Override
	public PageInfo<Commodity> selectCommodityByCategory(String categoryCode,
			Commodity commodity, PageInfo<Commodity> pageInfo) {
		commodity.setCommodityShowPlace("C端");
		return commodityManager.selectCommodityListByPage(categoryCode,commodity,pageInfo);
	}
	
	@Override
	public List<Commodity> selectCommodityByCategory(String categoryCode) {
		
		Commodity commodity = new Commodity();
		ProductCategory category = new ProductCategory();
		category.setCategoryCode(categoryCode);
		commodity.setCategory(category);
		commodity.setCommodityShowPlace("C端");
		commodity.setCommodityStatus(3);
		
		return commodityManager.selectCommodityByCategory(commodity);
	}
	
	@Override
	public List<Commodity> selectListByCommodityCodeList(List<String> CommodityCodeList) {
		//获取商品的基本信息，包含属性信息
		List<Commodity> commodityList = commodityManager.selectListByCommodityCodeList(CommodityCodeList);
		return commodityList;
	}
    @Override
    public PageInfo<Commodity> selectCommodityCommentByPage(PageInfo<Commodity> pageInfo, Commodity commodity) {
    	//输入查询条件有值得情况
    	if (null != commodity.getCommodityCommentDomain()) {
    		//当查询条件有电话时
			if (null != commodity.getCommodityCommentDomain().getCommentUserTel() &&
					!"".equals(commodity.getCommodityCommentDomain().getCommentUserTel())) {
				//通过CRM接口通过电话查询出用户编码
				List<Customer> list = customerService.getCustomerByNumber(commodity.getCommodityCommentDomain().getCommentUserTel());
				List<String> userCodeList = new ArrayList<String>();
				for (Customer customer : list) {
					userCodeList.add(customer.getCustomerId().toString());
				}
				//用户编码查询条件
				commodity.getCommodityCommentDomain().setCommentUsercodeList(userCodeList);
			}
		}
    	
    	return commodityManager.selectCommodityCommentByPage(pageInfo,commodity);
    }
}
