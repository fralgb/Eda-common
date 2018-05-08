package com.omniselling.common.springboot.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.omniselling.microservice.client.MicroServiceScannerConfigurer;
import com.omniselling.microservice.client.RemoteServiceExecutor;

/** 
 * @author xslong 
 * @version 创建时间：Jan 10, 2017 10:32:36 AM 
 * 
*/
// @Configuration
public class MultiMsClientConfiguration implements EnvironmentAware
{

	private RelaxedPropertyResolver propertyResolver;

	private String[] groupIds;
	private String[] basePackages;

	@Override
	public void setEnvironment(Environment environment)
	{
		this.propertyResolver = new RelaxedPropertyResolver(environment, "ms.services.");
		this.groupIds = propertyResolver.getProperty("groupId", String[].class);
		this.basePackages = propertyResolver.getProperty("basePackage", String[].class);
	}

	@Bean
	// @ConditionalOnExpression("#{environment.acceptsProfiles('ms.services')}")
	public List<MicroServiceScannerConfigurer> multiMsClientConfiguration(
			RemoteServiceExecutor remoteServiceExecutor)
	{
		List<MicroServiceScannerConfigurer> list = new ArrayList<>();
		for (int i = 0, len = groupIds.length; i < len; i++)
		{
			MicroServiceScannerConfigurer scannerConfigurer = new MicroServiceScannerConfigurer();
			scannerConfigurer.setBasePackage(basePackages[i]);
			scannerConfigurer.setGroupId(groupIds[i]);
			scannerConfigurer.setRemoteServiceExecutor(remoteServiceExecutor);
		}
		return list;
	}

}
