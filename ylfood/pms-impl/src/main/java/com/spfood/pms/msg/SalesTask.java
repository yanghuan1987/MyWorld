/**
 * 
 */
package com.spfood.pms.msg;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.pms.manager.CommodityManager;

/**
 * @author Administrator
 *
 */
public class SalesTask {
	
	private static Logger logger = Logger.getLogger(TaskTimer.class);
	@Autowired
	private CommodityManager commodityManager;
	
	public void salesCount() {
		logger.info("start to update commodity table for commodity_quantity");
		
	}
}
