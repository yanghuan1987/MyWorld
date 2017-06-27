/**
 * 
 */
package com.spfood.mms.msg;

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

import com.spfood.mms.intf.domain.criteria.ItemServiceForWms;
import com.spfood.mms.manager.ItemServiceForWmsManager;
import com.spfood.mms.manager.MqMsgtempManager;

/**
 * @author Administrator
 *
 */
public class TaskTimer {

	private static Logger logger = Logger.getLogger(TaskTimer.class);

	@Autowired
	private ItemServiceForWmsManager itemServiceForWmsManager;

	@Autowired
	private MqMsgtempManager mmsCommodityMsgTempManager;

	 @Autowired
	 private JmsTemplate mmsCommodityWMSPWSMQ;
	 
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
				mmsCommodityMsgTempManager.updateRollBack(list);
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
		mmsCommodityWMSPWSMQ.setSessionTransacted(true);
		// 创建消息并发送
		mmsCommodityWMSPWSMQ.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session)
					throws JMSException {
				return session.createTextMessage(jsonArray.toString());
			}
		});
		logger.info("==>finish send message in Topic Modal");
	}
}
