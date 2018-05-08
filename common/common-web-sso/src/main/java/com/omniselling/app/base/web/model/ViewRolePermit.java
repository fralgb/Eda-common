package com.omniselling.app.base.web.model;

import java.util.List;

/**
 * 角色权限
 * 
 * @author baby
 *
 */
public class ViewRolePermit
{
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 名称
     */
    private String roleName;
    /**
     * 显示
     */
    private String roleLabel;
    private List<ViewAppResource> appResources;

    @Override
    public String toString()
    {
        return "ViewRolePermit [roleId=" + roleId + ", roleName=" + roleName + ", roleLabel=" + roleLabel
                + ", appResources=" + appResources + "]";
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleLabel()
    {
        return roleLabel;
    }

    public void setRoleLabel(String roleLabel)
    {
        this.roleLabel = roleLabel;
    }

    public List<ViewAppResource> getAppResources()
    {
        return appResources;
    }

    public void setAppResources(List<ViewAppResource> appResources)
    {
        this.appResources = appResources;
    }

}
