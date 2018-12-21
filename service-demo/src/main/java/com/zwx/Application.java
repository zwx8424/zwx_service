package com.zwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;

/**
 * Spring Boot部署的启动入口 包含了Spring Boot,Spring MVC,Spring JDBC,Spring JPA等主要功能, 
 * Spring Boot已自动启动了相关功能配置，并扫描当前Application目录及下层所有目录
 * 
 * @author weixin.zhu
 *
 */
@SpringBootApplication
@ConfigurationProperties
@EnableDiscoveryClient
//@ComponentScan(basePackages = { "com.zwx" })
//@ServletComponentScan
public class Application extends SpringBootServletInitializer {
	private static ApplicationContext ctx;

	// 获取上下文
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		ctx = SpringApplication.run(Application.class, args);

		ApplicationInit.init();
		//System.out.println("Application Initialization is complete.......");

	}
}