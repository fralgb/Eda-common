package com.omniselling.microservice.client;

import org.springframework.beans.factory.FactoryBean;

/**
 * 为微服务接口动态生成一个代理实现 
 * Created by xslong on 16/8/20.
 */
public class MicroSerivceBean<T> implements FactoryBean<T> {

	private Class<T> microServiceInterface;

	private String groupId;
	
	private RemoteServiceExecutor remoteServiceExecutor;

	public MicroSerivceBean() {
	}

	public MicroSerivceBean(Class<T> microServiceInterface) {
		this.microServiceInterface = microServiceInterface;
	}

	@Override
	public T getObject() throws Exception {
		return MicroServiceProxyFactory.instance().build(remoteServiceExecutor, groupId, microServiceInterface);
	}

	@Override
	public Class<?> getObjectType() {
		return microServiceInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public Class<T> getMicroServiceInterface() {
		return microServiceInterface;
	}

	public void setMicroServiceInterface(Class<T> microServiceInterface) {
		this.microServiceInterface = microServiceInterface;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public void setRemoteServiceExecutor(RemoteServiceExecutor remoteServiceExecutor) {
		this.remoteServiceExecutor = remoteServiceExecutor;
	}

}
