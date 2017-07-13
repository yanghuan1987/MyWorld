/**
 * 
 */
package com.spfood.cms.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.cms.dao.PromotedCommodityDao;
import com.spfood.cms.intf.domain.PromotedCommodity;
import com.spfood.cms.manager.PromotedCommodityManager;
import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;

/**
 * PromotedCommodity Manager
 *
 * @author zhangpei
 * @createTime 2017-02-04 14:04:57
 *
 */
@Service
public class PromotedCommodityManagerImpl extends BaseManagerImpl<PromotedCommodity> implements PromotedCommodityManager {

    private static final Log log = LogFactory.getLog(CatPromotePosManagerImpl.class);
    @Autowired
    private PromotedCommodityDao dao;
    
    @Override
    protected BaseDao<PromotedCommodity> getBaseDao() {
        return dao;
    }
    /**
     * 通过推荐位编码查询推荐商品
     */
    @Override
    public List<PromotedCommodity> getPromotedCommoditys(String commPromotePos) {
        log.info("Through the recommended location code query recommended goods");
        
        PromotedCommodity promotedCommodity=new PromotedCommodity();
        promotedCommodity.setCommPromotePos(commPromotePos);
        return dao.selectList(promotedCommodity);
    }

    /**
     * 修改推荐商品排序
     */
    @Override
    public boolean changeOrder(Long commodityid, Integer newOrder) {
        log.info("Changes recommended goods sorting");
        PromotedCommodity promotedCommodity=new PromotedCommodity();
        promotedCommodity.setId(commodityid);
        promotedCommodity.setDisplayOrder(newOrder);
        return dao.updateByIdSelective(promotedCommodity)>-1?true:false;
    }

    /**
     * 通过商品编码查询推荐商品
     */
    public PromotedCommodity selectByCommodityCode(String commodityCode) {
        log.info("By commodity code query recommended goods");
        PromotedCommodity promotedCommodity=new PromotedCommodity();
        promotedCommodity.setCommodityCode(commodityCode);
        return dao.selectOne(promotedCommodity);
    }


   


  

   



}
