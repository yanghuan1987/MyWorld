/**
 * 
 */
package com.spfood.pms.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.pms.dao.CommodityCommentDao;
import com.spfood.pms.dao.impl.CommoditySqlIds;
import com.spfood.pms.intf.domain.CommodityComment;
import com.spfood.pms.manager.CommodityCommentManager;

/**
 * CommodityComment Manager
 *
 * @author yanghuan
 * @createTime 2017-04-13 16:03:10
 *
 */
@Service
public class CommodityCommentManagerImpl extends BaseManagerImpl<CommodityComment> implements CommodityCommentManager {
	
	private static Logger logger = Logger.getLogger(CommodityCommentManagerImpl.class);
	
	@Autowired
	private CommodityCommentDao dao;
	
	@Override
	protected BaseDao<CommodityComment> getBaseDao() {
		return dao;
	}

    @Override
    public PageInfo<CommodityComment> selectCommodityCommentByPage(PageInfo<CommodityComment> pageInfo, CommodityComment commodityComment) {
        if(pageInfo == null) return null;
		logger.info("start to selectCommodityCommentPage");
        //参数组合
        //获取商品id数组，用于获取商品
        String sqlIdPage = CommoditySqlIds.SELECT_PAGE_IN_COMMODITYCOMMENT;
        PageInfo<CommodityComment> pageInfos = dao.selectListByPage(commodityComment,sqlIdPage,pageInfo);
        //id集合，通过id集合获取商品
        List<Long> idList = new ArrayList<Long>(15);
		logger.info("get page id is "+idList);
        //遍历增加id集合
        if(pageInfos.getResult() != null && pageInfos.getResult().size() > 0){
            for(CommodityComment commodityComment2 : pageInfos.getResult()){
                idList.add(commodityComment2.getId());
            }
            commodityComment.setIdList(idList);
            List<CommodityComment> clist = dao.selectListByIdsForComment(commodityComment);
            if (commodityComment.getOrderBy() == 99) {//初次检索的时候按时间排序，以后按评论排序
            	//得到结果按查询ID排序
            	List<CommodityComment> result = new ArrayList<CommodityComment>(15);
            	for (int i = 0; i < idList.size(); i++) {
            		for (CommodityComment commodityComment2 : clist) {
            			if (idList.get(i).equals(commodityComment2.getId())) {
            				result.add(commodityComment2);
            			}
            		}
            	}
            	pageInfos.setResult(result);
			}else {
				pageInfos.setResult(clist);
			}
        }else
            pageInfos.setResult(null);
		logger.info("selectCommodityCommentPage is End");
        return pageInfos;
    }
    

    //查询该商品的评分数量
    public CommodityComment selectCommodityCommentCount(CommodityComment commodityComment){
		logger.info("start to selectCommodityCommentCount");
		return dao.selectCommodityCommentCount(commodityComment);
    };
    //更新评论是否显示
    public boolean updateCommodityCommentCountStatus(CommodityComment commodityComment) {
		logger.info("start to updateCommodityCommentCountStatus");
		return dao.updateCommodityCommentCountStatus(commodityComment);
	}
}
