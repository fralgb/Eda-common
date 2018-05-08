package com.omniselling.app.base.web.model;

/**
 * 
 * 账号应用系统资源关系信息
 */
public class ViewAccountAppResource
{

    private Long id;
    /**
     * 账号id
     */
    private Long accountId;
    /**
     * 账号loginId
     */
    private String loginId;
    /**
     * 账号名称
     */
    private String accountName;
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
     * 关联权限类型
     */
    private String permitType;

    /**
     * 账号和应用资源绑定的id
     */
    private String accAppResRelId;

    public String getAccAppResRelId()
    {
        return accAppResRelId;
    }

    public void setAccAppResRelId(String accAppResRelId)
    {
        this.accAppResRelId = accAppResRelId;
    }

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

    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
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

    public String getPermitType()
    {
        return permitType;
    }

    public void setPermitType(String permitType)
    {
        this.permitType = permitType;
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
