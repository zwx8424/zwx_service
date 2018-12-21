package com.zwx.demo.activemq;

import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class ActiveMQDemo {

	@Autowired
	private Producer producer;

	public String sendMsg() {
		producer.SendMSg("test.queue", "sendmessage");
		producer.SendMSg("test.queue2", "sendmessage2");

		//System.out.println("send success");
		return "success";
	}
}
