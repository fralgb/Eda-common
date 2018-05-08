package com.omniselling.common.v5service.client;

import java.lang.reflect.Type;

public class TypeUtil {

	private static final String TYPE_NAME_PREFIX = "class ";
	private static final String TYPE_INTERFACE_NAME_PREFIX = "interface ";

	public static String getClassName(Type type) {
		if (type == null) {
			return null;
		}
		String className = type.toString();
		if (className.startsWith(TYPE_NAME_PREFIX)) {
			className = className.substring(TYPE_NAME_PREFIX.length());
		}
		if (className.startsWith(TYPE_INTERFACE_NAME_PREFIX)) {
			className = className.substring(TYPE_INTERFACE_NAME_PREFIX.length());
		}
		return className;
	}

}
