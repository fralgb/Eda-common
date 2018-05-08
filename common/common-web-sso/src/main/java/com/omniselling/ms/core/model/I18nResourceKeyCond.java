package com.omniselling.ms.core.model;


import com.omniselling.common.model.BasePageInfo;

/**
 * 国际化资源信息查询条件
 *
 */
public class I18nResourceKeyCond extends BasePageInfo
{
    /**
     * 应用系统名
     */
    private String applicationName;
    /**
     * 应用系统名模糊查询
     */
    private String applicationNameLike;
    /**
     * 资源名
     */
    private String resourceKey;
    /**
     * 资源名模糊查询
     */
    private String resourceKeyLike;

    public String getApplicationName()
    {
        return applicationName;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public String getApplicationNameLike()
    {
        return applicationNameLike;
    }

    public void setApplicationNameLike(String applicationNameLike)
    {
        this.applicationNameLike = applicationNameLike;
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

    @Override
    public String toString()
    {
        return "I18nResourceKeyCond [applicationName=" + applicationName + ", applicationNameLike="
                + applicationNameLike + ", resourceKey=" + resourceKey + ", resourceKeyLike=" + resourceKeyLike + "]";
    }

}
