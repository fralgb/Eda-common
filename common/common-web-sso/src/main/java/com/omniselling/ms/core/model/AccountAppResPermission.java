package com.omniselling.ms.core.model;

/**
 * 账号应用系统资源权限
 * 
 * @author Administrator
 *
 */
public class AccountAppResPermission
{
    /**
     * 资源权限id
     */
    private Long id;
    /**
     * 账号id
     */
    private Long accountId;
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 菜单资源类型id
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
     * 资源组
     */
    private String resourceGroup;
    /**
     * 权限类型
     */
    private String permitType;
    /**
     * 资源标签
     */
    private String resourceLabel;
    /**
     * 资源路径
     */
    private String resourceUrl;
    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 账号信息
     */
    private String loginId;

    /**
     * enum 账户类型 AccountType 主账号 MAIN, 子账号 , SUB
     */
    private String accountType;
    /**
     * 账户名称
     */
    private String accountName;
    /**
     * 账户父编号
     */
    private Long accountParentId;
    /**
     * 资源排列次序
     */
    private Integer resourceOrder;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getAccountId()
    {
        return accountId;
    }

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public Long getAccountParentId()
    {
        return accountParentId;
    }

    public void setAccountParentId(Long accountParentId)
    {
        this.accountParentId = accountParentId;
    }

    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
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

    public String getPermitType()
    {
        return permitType;
    }

    public void setPermitType(String permitType)
    {
        this.permitType = permitType;
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

    public String getResourceTypeLabel()
    {
        return resourceTypeLabel;
    }

    public void setResourceTypeLabel(String resourceTypeLabel)
    {
        this.resourceTypeLabel = resourceTypeLabel;
    }

}