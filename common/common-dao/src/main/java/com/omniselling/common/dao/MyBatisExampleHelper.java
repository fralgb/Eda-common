package com.omniselling.common.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/**
 * MyBatis Example 工具类
 * 
 * @author xslong
 * @version 创建时间：Dec 20, 2016 3:32:44 PM
 * 
 */

public class MyBatisExampleHelper
{

    final static String CRITERIA_AND_METHOD_FORMAT = "and%sEqualTo";

    final static int DEFAULT_LIMIT = 100;

    /**
     * 根据DbModel的类型生成DbModel对应的Example，并把condition的信息设置到Example
     * 
     * @param modelClass
     * @param condition
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     */
    @SuppressWarnings("rawtypes")
    public static Object toExample(Class modelClass, Map<String, Object> condition, int offset, int limit,
            String orderByClause) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
                    IllegalArgumentException, InvocationTargetException
    {
        String exampleClassName = modelClass.getName() + "Example";
        Class exampleClass = Class.forName(exampleClassName);
        Object example = exampleClass.newInstance();

        Method setOffset = ReflectionUtils.findMethod(exampleClass, "setOffset", new Class[] { Integer.class });
        setOffset.invoke(example, offset);

        Method setLimit = ReflectionUtils.findMethod(exampleClass, "setLimit", new Class[] { Integer.class });
        setLimit.invoke(example, limit);

        if (!StringUtils.isEmpty(orderByClause))
        {
            Method setOrderByClause = ReflectionUtils.findMethod(exampleClass, "setOrderByClause",
                    new Class[] { String.class });
            setOrderByClause.invoke(example, orderByClause);
        }

        Method createCriteria = ReflectionUtils.findMethod(exampleClass, "createCriteria");
        Object criteria = createCriteria.invoke(example, new Object[] {});
        Class criteriaClass = criteria.getClass();
        for (Map.Entry<String, Object> entry : condition.entrySet())
        {
            String propertyName = entry.getKey();
            String andMethodName = String.format(CRITERIA_AND_METHOD_FORMAT,
                    propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1));
            Method andMethod = ReflectionUtils.findMethod(criteriaClass, andMethodName,
                    new Class[] { entry.getValue().getClass() });
            if (andMethod == null)
                throw new RuntimeException(
                        String.format("Can't find method %s [%s] param type ", criteriaClass, andMethodName));
            andMethod.invoke(criteria, entry.getValue());
        }
        return example;
    }

    @SuppressWarnings("rawtypes")
    public static Object toExample(Class modelClass, Map<String, Object> condition) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        return toExample(modelClass, condition, 0, DEFAULT_LIMIT, null);
    }

    /**
     * 执行 selectByExample 并返回
     * 
     * @param mapper
     * @param example
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <DM extends DbBaseModel> List<DM> selectByExample(Object mapper, Object example)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        Method selectByExample = findMethod(mapper.getClass(), "selectByExample", example.getClass());
        return (List<DM>) selectByExample.invoke(mapper, example);
    }

    @SuppressWarnings("rawtypes")
    static Method findMethod(Class clazz, String methodName, Class classParam)
    {
        Method method = null;
        Class paramType = classParam;
        do
        {
            method = ReflectionUtils.findMethod(clazz, methodName, new Class[] { paramType });
            paramType = paramType.getSuperclass();
        } while (method == null && paramType != null);
        if (method == null)
            throw new RuntimeException("The '" + methodName + "' method was not found");
        return method;
    }

    @SuppressWarnings("rawtypes")
    public static <DM extends DbBaseModel> List<DM> selectByMapCondition(Class<DM> modelClass, Object mapper,
            Map<String, Object> condition, int offset, int limit, String orderByClause) throws ClassNotFoundException,
                    InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        Object example = toExample(modelClass, condition, offset, limit, orderByClause);
        return selectByExample(mapper, example);
    }

    /**
     * 执行 countByExample 并返回
     * 
     * @param mapper
     * @param example
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("rawtypes")
    public static <DM extends DbBaseModel> Long countByExample(Object mapper, Object example)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        Method countByExample = findMethod(mapper.getClass(), "countByExample", example.getClass());
        return (Long) countByExample.invoke(mapper, example);
    }

    @SuppressWarnings("rawtypes")
    public static <DM extends DbBaseModel> Long countByMapCondition(Class<DM> modelClass, Object mapper,
            Map<String, Object> condition) throws ClassNotFoundException, InstantiationException,
                    IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        Object example = toExample(modelClass, condition);
        return countByExample(mapper, example);
    }

}
