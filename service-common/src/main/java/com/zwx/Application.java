package com.zwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Spring Boot部署的启动入口 包含了Spring Boot,Spring MVC,Spring JDBC,Spring JPA等主要功能, 
 * Spring Boot已自动启动了相关功能配置，并扫描当前Application目录及下层所有目录
 * 
 * @author weixin.zhu
 *
 */
@SpringBootApplication
@ConfigurationProperties
//@ServletComponentScan
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}