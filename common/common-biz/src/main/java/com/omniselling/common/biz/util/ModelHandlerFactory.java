package com.omniselling.common.biz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import com.omniselling.common.biz.IBizModelHandler;
import com.omniselling.common.util.SpringContextUtils;

public class ModelHandlerFactory
{
    private static Logger logger = LoggerFactory.getLogger(ModelHandlerFactory.class);

    @SuppressWarnings({ "rawtypes" })
    public static IBizModelHandler getRepository(Class clazz)
    {
        IBizModelHandler handler = null;
        try
        {
            handler = (IBizModelHandler) SpringContextUtils.getBean(clazz.getName());
        }
        catch (BeansException e)
        {
            logger.error(e.getMessage(), e);
        }
        catch (ClassNotFoundException e)
        {
            logger.error(e.getMessage(), e);
        }
        if (handler == null)
        {
            return null;
        }
        return handler;
    }

}
