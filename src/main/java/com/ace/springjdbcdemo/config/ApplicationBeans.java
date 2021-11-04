package com.ace.springjdbcdemo.config;

import javax.sql.DataSource;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ace.springjdbcdemo.common.filter.ErrorLogFilter;

@Configuration
@EnableTransactionManagement
public class ApplicationBeans {
	
	//we need to create this bean if we want to use @Transaction feature at service layer.
	@Bean
	public PlatformTransactionManager getDataSourceTransactionManager(DataSource datasource) {
		DataSourceTransactionManager dataSourceTransactionManager=new DataSourceTransactionManager(datasource);
		return dataSourceTransactionManager;
	}
	
	//here we specify that the jdbc templet we are going to use should use which data source in case of multiple datasource
	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);		
	}
	
	/*
	 * @Bean public FilterRegistrationBean
	 * preAuthTenantContextInitializerFilterRegistration(ErrorLogFilter filter) {
	 * FilterRegistrationBean registration = new FilterRegistrationBean(filter);
	 * registration.setEnabled(false); return registration; }
	 */

}
