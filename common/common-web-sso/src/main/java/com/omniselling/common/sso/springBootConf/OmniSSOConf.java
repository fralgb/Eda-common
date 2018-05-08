package com.omniselling.common.sso.springBootConf;

import javax.servlet.Filter;

import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OmniSSOConf {

	
	/*@Bean
	public OmniSessionListener omniSessionListener()
	{
		return new OmniSessionListener();
	}*/
	
	/**************************************************
	 * Bean start
	 ***************************************************/
	@Bean("singleSignOutHttpSessionListener")
	public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener()
	{
		return new SingleSignOutHttpSessionListener();
	}
	
	@Bean
	public FilterRegistrationBean registrationajaxRedirectFilter(@Qualifier("ajaxRedirectFilter")Filter filter) {  
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	
	
	@Bean
	public FilterRegistrationBean registrationcasAuthenticationFilter(@Qualifier("casAuthenticationFilter")Filter filter) {  
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	
	@Bean
	public FilterRegistrationBean registration2(@Qualifier("ticketCacheFilter")Filter filter) {  
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	
	@Bean
	public FilterRegistrationBean registrationcasValidationFilter(@Qualifier("casValidationFilter")Filter filter) {  
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	
	@Bean
	public FilterRegistrationBean registrationwrappingFilter(@Qualifier("wrappingFilter")Filter filter) {  
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	@Bean
	public FilterRegistrationBean registrationsif(@Qualifier("sif")Filter filter) {  
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	@Bean
	public FilterRegistrationBean registrationj2eePreAuthFilter(@Qualifier("j2eePreAuthFilter")Filter filter) {  
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	@Bean
	public FilterRegistrationBean registrationlogoutFilter(@Qualifier("logoutFilter")Filter filter) {  
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	@Bean
	public FilterRegistrationBean registrationetf(@Qualifier("etf")Filter filter) {  
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	@Bean
	public FilterRegistrationBean registrationfsi(@Qualifier("fsi")Filter filter) {  
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	@Bean
	public FilterRegistrationBean registrationsessionUserInitFilter(@Qualifier("sessionUserInitFilter")Filter filter) {  
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}

	
	
}
