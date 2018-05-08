package com.omniselling.ms.core.model;

import java.util.List;

/**
 * 角色id和name
 *
 */
public class AccountRoleIdNameCond
{
    /**
     * 角色id列表
     */
    private List<Long> accountRoleIds;
    
    /**
     * 角色名列表
     */
    private List<String> accountRoleNames;


    public List<Long> getAccountRoleIds()
    {
        return accountRoleIds;
    }

    public void setAccountRoleIds(List<Long> accountRoleIds)
    {
        this.accountRoleIds = accountRoleIds;
    }

    public List<String> getAccountRoleNames()
    {
        return accountRoleNames;
    }

    public void setAccountRoleNames(List<String> accountRoleNames)
    {
        this.accountRoleNames = accountRoleNames;
    }

}
