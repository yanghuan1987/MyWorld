/**
 * 
 */
package com.spfood.pms.manager;

import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.intf.domain.CommodityCommentType;

/**
 * CommodityCommentType Manager
 *
 * @author yanghuan
 * @createTime 2017-04-13 16:33:53
 *
 */
public interface CommodityCommentTypeManager extends BaseManager<CommodityCommentType> {
	//检索所有数据
	List<CommodityCommentType> selectAllInfo();
}
