/**
 * 
 */
package com.spfood.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.cms.dao.CommodityPromotionPosDao;
import com.spfood.cms.intf.domain.CommodityPromotionPos;
import com.spfood.cms.intf.domain.PromotedCommodity;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.cms.manager.CommodityPromotionPosManager;

/**
 * commodityPromotionPos Manager
 *
 * @author zhangpei
 * @createTime 2017-02-06 13:11:29
 *
 */
@Service
public class CommodityPromotionPosManagerImpl extends BaseManagerImpl<CommodityPromotionPos> implements CommodityPromotionPosManager {

	@Autowired
	private CommodityPromotionPosDao dao;
	
	@Override
	protected BaseDao<CommodityPromotionPos> getBaseDao() {
		return dao;
	}

    /**
     * 推荐位列表及推荐商品
     */
    @Override
    public List<PromotedCommodity> getCommPromotePosList(
            CommodityPromotionPos commodityPromotionPos) {
        List<CommodityPromotionPos> cList=dao.getCommPromotePosList(commodityPromotionPos);
      //执行查询
        return cList!=null&&cList.size()>0?cList.get(0).getPromotedCommodityList():null;
    }

}
