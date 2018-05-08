package com.omniselling.common.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * DbModel Service 工厂 
 * 
 * @author xslong
 * @version 创建时间：Dec 16, 2016 4:42:43 PM
 * 
 */

public class DbModelServiceFactory implements ApplicationContextAware {

	static ApplicationContext applicationContext;

	@SuppressWarnings("rawtypes")
	private static Map<Class, DbBaseService> dbServiceCache = new HashMap<>();

	@SuppressWarnings("rawtypes")
	public static DbBaseService getDbService(Class<? extends DbBaseModel> dbModelClass) {
		DbBaseService dbService = dbServiceCache.get(dbModelClass);
		if (dbService != null)
			return dbService;
		Map<String, Object> map = applicationContext.getBeansWithAnnotation(DbMapping.class);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Object object = entry.getValue();
			DbMapping mapping = object.getClass().getAnnotation(DbMapping.class);
			if (mapping == null || !dbModelClass.equals(mapping.value()))
				continue;
			dbService = (DbBaseService) object;
			dbServiceCache.put(dbModelClass, dbService);
			return dbService;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static DbBaseService getDbService(DbBaseModel dbModel) {
		return getDbService(dbModel.getClass());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		DbModelServiceFactory.applicationContext = applicationContext;
	}
}
