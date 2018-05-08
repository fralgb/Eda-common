package com.omniselling.microservice.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.protocol.ApplicationMetadata;
import com.omniselling.common.zookeeper.ZKClient;
import com.omniselling.microservice.MicroSerivceConstant;
import com.omniselling.microservice.MicroServiceException;
import com.omniselling.microservice.MicroServiceHelper;
import com.omniselling.microservice.RegistInfo;

/**
 * 微服务Context
 * 
 * @author xslong
 * @time Nov 22, 2016 4:48:20 PM
 */
public class MicroServiceContext
{

    final static Logger logger = LoggerFactory.getLogger(MicroServiceContext.class);

    private Map<String, MicroServiceDefinition> microServices = new ConcurrentHashMap<>();

    private MicroServiceLoader microServiceLoader;

    private MicroServiceProcesser microServiceProcesser;

    private ZKClient zkClient;

    private String serviceName;

    public MicroServiceContext(MicroServiceLoader microServiceLoader, MicroServiceProcesser microServiceProcesser)
    {
        this.microServiceLoader = microServiceLoader;
        this.microServiceProcesser = microServiceProcesser;

        ApplicationMetadata metadata = ApplicationMetadata.instance();
        this.serviceName = metadata.getName();
    }

    public void init()
    {
        logger.debug("init...");
        logger.debug("microServiceLoader : {}", microServiceLoader.getClass());
        logger.debug("microServiceProcesser : {}", microServiceProcesser.getClass());

        List<MicroServiceDefinition> list = microServiceLoader.load();
        if (list == null || list.size() == 0)
            throw new MicroServiceException("From the '" + microServiceLoader.getClass().getName()
                    + "' was not found in the microservice definition");
        for (MicroServiceDefinition mse : list)
        {
            String serviceId = mse.getServiceId();
            if (microServices.containsKey(serviceId))
            {
                MicroServiceDefinition exists = microServices.get(serviceId);
                if (mse.getTarget().getMethod().getParameterCount() != exists.getTarget().getMethod()
                        .getParameterCount())
                    throw new MicroServiceException(CommonErrorCode.PARAM_ERROR,
                            String.format("microservice [%s.%s] already exists", this.serviceName, mse.getServiceId()));
            }
            if (mse.getServiceName() == null)
                mse.setServiceName(this.serviceName);
            microServices.put(serviceId, mse);
            logger.info("put service : {}", mse.getServiceId());
        }
        logger.info("Initialization is complete .");

        if (getZkClient() != null)
        {
            getZkClient().createNode(MicroSerivceConstant.SERVICE_ROOT_PATH, null);
        }
    }

    public void regist(MicroServiceRegistrable mr)
    {
        if (zkClient == null)
            return;
        RegistInfo registInfo = mr.registInfo();
        registInfo.setServiceName(this.serviceName);

        String servicePath = MicroSerivceConstant.SERVICE_ROOT_PATH + "/" + this.serviceName;
        getZkClient().createNode(servicePath, null);

        String nodePath = servicePath + "/" + registInfo.getName();
        logger.info(String.format("Register microservice %s", nodePath));
        getZkClient().createEphemeralNode(nodePath, registInfo.toString());
    }

    public Object processRequest(String serviceName, String serviceId, Object[] args)
    {
        MicroServiceDefinition mse = microServices.get(serviceId);
        if (mse == null)
            throw new MicroServiceException(String.format("microservice '%s' not found", serviceId));
        return microServiceProcesser.process(mse, args);
    }

    public Object processRequest(String serviceName, String serviceId, List<String> args)
    {
        MicroServiceDefinition mse = microServices.get(serviceId);
        if (mse == null)
            throw new MicroServiceException(String.format("microservice '%s' not found", serviceId));
        Object[] arr = MicroServiceHelper.parseMethodArguments(mse.getTarget().getClazz(), mse.getTarget().getMethod(),
                mse.getTarget().getGenericMap(), args);
        return microServiceProcesser.process(mse, arr);
    }

    public MicroServiceLoader getMicroServiceLoader()
    {
        return microServiceLoader;
    }

    public void setMicroServiceLoader(MicroServiceLoader microServiceLoader)
    {
        this.microServiceLoader = microServiceLoader;
    }

    public MicroServiceProcesser getMicroServiceProcesser()
    {
        return microServiceProcesser;
    }

    public void setMicroServiceProcesser(MicroServiceProcesser microServiceProcesser)
    {
        this.microServiceProcesser = microServiceProcesser;
    }

    public void setZkClient(ZKClient zkClient)
    {
        this.zkClient = zkClient;
    }

    public ZKClient getZkClient()
    {
        return zkClient;
    }

}
