package com.spfood.cms.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.spfood.cms.intf.CatPromotePosService;
import com.spfood.cms.intf.domain.CatPromotePos;
import com.spfood.cms.manager.CatPromotePosManager;

public class CatPromotePosServiceImpl implements CatPromotePosService {

	@Resource
	private CatPromotePosManager catPromotePosManager;
	
	private static final Log log = LogFactory.getLog(CatPromotePosServiceImpl.class);
	/** 获取菜单信息
     * @param CatPromotePos 商品组对象，不能为空
     * @pdOid 3db4d01f-ee23-4901-8275-bfb7cbcf064a */
	@Override
	public List<CatPromotePos> getCatPromotePosInfo(CatPromotePos catPromotePos) {
	    log.info("getCatPromotePosInfo for B2C start");
		return catPromotePosManager.getCatPromotePosInfo(catPromotePos);
	}
	/** 删除菜单信息
     * 
     * @param CatPromotePos 商品组对象，不能为空
     * @pdOid 2caff14f-d112-495c-85f3-162665e91785 */
	@Override
	public boolean deleteOne(CatPromotePos catPromotePos) {
		return catPromotePosManager.deleteOne(catPromotePos);
	}
	/**修改菜单位 
	 * @param CatPromotePos 商品组对象，不能为空
     * @pdOid 4e838cde-10ff-4dba-a952-243a29af6cd6 */
	@Override
	public boolean updateOne(CatPromotePos catPromotePos) {
		return catPromotePosManager.updateOne(catPromotePos);
	}
	/**添加菜单位 
     * @param CatPromotePos 商品组对象，不能为空
     * @pdOid 4e838cde-10ff-4dba-a952-243a29af6cd6 */
	@Override
	public void addOne(CatPromotePos catPromotePos) {
	    catPromotePosManager.addOne(catPromotePos);
	}
	/**验证排序是否重复 
     * @param CatPromotePos 商品组对象，不能为空
     * @pdOid 4e838cde-10ff-4dba-a952-243a29af6cd6 */
	@Override
	public boolean selectBydisplayOrderIsHave(CatPromotePos catPromotePos){
		return catPromotePosManager.selectBydisplayOrderIsHave(catPromotePos);
	};
	/**
	 * 查询全部
	 */
	@Override
    public List<CatPromotePos> getCatPromotePosInfo() {
        CatPromotePos catPromotePos = new CatPromotePos();
        return catPromotePosManager.getCatPromotePosInfo(catPromotePos);
    }
}
