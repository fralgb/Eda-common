package com.omniselling.ms.core.model;

/**
 * 账号系统角色类
 * 
 * @author user
 * @version 1.0
 * @created 19-11-2015 19:44:20
 */
public class AccountRole
{
    /**
     * 账号角色id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色标签
     */
    private String roleLabel;
    /**
     * 角色状态
     */
    private String roleStatus;
    /**
     * 角色状态标签
     */
    private String roleStatusLabel;
    /**
     * 角色简介
     */
    private String roleDescription;

    @Override
    public String toString()
    {
        return "AccountRole [id=" + id + ", roleName=" + roleName + ", roleLabel=" + roleLabel + ", roleStatus="
                + roleStatus + ", roleStatusLabel=" + roleStatusLabel + ", roleDescription=" + roleDescription + "]";
    }

    public String getRoleStatusLabel()
    {
        return roleStatusLabel;
    }

    public void setRoleStatusLabel(String roleStatusLabel)
    {
        this.roleStatusLabel = roleStatusLabel;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getRoleDescription()
    {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription)
    {
        this.roleDescription = roleDescription;
    }

    public String getRoleLabel()
    {
        return roleLabel;
    }

    public void setRoleLabel(String roleLabel)
    {
        this.roleLabel = roleLabel;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
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