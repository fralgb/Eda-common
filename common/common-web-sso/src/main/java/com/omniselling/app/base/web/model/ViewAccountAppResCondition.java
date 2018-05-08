package com.omniselling.app.base.web.model;

/**
 * 账户资源权限 model
 * 
 * @author Administrator
 *
 */
public class ViewAccountAppResCondition
{
    /**
     * 账户 loginid
     */
    private String loginId;
    /**
     * 账户 loginid (模糊)
     */
    private String loginIdLike;
    /**
     * 账户名
     */
    private String accountName;
    /**
     * 账户名(模糊)
     */
    private String accountNameLike;
    /**
     * 应用资源名
     */
    private String applicationName;
    /**
     * 应用资源名(模糊)
     */
    private String applicationNameLike;
    /**
     * 资源名
     */
    private String resourceName;
    /**
     * 资源名 (模糊)
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
     * 资源权限
     */
    private String permitType;

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
        return "ViewAccountAppResCondition [loginId=" + loginId + ", loginIdLike=" + loginIdLike + ", accountName="
                + accountName + ", accountNameLike=" + accountNameLike + ", applicationName=" + applicationName
                + ", applicationNameLike=" + applicationNameLike + ", resourceName=" + resourceName
                + ", resourceNameLike=" + resourceNameLike + ", resourceUrl=" + resourceUrl + ", resourceUrlLike="
                + resourceUrlLike + ", resourceType=" + resourceType + ", permitType=" + permitType + ", rowsPerPage="
                + rowsPerPage + ", offset=" + offset + "]";
    }

    public Integer getRowsPerPage()
    {
        return rowsPerPage;
    }

    public String getPermitType()
    {
        return permitType;
    }

    public void setPermitType(String permitType)
    {
        this.permitType = permitType;
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

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    public String getLoginIdLike()
    {
        return loginIdLike;
    }

    public void setLoginIdLike(String loginIdLike)
    {
        this.loginIdLike = loginIdLike;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getAccountNameLike()
    {
        return accountNameLike;
    }

    public void setAccountNameLike(String accountNameLike)
    {
        this.accountNameLike = accountNameLike;
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
