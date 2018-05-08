package com.omniselling.common.springboot.configuration;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.omniselling.common.zookeeper.ZKClient;
import com.omniselling.microservice.client.RemoteServiceExecutor;
import com.omniselling.microservice.client.RemoteServiceZKExecutor;

/** 
 * @author xslong 
 * @version 创建时间：Jan 9, 2017 1:54:07 PM 
 * 
*/
@ConditionalOnClass(RemoteServiceExecutor.class)
@Configuration
public class RemoteServiceZKExecutorConfiguration
{
	@Bean
	public RemoteServiceZKExecutor remoteServiceZKExecutor(ZKClient zkClient,HttpClientBuilder httpClientBuilder) throws Exception{
		RemoteServiceZKExecutor zkExecutor = new RemoteServiceZKExecutor(zkClient, httpClientBuilder);
		zkExecutor.init();
		return zkExecutor;
	}

}
