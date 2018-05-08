package com.omniselling.common.util.bootstrap;

import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * This is for application beans to each execute a method after Spring context is refreshed/started in their order of injection
 * Those beans must implement    
 * Spring 启动以后自动按顺序运行所有注入的bean, 如果不设置runInParalle=false则会并行执行
 * @author Atomic
 *
 */
public class BootstrapListener extends AbstractContextListener implements ApplicationListener<ContextRefreshedEvent>
{
    private List<IBootstrapAction> beans = Collections.emptyList();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        executeBeanMethods(event, beans);
    }

    public void setBeans(List<IBootstrapAction> beans)
    {
        this.beans = beans;
    }

}
