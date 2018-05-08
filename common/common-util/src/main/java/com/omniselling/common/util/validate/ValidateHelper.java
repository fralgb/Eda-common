package com.omniselling.common.util.validate;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * @author xslong
 *
 */
public class ValidateHelper {

	public static void validate(Object obj) throws ValidateException {
		if (obj == null)
			throw new ValidateException("obj is required");
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			Validate validate = field.getAnnotation(Validate.class);
			if (validate == null)
				continue;
			String name = field.getName();
			Object value = null;
			try {
				value = getFieldValue(name, obj);
			} catch (Exception e){
				e.printStackTrace();
				throw new ValidateException(e.getMessage());
			}
			if (value == null) {
				if (validate.required())
					throw new ValidateException(name + " must be specified");
			}
			if (validate.minLength() > 0 || validate.maxLength() > 0) {
				String str = validate.trim() ? value.toString().trim() : value.toString();
				int length = str.length();
				if (validate.minLength() > 0 && length < validate.minLength())
					throw new ValidateException(name + " Minimum value is " + validate.minLength());
				if (validate.maxLength() > 0 && length > validate.maxLength())
					throw new ValidateException(name + " Maximum value is " + validate.maxLength());
			}
		}
	}

	/**
	 * 验证对象是否为NULL,空字符串，空数组，空的Collection或Map(只有空格的字符串也认为是空串)
	 * 
	 * @param obj
	 *            被验证的对象
	 * @param message
	 *            异常信息
	 */
	@SuppressWarnings("rawtypes")
	public static void notEmpty(Object obj, String field) {
		if (obj == null)
			throw new ValidateException(field + " must be specified");
		if (obj instanceof String && obj.toString().trim().length() == 0)
			throw new ValidateException(field + " must be specified");
		if (obj.getClass().isArray() && Array.getLength(obj) == 0)
			throw new ValidateException(field + " must be specified");
		if (obj instanceof Collection && ((Collection) obj).isEmpty())
			throw new ValidateException(field + " must be specified");
		if (obj instanceof Map && ((Map) obj).isEmpty())
			throw new ValidateException(field + " must be specified");
	}

	public static Object getFieldValue(String field, Object object) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field, object.getClass());
		Method readMethod = propertyDescriptor.getReadMethod();
		return readMethod.invoke(object, new Object[0]);
	}

}
