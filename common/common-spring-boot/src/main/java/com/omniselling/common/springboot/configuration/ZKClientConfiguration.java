package com.omniselling.common.springboot.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.omniselling.common.util.StringUtils;
import com.omniselling.common.zookeeper.ZKClient;

/**
 * 配置zookeeper
 * 
 * @author xslong
 * @version 创建时间：Dec 2, 2016 2:08:57 PM
 * 
 */
@ConditionalOnClass(ZKClient.class)
@Configuration
public class ZKClientConfiguration implements EnvironmentAware {

	private RelaxedPropertyResolver propertyResolver;

	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment, "zk.");
	}

	@Bean(destroyMethod="destory")
	public ZKClient ZKClient() {
		String zkAddress = propertyResolver.getProperty("zkAddress");
		String baseSleepTimeMs = propertyResolver.getProperty("baseSleepTimeMs");
		String maxRetries = propertyResolver.getProperty("maxRetries");
		
		if (StringUtils.isBlank(baseSleepTimeMs)) {
			return new ZKClient(zkAddress);
		} else {
			return new ZKClient(zkAddress, Integer.parseInt(baseSleepTimeMs), Integer.parseInt(maxRetries));
		}
	}

}
