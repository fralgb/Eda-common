package com.omniselling.app.base.web.model;

/**
 * 系统资源model
 * 
 * @author Administrator
 *
 */
public class ViewAppResourceCondition
{
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 模糊查询
     */
    private String resourceNameLike;
    /**
     * 应用程序名
     */
    private String applicationName;
    /**
     * 应用程序名 模糊
     */
    private String applicationNameLike;
    /**
     * 资源路径
     */
    private String resourceUrl;
    /**
     * 资源路径 模糊
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

    @Override
    public String toString()
    {
        return "ViewAppResourceCondition [resourceName=" + resourceName + ", resourceNameLike=" + resourceNameLike
                + ", applicationName=" + applicationName + ", applicationNameLike=" + applicationNameLike
                + ", resourceUrl=" + resourceUrl + ", resourceUrlLike=" + resourceUrlLike + ", resourceType="
                + resourceType + ", rowsPerPage=" + rowsPerPage + ", offset=" + offset + "]";
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

    public String getResourceUrlLike()
    {
        return resourceUrlLike;
    }

    public void setResourceUrlLike(String resourceUrlLike)
    {
        this.resourceUrlLike = resourceUrlLike;
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

    public String getApplicationName()
    {
        return applicationName;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public String getResourceUrl()
    {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl)
    {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

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

}
