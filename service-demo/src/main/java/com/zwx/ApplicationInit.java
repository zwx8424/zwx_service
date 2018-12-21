package com.zwx;

import java.util.Enumeration;
import java.util.Properties;

import org.springframework.context.ApplicationContext;

public class ApplicationInit {

	@SuppressWarnings("unused")
	public static void init() {
		ApplicationContext ctx = Application.getApplicationContext();
		String tempStr = null;
		try {
			//
		} catch (Exception e) {
			System.err.println("init error!");
			e.printStackTrace();
			System.exit(-1);
		}

		//System.out.println("printSystemInfo DataSource>>>>>>>>>>" + ctx.getBean(DataSource.class).getClass().getName());
		//System.out.println("printSystemInfo JdbcTemplate>>>>>>>>>>" + ctx.getBean(JdbcTemplate.class).getClass().getName());
		//System.out.println("printSystemInfo TransactionManager>>>>>>>>>>" + ctx.getBean(PlatformTransactionManager.class).getClass().getName());

		// printSystemInfo(ctx);
		// printSpringProperties(ctx);
		// printAllSpringBeanInfo(ctx);
		// printAllSpringControllerBeanInfo(ctx);
		// printAllSpringServiceBeanInfo(ctx);
		// printAllSpringRepositoryBeanInfo(ctx);
	}

	public static void printSystemInfo() {
		StringBuffer strb = new StringBuffer();
		strb.append("=========================print system properties start!\n");
		Properties pros = System.getProperties();
		Enumeration<Object> enu = pros.keys();
		String key = null;
		String value = null;
		while (enu.hasMoreElements()) {
			key = (String) enu.nextElement();
			value = (String) pros.get(key);
			if ("line.separator".equals(key)) {
				String lineSeparatorStr = "";
				for (int i = 0; i < value.length(); i++) {
					char temp = value.charAt(i);
					switch (temp) {
					case 10:
						lineSeparatorStr += "\\n";
						break;
					case 13:
						lineSeparatorStr += "\\r";
						break;
					}
				}
				strb.append(key + "=" + lineSeparatorStr + "\n");
			} else {
				strb.append(key + "=" + value + "\n");
			}
		}
		strb.append("=========================print system properties end!");
	}

	public static void printSpringProperties(ApplicationContext ctx) {
		// 打印Spring Properties
		// System.out.println("printSpringProperties---------------");
		System.out.println("printSpringProperties server.contextPath>>>>>>>>>>" + ctx.getEnvironment().getProperty("server.contextPath"));

	}

	public static void printAllSpringBeanInfo(ApplicationContext ctx) {
		String[] beanNames = null;
		// 打印所有的Spring Bean
		beanNames = ctx.getBeanDefinitionNames();
		System.out.println("所有beanNames个数：" + beanNames.length);
		for (String bn : beanNames) {
			System.out.println(bn);
		}
	}

	public static void printAllSpringControllerBeanInfo(ApplicationContext ctx) {
		String[] beanNames = null;
		// 打印所有的Spring Bean
		beanNames = ctx.getBeanNamesForAnnotation(org.springframework.stereotype.Controller.class);
		System.out.println("Service注解Controller个数：" + beanNames.length);
		for (String bn : beanNames) {
			System.out.println(bn);
		}
	}

	public static void printAllSpringServiceBeanInfo(ApplicationContext ctx) {
		String[] beanNames = null;
		// 打印所有的Spring Bean
		beanNames = ctx.getBeanNamesForAnnotation(org.springframework.stereotype.Service.class);
		System.out.println("Service注解beanNames个数：" + beanNames.length);
		for (String bn : beanNames) {
			System.out.println(bn);
		}
	}

	public static void printAllSpringRepositoryBeanInfo(ApplicationContext ctx) {
		String[] beanNames = null;
		// 打印所有的Spring Bean
		beanNames = ctx.getBeanNamesForAnnotation(org.springframework.stereotype.Repository.class);
		System.out.println("Service注解Repository个数：" + beanNames.length);
		for (String bn : beanNames) {
			System.out.println(bn);
		}
	}
}
