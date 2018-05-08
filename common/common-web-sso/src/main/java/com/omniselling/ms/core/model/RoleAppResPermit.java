package com.omniselling.ms.core.model;

/**
 * 角色应用资源权限
 *
 */
public class RoleAppResPermit
{
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 应用资源id
     */
    private Long appResourceId;
    /**
     * 权限类型
     */
    private String permitType;

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

    public String getPermitType()
    {
        return permitType;
    }

    public void setPermitType(String permitType)
    {
        this.permitType = permitType;
    }
}
