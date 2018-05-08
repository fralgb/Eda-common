package com.omniselling.ms.core.model;

import com.omniselling.common.model.BasePageInfo;

/**
 * 用户角色查询条件
 *
 */
public class AccountRoleCond extends BasePageInfo
{
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色名称模糊查询
     */
    private String roleNameLike;
    /**
     * 角色状态
     */
    private String roleStatus;

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleNameLike()
    {
        return roleNameLike;
    }

    public void setRoleNameLike(String roleNameLike)
    {
        this.roleNameLike = roleNameLike;
    }

    public String getRoleStatus()
    {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus)
    {
        this.roleStatus = roleStatus;
    }

}
