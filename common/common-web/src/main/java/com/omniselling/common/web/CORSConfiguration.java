package com.omniselling.common.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/** 
 * 支持跨域
 * @author xslong 
 * @version 创建时间：Feb 24, 2017 11:56:05 AM 
 * 
*/
@Configuration
@EnableWebMvc
public class CORSConfiguration extends WebMvcConfigurerAdapter
{

	@Override
    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("GET","POST")
		.allowedOrigins("*")
		.allowedHeaders("*")
		.allowCredentials(false)
		.maxAge(3600);
    }
	
}
