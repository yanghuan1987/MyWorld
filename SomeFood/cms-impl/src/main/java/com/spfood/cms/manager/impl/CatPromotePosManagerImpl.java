/**
 * 
 */
package com.spfood.cms.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.cms.dao.CatPromotePosDao;
import com.spfood.cms.dao.CatPromotedCommodityDao;
import com.spfood.cms.intf.domain.CatPromotePos;
import com.spfood.cms.intf.domain.CatPromotedCommodity;
import com.spfood.cms.manager.CatPromotePosManager;
import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.manager.impl.BaseManagerImpl;

/**
 * CatPromotePos Manager
 *
 * @author yanghuan
 * @createTime 2017-01-22 15:55:25
 *
 */
@Service
public class CatPromotePosManagerImpl extends BaseManagerImpl<CatPromotePos>
		implements CatPromotePosManager {
	
	private static final Log log = LogFactory.getLog(CatPromotePosManagerImpl.class);

	@Autowired
	private CatPromotePosDao catpromoteposdao;
	
	@Autowired
	private CatPromotedCommodityDao catPromotedCommodityDao;

	@Override
	protected BaseDao<CatPromotePos> getBaseDao() {
		return catpromoteposdao;
	}

	/**
	 * 获取商品组及明细信息
	 * 
	 * @pdOid 3db4d01f-ee23-4901-8275-bfb7cbcf064a
	 */
	public List<CatPromotePos> getCatPromotePosInfo(CatPromotePos catPromotePos) {
		log.info("Get all the goods information");
		return catpromoteposdao.selectList(catPromotePos);
	};

	/**
	 * 插入商品组及明细信息
	 * 
	 * @param list
	 * @pdOid b67603d6-d05d-4f84-9e09-1dacaee3932a
	 */
	@Transactional
	public void addOne(CatPromotePos catPromotePos){
		log.info("Insert the new data，["+catPromotePos+"]。");
		List<CatPromotedCommodity> list = new ArrayList<CatPromotedCommodity>();
		catpromoteposdao.insert(catPromotePos);
		list = catPromotePos.getCatPromotedCommodityList();
		for (CatPromotedCommodity catPromotedCommodity : list) {
			catPromotedCommodity.setCatPromotePos(catPromotePos);
		}
		catPromotedCommodityDao.insertInBatch(list);
	}

	/**
	 * 更新商品组及明细信息
	 * 
	 * @param CatPromotePos
	 * @pdOid 2caff14f-d112-495c-85f3-162665e91785
	 */
	@Transactional
	public boolean updateOne(CatPromotePos catPromotePos) {
		log.info("Update the data in the page editor，["+catPromotePos+"]。");
		List<CatPromotedCommodity> list = new ArrayList<CatPromotedCommodity>();
		list = catPromotePos.getCatPromotedCommodityList();
		//当商品信息有值的时候
		if (0 != list.size()) {
			log.info("Update the product information, update the number["+list.size()+"]。");
			//将得到的分类推荐位赋值给每一个商品
			for(CatPromotedCommodity CommodityInfo : list){
				CommodityInfo.setCatPromotePos(catPromotePos);
			}
			//执行删除
			catPromotedCommodityDao.deleteById(catPromotePos.getId());
			//执行新增
			catPromotedCommodityDao.insertInBatch(list);
		}
		return catpromoteposdao.updateByIdSelective(catPromotePos)>0?true:false;
	};
	
	/**
	 * 删除商品组及明细信息
	 * 
	 * @param CatPromotePos
	 * @pdOid 2caff14f-d112-495c-85f3-162665e91785
	 */
	@Transactional
	public boolean deleteOne(CatPromotePos catPromotePos) {
		log.info("Delete select data，["+catPromotePos+"]。");
		return catpromoteposdao.deleteById(catPromotePos.getId())>0?true:false;
	};

	   /** 排序重复检查
	    * 
	    * @param catPromotePos
	    * @pdOid de5214af-681a-47eb-92f1-2ddb5ef28fff */
	  public boolean selectBydisplayOrderIsHave(CatPromotePos catPromotePos){
		  log.info("Check whether the ID is repeated");
		return catpromoteposdao.selectBydisplayOrderIsHave(catPromotePos);
	  }
	  

	
}
