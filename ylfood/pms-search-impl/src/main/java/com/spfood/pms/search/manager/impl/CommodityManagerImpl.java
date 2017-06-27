package com.spfood.pms.search.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.search.dao.CommodityDao;
import com.spfood.pms.search.dao.ProductCategoryPropertyDao;
import com.spfood.pms.search.intf.domain.Commodity;
import com.spfood.pms.search.intf.domain.CommodityProperty;
import com.spfood.pms.search.intf.domain.CommoditySqlIds;
import com.spfood.pms.search.intf.domain.ProductCategory;
import com.spfood.pms.search.intf.domain.ProductCategoryProperty;
import com.spfood.pms.search.intf.domain.criteria.Parameters;
import com.spfood.pms.search.intf.utils.Constant.CommodityShowPlace;
import com.spfood.pms.search.intf.utils.Constant.CommodityState;
import com.spfood.pms.search.intf.utils.Constant.OrderByCondition;
import com.spfood.pms.search.manager.CommodityManager;
@Service
public class CommodityManagerImpl extends BaseManagerImpl<Commodity> implements CommodityManager {
    
    private static Logger logger = Logger.getLogger(CommodityManagerImpl.class);
    //B2C根据商品模糊搜索时，传入的property的集合中将K值设置为catg表示该参数为品类的参数
    private final String searchPram = "catg";
    @Autowired
    private CommodityDao commodityDao;
    @Autowired
    private ProductCategoryPropertyDao productCategoryPropertyDao;
    
    @Override
    protected BaseDao<Commodity> getBaseDao() {
        return commodityDao;
    }

    @Override
    public List<Commodity> selectCommodityByProduct(String productCode) {
        return commodityDao.selectCommodityByProduct(productCode);
    }

    @Override
    public Commodity selectByCommodityCode(String commodityCode) {
        return commodityDao.selectByCommodityCode(commodityCode);
    }


    @Override
    public List<ProductCategoryProperty> selectCategoryPropertyByCategoryCode(
            String categoryCode) {
        return productCategoryPropertyDao.selectCategoryProperty(categoryCode);
    }
    
    /**
     * 参数配置
     * @param keywords
     * @param propertyMap
     * @param orderBy
     * @param state
     * @return
     */
    public Commodity ParameterMerge(List<String> keywords,
            HashMap<String, String> propertyMap, OrderByCondition orderBy,CommodityState state,CommodityShowPlace showState){
        Commodity commodity = new Commodity();
        logger.info("selectCommodityListByPage start. state"+state);
        //多关键字搜索
        List<String> list = new ArrayList<String>();
        if(keywords != null)
        for(String str : keywords){
            list.add(str);
        }
        commodity.setKeywords(list);
        if(state != null)
            commodity.setCommodityStatus(state.ordinal());
        if(showState != null)
            commodity.setCommodityShowPlace(showState.ordinal() == 0?"小B端":"C端");
        
        //多属性搜索
        List<CommodityProperty> pList = new ArrayList<CommodityProperty>();
        if(propertyMap != null){
            @SuppressWarnings("rawtypes")
            Iterator iter = propertyMap.keySet().iterator();
            while (iter.hasNext()) {
                Object key = iter.next();
                Object val = propertyMap.get(key);
                if(key.equals(searchPram)){
                    //品类的参数
                    ProductCategory category = new ProductCategory();
                    category.setCategoryCode(val.toString());
                    commodity.setCategory(category);
                }else{
                    //属性的参数
                    CommodityProperty prop = new CommodityProperty();
                    prop.setCommdityPropertyName("%"+key+":"+val+"%");
                    pList.add(prop);
                }
            }
        }
        commodity.setCommodityPropertys(pList);
        
        //排序
        if(orderBy != null && !"".equals(orderBy)){
            commodity.setOrderBys(orderBy.ordinal());
        }
        return commodity;
    }

    @Override
    public PageInfo<Commodity> selectCommodityListByPage(
            PageInfo<Commodity> pageInfo, List<String> keywords,
            HashMap<String, String> propertyMap, OrderByCondition orderBy,CommodityState state,CommodityShowPlace showState) {
        if(pageInfo == null) return null;
        
        //参数组合
        //获取商品id数组，用于获取商品
        PageInfo<Commodity> pageInfos = commodityDao.selectListByPage(ParameterMerge(keywords,propertyMap,orderBy,state,showState),CommoditySqlIds.SQL_SELECT_BYPAGE,pageInfo);
        //id集合，通过id集合获取商品
        List<Long> idList = new ArrayList<Long>();
        //遍历增加id集合
        if(pageInfos.getResult() != null && pageInfos.getResult().size() > 0){
            for(Commodity com : pageInfos.getResult()){
                idList.add(com.getId());
            }
            List<Commodity> clist = commodityDao.selectListByIds(idList);
            List<Commodity> newlist = new LinkedList<Commodity>();
            //排序
            for(Long l : idList){
                for(Commodity c : clist){
                    if(c.getId().equals(l)){
                        if(c.getCommodityPictures() != null && c.getCommodityPictures().size() > 0
                                && c.getCommodityPictures().get(0) != null)
                        c.setImageUrl(c.getCommodityPictures().get(0).getPictureUrl());
                        newlist.add(c);
                        break;
                    }
                }
            }
            pageInfos.setResult(newlist);
        }else
            pageInfos.setResult(null);
        return pageInfos;
    }

    @Override
    public List<Commodity> selectCommodityByCodelist(List<String> codeList) {
    	if(codeList == null || codeList.size() < 1)
    		return null;
        return commodityDao.selectCommodityByCodelist(codeList);
    }

    @Override
    public PageInfo<Commodity> selectCommodityListByPageForApp(PageInfo<Commodity> pageInfo, Commodity commodity) {
        if(pageInfo == null) return null;
        
        //参数组合
        //获取商品id数组，用于获取商品
        PageInfo<Commodity> pageInfos = commodityDao.selectListByPage(commodity,CommoditySqlIds.SQL_SELECT_BYPAGE_APP,pageInfo);
        //id集合，通过id集合获取商品
        List<Long> idList = new ArrayList<Long>();
        //遍历增加id集合
        if(pageInfos.getResult() != null && pageInfos.getResult().size() > 0){
            for(Commodity com : pageInfos.getResult()){
                idList.add(com.getId());
            }
            List<Commodity> clist = commodityDao.selectListByIds(idList);
            List<Commodity> newlist = new ArrayList<Commodity>();
            //排序
            for(Long l : idList){
                for(Commodity c : clist){
                    if(c.getId().equals(l)){
                        if(c.getCommodityPictures() != null && c.getCommodityPictures().size() > 0
                                && c.getCommodityPictures().get(0) != null)
                        c.setImageUrl(c.getCommodityPictures().get(0).getPictureUrl());
                        newlist.add(c);
                        break;
                    }
                }
            }
            pageInfos.setResult(newlist);
        }else
            pageInfos.setResult(null);
            
        return pageInfos;
    }

    @Override
    public List<Commodity> selectCommodityByCodelist(Parameters parameters) {
        return commodityDao.selectCommodityByCodelist(parameters);
    }
    
    @Override
    public List<Commodity> selectCommodityPriceByCodelist(List<String> codeList) {
    	if(codeList == null || codeList.size() < 1)
    		return null;
        return commodityDao.selectCommodityPriceByCodelist(codeList);
    }
}
