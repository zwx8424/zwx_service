package com.zwx.demo.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.messaging.handler.annotation.SendTo;

//@Service
public class Consumer {

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	//@JmsListener(destination = "test.queue")
	public void receiveMsg1(String text) {
		try {
			logger.info("{} receive:{}", "test.queue", text);
			Thread.sleep(1000 * 2);
			logger.info("{} receive finish", "test.queue");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//@JmsListener(destination = "test.queue2")
	//@SendTo("out.queue")
	public String receiveMsg2(String text) {
		try {
			logger.info("{} receive:{}", "test.queue2", text);
			Thread.sleep(1000 * 2);
			logger.info("{} receive finish", "test.queue2");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "return message:" + text;
	}
}
