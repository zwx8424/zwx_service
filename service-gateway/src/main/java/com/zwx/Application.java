package com.zwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootConfiguration
@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
public class Application {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		//@formatter:off
		// String uri = "http://httpbin.org:80";
		// String uri = "http://localhost:9080";
		return builder.routes()
				.route(r -> r.path("/demo/**").filters(f ->
					f.stripPrefix(1))
					.uri("lb://demo")
				)
				.route(r -> r.path("/thriftdemo/**").filters(f ->
				f.stripPrefix(1))
				.uri("lb://thriftdemo")
				)
				.build();
		//@formatter:on
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
