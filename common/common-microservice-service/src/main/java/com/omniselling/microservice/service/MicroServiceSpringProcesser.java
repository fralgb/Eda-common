package com.omniselling.microservice.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import com.omniselling.common.protocol.BizException;
import com.omniselling.common.protocol.OmniException;
import com.omniselling.microservice.MicroServiceException;

public class MicroServiceSpringProcesser implements MicroServiceProcesser {

	private static final Logger logger = LoggerFactory.getLogger(MicroServiceSpringProcesser.class);

	private ApplicationContext applicationContext;

	public MicroServiceSpringProcesser(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public Object process(MicroServiceDefinition microServiceDefinition, Object[] args) {
		Method method = microServiceDefinition.getTarget().getMethod();
		Object target;
		boolean isProxyBean = false;
		try
		{
			target = applicationContext.getBean(microServiceDefinition.getTarget().getClazz());
		} catch (NoSuchBeanDefinitionException e){
			target = applicationContext.getBean(microServiceDefinition.getTarget().getInterfaceClazz());
			isProxyBean = true;
		}
		if (target == null)
			throw new MicroServiceException(String.format(
					"microservice target not found with class :" + microServiceDefinition.getTarget().getClazz()));
		try {
			if(isProxyBean){
				/*
				Class<?>[] paramTypes = new Class<?>[args == null ? 0 : args.length];
				for(int i = 0;i<paramTypes.length;i++)
					paramTypes[i] = args[i].getClass();
				ReflectionUtils.findMethod(target.getClass(), method.getName(), paramTypes);
				*/
				/**
				 * 这里需要注意：ReflectionUtils.findMethod 的方法与实际类的参数类型不一致（基类泛型引起的），所以只能用名字找方法
				 */
				for(Method m :target.getClass().getDeclaredMethods()){
					if(m.getName().equals(method.getName()) && m.getParameterCount() == method.getParameterCount()){
						return m.invoke(target, args);
					}
				}
				throw new MicroServiceException(String.format("method '%s' can not be found in the proxy class '%s'",
						method.getName(),microServiceDefinition.getTarget().getClazz()));
			}else{
				return method.invoke(target, args);
			}
		} catch (BizException e) {
			throw e;
		} catch (InvocationTargetException e) {
			if(e.getCause() instanceof OmniException)
				throw (OmniException)e.getCause();
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			throw new MicroServiceException("invoke microservice method error", e.getCause());
		} catch (OmniException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MicroServiceException("invoke microservice method error", e);
		}
	}

}
