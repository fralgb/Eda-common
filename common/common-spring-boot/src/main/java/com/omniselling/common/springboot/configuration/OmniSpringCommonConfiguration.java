package com.omniselling.common.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.omniselling.common.parallet.*;

@Configuration
public class OmniSpringCommonConfiguration {
	
	@Bean
	public OmniParalletTaskHandlerFactory omniParalletTaskHandlerFactory(){
		return new OmniParalletTaskHandlerFactoryImpl();		
	}
	
}
