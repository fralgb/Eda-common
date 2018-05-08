package com.omniselling.app.base.web.model;

import java.util.List;

public class ViewAccountRefAppResource
{
    /**
     * 账号id
     */
    private Long accountId;
    /**
     * 账号loginId
     */
    private String loginId;
    /**
     * 账号名称
     */
    private String accountName;
    /**
     * 权限类型 ALLOW， NOTALLOW
     */
    private String permitType;
    /**
     * 资源列表
     */
    private List<ViewAppResource> appResources;

    public Long getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getPermitType()
    {
        return permitType;
    }

    public void setPermitType(String permitType)
    {
        this.permitType = permitType;
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
