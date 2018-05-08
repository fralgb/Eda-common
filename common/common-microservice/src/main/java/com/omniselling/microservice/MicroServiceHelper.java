package com.omniselling.microservice;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * 微服务工具类，主要转换微服务中方法的参数
 * @author xslong
 * @time Nov 22, 2016 3:56:14 PM
 */
public class MicroServiceHelper {

	static ObjectMapper mapper = new ObjectMapper();
	
	static Map<String, String> OBJECT_METHOD = new ConcurrentHashMap<String, String>();

	static {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// Object 对象定义的方法,一律不支持
		Method[] method = Object.class.getMethods();
		for (Method m : method) {
			OBJECT_METHOD.put(m.getName(), "");
		}
	}
	
	/**
	 * 判断methodName是不是Object的方法
	 * @param methodName
	 * @return
	 */
	public static boolean isObjectClassMechod(String methodName){
		return OBJECT_METHOD.containsKey(methodName);
	}
	
	/**
	 * 根据Method的定义解析成json对应的实际类型
	 * @param clazz
	 * @param method
	 * @param genericMap clazz类中定义的泛型
	 * @param json 需要解析的JSON
	 * @return
	 */
	public static Object[] parseMethodArguments(Class<?> clazz, Method method,Map<String,Class<?>> genericMap, List<String> json) {

		Class<?>[] paramTypes = method.getParameterTypes();
		if(paramTypes.length == 0 || genericMap == null)
			return new Object[0];
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		Object[] arguments = new Object[genericParameterTypes.length];

		TypeFactory typeFactory = mapper.getTypeFactory();
		for(int i= 0;i<paramTypes.length;i++){
			try
			{
				if(json.get(i) == null){
					arguments[i] = null;
				}else if(String.class == paramTypes[i]){
					arguments[i] = json.get(i);
				}else{
					JavaType javaType = resolveType(genericParameterTypes[i], genericMap, typeFactory);
					arguments[i] = mapper.readValue(json.get(i), javaType);
				}
			} catch (Exception e)
			{
				e.printStackTrace();
				throw new MicroServiceException("Type parsing error", e);
			}
		}
		return arguments;
	}
	
	public static JavaType resolveType(Type returnType, Map<String, Class<?>> genericMap, TypeFactory typeFactory)
	{
		JavaType dataType = null;
		if (returnType instanceof TypeVariable)
		{
			TypeVariable<?> typeVariable = (TypeVariable<?>) returnType;
			Class<?> type = genericMap.get(typeVariable.getName());
			if (type != null)
			{
				dataType = typeFactory.constructType(type);
			}
		} else if (returnType instanceof ParameterizedType)
		{
			// 注意：目前能够解析 response<List<M>> 的泛型结构，如果是更复杂的需要进一步用typeFactory构建
			ParameterizedType pt = (ParameterizedType) returnType;
			List<Class<?>> list = new ArrayList<>();
			for (Type t : pt.getActualTypeArguments())
			{
				if (t instanceof TypeVariable)
				{
					Class<?> type = genericMap.get(((TypeVariable<?>) t).getName());
					if (type != null)
					{
						list.add(type);
					}
				} else if (t instanceof Class)
				{
					list.add((Class) t);
				}
			}
			dataType = typeFactory.constructParametricType((Class<?>) pt.getRawType(), list.toArray(new Class[list.size()]));
		}

		if (dataType == null)
		{
			dataType = typeFactory.constructType(returnType);
		}
		return dataType;
	}

	/**
	 * 根据types定义解析成json对应的实际类型
	 * @param types
	 * @param json 需要解析的JSON
	 * @return
	 
	public static Object[] parseMethodArguments(Class<?> clazz,Class<?>[] types, List<String> json) {
		ObjectMapper mapper = new ObjectMapper();
		Object[] arguments = new Object[types.length];
		try {
			for (int i = 0; i < types.length; i++) {
				if (json.get(i) == null)
					continue;
				if(String.class.equals(types[i])){
					arguments[i] = json.get(i);
				}else{
					arguments[i] = mapper.readValue(json.get(i), types[i]);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new MicroServiceException("Type parsing error",e);
		}
		return arguments;
	}
	*/
	/**
	 * 将array中的每个Object元素转为JSON
	 * @param array
	 * @return
	 */
	public static List<String> toJsonAarry(Object[] array) {
		if (array == null)
			return null;
		ObjectMapper mapper = new ObjectMapper();
		List<String> json = new ArrayList<>();
		try {
			for (Object obj : array) {
				if (obj == null) {
					json.add(null);
				}else  if(obj instanceof String) {
					json.add((String)obj);
				}else {
					json.add(mapper.writeValueAsString(obj));
				}
			}
		} catch (IOException e) {
			throw new MicroServiceException("Type parsing error",e);
		}
		return json;
	}

	public static <T> T toObject(String json, Class<T> clazz) {
		if (json == null)
			return null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断方法是不是getter、setter 方法
	 * @param method
	 * @return
	 */
	public static boolean isGetOrSetMethod(Method method) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(method.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0, len = propertyDescriptors.length; i < len; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					if(descriptor.getReadMethod().equals(method))
						return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
