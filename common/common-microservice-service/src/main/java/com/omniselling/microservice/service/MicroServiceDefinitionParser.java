package com.omniselling.microservice.service;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;

import com.omniselling.microservice.GenericUtils;
import com.omniselling.microservice.MicroSerivceIDBuilder;
import com.omniselling.microservice.MicroService;
import com.omniselling.microservice.MicroServiceException;
import com.omniselling.microservice.MicroServiceHelper;

/**
 * 微服务定义解析 Created by xslong on 16/8/20.
 */
public class MicroServiceDefinitionParser
{

    final static Logger logger = LoggerFactory.getLogger(MicroServiceDefinitionParser.class);

    public static List<MicroServiceDefinition> parser(Object obj)
    {
        Class<?> c = AopUtils.getTargetClass(obj);
        return parser(c);
    }

    /**
     * 解析class获取微服务的定义
     * 
     * @param clazz
     * @return
     */
    public static List<MicroServiceDefinition> parser(Class<?> clazz)
    {
        if (Proxy.isProxyClass(clazz))
            return null;
        logger.info("parser class :{}", clazz);
        MicroService typeDefine = (MicroService) clazz.getAnnotation(MicroService.class);
        if (typeDefine == null)
            return null;
        if (typeDefine.value() == null)
        {
            throw new MicroServiceException("annotation MicroService'value can't be null." + clazz.getName());
        }

        List<Method> methods = new ArrayList<Method>();
        for (Method method : clazz.getMethods())
        {
            if (method.getDeclaredAnnotation(MicroService.class) == null)
                continue;

            methods.add(method);
        }
        // 如果没有批量微服务名,就把所有可公开访问的方法自动注册为微服务
        if (methods.size() == 0)
        {
            methods.addAll(Arrays.asList(clazz.getMethods()));
        }

        Collections.sort(methods, new Comparator<Method>()
        {
            @Override
            public int compare(Method o1, Method o2)
            {
                int c = o1.getName().compareTo(o2.getName());
                if (c != 0)
                    return c;
                c = o1.getParameterCount() - o2.getParameterCount();
                if (c != 0)
                    return c;
                for (int i = 0; i < o1.getParameterCount(); i++)
                {
                    if (o1.getParameterTypes()[i].isAssignableFrom(o2.getParameterTypes()[i]))
                        return -1;
                }
                return 0;
            }
        });
        List<MicroServiceDefinition> microServices = new ArrayList<>();
        for (Method method : methods)
        {
            if (!Modifier.isPublic(method.getModifiers()) || Modifier.isStatic(method.getModifiers()))
                continue;
            if (MicroServiceHelper.isGetOrSetMethod(method))
                continue;
            String microServiceName = buildMicroServiceId(null, clazz, method);
            Map<String, Class<?>> genericMap = GenericUtils.parserGenericMap(clazz);

            logger.info("info microservice:{} ", microServiceName);
            microServices.add(new MicroServiceDefinition(null, microServiceName,
                    new MicroServiceTarget(typeDefine.value(), clazz, method, genericMap)));
        }
        return microServices;
    }

    /**
     * 生成微服务名
     * 
     * @param type
     * @param name
     * @return
     */
    public static String buildMicroServiceId(String serviceName, Class<?> clazz, Method method)
    {
        if (clazz == null || method == null)
            throw new MicroServiceException("");

        return MicroSerivceIDBuilder.build(serviceName, clazz, method);
    }

}
