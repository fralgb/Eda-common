package com.omniselling.ms.core.model;

/**
 * 角色应用系统资源权限
 * 
 * @author huanghuancheng
 * @version 1.0
 * @created 19-11-2015 19:44:30
 */
public class RoleAppResPermission
{
    /**
     * 权限id
     */
    private Long id;
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 应用系统资源id
     */
    private Long appResourceId;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源类型
     */
    private String resourceType;
    /**
     * 资源类型标签
     */
    private String resourceTypeLabel;
    /**
     * 资源分组
     */
    private String resourceGroup;
    /**
     * 角色id
     */
    private Long accountRoleId;
    /**
     * 角色名称
     */
    private String accountRoleName;
    /**
     * 角色标签
     */
    private String accountRoleLabel;
    /**
     * 资源标签
     */
    private String resourceLabel;
    /**
     * 权限类型
     */
    private String permitType;
    /**
     * 资源路径
     */
    private String resourceUrl;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 资源排列次序
     */
    private Integer resourceOrder;

    @Override
    public String toString()
    {
        return "RoleAppResPermission [id=" + id + ", applicationName=" + applicationName + ", appResourceId="
                + appResourceId + ", resourceName=" + resourceName + ", resourceType=" + resourceType
                + ", resourceTypeLabel=" + resourceTypeLabel + ", resourceGroup=" + resourceGroup + ", accountRoleId="
                + accountRoleId + ", accountRoleName=" + accountRoleName + ", accountRoleLabel=" + accountRoleLabel
                + ", resourceLabel=" + resourceLabel + ", permitType=" + permitType + ", resourceUrl=" + resourceUrl
                + ", parentId=" + parentId + ", resourceOrder=" + resourceOrder + "]";
    }

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

    public Long getAppResourceId()
    {
        return appResourceId;
    }

    public void setAppResourceId(Long appResourceId)
    {
        this.appResourceId = appResourceId;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
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

    public Long getAccountRoleId()
    {
        return accountRoleId;
    }

    public void setAccountRoleId(Long accountRoleId)
    {
        this.accountRoleId = accountRoleId;
    }

    public String getAccountRoleName()
    {
        return accountRoleName;
    }

    public void setAccountRoleName(String accountRoleName)
    {
        this.accountRoleName = accountRoleName;
    }

    public String getResourceLabel()
    {
        return resourceLabel;
    }

    public void setResourceLabel(String resourceLabel)
    {
        this.resourceLabel = resourceLabel;
    }

    public String getPermitType()
    {
        return permitType;
    }

    public void setPermitType(String permitType)
    {
        this.permitType = permitType;
    }

    public String getResourceUrl()
    {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl)
    {
        this.resourceUrl = resourceUrl;
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

    public String getAccountRoleLabel()
    {
        return accountRoleLabel;
    }

    public void setAccountRoleLabel(String accountRoleLabel)
    {
        this.accountRoleLabel = accountRoleLabel;
    }

}