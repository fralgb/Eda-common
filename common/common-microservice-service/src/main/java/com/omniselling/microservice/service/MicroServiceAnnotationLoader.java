package com.omniselling.microservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.omniselling.microservice.MicroService;

/**
 * 用注解从Spring context中加载微服务的定义
 * @author xslong
 *
 */
public class MicroServiceAnnotationLoader implements MicroServiceLoader {
	
	private ApplicationContext applicationContext;

    public MicroServiceAnnotationLoader(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public List<MicroServiceDefinition> load() {
        Map<String, Object> microServiceMap = applicationContext.getBeansWithAnnotation(MicroService.class);
        List<MicroServiceDefinition> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : microServiceMap.entrySet()) {
            List<MicroServiceDefinition> newMicroServices = MicroServiceDefinitionParser.parser(entry.getValue());
            if (newMicroServices == null)
                continue;
            list.addAll(newMicroServices);
        }
        return list;
    }


}
