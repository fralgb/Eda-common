package com.omniselling.microservice.service;

/**
 * Created by xslong on 16/8/20.
 */
public class MicroServiceDefinition {

    private String serviceName;  //应用
    private String serviceId ; //微服务ID,唯一

    private MicroServiceTarget target;  //微服务供应对象

    public MicroServiceDefinition(){
    }

    public MicroServiceDefinition(String serviceName, String serviceId, MicroServiceTarget target) {
        this.serviceName = serviceName;
        this.serviceId = serviceId;
        this.target = target;
    }

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public MicroServiceTarget getTarget() {
        return target;
    }

    public void setTarget(MicroServiceTarget target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "MicroServiceEntity{" +
                "serviceName='" + serviceName + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", target=" + target +
                '}';
    }
}
