package com.omniselling.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class ObjectUtil {

	public static <T> T clone(Class<T> clazz, Object orig) {
		T t = null;
		try {
			t = clazz.newInstance();
			BeanUtils.copyProperties(t, orig);
			return t;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return t;
	}

	public static <T> List<T> clone(Class<T> clazz, Collection<?> list) {
		if (list == null)
			return null;
		List<T> result = new ArrayList<T>();
		for (Object o : list) {
			result.add(clone(clazz, o));
		}
		return result;
	}

}
