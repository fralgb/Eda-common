package com.omniselling.ms.core.model;

import com.omniselling.common.model.BasePageInfo;

/**
 * biz 应用资源条件 model
 * 
 * @author Administrator
 *
 */
public class AppResourceCond extends BasePageInfo
{
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 应用程序名 模糊
     */
    private String applicationNameLike;
    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 相似资源
     */
    private String resourceNameLike;

    /**
     * 资源类型
     */
    private String resourceType;

    /**
     * 资源分组
     */
    private String resourceGroup;

    /**
     * 父级
     */
    private Long parentId;

    /**
     * 资源路劲
     */
    private String resourceUrl;

    /**
     * 相似资源
     */
    private String resourceUrlLike;

    @Override
    public String toString()
    {
        return "AppResourceCond [applicationName=" + applicationName + ", applicationNameLike=" + applicationNameLike
                + ", resourceName=" + resourceName + ", resourceNameLike=" + resourceNameLike + ", resourceType="
                + resourceType + ", resourceGroup=" + resourceGroup + ", parentId=" + parentId + ", resourceUrl="
                + resourceUrl + ", resourceUrlLike=" + resourceUrlLike + "]";
    }

    public String getApplicationNameLike()
    {
        return applicationNameLike;
    }

    public void setApplicationNameLike(String applicationNameLike)
    {
        this.applicationNameLike = applicationNameLike;
    }

    public String getApplicationName()
    {
        return applicationName;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getResourceNameLike()
    {
        return resourceNameLike;
    }

    public void setResourceNameLike(String resourceNameLike)
    {
        this.resourceNameLike = resourceNameLike;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    public String getResourceGroup()
    {
        return resourceGroup;
    }

    public void setResourceGroup(String resourceGroup)
    {
        this.resourceGroup = resourceGroup;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getResourceUrl()
    {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl)
    {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceUrlLike()
    {
        return resourceUrlLike;
    }

    public void setResourceUrlLike(String resourceUrlLike)
    {
        this.resourceUrlLike = resourceUrlLike;
    }
}
