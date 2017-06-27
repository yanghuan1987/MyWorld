/**
 * 
 */
package com.spfood.pms.search.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.pms.search.intf.domain.CommodityProperty;
import com.spfood.pms.search.intf.domain.CommoditySqlIds;
import com.spfood.pms.search.intf.domain.ProductCategory;
import com.spfood.pms.search.intf.domain.criteria.CommodityAndComment;
import com.spfood.pms.search.intf.utils.Constant.CommodityShowPlace;
import com.spfood.pms.search.intf.utils.Constant.CommodityState;
import com.spfood.pms.search.intf.utils.Constant.OrderByCondition;
import com.spfood.pms.search.manager.CommodityAndCommentManager;
import com.spfood.pms.search.dao.CommodityAndCommentDao;

/**
 * @author Administrator
 *
 */
@Service
public class CommodityAndCommentManagerImpl implements
		CommodityAndCommentManager {
    private static Logger logger = Logger.getLogger(CommodityManagerImpl.class);
    //B2C根据商品模糊搜索时，传入的property的集合中将K值设置为catg表示该参数为品类的参数
    private final String searchPram = "catg";
    @Autowired
    private CommodityAndCommentDao CommodityAndCommentDao;

	@Override
	public List<CommodityAndComment> selectCommodityByCodelist(
			List<String> codeList) {
    	if(codeList == null || codeList.size() < 1)
    		return null;
        return CommodityAndCommentDao.selectCommodityByCodelist(codeList);
	}

	@Override
	public PageInfo<CommodityAndComment> selectCommodityListByPage(
			PageInfo<CommodityAndComment> pageInfo, List<String> keywords,
			HashMap<String, String> propertyMap, OrderByCondition orderBy,
			CommodityState state, CommodityShowPlace showState) {
        if(pageInfo == null) return null;
        
        //参数组合
        //获取商品id数组，用于获取商品
        PageInfo<CommodityAndComment> pageInfos = CommodityAndCommentDao.selectListByPage(ParameterMerge(keywords,propertyMap,orderBy,state,showState),CommoditySqlIds.SQL_SELECT_BYPAGE,pageInfo);
        //id集合，通过id集合获取商品
        List<Long> idList = new ArrayList<Long>();
        //遍历增加id集合
        if(pageInfos.getResult() != null && pageInfos.getResult().size() > 0){
            for(CommodityAndComment com : pageInfos.getResult()){
                idList.add(com.getId());
            }
            List<CommodityAndComment> clist = CommodityAndCommentDao.selectListByIds(idList);
            List<CommodityAndComment> newlist = new LinkedList<CommodityAndComment>();
            //排序
            for(Long l : idList){
                for(CommodityAndComment c : clist){
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

    
    /**
     * 参数配置
     * @param keywords
     * @param propertyMap
     * @param orderBy
     * @param state
     * @return
     */
    public CommodityAndComment ParameterMerge(List<String> keywords,
            HashMap<String, String> propertyMap, OrderByCondition orderBy,CommodityState state,CommodityShowPlace showState){
        CommodityAndComment CommodityAndComment = new CommodityAndComment();
        logger.info("selectCommodityListByPage start. state"+state);
        //多关键字搜索
        List<String> list = new ArrayList<String>();
        if(keywords != null)
        for(String str : keywords){
            list.add(str);
        }
        CommodityAndComment.setKeywords(list);
        if(state != null)
            CommodityAndComment.setCommodityStatus(state.ordinal());
        if(showState != null)
            CommodityAndComment.setCommodityShowPlace(showState.ordinal() == 0?"小B端":"C端");
        
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
                    CommodityAndComment.setCategory(category);
                }else{
                    //属性的参数
                    CommodityProperty prop = new CommodityProperty();
                    prop.setCommdityPropertyName("%"+key+":"+val+"%");
                    pList.add(prop);
                }
            }
        }
        CommodityAndComment.setCommodityPropertys(pList);
        
        //排序
        if(orderBy != null && !"".equals(orderBy)){
            CommodityAndComment.setOrderBys(orderBy.ordinal());
        }
        return CommodityAndComment;
    }
    @Override
    public PageInfo<CommodityAndComment> selectCommodityListByPageForApp(PageInfo<CommodityAndComment> pageInfo, CommodityAndComment commodity) {
        if(pageInfo == null) return null;
        
        //参数组合
        //获取商品id数组，用于获取商品
        PageInfo<CommodityAndComment> pageInfos = CommodityAndCommentDao.selectListByPage(commodity,CommoditySqlIds.SQL_SELECT_BYPAGE_APP,pageInfo);
        //id集合，通过id集合获取商品
        List<Long> idList = new ArrayList<Long>();
        //遍历增加id集合
        if(pageInfos.getResult() != null && pageInfos.getResult().size() > 0){
            for(CommodityAndComment com : pageInfos.getResult()){
                idList.add(com.getId());
            }
            List<CommodityAndComment> clist = CommodityAndCommentDao.selectListByIds(idList);
            List<CommodityAndComment> newlist = new ArrayList<CommodityAndComment>();
            //排序
            for(Long l : idList){
                for(CommodityAndComment c : clist){
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
}
