/**
 * 
 */
package com.spfood.pms.msg;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.spfood.pms.intf.domain.criteria.ItemServiceForWms;
import com.spfood.pms.manager.CommodityManager;
import com.spfood.pms.manager.ItemServiceForWmsManager;
import com.spfood.pms.manager.PmsCommodityMsgTempManager;

/**
 * @author Administrator
 *
 */
public class TaskTimer {

	private static Logger logger = Logger.getLogger(TaskTimer.class);
	@Autowired
	private CommodityManager commodityManager;

	@Autowired
	private ItemServiceForWmsManager itemServiceForWmsManager;

	@Autowired
	private PmsCommodityMsgTempManager pmsCommodityMsgTempManager;

	 @Autowired
	 private JmsTemplate pmsCommodityWMSPWSMQ;
	 
	/**
	 * 计时器功能,定时发送MQ消息队列
	 */
	public void doJob() {

		logger.info("start to send MQ");
		// 发送实体
		List<ItemServiceForWms> listItemServiceForWms = new ArrayList<ItemServiceForWms>();
		// 取得商品信息
		listItemServiceForWms = itemServiceForWmsManager.sendDate();
		if (null != listItemServiceForWms && listItemServiceForWms.size() != 0) {
			// 获取商品CODE集合
			List<String> list = new ArrayList<String>();
			for (ItemServiceForWms itemServiceForWms : listItemServiceForWms) {
				list.add(itemServiceForWms.getItemCode());
			}
			try {
				// 发送WMS与PWS消息队列
				mailSendTopicMQ(listItemServiceForWms);
			} catch (Exception e1) {
				// 手动回滚
				pmsCommodityMsgTempManager.updateRollBack(list);
				logger.error("mailSend Exception happend.exception is " + e1);
			}
		} else {
			logger.info("There is no message yet,so does not send MQ");
		}
	}


	/**
	 * 发送WMS与PWS消息队列(广播模式)
	 */

	public void mailSendTopicMQ(final List<ItemServiceForWms> listItemServiceForWms)
			throws Exception {
		//将数组转成JSON格式
		final JSONArray jsonArray = JSONArray.fromObject(listItemServiceForWms);
		System.out.println("===========================================================");
		System.out.println(jsonArray.toString());
		System.out.println("===========================================================");
		pmsCommodityWMSPWSMQ.setSessionTransacted(true);
		// 创建消息并发送
		pmsCommodityWMSPWSMQ.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session)
					throws JMSException {
				return session.createTextMessage(jsonArray.toString());
			}
		});
		logger.info("==>finish send message in Topic Modal");
	}
}
