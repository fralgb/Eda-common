package com.omniselling.common.v5service.client;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 
 * @author xslong
 * @time Nov 18, 2016 9:28:44 AM
 */
public class ClassUtil {




    public static List<Class<?>> findClass(String packageName, boolean isRecursion,
			Class<? extends Annotation> annotation) {

		Set<String> classNames = findClassName(packageName, isRecursion);
		if (classNames == null)
			return null;

        List<Class<?>> clazzList = new ArrayList<>();
		for (String className : classNames) {
			try {
                Class<?> clazz = Class.forName(className);
				if (annotation == null) {
					clazzList.add(clazz);
				} else if (clazz.isAnnotationPresent(annotation)) {
					clazzList.add(clazz);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return clazzList;
	}

	/**
	 * 获取某包下所有类
	 * 
	 * @param packageName
	 *            包名
	 * @param isRecursion
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	public static Set<String> findClassName(String packageName, boolean isRecursion) {
		Set<String> classNames = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", "/");

		URL url = loader.getResource(packagePath);
		if (url != null) {
			String protocol = url.getProtocol();
			if (protocol.equals("file")) {
				classNames = findClassNameFromDir(url.getPath(), packageName, isRecursion);
			} else if (protocol.equals("jar")) {
				JarFile jarFile = null;
				try {
					jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (jarFile != null) {
					findClassNameFromJar(jarFile.entries(), packageName, isRecursion);
				}
			}
		} else {
			/* 从所有的jar包中查找包名 */
			classNames = findClassNameFromJars(((URLClassLoader) loader).getURLs(), packageName, isRecursion);
		}

		return classNames;
	}

	/**
	 * 从项目文件获取某包下所有类
	 * 
	 * @param filePath
	 *            文件路径
	 * @param className
	 *            类名集合
	 * @param isRecursion
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	private static Set<String> findClassNameFromDir(String filePath, String packageName, boolean isRecursion) {
		Set<String> className = new HashSet<>();
		File file = new File(filePath);
		File[] files = file.listFiles();
		for (File childFile : files) {
			if (childFile.isDirectory()) {
				if (isRecursion) {
					className.addAll(findClassNameFromDir(childFile.getPath(), packageName + "." + childFile.getName(),
							isRecursion));
				}
			} else {
				String fileName = childFile.getName();
				if (fileName.endsWith(".class") && !fileName.contains("$")) {
					className.add(packageName + "." + fileName.replace(".class", ""));
				}
			}
		}

		return className;
	}

	/**
	 * @param jarEntries
	 * @param packageName
	 * @param isRecursion
	 * @return
	 */
	private static Set<String> findClassNameFromJar(Enumeration<JarEntry> jarEntries, String packageName,
			boolean isRecursion) {
		Set<String> classNames = new HashSet<>();

		while (jarEntries.hasMoreElements()) {
			JarEntry jarEntry = jarEntries.nextElement();
			if (!jarEntry.isDirectory()) {
				/*
				 * 这里是为了方便，先把"/" 转成 "." 再判断 ".class" 的做法可能会有bug (FIXME: 先把"/" 转成
				 * "." 再判断 ".class" 的做法可能会有bug)
				 */
				String entryName = jarEntry.getName().replace("/", ".");
				if (entryName.endsWith(".class") && !entryName.contains("$") && entryName.startsWith(packageName)) {
					entryName = entryName.replace(".class", "");
					if (isRecursion) {
						classNames.add(entryName);
					} else if (!entryName.replace(packageName + ".", "").contains(".")) {
						classNames.add(entryName);
					}
				}
			}
		}

		return classNames;
	}

	/**
	 * 从所有jar中搜索该包，并获取该包下所有类
	 * 
	 * @param urls
	 *            URL集合
	 * @param packageName
	 *            包路径
	 * @param isRecursion
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	private static Set<String> findClassNameFromJars(URL[] urls, String packageName, boolean isRecursion) {
		Set<String> classNames = new HashSet<>();

		for (int i = 0; i < urls.length; i++) {
			String classPath = urls[i].getPath();

			// 不必搜索classes文件夹
			if (classPath.endsWith("classes/")) {
				continue;
			}

			JarFile jarFile = null;
			try {
				jarFile = new JarFile(classPath.substring(classPath.indexOf("/")));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (jarFile != null) {
				classNames.addAll(findClassNameFromJar(jarFile.entries(), packageName, isRecursion));
			}
		}

		return classNames;
	}

    public static void printField(Class<?> clazz)
    {
        //Map<String, Object> returnMap = new HashMap<>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			System.out.println("-----------");
			clazz.getGenericInterfaces();
			for (int i = 0, len = propertyDescriptors.length; i < len; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					System.out.println("DisplayName :" + descriptor.getDisplayName());
					System.out.println("PropertyType :" + descriptor.getPropertyType());
					System.out.println("WriteMethod :" + descriptor.getWriteMethod());
					System.out.println("ReadMethod :" + descriptor.getReadMethod());
					Method readMethod = descriptor.getReadMethod();
					System.out.println((readMethod.getGenericReturnType()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int count(String text, String sub) {
		int count = 0, start = 0;
		while ((start = text.indexOf(sub, start)) >= 0) {
			start += sub.length();
			count++;
		}
		return count;
	}

}
