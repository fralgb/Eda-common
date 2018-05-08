package com.omniselling.microservice.client;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * 远程服务调用，此接口是为了屏蔽不同远程调用协议的协议而
 * @author xslong
 */
public interface RemoteServiceExecutor {

	Object postRequest(String serviceName, String serviceId, Object[] args, Type returnType, Map<String, Class<?>> genericMap);
	
}
