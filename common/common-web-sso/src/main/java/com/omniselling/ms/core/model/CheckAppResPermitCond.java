package com.omniselling.ms.core.model;

/**
 * 应用系统资源权限检查条件
 * @author user
 *
 */
public class CheckAppResPermitCond
{

    /**
     * 账号id
     */
    private Long accountId;
    
    /**
     * 角色id
     */
    private Long accountRoleId;
    
    /**
     * 应用系统名称
     */
    private String applicationName;
    
    /**
     * 资源名称
     */
    private String resourceName;
    
    /**
     * 应用系统资源类型
     */
    private String resourceType;

    public Long getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

    public Long getAccountRoleId()
    {
        return accountRoleId;
    }

    public void setAccountRoleId(Long accountRoleId)
    {
        this.accountRoleId = accountRoleId;
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

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }
    
}
