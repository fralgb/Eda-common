package com.omniselling.common.v5service.client;

import java.lang.reflect.Method;

/**
 * 远程服务调用，此接口是为了屏蔽不同远程调用协议的差异
 * @author xslong
 */
public interface RemoteV5ServiceExecutor {

	Object postRequest(String serviceName, Method method, Object[] args);
	
}
