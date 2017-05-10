/**
 * 
 */
package com.spfood.pms.intf;

import java.util.List;

import com.spfood.pms.intf.domain.CommodityCommentType;

/**
 * 评价类别
 * @author Administrator
 *
 * @author yanghuan
 * @createTime 2017-04-13 16:03:10
 */
public interface CommodityCommentTypeService {
	//检索所有数据
	List<CommodityCommentType> selectAllInfo();

}
