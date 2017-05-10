/**
 * 
 */
package com.spfood.pms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.pms.intf.CommodityCommentTypeService;
import com.spfood.pms.intf.domain.CommodityCommentType;
import com.spfood.pms.manager.CommodityCommentTypeManager;

/**
 * @author Administrator
 *
 */
public class CommodityCommentTypeServiceImpl implements
		CommodityCommentTypeService {
	
	@Autowired
	private CommodityCommentTypeManager commodityCommentTypeManager;
	
	/* (non-Javadoc)
	 * @see com.spfood.pms.intf.CommodityCommentTypeService#selectAllInfo()
	 */
	@Override
	public List<CommodityCommentType> selectAllInfo() {
		return commodityCommentTypeManager.selectAllInfo();
	}

}
