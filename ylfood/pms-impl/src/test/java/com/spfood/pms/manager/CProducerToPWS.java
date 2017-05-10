package com.spfood.pms.manager;
import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.spfood.pms.intf.domain.PmsCommodityMsgTemp;
/**
 * 消息的生产者（发送者） 
 * @author liang
 *
 */
public class CProducerToPWS implements Serializable{

    //默认连接用户名
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String USERNAME = "admin";
    //默认连接密码
    private static final String PASSWORD = "admin";
    //默认连接地址
    private static final String BROKEURL = "tcp://127.0.0.1:61616";

    public Boolean doSend(PmsCommodityMsgTemp pmsCommodityMsgTemp) {
    	//没有数据时不发送
    	if (null == pmsCommodityMsgTemp || null == pmsCommodityMsgTemp.getListMsgTemps() ||
    			pmsCommodityMsgTemp.getListMsgTemps().size() == 0) {
			return true;
		}
    	
        //连接工厂
        ConnectionFactory connectionFactory;
        //连接
        Connection connection = null;
        //会话 接受或者发送消息的线程
        Session session;
        //消息的目的地PWS
        Destination destinationToPWS;
        //消息生产者
        MessageProducer messageProducerToPWS;
        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(CProducerToPWS.USERNAME, CProducerToPWS.PASSWORD, CProducerToPWS.BROKEURL);
        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建一个名称为HelloWorld的消息队列
            
            destinationToPWS = session.createQueue("pms_commodity_pws");
            //创建消息生产者
            messageProducerToPWS = session.createProducer(destinationToPWS);
            //发送消息
            sendMessage(session, messageProducerToPWS,pmsCommodityMsgTemp);

            session.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
		return true;

    }
    /**
     * 发送消息
     * @param session
     * @param messageProducer  消息生产者
     * @throws Exception
     */
    public static void sendMessage(Session session,MessageProducer messageProducer,PmsCommodityMsgTemp pmsCommodityMsgTemp) throws Exception{
        	ObjectMessage  message = session.createObjectMessage(pmsCommodityMsgTemp);
            //通过消息生产者发出消息 
            messageProducer.send(message);

    }
}