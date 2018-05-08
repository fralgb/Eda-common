package com.omniselling.common.v5service.client;

import org.springframework.beans.factory.FactoryBean;

/**
 * 为微服务接口动态生成一个代理实现 
 * Created by xslong on 16/8/20.
 */
public class V5SerivceBean<T> implements FactoryBean<T> {

	private Class<T> v5ServiceInterface;

	private String serviceName;
	
	private RemoteV5ServiceExecutor remoteV5ServiceExecutor;

	public V5SerivceBean() {
	}

	public V5SerivceBean(Class<T> v5ServiceInterface) {
		this.v5ServiceInterface = v5ServiceInterface;
	}

	@Override
	public T getObject() throws Exception {
		return V5ServiceProxyFactory.instance().build(remoteV5ServiceExecutor, serviceName, v5ServiceInterface);
	}

	@Override
	public Class<?> getObjectType() {
		return v5ServiceInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public RemoteV5ServiceExecutor getRemoteV5ServiceExecutor() {
		return remoteV5ServiceExecutor;
	}

	public void setRemoteV5ServiceExecutor(RemoteV5ServiceExecutor remoteV5ServiceExecutor) {
		this.remoteV5ServiceExecutor = remoteV5ServiceExecutor;
	}

	public Class<T> getV5ServiceInterface() {
		return v5ServiceInterface;
	}

	public void setV5ServiceInterface(Class<T> v5ServiceInterface) {
		this.v5ServiceInterface = v5ServiceInterface;
	}

}
