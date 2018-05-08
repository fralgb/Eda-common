package com.omniselling.microservice.service;

import java.lang.reflect.Method;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 微服务调用对象及方法 Created by xslong on 16/8/20.
 */
public @ToString class MicroServiceTarget {

	private @Getter @Setter Class<?> interfaceClazz;
	private @Getter @Setter Class<?> clazz;
	private @Getter @Setter Method method;
	
	private @Getter @Setter Map<String,Class<?>> genericMap;

	public MicroServiceTarget(Class<?> interfaceClazz, Class<?> clazz, Method method,Map<String,Class<?>> genericMap) {
		this.interfaceClazz = interfaceClazz;
		this.clazz = clazz;
		this.method = method;
		this.genericMap = genericMap;
	}

}
