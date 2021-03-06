package com.omniselling.microservice;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/** 
 * 泛型工具类
 * 
 * @author xslong 
 * @version 创建时间：Dec 23, 2016 3:47:46 PM 
 * 
*/

public abstract class GenericUtils
{

	/**
	 * 解析Class类，把继承父类、实现接口时指明的泛型放在Map中，Key为泛型名，Value为实际类型
	 * 
	 * 递归
	 * 
	<pre>
	{
	P=class java.lang.Long, 
	I=class java.lang.String, 
	M=class com.omniselling.distribution.biz.model.BizDistributionPublish
	}
	</pre>
	
	 * @param clazz
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Map<String, Class<?>> parserGenericMap(Class<?> clazz)
	{
		Map<String, Class<?>> result = new HashMap<>();
		Class<?> c = clazz;
		while (c != null && Object.class != c)
		{
			result.putAll(parserGeneric(c));
			c = c.getSuperclass();
		}
		if (clazz.getGenericInterfaces() != null)
		{
			for (Class<?> ie : clazz.getInterfaces())
			{
				Class<?> ci = ie;
				while (ci != null && Object.class != ci)
				{
					result.putAll(parserGeneric(ci));
					ci = ci.getSuperclass();
				}
			}
		}
		return result;
	}

	public static Map<String, Class<?>> parserGeneric(Class<?> clazz)
	{
		try
		{
			Map<String, Class<?>> result = new HashMap<>();
			//继承父类时指定的类型
			if (clazz.getSuperclass() != null)
			{
				TypeVariable<?>[] superTypeVariable = clazz.getSuperclass().getTypeParameters();
				if (superTypeVariable.length > 0)
				{
					Map<String, Class<?>> map;
					map = parserGenericMap((ParameterizedType) clazz.getGenericSuperclass(), superTypeVariable);
					result.putAll(map);
				}
			}
			//实现接口时指定的类型
			Class<?>[] interfaces = clazz.getInterfaces();
			if (interfaces.length > 0)
			{
				Type[] interfaceTypes = clazz.getGenericInterfaces();
				for (int i = 0; i < interfaces.length; i++)
				{
					if (interfaceTypes[i] instanceof ParameterizedType)
					{
						Map<String, Class<?>> map = parserGenericMap((ParameterizedType) interfaceTypes[i],
								interfaces[i].getTypeParameters());
						result.putAll(map);
					}
				}
			}
			return result;
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private static Map<String, Class<?>> parserGenericMap(ParameterizedType pt, TypeVariable<?>[] typeVariables)
			throws ClassNotFoundException
	{
		Type[] arguments = pt.getActualTypeArguments();
		if (arguments == null)
			return null;
		if (arguments.length != typeVariables.length)
			throw new RuntimeException("The lengths are not the same");
		int len = arguments.length;
		Map<String, Class<?>> map = new HashMap<>();
		for (int i = 0; i < len; i++)
		{
			try
			{
				map.put(typeVariables[i].getTypeName(), Class.forName(arguments[i].getTypeName()));
			} catch (ClassNotFoundException e)
			{
			}
		}
		return map;
	}
}
