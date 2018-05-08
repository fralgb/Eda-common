package com.omniselling.common.dao;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author xslong
 * @version 创建时间：Dec 2, 2016 9:08:39 PM
 * 
 */
public class HikariDataSourceUtil {

	public static HikariDataSource build(RelaxedPropertyResolver propertyResolver) {
		if (propertyResolver.getProperty("url") == null && propertyResolver.getProperty("databaseName") == null) {
			throw new ApplicationContextException("DataBase connection pool is not configured correctly");
		}
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName(propertyResolver.getProperty("dataSourceClassName"));
		if (propertyResolver.getProperty("url") == null || "".equals(propertyResolver.getProperty("url"))) {
			config.addDataSourceProperty("databaseName", propertyResolver.getProperty("databaseName"));
			config.addDataSourceProperty("serverName", propertyResolver.getProperty("serverName"));
		} else {
			config.addDataSourceProperty("url", propertyResolver.getProperty("url"));
		}
		config.setUsername(propertyResolver.getProperty("username"));
		config.setPassword(propertyResolver.getProperty("password"));
		if ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource".equals(propertyResolver.getProperty("dataSourceName"))) {
			config.addDataSourceProperty("cachePrepStmts", propertyResolver.getProperty("cachePrepStmts"));
			config.addDataSourceProperty("prepStmtCacheSize", propertyResolver.getProperty("prepStmtsCacheSize"));
			config.addDataSourceProperty("prepStmtCacheSqlLimit",
					propertyResolver.getProperty("prepStmtCacheSqlLimit"));
			config.addDataSourceProperty("userServerPrepStmts", propertyResolver.getProperty("userServerPrepStmts"));
		}
		return new HikariDataSource(config);
	}
}
