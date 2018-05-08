package com.omniselling.app.base.web.model;

/**
 * 角色资源权限 model
 * 
 * @author Administrator
 *
 */
public class ViewRoleAppResCondition
{
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色名称(模糊)
     */
    private String roleNameLike;
    /**
     * 应用资源名称
     */
    private String applicationName;
    /**
     * 应用资源名称(模糊)
     */
    private String applicationNameLike;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源名称(模糊)
     */
    private String resourceNameLike;
    /**
     * 资源路径
     */
    private String resourceUrl;
    /**
     * 资源路径(模糊)
     */
    private String resourceUrlLike;
    /**
     * 资源类型
     */
    private String resourceType;
    /**
     * 账号配置的每一页记录数
     */
    private Integer rowsPerPage;
    /**
     * 开始记录数
     */
    private Integer offset;

    public Integer getRowsPerPage()
    {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rowsPerPage)
    {
        this.rowsPerPage = rowsPerPage;
    }

    public Integer getOffset()
    {
        return offset;
    }

    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }

    @Override
    public String toString()
    {
        return "ViewRoleAppResCondition [roleName=" + roleName + ", roleNameLike=" + roleNameLike
                + ", applicationName=" + applicationName + ", applicationNameLike=" + applicationNameLike
                + ", resourceName=" + resourceName + ", resourceNameLike=" + resourceNameLike + ", resourceUrl="
                + resourceUrl + ", resourceUrlLike=" + resourceUrlLike + ", resourceType=" + resourceType
                + ", rowsPerPage=" + rowsPerPage + ", offset=" + offset + "]";
    }

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

    public String getApplicationName()
    {
        return applicationName;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public String getApplicationNameLike()
    {
        return applicationNameLike;
    }

    public void setApplicationNameLike(String applicationNameLike)
    {
        this.applicationNameLike = applicationNameLike;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getResourceNameLike()
    {
        return resourceNameLike;
    }

    public void setResourceNameLike(String resourceNameLike)
    {
        this.resourceNameLike = resourceNameLike;
    }

    public String getResourceUrl()
    {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl)
    {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceUrlLike()
    {
        return resourceUrlLike;
    }

    public void setResourceUrlLike(String resourceUrlLike)
    {
        this.resourceUrlLike = resourceUrlLike;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

}
