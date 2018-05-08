package com.omniselling.app.base.web.model;

/**
 * for in
 * 
 * @author huang huancheng
 *
 */
public class ViewAccountRoleCondition
{
    /**
     * 角色名称
     */
    private String roleName;

    private String roleNameLike;
    /**
     * 角色状态
     */
    private String roleStatus;

    private Integer offset;

    private Integer rowsPerPage;

    @Override
    public String toString()
    {
        return "ViewAccountRoleCondition [roleName=" + roleName + ", roleNameLike=" + roleNameLike + ", roleStatus="
                + roleStatus + ", offset=" + offset + ", rowsPerPage=" + rowsPerPage + "]";
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

    public String getRoleNameLike()
    {
        return roleNameLike;
    }

    public void setRoleNameLike(String roleNameLike)
    {
        this.roleNameLike = roleNameLike;
    }

    public void setRoleStatus(String roleStatus)
    {
        this.roleStatus = roleStatus;
    }

    public Integer getOffset()
    {
        return offset;
    }

    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }

    public Integer getRowsPerPage()
    {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rowsPerPage)
    {
        this.rowsPerPage = rowsPerPage;
    }

}
