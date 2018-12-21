package com.zwx.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zwx.framework.interceptor.TransMonitorInterceptor;

@Configuration
@EnableWebMvc
// 启用Spring MVC，Spring Boot方式已自动自动
public class WebMvcConfig implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

	@Bean
	public InternalResourceViewResolver viewResolver() {
		logger.debug("define viewResolver------------start");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");
		// viewResolver.setViewClass(JstlView.class);
		logger.debug("define viewResolver------------end");
		return viewResolver;
	}

	// 在Spring Context中定义拦截器Bean
	@Bean
	public TransMonitorInterceptor transMonitorInterceptor() {
		return new TransMonitorInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 增加Interceptor配置
		logger.debug("define addInterceptors------------start");
		registry.addInterceptor(transMonitorInterceptor()).addPathPatterns("/demo/**", "/common/**", "/newbiz/**", "/cs/**", "/claim/**", "/old/**")
				.excludePathPatterns("/error");
		// .excludePathPatterns(FAVICON_URL);
		logger.debug("define addInterceptors------------end");
	}
}
