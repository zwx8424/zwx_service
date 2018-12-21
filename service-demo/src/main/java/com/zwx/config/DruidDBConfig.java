package com.zwx.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidDBConfig {
	private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.druid.initial-size}")
	private int initialSize;

	@Value("${spring.datasource.druid.min-idle}")
	private int minIdle;

	@Value("${spring.datasource.druid.max-active}")
	private int maxActive;

	@Value("${spring.datasource.druid.max-wait}")
	private int maxWait;

	@Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.druid.validation-query}")
	private String validationQuery;

	@Value("${spring.datasource.druid.test-while-idle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.druid.test-on-borrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.druid.test-on-return}")
	private boolean testOnReturn;

	@Value("${spring.datasource.druid.pool-prepared-statements}")
	private boolean poolPreparedStatements;

	@Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size}")
	private int maxPoolPreparedStatementPerConnectionSize;

	@Value("${spring.datasource.druid.filters}")
	private String filters;

	@Value("{spring.datasource.druid.connectionProperties}")
	private String connectionProperties;

	@Value("${spring.datasource.druid.useGlobalDataSourceStat}")
	private boolean useGlobalDataSourceStat;

	@Value("${spring.datasource.druid.stat-view-servlet.login-username}")
	private String druidLoginName;

	@Value("${spring.datasource.druid.stat-view-servlet.login-password}")
	private String druidPassword;

	@Value("${spring.datasource.druid.stat-view-servlet.url-pattern}")
	private String statViewServletUrlPattern;

	@Value("${spring.datasource.druid.stat-view-servlet.reset-enable}")
	private String statViewServletResetEnable;

	@Value("${spring.datasource.druid.web-stat-filter.url-pattern}")
	private String webStatFilterUrlPattern;

	@Value("${spring.datasource.druid.web-stat-filter.exclusions}")
	private String webStatFilterExclusions;

	@Bean
	// 声明其为Bean实例
	@Primary
	// 在同样的DataSource中，首先使用被标注的DataSource
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(this.dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);

		// configuration
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		datasource.setConnectionProperties(connectionProperties);

		return datasource;
	}

	// /////// 下面是druid 监控访问的设置 /////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings(statViewServletUrlPattern/*"/druid/*"*/); // url 匹配
		// reg.addInitParameter("allow", "192.168.16.110,127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)
		// reg.addInitParameter("deny", "192.168.16.111"); //IP黑名单 (存在共同时，deny优先于allow)
		reg.addInitParameter("loginUsername", this.druidLoginName);// 登录名
		reg.addInitParameter("loginPassword", this.druidPassword);// 登录密码
		reg.addInitParameter("resetEnable", statViewServletResetEnable/*"false"*/); // 禁用HTML页面上的“Reset All”功能
		return reg;
	}

	@Bean(name = "druidWebStatFilter")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean reg = new FilterRegistrationBean();
		reg.setFilter(new WebStatFilter());
		reg.addUrlPatterns(webStatFilterUrlPattern/*"/*"*/);
		reg.addInitParameter("exclusions", webStatFilterExclusions/*"*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"*/); // 忽略资源
		// reg.addInitParameter("profileEnable", "true");
		// reg.addInitParameter("principalCookieName", "USER_COOKIE");
		// reg.addInitParameter("principalSessionName", "USER_SESSION");
		return reg;
	}
}