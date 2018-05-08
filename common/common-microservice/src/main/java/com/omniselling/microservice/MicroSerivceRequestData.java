package com.omniselling.microservice;

import java.io.Serializable;
import java.util.List;

/**
 *客户端调用微服务发出的请求
 * Created by xslong on 16/8/20.
 */
public class MicroSerivceRequestData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String requestId; //全局唯一，每一次请求由客户端生成（预留）
	
	private String version;// 可以从jar Manifest获取版本信息（预留）
	private String serviceName;
	private String serviceId;
	private List<String> arguments;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public List<String> getArguments() {
		return arguments;
	}

	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}


	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

}
