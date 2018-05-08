package com.omniselling.ms.core.model;

/**
 * 用户和角色关联关系
 *
 */
public class AccountRoleRelation
{
    /**
     * 用户id
     */
    private Long accountId;
    
    /**
     * 关联的角色id
     */
    private Long accountRoleId;
    
    /**
     * 关联角色名
     */
    private String accountRoleName;

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

    public String getAccountRoleName()
    {
        return accountRoleName;
    }

    public void setAccountRoleName(String accountRoleName)
    {
        this.accountRoleName = accountRoleName;
    }

}
