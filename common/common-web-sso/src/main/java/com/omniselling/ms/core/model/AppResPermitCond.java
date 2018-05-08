package com.omniselling.ms.core.model;

import java.util.List;

/**
 * 账号、角色、系统资源权限关联
 * @author user
 *
 */
public class AppResPermitCond
{

    /**
     * 账号id列表
     */
    private List<Long> accountIds;
    
    /**
     * 角色id列表
     */
    private List<Long> accountRoleIds;
    
    /**
     * 应用系统资源id列表
     */
    private List<Long> appResourceIds;
    
    /**
     * 资源权限类型
     * ALLOW， NOTALLOW
     */
    private String permitType;

    public List<Long> getAccountIds()
    {
        return accountIds;
    }

    public void setAccountIds(List<Long> accountIds)
    {
        this.accountIds = accountIds;
    }

    public List<Long> getAccountRoleIds()
    {
        return accountRoleIds;
    }

    public void setAccountRoleIds(List<Long> accountRoleIds)
    {
        this.accountRoleIds = accountRoleIds;
    }

    public List<Long> getAppResourceIds()
    {
        return appResourceIds;
    }

    public void setAppResourceId(List<Long> appResourceIds)
    {
        this.appResourceIds = appResourceIds;
    }

    public String getPermitType()
    {
        return permitType;
    }

    public void setPermitType(String permitType)
    {
        this.permitType = permitType;
    }

}
