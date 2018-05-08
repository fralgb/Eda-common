package com.omniselling.common.dao.configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.omniselling.common.Constant;

/**
 * 配置DataSource
 * 
 * @author xslong
 * @version 创建时间：Dec 2, 2016 4:45:29 PM
 * 
 */
//@ConditionalOnClass(PlatformTransactionManager.class)
@Configuration
//@EnableTransactionManagement
public class DataBaseConfiguration implements EnvironmentAware
{

	private RelaxedPropertyResolver propertyResolver;

	private static Logger logger = LoggerFactory.getLogger(DataBaseConfiguration.class);

	@Override
	public void setEnvironment(Environment env)
	{
		this.propertyResolver = new RelaxedPropertyResolver(env, "jdbc.");
	}

    @Primary
    @Bean(name = Constant.DATASOURCE_NAME, destroyMethod = "close")
	@ConditionalOnMissingBean
	public DataSource dataSource()
	{
		logger.debug("Configruing DataSource");
		return HikariDataSourceUtil.build(propertyResolver);
	}
	

}