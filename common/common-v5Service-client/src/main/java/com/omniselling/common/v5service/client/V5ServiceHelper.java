package com.omniselling.common.v5service.client;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class V5ServiceHelper {

	static Map<String, String> OBJECT_METHOD = new ConcurrentHashMap<String, String>();

	static {
		// Object 对象定义的方法,一律不支持
		Method[] method = Object.class.getMethods();
		for (Method m : method) {
			OBJECT_METHOD.put(m.getName(), "");
		}
	}

	public static boolean isObjectClassMechod(String methodName) {
		return OBJECT_METHOD.containsKey(methodName);
	}

	/**
	 * 判断方法是不是getter、setter 方法
	 * 
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
					if (descriptor.getReadMethod().equals(method))
						return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
