package com.omniselling.common.v5service.client.configuration;

import java.io.IOException;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.omniselling.common.v5service.client.RemoteV5ServiceExecutor;
import com.omniselling.common.v5service.client.RemoteV5ServiceZookeeperExecutor;

/** 
 * @author xslong 
 * @version 创建时间：Jan 9, 2017 11:02:41 AM 
 * 
*/
@ConditionalOnClass(RemoteV5ServiceExecutor.class)
@Configuration
public class RemoteV5ServiceExecutorConfiguration implements EnvironmentAware
{

	private RelaxedPropertyResolver propertyResolver;
	
	public void setEnvironment(Environment environment)
	{
		this.propertyResolver = new RelaxedPropertyResolver(environment, "V5Service.client.zk.");
	}

	@Bean
	public RemoteV5ServiceExecutor billingRemoteV5ServiceZookeeperExecutor(HttpClientBuilder httpClientBuilder) throws IOException
	{
		String host = propertyResolver.getProperty("zkAddress");
		RemoteV5ServiceZookeeperExecutor excutor = new RemoteV5ServiceZookeeperExecutor(httpClientBuilder,
		 "/", host, 
		propertyResolver.getProperty("baseSleepTimeMs",Integer.class));
		excutor.init();
		return excutor;
	}
}
