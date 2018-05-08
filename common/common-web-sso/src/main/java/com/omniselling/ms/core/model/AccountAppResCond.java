package com.omniselling.ms.core.model;

import java.util.List;

import com.omniselling.common.model.BasePageInfo;

public class AccountAppResCond extends BasePageInfo
{
    /**
     * 账号id
     */
    private List<Long> accountIds;

    /**
     * 账号loginid
     */
    private String loginId;

    /**
     * 账号loginid模糊查询
     */
    private String loginIdLike;

    /**
     * 账号名称
     */
    private String accountName;

    /**
     * 账号名称模糊查询
     */
    private String accountNameLike;

    /**
     * 应用系统资源id
     */
    private List<Long> resourceIds;

    /**
     * 系统应用名称
     */
    private String applicationName;

    /**
     * 系统应用名称模糊查询
     */
    private String applicationNameLike;

    /**
     * 系统应用资源类型集合
     */
    private List<String> resourceTypes;

    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源名称模糊查询
     */
    private String resourceNameLike;
    /**
     * 资源路径
     */
    private String resourceUrl;
    /**
     * 资源路径模糊查询
     */
    private String resourceUrlLike;
    /**
     * 资源权限
     */
    private String permitType;

    public List<Long> getAccountIds()
    {
        return accountIds;
    }

    public void setAccountIds(List<Long> accountIds)
    {
        this.accountIds = accountIds;
    }

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    public String getPermitType()
    {
        return permitType;
    }

    public void setPermitType(String permitType)
    {
        this.permitType = permitType;
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

    public List<Long> getResourceIds()
    {
        return resourceIds;
    }

    public void setResourceIds(List<Long> resourceIds)
    {
        this.resourceIds = resourceIds;
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

    public List<String> getResourceTypes()
    {
        return resourceTypes;
    }

    public void setResourceTypes(List<String> resourceTypes)
    {
        this.resourceTypes = resourceTypes;
    }

    @Override
    public String toString()
    {
        return "AccountAppRes [accountIds=" + accountIds + ", loginId=" + loginId + ", loginIdLike=" + loginIdLike
                + ", accountName=" + accountName + ", accountNameLike=" + accountNameLike + ", resourceIds="
                + resourceIds + ", applicationName=" + applicationName + ", applicationNameLike=" + applicationNameLike
                + ", resourceTypes=" + resourceTypes + ", resourceName=" + resourceName + ", resourceNameLike="
                + resourceNameLike + ", resourceUrl=" + resourceUrl + ", resourceUrlLike=" + resourceUrlLike
                + ", permitType=" + permitType + "]";
    }

}
