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

import com.spfood.pms.intf.domain.criteria.ItemServiceForFms;
import com.spfood.pms.manager.ItemServiceForFmsManager;
import com.spfood.pms.manager.PmsCommodityMsgTempManager;

/**
 * @author Administrator
 *
 */
public class TaskTimer {

	private static Logger logger = Logger.getLogger(TaskTimer.class);

	@Autowired
	private ItemServiceForFmsManager itemServiceForFmsManager;

	@Autowired
	private PmsCommodityMsgTempManager pmsCommodityMsgTempManager;

	@Autowired
	private JmsTemplate pmsCommodityFMSMQ;

	 
	/**
	 * 计时器功能,定时发送MQ消息队列
	 */
	public void doJob() {

		logger.info("start to send MQ");
		// 发送实体
		List<ItemServiceForFms> listItemServiceForFms = new ArrayList<ItemServiceForFms>();
		// 取得商品信息
		listItemServiceForFms = itemServiceForFmsManager.sendDate();
		if (null != listItemServiceForFms && listItemServiceForFms.size() != 0) {
			// 获取商品CODE集合
			List<String> list = new ArrayList<String>();
			for (ItemServiceForFms itemServiceForFms : listItemServiceForFms) {
				list.add(itemServiceForFms.getItemCode());
			}
			try {
				// 发送
				mailSendQueueMQ(listItemServiceForFms);
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
	 * 发送FMS消息队列(点对点)
	 */
	public void mailSendQueueMQ(final List<ItemServiceForFms> listItemServiceForFms)
			throws Exception {
		//将数组转成JSON格式
		final JSONArray jsonArray = JSONArray.fromObject(listItemServiceForFms);
		
		pmsCommodityFMSMQ.setSessionTransacted(true);
		// 创建消息并发送
		pmsCommodityFMSMQ.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session
						.createTextMessage(jsonArray.toString());
			}
		});
		logger.info("==>finish send message in Queue Modal");
	}
}
