package com.omniselling.common.v5service.client;

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

import java.lang.annotation.Annotation;
import java.util.Map;

import static org.springframework.util.Assert.notNull;

/**
 * 配置扫描微服务的规则（注意:扫描的是生成的接口）
 * 
 * @author xslong
 *
 */
public class V5ServiceScannerConfigurer
		implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware, BeanNameAware {

	private String serviceName;

	private String basePackage;

	private RemoteV5ServiceExecutor remoteV5ServiceExecutor;

	private Class<? extends Annotation> annotationClass;

	private Class<?> markerInterface;

	private ApplicationContext applicationContext;

	private String beanName;

	private boolean processPropertyPlaceHolders;

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public void setRemoteV5ServiceExecutor(RemoteV5ServiceExecutor remoteV5ServiceExecutor) {
		this.remoteV5ServiceExecutor = remoteV5ServiceExecutor;
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

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void setBeanName(String name) {
		this.beanName = name;
	}

	public void afterPropertiesSet() throws Exception {
		notNull(this.basePackage, "Property 'basePackage' is required");
		notNull(this.serviceName, "Property 'serviceName' is required");
	}

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
	}

	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		if (this.processPropertyPlaceHolders) {
			processPropertyPlaceHolders();
		}
		ClassPathMicroServiceDefinitionScanner scanner = new ClassPathMicroServiceDefinitionScanner(registry);
		scanner.setAnnotationClass(this.annotationClass);
		scanner.setMarkerInterface(this.markerInterface);
		scanner.setResourceLoader(this.applicationContext);
		//scanner.setApplicationContext(this.applicationContext);
		scanner.setServiceName(this.serviceName);
		scanner.setRemoteV5ServiceExecutor(this.remoteV5ServiceExecutor);
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
