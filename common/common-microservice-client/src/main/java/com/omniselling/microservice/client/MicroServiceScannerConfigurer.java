package com.omniselling.microservice.client;

import static org.springframework.util.Assert.notNull;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StringUtils;

/**
 * 配置扫描微服务的规则（注意:扫描的是接口）
 * 
 * @author xslong
 *
 */
public class MicroServiceScannerConfigurer
		implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware, BeanNameAware {

	private String groupId;

	private String basePackage;

	private RemoteServiceExecutor remoteServiceExecutor;

	private Class<? extends Annotation> annotationClass;

	private Class<?> markerInterface;

	private ApplicationContext applicationContext;

	private String beanName;

	private boolean processPropertyPlaceHolders;
	
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public void setRemoteServiceExecutor(RemoteServiceExecutor remoteServiceExecutor) {
		this.remoteServiceExecutor = remoteServiceExecutor;
	}
	public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}
	public void setMarkerInterface(Class<?> superClass) {
		this.markerInterface = superClass;
	}

	public void setProcessPropertyPlaceHolders(boolean processPropertyPlaceHolders) {
		this.processPropertyPlaceHolders = processPropertyPlaceHolders;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		notNull(this.basePackage, "Property 'basePackage' is required");
		notNull(this.groupId, "Property 'groupId' is required");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
	}
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		if (this.processPropertyPlaceHolders) {
			processPropertyPlaceHolders();
		}
		ClassPathMicroServiceDefinitionScanner scanner = new ClassPathMicroServiceDefinitionScanner(registry);
		scanner.setAnnotationClass(this.annotationClass);
		scanner.setMarkerInterface(this.markerInterface);
		scanner.setResourceLoader(this.applicationContext);
		//scanner.setApplicationContext(this.applicationContext);
		scanner.setGroupId(this.groupId);
		scanner.setRemoteServiceExecutor(this.remoteServiceExecutor);
		scanner.registerFilters();
		scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage,
				ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	}

	private void processPropertyPlaceHolders() {
		Map<String, PropertyResourceConfigurer> prcs = applicationContext
				.getBeansOfType(PropertyResourceConfigurer.class);

		if (!prcs.isEmpty() && applicationContext instanceof GenericApplicationContext) {
			BeanDefinition miscroSerivceScannerBean = ((GenericApplicationContext) applicationContext).getBeanFactory()
					.getBeanDefinition(beanName);
			DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
			factory.registerBeanDefinition(beanName, miscroSerivceScannerBean);

			for (PropertyResourceConfigurer prc : prcs.values()) {
				prc.postProcessBeanFactory(factory);
			}
			PropertyValues values = miscroSerivceScannerBean.getPropertyValues();
			this.basePackage = updatePropertyValue("basePackage", values);
		}
	}

	private String updatePropertyValue(String propertyName, PropertyValues values) {
		PropertyValue property = values.getPropertyValue(propertyName);
		if (property == null) {
			return null;
		}
		Object value = property.getValue();

		if (value == null) {
			return null;
		} else if (value instanceof String) {
			return value.toString();
		} else if (value instanceof TypedStringValue) {
			return ((TypedStringValue) value).getValue();
		} else {
			return null;
		}
	}

}
