package com.omniselling.common.util;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

public class EntityPropertyCopyUtil
{
    private final static Logger logger = LoggerFactory.getLogger(EntityPropertyCopyUtil.class);

    public static void copyProperties(Object sourceObj, Object targetObj)
    {
        BeanCopier copier = BeanCopier.create(sourceObj.getClass(), targetObj.getClass(), false);
        copier.copy(sourceObj, targetObj, null);
    }
    
    @Deprecated
    public static void copyProperties(Object sourceObj, Object targetObj,String...ignors)
    {
        BeanCopier copier = BeanCopier.create(sourceObj.getClass(), targetObj.getClass(), false);
        copier.copy(sourceObj, targetObj, null);
    }

    /**
     * 将原对象转成新的对象
     * 
     * @param sourceObj
     * @param targetClass
     * @return
     */
    public static <T> T copy(Object sourceObj, Class<T> targetClass)
    {
        T targetObj = null;
        try
        {
            BeanCopier copier = BeanCopier.create(sourceObj.getClass(), targetClass, false);
            targetObj = targetClass.getConstructor().newInstance();
            copier.copy(sourceObj, targetObj, null);
        }
        catch (InstantiationException e)
        {
            logger.error(e.getMessage(), e);
        }
        catch (IllegalAccessException e)
        {
            logger.error(e.getMessage(), e);
        }
        catch (IllegalArgumentException e)
        {
            logger.error(e.getMessage(), e);
        }
        catch (InvocationTargetException e)
        {
            logger.error(e.getMessage(), e);
        }
        catch (NoSuchMethodException e)
        {
            logger.error(e.getMessage(), e);
        }
        catch (SecurityException e)
        {
            logger.error(e.getMessage(), e);
        }
        return targetObj;
    }
}
