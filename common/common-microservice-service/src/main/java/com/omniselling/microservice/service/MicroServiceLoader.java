package com.omniselling.microservice.service;

import java.util.List;
/**
 * 微服务接口装载机
 * @author xslong
 */
public interface MicroServiceLoader {

	List<MicroServiceDefinition> load();
}
