package com.omniselling.ms.core.model;

import java.util.List;

/**
 * 角色与用户关联
 *
 */
public class RoleRefAccountsCond
{ 
    /**
     * 角色id
     */
    private Long accountRoleId;
    
    /**
     * 关联角色名列表
     */
    private String roleName;
    
    /**
     * 用户id列表
     */
    private List<Long> accountIds;

    public Long getAccountRoleId()
    {
        return accountRoleId;
    }

    public void setAccountRoleId(Long accountRoleId)
    {
        this.accountRoleId = accountRoleId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public List<Long> getAccountIds()
    {
        return accountIds;
    }

    public void setAccountIds(List<Long> accountIds)
    {
        this.accountIds = accountIds;
    }

}
