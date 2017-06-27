/**
 * 
 */
package com.spfood.pms.manager;

import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.pms.intf.domain.PmsCommodityMsgTemp;

/**
 * PmsCommodityMsgTemp Manager
 *
 * @author yanghuan
 * @createTime 2017-03-30 17:31:20
 *
 */
public interface PmsCommodityMsgTempManager extends BaseManager<PmsCommodityMsgTemp> {
	Boolean updateRollBack(List<String> list);
}
