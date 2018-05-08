package com.omniselling.common.springboot.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.omniselling.common.web.ControllerExceptionHandler;
import com.omniselling.common.web.JacksonHttpMessageConverter;
import com.omniselling.common.web.ResponseBodyWrapHandlerBean;

/** 
 * @author xslong 
 * @version 创建时间：Dec 2, 2016 7:25:25 PM 
 * 
*/
@ConditionalOnWebApplication
@Configuration
@Import({ ResponseBodyWrapHandlerBean.class })
public class WebConfiguration
{

	@Bean
	public JacksonHttpMessageConverter JacksonHttpMessageConverter()
	{
		JacksonHttpMessageConverter c = new JacksonHttpMessageConverter();
		c.init();
		return c;
	}

	@Bean
	public ControllerExceptionHandler ControllerExceptionHandler()
	{
		ControllerExceptionHandler c = new ControllerExceptionHandler();
		return c;
	}

}
