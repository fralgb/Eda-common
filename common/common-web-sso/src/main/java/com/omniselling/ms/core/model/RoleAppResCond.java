package com.omniselling.ms.core.model;

import java.util.List;

import com.omniselling.common.model.BasePageInfo;

public class RoleAppResCond extends BasePageInfo
{
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色名称
     */
    private String roleNameLike;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源名称
     */
    private String resourceNameLike;
    /**
     * 应用资源名称(模糊)
     */
    private String applicationNameLike;
    /**
     * 应用资源名称
     */
    private String applicationName;
    /**
     * 资源路径
     */
    private String resourceUrl;
    /**
     * 资源路径
     */
    private String resourceUrlLike;
    /**
     * 资源类型
     */
    private List<String> resourceTypes;

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

    public List<String> getResourceTypes()
    {
        return resourceTypes;
    }

    public void setResourceTypes(List<String> resourceTypes)
    {
        this.resourceTypes = resourceTypes;
    }

    public String getApplicationNameLike()
    {
        return applicationNameLike;
    }

    public void setApplicationNameLike(String applicationNameLike)
    {
        this.applicationNameLike = applicationNameLike;
    }

    public String getApplicationName()
    {
        return applicationName;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    @Override
    public String toString()
    {
        return "RoleAppResCond [roleName=" + roleName + ", roleNameLike=" + roleNameLike + ", resourceName="
                + resourceName + ", resourceNameLike=" + resourceNameLike + ", applicationNameLike="
                + applicationNameLike + ", applicationName=" + applicationName + ", resourceUrl=" + resourceUrl
                + ", resourceUrlLike=" + resourceUrlLike + ", resourceTypes=" + resourceTypes + "]";
    }

}
