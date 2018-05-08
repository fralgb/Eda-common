package com.omniselling.ms.core.model;

import java.util.List;

/**
 * 应用系统资源
 * 
 * @author Administrator
 *
 */
public class AppResource
{
    /**
     * 应用系统资源id
     */
    private Long id;
    /**
     * 应用系统名称
     */
    private String applicationName;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源标签
     */
    private String resourceLabel;
    /**
     * 资源类型
     */
    private String resourceType;
    /**
     * 资源类型标签
     */
    private String resourceTypeLabel;
    /**
     * 资源路径
     */
    private String resourceUrl;
    /**
     * 资源组
     */
    private String resourceGroup;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 资源排列次序
     */
    private Integer resourceOrder;
    /**
     * 子资源信息
     */
    private List<AppResource> subResources;

    public String getResourceTypeLabel()
    {
        return resourceTypeLabel;
    }

    public void setResourceTypeLabel(String resourceTypeLabel)
    {
        this.resourceTypeLabel = resourceTypeLabel;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public String getResourceLabel()
    {
        return resourceLabel;
    }

    public void setResourceLabel(String resourceLabel)
    {
        this.resourceLabel = resourceLabel;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    public String getResourceUrl()
    {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl)
    {
        this.resourceUrl = resourceUrl;
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

    public Integer getResourceOrder()
    {
        return resourceOrder;
    }

    public void setResourceOrder(Integer resourceOrder)
    {
        this.resourceOrder = resourceOrder;
    }

    public List<AppResource> getSubResources()
    {
        return subResources;
    }

    public void setSubResources(List<AppResource> subResources)
    {
        this.subResources = subResources;
    }

    @Override
    public String toString()
    {
        return "AppResource [id=" + id + ", applicationName=" + applicationName + ", resourceName=" + resourceName
                + ", resourceLabel=" + resourceLabel + ", resourceType=" + resourceType + ", resourceTypeLabel="
                + resourceTypeLabel + ", resourceUrl=" + resourceUrl + ", resourceGroup=" + resourceGroup
                + ", parentId=" + parentId + ", resourceOrder=" + resourceOrder + ", subResources=" + subResources
                + "]";
    }
}