package com.omniselling.common.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 
 * 绑定DbModel与DbService
 * @author xslong 
 * @version 创建时间：Dec 16, 2016 2:37:22 PM 
 * 
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface DbMapping {

	@SuppressWarnings("rawtypes")
	Class<? extends DbBaseModel> value();

}