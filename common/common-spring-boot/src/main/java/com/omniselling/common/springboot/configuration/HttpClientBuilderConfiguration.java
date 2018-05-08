package com.omniselling.common.springboot.configuration;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/** 
 * @author xslong 
 * @version 创建时间：Jan 9, 2017 11:02:41 AM 
 * 
*/
@ConditionalOnClass(HttpClient.class)
@Configuration
@ConfigurationProperties
public class HttpClientBuilderConfiguration implements EnvironmentAware
{

	private RelaxedPropertyResolver propertyResolver;
	
	@Override
	public void setEnvironment(Environment environment)
	{
		this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.httpclient.");
	}

	@Bean(name="httpClientBuilder")
	public HttpClientBuilder HttpClientBuilder(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager,
			ConnectionConfig connectionConfig, RequestConfig requestConfig)
	{
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
		httpClientBuilder.setDefaultConnectionConfig(connectionConfig);
		httpClientBuilder.setDefaultRequestConfig(requestConfig);
		return httpClientBuilder;
	}

	@Bean
	public PoolingHttpClientConnectionManager PoolingHttpClientConnectionManager(RequestConfig requestConfig)
	{
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		if (containsProperty("maxTotal"))
			connectionManager.setMaxTotal(getIntProperty("maxTotal"));
		if (containsProperty("defaultMaxPerRoute"))
			connectionManager.setDefaultMaxPerRoute(getIntProperty("defaultMaxPerRoute"));
		return connectionManager;
	}

	private boolean containsProperty(String propertyName)
	{
		return propertyResolver.containsProperty(propertyName);
	}

	private int getIntProperty(String propertyName)
	{
		return propertyResolver.getProperty(propertyName,Integer.class);
	}

	private String getProperty(String propertyName)
	{
		return propertyResolver.getProperty(propertyName);
	}

	@Bean
	public RequestConfig.Builder Builder()
	{
		RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
		if (containsProperty("connectTimeout"))
			requestConfigBuilder.setConnectionRequestTimeout(getIntProperty("connectionRequestTimeout"));
		if (containsProperty("socketTimeout"))
			requestConfigBuilder.setSocketTimeout(getIntProperty("socketTimeout"));
		if (containsProperty("proxyPort"))
			requestConfigBuilder.setProxy(new HttpHost(getProperty("proxyHost"), getIntProperty("proxyPort")));
		if (containsProperty("contentCompressionEnabled"))
			requestConfigBuilder
					.setContentCompressionEnabled(propertyResolver.getProperty("contentCompressionEnabled",Boolean.class));
		return requestConfigBuilder;
	}

	@Bean
	public RequestConfig build(RequestConfig.Builder builder)
	{
		return builder.build();
	}

	@Bean
	public ConnectionConfig.Builder ConnectionConfigBuilder()
	{
		ConnectionConfig.Builder builder = ConnectionConfig.custom();
		if (containsProperty("charset"))
			builder.setCharset(java.nio.charset.Charset.forName(getProperty("charset")));
		return builder;
	}

	@Bean
	public ConnectionConfig ConnectionConfig(ConnectionConfig.Builder builder)
	{
		return builder.build();
	}
}
