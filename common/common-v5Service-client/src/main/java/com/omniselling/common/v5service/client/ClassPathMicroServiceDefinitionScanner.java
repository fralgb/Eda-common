package com.omniselling.common.v5service.client;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

/**
 * 扫描指定包下面的微服务接口,把成功扫描到的接口声明为V5SerivceBean
 * @author xslong
 *
 */
public class ClassPathMicroServiceDefinitionScanner extends ClassPathBeanDefinitionScanner {


    private Class<? extends Annotation> annotationClass;

    private Class<?> markerInterface;
    
    private String serviceName;
    
    private RemoteV5ServiceExecutor remoteV5ServiceExecutor;
    
    public ClassPathMicroServiceDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setMarkerInterface(Class<?> markerInterface) {
        this.markerInterface = markerInterface;
    }


    public void registerFilters() {
        boolean acceptAllInterfaces = true;

        if (this.annotationClass != null) {
            addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
            acceptAllInterfaces = false;
        }

        if (this.markerInterface != null) {
            addIncludeFilter(new AssignableTypeFilter(this.markerInterface) {
                @Override
                protected boolean matchClassName(String className) {
                    return false;
                }
            });
            acceptAllInterfaces = false;
        }

        if (acceptAllInterfaces) {
            addIncludeFilter(new TypeFilter() {
                public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                    return true;
                }
            });
        }
        addExcludeFilter(new TypeFilter() {
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            }
        });
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        if (beanDefinitions.isEmpty()) {
            logger.warn("No V5Service was found in '" + Arrays.toString(basePackages) + "' package.");
        } else {
            for (BeanDefinitionHolder holder : beanDefinitions) {
                GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
                if (logger.isDebugEnabled()) {
                    logger.debug("Creating MicroServiceBean with name '" + holder.getBeanName()
                            + "' and '" + definition.getBeanClassName() + "' microServiceInterface");
                }
                definition.getPropertyValues().add("v5ServiceInterface", definition.getBeanClassName());
                definition.getPropertyValues().add("serviceName",this.serviceName);
                definition.getPropertyValues().add("remoteV5ServiceExecutor",this.remoteV5ServiceExecutor);
                definition.setBeanClass(V5SerivceBean.class);
            }
        }
        return beanDefinitions;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return (beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent());
    }

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setRemoteV5ServiceExecutor(RemoteV5ServiceExecutor remoteV5ServiceExecutor) {
		this.remoteV5ServiceExecutor = remoteV5ServiceExecutor;
	}

}
