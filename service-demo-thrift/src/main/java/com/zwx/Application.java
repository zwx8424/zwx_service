package com.zwx;

import javax.annotation.PreDestroy;

import org.apache.thrift.server.TServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;

import com.zwx.thriftdemo.consul.ConsulService;

/**
 * Spring Boot部署的启动入口 包含了Spring Boot,Spring MVC,Spring JDBC,Spring JPA等主要功能, 
 * Spring Boot已自动启动了相关功能配置，并扫描当前Application目录及下层所有目录
 * 
 * @author weixin.zhu
 *
 */
@SpringBootApplication
//@ConfigurationProperties
//@ServletComponentScan
public class Application extends SpringBootServletInitializer {
	private static ApplicationContext ctx;
	private static TServer thriftServer;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		ctx = SpringApplication.run(Application.class, args);
		ApplicationInit.init();
		try {
			/*TProcessor tprocessor = new NewbizThriftService.Processor<NewbizThriftService.Iface>(new NewbizThriftServiceHandler());
			TServerTransport serverTransport = new TServerSocket(8031);
			thriftServer = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(tprocessor));
			// Use this for a singlethreaded server
			// TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
			// Use this for a multithreaded server
			// TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

			System.out.println("Starting the TThreadPoolServer server complete!...");
			thriftServer.serve();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Application Initialization is complete.......");

	}

	// 获取上下文
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	@PreDestroy
	public static void preDestroy() {
		System.out.println("-----------------------------BEFORE EXIT START--------------------------------");
		ctx.getBean(ConsulService.class).deregisterService();
		System.out.println("-----------------------------BEFORE EXIT END--------------------------------");
		thriftServer.stop();
	}
}