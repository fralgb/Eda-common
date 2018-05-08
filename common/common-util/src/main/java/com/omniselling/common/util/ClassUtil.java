package com.omniselling.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * 
 * @author xslong
 * @time Nov 18, 2016 9:28:44 AM
 */
public class ClassUtil
{
	
    private static final Map<Type, Class<?>> primitiveWrapperMap = new HashMap<>();
    static {
        primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
        primitiveWrapperMap.put(Byte.TYPE, Byte.class);
        primitiveWrapperMap.put(Character.TYPE, Character.class);
        primitiveWrapperMap.put(Short.TYPE, Short.class);
        primitiveWrapperMap.put(Integer.TYPE, Integer.class);
        primitiveWrapperMap.put(Long.TYPE, Long.class);
        primitiveWrapperMap.put(Double.TYPE, Double.class);
        primitiveWrapperMap.put(Float.TYPE, Float.class);
         primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
    }
    
    
    /**
     * 根据Type找对应的包装Class
     * @param type
     * @return
     */
    public static Class<?> findPrimitiveClass(Type type)
    {
    	return primitiveWrapperMap.get(type);
    }

	public static void main(String[] args) throws Exception {
        String arrayClassName = "[Ljava.lang.Long;";
        String str = arrayClassName.substring(1, arrayClassName.length() - 1);
		System.out.println(str);
		int count = 0;
		for (char c : str.toCharArray()) {
			if ('L' == c) {
				count++;
			} else {
				break;
			}
		}
        String itemClassName = str.substring(count);
		System.out.println(count);
        System.out.println(itemClassName);
        
        //find class in package
        /*		List<Class<?>> classes = ClassUtil.getClasses("com.omniselling.fms.common.model.enumerate");
        for (Class clas :classes) {  
            System.out.println(clas.getName());  
        }  */
	}

    public static void test2() throws ClassNotFoundException
    {
        String className = "com.omniselling.common.model.ClientRequest";
        Class<?> clazz = Class.forName(className);
		printField(clazz);
        printField(clazz.getSuperclass());
	}

    public static List<Class<?>> findClass(String packageName, boolean isRecursion,
            Class<? extends Annotation> annotation)
    {

        Set<String> classNames = findClassName(packageName, isRecursion);
        if (classNames == null)
			return null;

        List<Class<?>> clazzList = new ArrayList<>();
        for (String className : classNames)
        {
			try {
                Class<?> clazz = Class.forName(className);
				if (annotation == null) {
					clazzList.add(clazz);
				} else if (clazz.isAnnotationPresent(annotation)) {
					clazzList.add(clazz);
				}
            }
            catch (ClassNotFoundException e)
            {
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
    public static Set<String> findClassName(String packageName, boolean isRecursion)
    {
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
    private static Set<String> findClassNameFromDir(String filePath, String packageName, boolean isRecursion)
    {
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
                if (fileName.endsWith(".class") && !fileName.contains("$"))
                {
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
                if (entryName.endsWith(".class") && !entryName.contains("$") && entryName.startsWith(packageName))
                {
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
    private static Set<String> findClassNameFromJars(URL[] urls, String packageName, boolean isRecursion)
    {
        Set<String> classNames = new HashSet<>();

		for (int i = 0; i < urls.length; i++) {
            String classPath = urls[i].getPath();

            // 不必搜索classes文件夹
            if (classPath.endsWith("classes/"))
            {
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
        //Map<String, Object> returnMap = new HashMap();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			System.out.println("-----------");
			clazz.getGenericInterfaces();
			for (int i = 0, len = propertyDescriptors.length; i < len; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
                if (!propertyName.equals("class"))
                {
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

    /** 
     * 取得某个接口下所有实现这个接口的类 
     * */  
    public static List<Class> getAllClassByInterface(Class c) {  
            List<Class>  returnClassList = null;  
              
            if(c.isInterface()) {  
                // 获取当前的包名  
                String packageName = c.getPackage().getName();  
                // 获取当前包下以及子包下所以的类  
                List<Class<?>> allClass = getClasses(packageName);  
                if(allClass != null) {  
                    returnClassList = new ArrayList<Class>();  
                    for(Class classes : allClass) {  
                        // 判断是否是同一个接口  
                        if(c.isAssignableFrom(classes)) {  
                            // 本身不加入进去  
                            if(!c.equals(classes)) {  
                                returnClassList.add(classes);          
                            }  
                        }  
                    }  
                }  
            }  
              
            return returnClassList;  
        }  
  
      
    /* 
     * 取得某一类所在包的所有类名 不含迭代 
     */  
    public static String[] getPackageAllClassName(String classLocation, String packageName){  
        //将packageName分解  
        String[] packagePathSplit = packageName.split("[.]");  
        String realClassLocation = classLocation;  
        int packageLength = packagePathSplit.length;  
        for(int i = 0; i< packageLength; i++){  
            realClassLocation = realClassLocation + File.separator+packagePathSplit[i];  
        }  
        File packeageDir = new File(realClassLocation);  
        if(packeageDir.isDirectory()){  
            String[] allClassName = packeageDir.list();  
            return allClassName;  
        }  
        return null;  
    }  
      
    /** 
     * 从包package中获取所有的Class 
     * @param pack 
     * @return 
     */  
    public static List<Class<?>> getClasses(String packageName){  
          
        //第一个class类的集合  
        List<Class<?>> classes = new ArrayList<Class<?>>();  
        //是否循环迭代  
        boolean recursive = true;  
        //获取包的名字 并进行替换  
        String packageDirName = packageName.replace('.', '/');  
        //定义一个枚举的集合 并进行循环来处理这个目录下的things  
        Enumeration<URL> dirs;  
        try {  
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);  
            //循环迭代下去  
            while (dirs.hasMoreElements()){  
                //获取下一个元素  
                URL url = dirs.nextElement();  
                //得到协议的名称  
                String protocol = url.getProtocol();  
                //如果是以文件的形式保存在服务器上  
                if ("file".equals(protocol)) {  
                    //获取包的物理路径  
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");  
                    //以文件的方式扫描整个包下的文件 并添加到集合中  
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);  
                } else if ("jar".equals(protocol)){  
                    //如果是jar包文件   
                    //定义一个JarFile  
                    JarFile jar;  
                    try {  
                        //获取jar  
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();  
                        //从此jar包 得到一个枚举类  
                        Enumeration<JarEntry> entries = jar.entries();  
                        //同样的进行循环迭代  
                        while (entries.hasMoreElements()) {  
                            //获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件  
                            JarEntry entry = entries.nextElement();  
                            String name = entry.getName();  
                            //如果是以/开头的  
                            if (name.charAt(0) == '/') {  
                                //获取后面的字符串  
                                name = name.substring(1);  
                            }  
                            //如果前半部分和定义的包名相同  
                            if (name.startsWith(packageDirName)) {  
                                int idx = name.lastIndexOf('/');  
                                //如果以"/"结尾 是一个包  
                                if (idx != -1) {  
                                    //获取包名 把"/"替换成"."  
                                    packageName = name.substring(0, idx).replace('/', '.');  
                                }  
                                //如果可以迭代下去 并且是一个包  
                                if ((idx != -1) || recursive){  
                                    //如果是一个.class文件 而且不是目录  
                                    if (name.endsWith(".class") && !entry.isDirectory()) {  
                                        //去掉后面的".class" 获取真正的类名  
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);  
                                        try {  
                                            //添加到classes  
                                            classes.add(Class.forName(packageName + '.' + className));  
                                        } catch (ClassNotFoundException e) {  
                                            e.printStackTrace();  
                                        }  
                                      }  
                                }  
                            }  
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }   
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
         
        return classes;  
    }  
      
    /** 
     * 以文件的形式来获取包下的所有Class 
     * @param packageName 
     * @param packagePath 
     * @param recursive 
     * @param classes 
     */  
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes){  
        //获取此包的目录 建立一个File  
        File dir = new File(packagePath);  
        //如果不存在或者 也不是目录就直接返回  
        if (!dir.exists() || !dir.isDirectory()) {  
            return;  
        }  
        //如果存在 就获取包下的所有文件 包括目录  
        File[] dirfiles = dir.listFiles(new FileFilter() {  
        //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)  
              public boolean accept(File file) {  
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));  
              }  
            });  
        //循环所有文件  
        for (File file : dirfiles) {  
            //如果是目录 则继续扫描  
            if (file.isDirectory()) {  
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),  
                                      file.getAbsolutePath(),  
                                      recursive,  
                                      classes);  
            }  
            else {  
                //如果是java类文件 去掉后面的.class 只留下类名  
                String className = file.getName().substring(0, file.getName().length() - 6);  
                try {  
                    //添加到集合中去  
                    classes.add(Class.forName(packageName + '.' + className));  
                } catch (ClassNotFoundException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
    
    /**
     * Get getter method name by field name
     * @param fieldname
     * @return
     */
    public static String toGetter(String fieldname) {
        
        if (fieldname == null || fieldname.length() == 0) {
            return null;
        }
        
        /* If the second char is upper, make 'get' + field name as getter name. For example, eBlog -> geteBlog */
        if (fieldname.length() > 2) {
            String second = fieldname.substring(1, 2);
            if (second.equals(second.toUpperCase())) {
                return new StringBuffer("get").append(fieldname).toString();
            }
        }
        
        /* Common situation */
        fieldname = new StringBuffer("get").append(fieldname.substring(0, 1).toUpperCase())
                .append(fieldname.substring(1)).toString();
        
        return  fieldname;
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
