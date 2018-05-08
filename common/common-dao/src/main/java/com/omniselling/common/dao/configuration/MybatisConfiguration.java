package com.omniselling.common.dao.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.omniselling.common.protocol.OmniException;

/**
 * 
 * 配置Mybatis
 * @author xslong
 * @version 创建时间：Dec 2, 2016 4:44:10 PM
 * 
 */
//@ConditionalOnClass(DataSourceTransactionManager.class)
@Configuration
@AutoConfigureAfter({ DataBaseConfiguration.class })
public class MybatisConfiguration implements EnvironmentAware 
/*
, TransactionManagementConfigurer
*/
{

	private static Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);

	private RelaxedPropertyResolver propertyResolver;
	

	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment, "mybatis.");
	}

	@Bean(name="bizSqlSessionFactory")
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("bizDataSource")DataSource bizDataSource) {
		try {
			SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
			sessionFactory.setDataSource(bizDataSource);
			sessionFactory.setTypeAliasesPackage(propertyResolver.getProperty("typeAliasesPackage"));
			sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(propertyResolver.getProperty("mapperLocations")));
			sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(propertyResolver.getProperty("configLocation")));
			return sessionFactory.getObject();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new OmniException(e.getMessage(),e);
		}
	}
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage(propertyResolver.getProperty("mapperScanBasePackages"));
		// mapperScannerConfigurer.setSqlSessionFactoryBeanName(sqlSessionFactoryName);
		return mapperScannerConfigurer;
	}

//	@Bean
//	@ConditionalOnMissingBean
//	public DataSourceTransactionManager transactionManager(@Qualifier("bizDataSource")DataSource bizDataSource) {
//		return new DataSourceTransactionManager(bizDataSource);
//	}
//	
	@Bean
	@ConditionalOnMissingBean
	public SqlSessionTemplate SqlSessionTemplate(@Qualifier("bizSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	/*
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager()
	{
		return new DataSourceTransactionManager(bizDataSource);
	}
	
	*/
	
}
