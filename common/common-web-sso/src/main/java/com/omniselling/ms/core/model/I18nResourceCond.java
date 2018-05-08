package com.omniselling.ms.core.model;

import com.omniselling.common.model.BasePageInfo;

/**
 * i18n国际化资源查询条件
 *
 */
public class I18nResourceCond extends BasePageInfo
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
    private String resourceKey;
    private String resourceKeyLike;

    @Override
    public String toString()
    {
        return "I18nResourceCond [applicationName=" + applicationName + ", languageCode=" + languageCode
                + ", resourceKey=" + resourceKey + ", resourceKeyLike=" + resourceKeyLike + "]";
    }

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

    public String getResourceKey()
    {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey)
    {
        this.resourceKey = resourceKey;
    }

    public String getResourceKeyLike()
    {
        return resourceKeyLike;
    }

    public void setResourceKeyLike(String resourceKeyLike)
    {
        this.resourceKeyLike = resourceKeyLike;
    }

}
