package com.omniselling.app.base.web.model;

/**
 * 
 * 角色应用系统资源关系信息
 */
public class ViewRoleAppResource
{

    private Long id;
    /**
     * 角色id
     */
    private Long accountRoleId;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色名国际化
     */
    private String roleLabel;
    /**
     * 应用系统名
     */
    private String applicationName;
    /**
     * 应用系统资源id
     */
    private Long appResourceId;
    /**
     * 资源名
     */
    private String resourceName;
    /**
     * 资源名国际化
     */
    private String resourceLabel;
    /**
     * 资源url
     */
    private String resourceUrl;
    /**
     * 资源类型
     */
    private String resourceType;
    /**
     * 资源类型标签
     */
    private String resourceTypeLabel;
    /**
     * 资源组
     */
    private String resourceGroup;
    /**
     * 资源次序
     */
    private Integer resourceOrder;
    /**
     * 资源权限
     */
    private String permitType;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getAccountRoleId()
    {
        return accountRoleId;
    }

    public void setAccountRoleId(Long accountRoleId)
    {
        this.accountRoleId = accountRoleId;
    }

    public String getPermitType()
    {
        return permitType;
    }

    public void setPermitType(String permitType)
    {
        this.permitType = permitType;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleLabel()
    {
        return roleLabel;
    }

    public void setRoleLabel(String roleLabel)
    {
        this.roleLabel = roleLabel;
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

    public String getResourceLabel()
    {
        return resourceLabel;
    }

    public void setResourceLabel(String resourceLabel)
    {
        this.resourceLabel = resourceLabel;
    }

    public String getResourceUrl()
    {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl)
    {
        this.resourceUrl = resourceUrl;
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

    public Integer getResourceOrder()
    {
        return resourceOrder;
    }

    public void setResourceOrder(Integer resourceOrder)
    {
        this.resourceOrder = resourceOrder;
    }

    public String getResourceTypeLabel()
    {
        return resourceTypeLabel;
    }

    public void setResourceTypeLabel(String resourceTypeLabel)
    {
        this.resourceTypeLabel = resourceTypeLabel;
    }

}
