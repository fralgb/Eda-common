package com.omniselling.ms.core.model;

import java.util.List;

/**
 * 应用系统资源权限检查条件
 * @author user
 *
 */
public class I18nResLangAndKeys
{
    /**
     * 应用系统名
     */
    private String applicationName;
    
    /**
     * 国际化语言代码
     */
    private String languageCode;
    
    /**
     * 资源名
     */
    private List<String> resourceKeys;

    public String getApplicationName()
    {
        return applicationName;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public String getLanguageCode()
    {
        return languageCode;
    }

    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }

    public List<String> getResourceKeys()
    {
        return resourceKeys;
    }

    public void setResourceKeys(List<String> resourceKeys)
    {
        this.resourceKeys = resourceKeys;
    }  
 
}
