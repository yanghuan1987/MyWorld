package com.spfood.pms.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.dao.impl.SqlIds;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.oms.order.intf.domain.OrderCommodity;
import com.spfood.oms.orderinfosyn.intf.OrderCommodityService;
import com.spfood.pms.dao.CommodityDao;
import com.spfood.pms.dao.CommodityPictureDao;
import com.spfood.pms.dao.CommodityPropertyDao;
import com.spfood.pms.dao.PmsCommodityMsgTempDao;
import com.spfood.pms.dao.ProductCategoryDao;
import com.spfood.pms.dao.ProductDao;
import com.spfood.pms.dao.ProductPropertyDao;
import com.spfood.pms.dao.impl.CommoditySqlIds;
import com.spfood.pms.intf.domain.Commodity;
import com.spfood.pms.intf.domain.CommodityPicture;
import com.spfood.pms.intf.domain.CommodityUpdateSalesError;
import com.spfood.pms.intf.domain.PmsCommodityMsgTemp;
import com.spfood.pms.intf.domain.Product;
import com.spfood.pms.intf.domain.ProductCategory;
import com.spfood.pms.intf.domain.ProductCategoryProperty;
import com.spfood.pms.intf.domain.ProductProperty;
import com.spfood.pms.intf.utils.Constant.errorStatus;
import com.spfood.pms.intf.utils.Constant.itemType;
import com.spfood.pms.manager.CommodityManager;
import com.spfood.pms.manager.CommodityUpdateSalesErrorManager;
@Service
public class CommodityManagerImpl extends BaseManagerImpl<Commodity> implements CommodityManager {
	
	private static Logger logger = Logger.getLogger(CommodityManagerImpl.class);

	@Autowired
	private CommodityDao commodityDao;
	
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Autowired
	private ProductPropertyDao productPropertyDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CommodityPropertyDao commodityPropertyDao;
	
	@Autowired
	private CommodityPictureDao commodityPictureDao;
	
	@Autowired
	private PmsCommodityMsgTempDao pmsCommodityMsgTempDat;
	
	@Resource
	private OrderCommodityService orderCommodityService;
	
	@Resource
	private CommodityUpdateSalesErrorManager CommodityUpdateSalesErrorManager;
	
	@Override
	protected BaseDao<Commodity> getBaseDao() {
		return commodityDao;
	}
	
	/**
	 * 
	 * @param commodity
	 * @param pageInfo
	 * @return
	 */
	public PageInfo<Commodity> selectCommodityPageInfo(Commodity commodity,PageInfo<Commodity> pageInfo,String sqlName){
		//获取商品id数组，用于获取商品
		PageInfo<Commodity> pageInfos = commodityDao.selectListByPage(commodity, sqlName, pageInfo);
		//id集合，通过id集合获取商品
		List<Long> idList = new ArrayList<Long>();
		//遍历增加id集合
		if(pageInfos.getResult() != null && pageInfos.getResult().size() > 0){
			for(Commodity com : pageInfos.getResult()){
				idList.add(com.getId());
			}
			pageInfos.setResult(commodityDao.selectListByIds(idList));
		}else
			pageInfos.setResult(null);
			
		return pageInfos;
	}

	@Override
	public PageInfo<Commodity> selectCommodityListByPage(Commodity commodity,PageInfo<Commodity> pageInfo,String orderBy,String descOrAsc,String con) {
		
		String strCommodity = commodity.getCommodityCode();
		if(strCommodity != null){
			commodity.setCommodityCode("%" + commodity.getCommodityCode() + "%");
		}
		
		return selectCommodityPageInfo(commodity,pageInfo,SqlIds.SQL_SELECT);
	}

	@Override
	public Boolean updateCommodityState(Long[] ids, Integer state) {
		boolean bol = false;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Long id : ids){
			Map<String,Object> obj = new HashMap<String,Object>();
			obj.put("state", state);
			obj.put("id", id);
			list.add(obj);
		}
		bol = commodityDao.updateCommodityState(list);
		logger.info("update commodityState: to "+state+",result:"+bol);
		return bol;
	}

	@Override
	public List<ProductCategoryProperty> selectCategoryPropertyByCategoryCode(
			String categoryCode) {
		return productCategoryDao.selectByCategoryCode(categoryCode).getProductCategoryPropertys();
	}

	@Transactional
	@Override
	public void addCommodity(Commodity commodity) {
		if(commodity != null && commodity.getCommodityPictures() != null){
			commodity.setCreateDate(new Date());
			commodity.setLastUpdateDate(new Date());
			//产品温区保存
			Product product = new Product();
			product = productDao.selectByProductCode(commodity.getProduct());
			commodity.getProduct().setProductTemperatureZoneName(product.getProductTemperatureZoneName());
			commodity.getProduct().setProductTemperatureZoneCode(product.getProductTemperatureZoneCode());
			//将产品的属性赋值给商品的属性值字段
			List<ProductProperty> propertyList = productPropertyDao.selectByProductCode(commodity.getProduct().getProductCode());
			StringBuffer strb = new StringBuffer();
			for(ProductProperty proper : propertyList){
				strb.append(proper.getProductPropertyName()+":"+proper.getProductPropertyValue()+"-");
			}
			commodity.setSearchProperty(strb.toString());
			commodity.setCommodityStatus(4);//新增时，商品默认是下架状态
			//插入商品基本信息，mybatis配置将会回传商品的编码
			commodityDao.insert(commodity);
			//将得到的商品编码赋值给每一个商品图片
			for(CommodityPicture pic : commodity.getCommodityPictures()){
				pic.setCommodity(commodity);
			}
			//插入商品图片
			commodityPictureDao.insertPictureList(commodity.getCommodityPictures());
			//插入商品属性
			commodityPropertyDao.insertByCommodity(commodity);
		}
		PmsCommodityMsgTemp pmsCommodityMsgTemp = new PmsCommodityMsgTemp();
		pmsCommodityMsgTemp.setCommdityCode(commodity.getCommodityCode());
		pmsCommodityMsgTemp.setType(itemType.commodity.getValue());
		pmsCommodityMsgTempDat.insert(pmsCommodityMsgTemp);
	}
	
	@Transactional
	@Override
	public void updateCommodity(Commodity commodity) {
		
		commodity.setLastUpdateDate(new Date());
		//插入商品基本信息，mybatis配置将会回传商品的编码
		commodityDao.updateById(commodity);
		//将得到的商品编码赋值给每一个商品图片
		for(CommodityPicture pic : commodity.getCommodityPictures()){
			pic.setCommodity(commodity);
		}
		//将得到的商品编码赋值给每一个商品图片
		if(commodity.getCommodityPictures() != null){
			//删除原有的商品图片
			commodityPictureDao.deleteByCommodityCode(commodity.getCommodityCode());
			//插入新的商品图片
			commodityPictureDao.insertPictureList(commodity.getCommodityPictures());
		}

		PmsCommodityMsgTemp pmsCommodityMsgTemp = new PmsCommodityMsgTemp();
		pmsCommodityMsgTemp.setCommdityCode(commodity.getCommodityCode());
		pmsCommodityMsgTemp.setType(itemType.commodity.getValue());
		pmsCommodityMsgTempDat.insert(pmsCommodityMsgTemp);
	}

	@Override
	public List<Commodity> selectCommodityByProduct(String productCode) {
		return commodityDao.selectCommodityByProduct(productCode);
	}

	@Override
	public List<Commodity> selectCommodityByProduct(List<String> productCodeList) {
		return commodityDao.selectCommodityByProduct(productCodeList);
	}
	
	@Override
	public Commodity selectByCommodityCode(String commodityCode) {
		return commodityDao.selectByCommodityCode(commodityCode);
	}

	@Override
	public PageInfo<Commodity> selectCommodityListByPage(String categoryCode,
			Commodity commodity, PageInfo<Commodity> pageInfo) {
		if(commodity.getCommodityCode() != null){
			commodity.setCommodityCode("%" + commodity.getCommodityCode() + "%");
		}
		if(categoryCode != null){
			ProductCategory category = new ProductCategory();
			category.setCategoryCode(categoryCode + "%");
			commodity.setCategory(category);
		}
		if(commodity.getCommodityName() != null){
			commodity.setCommodityName("%" + commodity.getCommodityName() + "%");
		}
		
		return selectCommodityPageInfo(commodity,pageInfo,CommoditySqlIds.SELECT_BY_CATEGORY);
	}

	@Override
	public List<Commodity> selectCommodityByCategory(Commodity commodity) {
		return commodityDao.selectCommodityByCategory(commodity);
	}
	
	@Override
	public List<Commodity> selectListByCommodityCodeList(List<String> CommodityCodeList) {
		return commodityDao.selectListByCommodityCodeList(CommodityCodeList);
	}
	
	//取得消息内容
	@Transactional
	public List<Commodity> sendDate(){
		
		List<Commodity> list = new ArrayList<Commodity>();
		//取得商品信息发送
		list = commodityDao.sendDate();
		if (null != list && list.size() != 0) {
			List<String> CommodityCode = new ArrayList<String>();
			for (Commodity commodity : list) {
				CommodityCode.add(commodity.getCommodityCode());
			}
			//更新取得信息时间
			pmsCommodityMsgTempDat.updateByCommodityCodeList(CommodityCode);
		}
		return list;
	}
	
    @Override
    public PageInfo<Commodity> selectCommodityCommentByPage(PageInfo<Commodity> pageInfo, Commodity commodity) {
        if(pageInfo == null) return null;
        //参数组合
        //获取商品id数组，用于获取商品
        String sqlIdPage = CommoditySqlIds.SELECT_PAGE_IN_COMMODITY_FOR_COMMENT;
        PageInfo<Commodity> pageInfos = commodityDao.selectListByPage(commodity,sqlIdPage,pageInfo);
        //id集合，通过id集合获取商品
        List<Long> idList = new ArrayList<Long>(15);
        //遍历增加id集合
        if(pageInfos.getResult() != null && pageInfos.getResult().size() > 0){
            for(Commodity commodity2 : pageInfos.getResult()){
                idList.add(commodity2.getId());
            }
            List<Commodity> clist = commodityDao.selectListByIdsForComment(idList);
            //得到结果按查询ID排序
            List<Commodity> result = new ArrayList<Commodity>(15);
            for (int i = 0; i < idList.size(); i++) {
            	for (Commodity commodity2 : clist) {
            		if (idList.get(i).equals(commodity2.getId())) {
            			result.add(commodity2);
            		}
				}
			}
            pageInfos.setResult(result);
        }else
            pageInfos.setResult(null);
            
        return pageInfos;
    }
    
	@Override
	public PageInfo<Commodity> selectCommodityListByPage(Commodity commodity, PageInfo<Commodity> pageInfo) {
		return selectCommodityPageInfo(commodity,pageInfo,CommoditySqlIds.SELECT_COMMODITY_DETAIL);
	}

	/**
	 * 更新销售数量
	 * @return
	 */
	public Boolean updateCommodityQuantity(){

		logger.info("Start updateCommodityQuantity");
		List<OrderCommodity> orderCommodities = new ArrayList<OrderCommodity>(200);
		CommodityUpdateSalesError commodityUpdateSalesError = new CommodityUpdateSalesError();
		List<Commodity> listCommodities = new ArrayList<Commodity>(200);
		try {
			orderCommodities = orderCommodityService.getcCommodities();
			if (orderCommodities.size() == 0) {
				logger.info("there is not any orders on today,so don't update CommodityQuantity");
				return true;
			}else {
				for (OrderCommodity orderCommodity : orderCommodities) {
					Commodity commodity = new Commodity();
					commodity.setCommodityCode(orderCommodity.getCode());
					commodity.setSalesAmount(orderCommodity.getCount());
					listCommodities.add(commodity);
				}
			}
		} catch (Exception e) {
			logger.error("There was a exception in OMS's interface,place check table[pms_commodity_update_sales_error],update later");
			commodityUpdateSalesError.setErrorDate(new Date());
			commodityUpdateSalesError.setErrorStatus(errorStatus.wating.getValue());
			commodityUpdateSalesError.setInfo("Exception is" + e);
			CommodityUpdateSalesErrorManager.insert(commodityUpdateSalesError);
		}
		
		int count = commodityDao.updateCommodityQuantity(listCommodities,orderCommodities.size());
		if (0 == count) {
			logger.error("Some of the Commodites was not be updated,place check table[pms_commodity_update_sales_error],update later");
			commodityUpdateSalesError.setErrorDate(new Date());
			commodityUpdateSalesError.setErrorStatus(errorStatus.wating.getValue());
			commodityUpdateSalesError.setInfo("There was not all Commodity be updated,Please check the CommodityCode");
			CommodityUpdateSalesErrorManager.insert(commodityUpdateSalesError);
			return false;
		}
		return true;
	}
    
    
}
