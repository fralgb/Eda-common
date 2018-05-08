package com.omniselling.microservice.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import com.omniselling.microservice.GenericUtils;
import com.omniselling.microservice.MicroSerivceIDBuilder;
import com.omniselling.microservice.MicroServiceException;
import com.omniselling.microservice.MicroServiceHelper;

/**
 * 用JDK Proxy代理生成微服务实现 Created by xslong on 16/8/20.
 */
public class MicroServiceProxyFactory
{

    private static MicroServiceProxyFactory instance;

    private MicroServiceProxyFactory()
    {
    };

    public static MicroServiceProxyFactory instance()
    {
        if (instance == null)
            synchronized (MicroServiceProxyFactory.class)
            {
                if (instance == null)
                    instance = new MicroServiceProxyFactory();
            }
        return instance;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> T build(RemoteServiceExecutor remoteServiceExecutor, String groupId, Class<T> microServiceInterface)
    {
        final T service = (T) Proxy.newProxyInstance(MicroServiceProxyFactory.class.getClassLoader(),
                new Class[] { microServiceInterface },
                new MicroServiceProxyBean(remoteServiceExecutor, groupId, microServiceInterface));
        return service;
    }

    class MicroServiceProxyBean<T> implements InvocationHandler
    {

        private RemoteServiceExecutor remoteServiceExecutor;
        private String groupId;
        private Class<T> microServiceInterface;

        private Map<String, Class<?>> genericMap;

        public MicroServiceProxyBean(RemoteServiceExecutor remoteServiceExecutor, String groupId,
                Class<T> microServiceInterface)
        {
            this.remoteServiceExecutor = remoteServiceExecutor;
            this.groupId = groupId;
            this.microServiceInterface = microServiceInterface;
            this.genericMap = GenericUtils.parserGenericMap(microServiceInterface);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
        {
            String methodName = method.getName();
            if ("toString".equals(methodName))
                return microServiceInterface.getClass().getName() + "Proxy";
            if (MicroServiceHelper.isObjectClassMechod(methodName))
                throw new MicroServiceException(
                        String.format("not support method '%s.%s'", microServiceInterface.getSimpleName(), methodName));
            String serviceId = MicroSerivceIDBuilder.build(this.groupId, this.microServiceInterface, method);
            Object result = remoteServiceExecutor.postRequest(this.groupId, serviceId, args,
                    method.getGenericReturnType(), genericMap);
            return result;
        }

    }

}
