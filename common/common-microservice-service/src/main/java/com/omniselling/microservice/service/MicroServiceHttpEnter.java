package com.omniselling.microservice.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omniselling.common.ResponseCodeEnum;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.protocol.ApplicationMetadata;
import com.omniselling.common.protocol.OmniException;
import com.omniselling.common.protocol.ProtocolTime;
import com.omniselling.common.protocol.RequestWrapper;
import com.omniselling.common.protocol.ResponseWrapper;
import com.omniselling.common.zookeeper.ZKClient;
import com.omniselling.microservice.MicroSerivceRequestData;
import com.omniselling.microservice.MicroServiceException;
import com.omniselling.microservice.RegistInfo;

/**
 *
 * 微服务HTTP入口 初始化微信服务上下文 实现服务注册信息 Created by xslong on 16/8/20.
 */
@Controller
@RequestMapping(value = "/microService")
public class MicroServiceHttpEnter implements ApplicationContextAware, InitializingBean, MicroServiceRegistrable
{

    final static Logger logger = LoggerFactory.getLogger(MicroServiceHttpEnter.class);

    private MicroServiceContext microServiceContext;
    private ApplicationContext applicationContext;

    @Resource
    private ZKClient zkClient;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        microServiceContext = new MicroServiceContext(new MicroServiceAnnotationLoader(applicationContext),
                new MicroServiceSpringProcesser(applicationContext));
        microServiceContext.setZkClient(zkClient);
        microServiceContext.init();
        microServiceContext.regist(this);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper microService(HttpServletRequest request,
            @RequestBody RequestWrapper<MicroSerivceRequestData> requestData)
    {
        MicroSerivceRequestData data = requestData.getData();
        logger.info("Received the request :[ServiceName: {} ServiceId: {}]", data.getServiceName(),
                data.getServiceId());
        ResponseWrapper response = null;
        long t1 = System.currentTimeMillis();
        try
        {
            Object result = microServiceContext.processRequest(data.getServiceName(), data.getServiceId(),
                    data.getArguments());
            response = ResponseWrapper.build(ResponseCodeEnum.SUCCESS, result);
        }
        catch (OmniException e)
        {
        	e.printStackTrace();
            logger.error(e.getMessage(), e);
            response = ResponseWrapper.build(e.getCodeEnum(), e.getMessage());
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            response = ResponseWrapper.build(CommonErrorCode.SYSTEM_ERROR);
        }
        response.setTime(ProtocolTime.currentTime());
        long used = System.currentTimeMillis() - t1;
        logger.info(String.format("Processing requests [ServiceName: %s ServiceId: %s] with a total of %s ms",
                data.getServiceName(), data.getServiceId(), used));
        return response;
    }

    @Override
    public RegistInfo registInfo()
    {
        RegistInfo info = new RegistInfo();
        info.setRegistTime(ProtocolTime.currentTime());

        ApplicationMetadata metadata = ApplicationMetadata.instance();
        String host = null;
        if (metadata.getHost() != null && metadata.getHost().trim().length() > 0)
        {
            host = metadata.getHost().trim();
        }
        else
        {
            String[] ips = LocalIpAddressUtil.resolveLocalIps();
            if (ips == null || ips.length == 0)
                throw new MicroServiceException("Can not get the native IP");
            host = ips[0];
        }
        String port = null;
        if (metadata.getPort() != null && metadata.getPort().trim().length() > 0)
        {
            port = metadata.getPort().trim();
        }
        String str = host + (port == null ? "" : ":" + port);
        info.setName(str);
        info.setPath(metadata.getPath() != null && metadata.getPath().trim().length() > 0 ? metadata.getPath().trim()
                : "http://" + str + "/microService");
        return info;
    }

    public void setZkClient(ZKClient zkClient)
    {
        this.zkClient = zkClient;
    }

}
