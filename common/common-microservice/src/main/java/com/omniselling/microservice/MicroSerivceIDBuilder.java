package com.omniselling.microservice;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public final class MicroSerivceIDBuilder
{

    public static String build(String serviceName, Class<?> clazz, Method method)
    {
        String typeName = clazz.getSimpleName();
        MicroService typeDefine = (MicroService) clazz.getAnnotation(MicroService.class);
        if (typeDefine != null && typeDefine.value() != null)
        {
            typeName = typeDefine.value().getSimpleName();
        }

        Map<String, Class<?>> genericMap = GenericUtils.parserGenericMap(clazz);

        String paramType = "";
        for (Type t : method.getGenericParameterTypes())
        {
            if (t instanceof Class)
            {
                paramType += ((Class<?>) t).getSimpleName() + ",";
            }
            else if (t instanceof ParameterizedType)
            {
                ParameterizedType newType = (ParameterizedType) t;
                paramType += newType.getTypeName() + ",";
            }
            else
            {
                Class<?> c = genericMap.get(t.getTypeName());
                paramType += c.getSimpleName() + ",";
            }

        }
        if (paramType.length() > 0)
        {
            paramType = paramType.substring(0, paramType.length() - 1);
        }

        return String.format("%s.%s(%s)", typeName, method.getName(), paramType);
    }
}
