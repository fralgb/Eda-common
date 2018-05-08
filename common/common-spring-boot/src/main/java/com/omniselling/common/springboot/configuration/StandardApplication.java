package com.omniselling.common.springboot.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import com.omniselling.common.protocol.ApplicationMetadata;
import com.omniselling.common.util.StringUtils;

/** 
 * @author xslong 
 * @version 创建时间：Jan 11, 2017 5:41:22 PM 
 * 
*/

public abstract class StandardApplication
		implements EmbeddedServletContainerCustomizer, ApplicationListener<ApplicationReadyEvent>, DisposableBean
{
	static final Logger logger = LoggerFactory.getLogger(StandardApplication.class);

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container)
	{
		ApplicationMetadata applicationMetadata = ApplicationMetadata.instance();
		if (StringUtils.isNotBlank(applicationMetadata.getPort()))
			container.setPort(Integer.parseInt(applicationMetadata.getPort()));
		if (StringUtils.isNotBlank(applicationMetadata.getName()))
			container.setDisplayName(applicationMetadata.getName());
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent)
	{
		logger.warn("<<<<<<<< STARTED >>>>>>>>");
		logger.warn("PID :{}", getPid());
	}

	@Override
	public void destroy() throws Exception
	{
		logger.warn("<<<<<<<< STOPPED >>>>>>>>");
	}

	private String getPid()
	{
		try
		{
			return System.getProperty("PID");
		} catch (Exception e)
		{
		}
		return "UNKNOWN";
	}

}
