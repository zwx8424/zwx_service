package com.zwx.demo.activemq;

//import org.springframework.beans.factory.annotation.Autowired;

//import javax.jms.Destination;
//import org.apache.activemq.command.ActiveMQQueue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.jms.core.JmsMessagingTemplate;

//@Service
public class Producer {
	//@Autowired
	//private JmsMessagingTemplate jmsMessagingTemplate;

	public void SendMSg(String name, String message) {
		System.out.println("receive message==" + name);
		//Destination destination = new ActiveMQQueue(name);
		//jmsMessagingTemplate.convertAndSend(name, message);
	}
}
