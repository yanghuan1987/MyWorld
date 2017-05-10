package com.spfood.pms.manager;
import java.awt.geom.Area;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.ObjectMessage;

import com.spfood.pms.intf.domain.PmsCommodityMsgTemp;
import com.spfood.pms.intf.domain.criteria.ItemServiceForWms;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-activeMQ.xml")
public class Applicationtest {

    private final Logger log = LoggerFactory.getLogger(Applicationtest.class);

    @Autowired
    private JmsTemplate pmsCommodityPWSMQ;
    @Autowired
    private JmsTemplate pmsCommodityWMSMQ;
    
    @Test
    public void mailSendWMS() throws Exception {
    	final Serializable listItemServiceForWms = new ArrayList<ItemServiceForWms>();
    	pmsCommodityWMSMQ.setSessionTransacted(true);
        for (int i = 0; i < 1; i++) {
        	pmsCommodityWMSMQ.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(listItemServiceForWms);
                }
            });
            log.info("==>finish send message to PWS"+ i);
        }
    }
    
//    @Test
//    public void mailSendPWS() throws Exception {
//    	final PmsCommodityMsgTemp pmsCommodityMsgTemp1 = new PmsCommodityMsgTemp();
//    	pmsCommodityPWSMQ.setSessionTransacted(true);
//        for (int i = 0; i < 1; i++) {
//        	pmsCommodityPWSMQ.send(new MessageCreator() {
//                @Override
//                public Message createMessage(Session session) throws JMSException {
//                    return session.createObjectMessage(pmsCommodityMsgTemp1);
//                }
//            });
//            log.info("==>finish send message to WMS"+ i);
//        }
//        while (true) {
//
//        }
//    }
}