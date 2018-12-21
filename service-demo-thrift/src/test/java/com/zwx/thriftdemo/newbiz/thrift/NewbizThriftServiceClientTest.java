package com.zwx.thriftdemo.newbiz.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zwx.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class NewbizThriftServiceClientTest {

	@Before
	public void init() {

		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNewbizApply() {
		try {
			/*System.out.println("客户端启动....");
			TTransport transport = null;
			try {
				transport = new TSocket("172.30.2.109", 8031, 30000);
				// 协议要和服务端一致
				TProtocol protocol = new TBinaryProtocol(transport);
				NewbizThriftService.Client client = new NewbizThriftService.Client(protocol);
				transport.open();
				String result = client.issue("zwx");
				System.out.println(result);
			} catch (TTransportException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			} finally {
				if (null != transport) {
					transport.close();
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
