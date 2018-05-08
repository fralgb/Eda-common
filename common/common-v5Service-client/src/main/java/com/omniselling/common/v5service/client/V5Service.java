package com.omniselling.common.v5service.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xslong
 * @version 创建时间：Nov 18, 2016 4:40:18 PM
 * 
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface V5Service {
	String path();
}