package com.omniselling.ms.core.model;

import java.util.List;

/**
 * 国际化资源key信息
 *
 */
public class I18nResourceKey
{
    /**
     * 应用系统名
     */
    private String applicationName;
    /**
     * 资源名
     */
    private String resourceKey;
    /**
     * 国际化资源值
     */
    private List<I18nResourceValue> resourceValues;

    public String getApplicationName()
    {
        return applicationName;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }
    
    public String getResourceKey()
    {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey)
    {
        this.resourceKey = resourceKey;
    }

    public List<I18nResourceValue> getResourceValues()
    {
        return resourceValues;
    }

    public void setResourceValues(List<I18nResourceValue> resourceValues)
    {
        this.resourceValues = resourceValues;
    }

}
