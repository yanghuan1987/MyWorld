/**
 * 
 */
package com.spfood.mms.manager;

import java.util.List;

import com.spfood.kernel.manager.BaseManager;
import com.spfood.mms.intf.domain.MqMsgtemp;

/**
 * MqMsgtemp Manager
 *
 * @author yanghuan
 * @createTime 2017-03-30 17:31:20
 *
 */
public interface MqMsgtempManager extends BaseManager<MqMsgtemp> {
	Boolean updateRollBack(List<String> list);
}
