package com.spfood.pms.manager;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.spfood.pms.intf.domain.Commodity;
import com.spfood.pms.intf.domain.PmsCommodityMsgTemp;
/**
 * 消息的生产者（发送者） 
 * @author liang
 *
 */
public class CProducerToPWStest implements Serializable{

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
    //发送的消息数量
    private static final int SENDNUM = 10;

    public static void main(String[] args) {
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
        connectionFactory = new ActiveMQConnectionFactory(CProducerToPWStest.USERNAME, CProducerToPWStest.PASSWORD, CProducerToPWStest.BROKEURL);
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
            sendMessage(session, messageProducerToPWS);

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

    }
    /**
     * 发送消息
     * @param session
     * @param messageProducer  消息生产者
     * @throws Exception
     */
    public static void sendMessage(Session session,MessageProducer messageProducer) throws Exception{
//        for (int i = 0; i < JMSProducer.SENDNUM; i++) {
        	ObjectMessage  message = session.createObjectMessage(ls());
        	String msg1 = "HelloWorld";
        	ObjectMessage  message2 = session.createObjectMessage(msg1);
            //通过消息生产者发出消息 
            messageProducer.send(message);
//        }

    }
    
    public static PmsCommodityMsgTemp ls() throws ParseException{
    	PmsCommodityMsgTemp msgTemp = new PmsCommodityMsgTemp();
    	Commodity commodity = new Commodity();
    	commodity.setCommodityCode("123");
    	commodity.setCommodityName("aaaa");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	commodity.setCommodityShowName((sdf.format(new Date())));
    	List<Commodity> list = new ArrayList<Commodity>();
    	list.add(commodity);
    	msgTemp.setListMsgTemps(list);
		return msgTemp;
	}
}