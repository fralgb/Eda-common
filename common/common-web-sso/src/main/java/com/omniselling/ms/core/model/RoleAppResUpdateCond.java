package com.omniselling.ms.core.model;

import java.util.List;

/**
 * 角色应用资源更新
 * 
 *
 */
public class RoleAppResUpdateCond
{

    /**
     * 指定更新的角色id
     */
    private Long roleId;
    /**
     * 指定更新的资源id
     */
    private Long appResourceId;
    /**
     * 待更新的角色应用资源关系
     */
    private List<RoleAppResPermit> roleAppResPermits;

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getAppResourceId()
    {
        return appResourceId;
    }

    public void setAppResourceId(Long appResourceId)
    {
        this.appResourceId = appResourceId;
    }

    public List<RoleAppResPermit> getRoleAppResPermits()
    {
        return roleAppResPermits;
    }

    public void setRoleAppResPermits(List<RoleAppResPermit> roleAppResPermits)
    {
        this.roleAppResPermits = roleAppResPermits;
    }

}
