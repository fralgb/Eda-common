package com.omniselling.common.v5service.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用JDK Proxy代理生成微服务实现 Created by xslong on 16/8/20.
 */
public class V5ServiceProxyFactory {

	private static V5ServiceProxyFactory instance;

	static Map<String, Object> services = new ConcurrentHashMap<>();

	private V5ServiceProxyFactory() {
	};

	public static V5ServiceProxyFactory instance() {
		if (instance == null)
			synchronized (V5ServiceProxyFactory.class) {
				if (instance == null)
					instance = new V5ServiceProxyFactory();
			}
		return instance;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T build(RemoteV5ServiceExecutor remoteV5ServiceExecutor, String serviceName,
			Class<T> v5ServiceInterface) {
		T service = (T) services.get(v5ServiceInterface.getName());
		if (service == null) {
			service = (T) Proxy.newProxyInstance(V5ServiceProxyFactory.class.getClassLoader(),
					new Class[] { v5ServiceInterface },
					new MicroServiceProxyBean(remoteV5ServiceExecutor, serviceName, v5ServiceInterface));
			services.put(v5ServiceInterface.getName(), service);
		}
		return service;
	}

	class MicroServiceProxyBean<T> implements InvocationHandler {

		private RemoteV5ServiceExecutor remoteV5ServiceExecutor;
		private String serviceName;
		private Class<T> microServiceInterface;

		public MicroServiceProxyBean(RemoteV5ServiceExecutor remoteV5ServiceExecutor, String serviceName,
				Class<T> microServiceInterface) {
			this.remoteV5ServiceExecutor = remoteV5ServiceExecutor;
			this.serviceName = serviceName;
			this.microServiceInterface = microServiceInterface;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			String methodName = method.getName();
			if (V5ServiceHelper.isObjectClassMechod(methodName))
				throw new V5ServiceException(
						String.format("not support method '%s.%s'", microServiceInterface.getSimpleName(), methodName));
			Object result = remoteV5ServiceExecutor.postRequest(this.serviceName, method, args);
			return result;
		}

	}

}
