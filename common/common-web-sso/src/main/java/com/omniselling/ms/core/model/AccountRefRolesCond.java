package com.omniselling.ms.core.model;

import java.util.List;

/**
 * 用户与角色关联
 *
 */
public class AccountRefRolesCond
{
    /**
     * 用户id
     */
    private Long accountId;
    
    /**
     * 关联的角色id列表
     */
    private List<Long> accountRoleIds;
    
    /**
     * 关联角色名列表
     */
    private List<String> accountRoleNames;

    public Long getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

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
