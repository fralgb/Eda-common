package com.omniselling.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;

public class SpringContextUtils implements ApplicationContextAware, Ordered
{
    private static String serviceName = "";
    
    private static ApplicationContext applicationContext = null;

    public void setServiceName(String serviceName)
    {
        SpringContextUtils.serviceName = serviceName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public static String getServiceName()
    {
        return serviceName;
    }
    
    public static Object getBean(String clazzName) throws BeansException, ClassNotFoundException
    {
        Class<?> clazz = Class.forName(clazzName);
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException
    {
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException
    {
        return applicationContext.getBean(requiredType);
    }

    public static boolean containsBean(String name)
    {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException
    {
        return applicationContext.isSingleton(name);
    }

    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException
    {
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException
    {
        return applicationContext.getAliases(name);
    }

    @Override
    public int getOrder()
    {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
