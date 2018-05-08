package com.omniselling.microservice.service;

import com.omniselling.microservice.MicroService;

/**
 * 找到服务接口的实例，并执行方法
 * @author xslong
 */

@MicroService(value = MicroServiceProcesser.class)
public interface MicroServiceProcesser {

	Object process(MicroServiceDefinition microServiceDefinition, Object[] args);
}
