package com.omniselling.app.base.web.model;

import java.util.List;

/**
 * 账户关联资源表
 * 
 * @author Administrator
 *
 */
public class ViewAcountRefAppResource
{
    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 账户loginid
     */
    private String loginId;
    /**
     * 账户名称
     */
    private String accountName;
    /**
     * 资源权限
     */
    private String permitType;
    /**
     * 资源详情
     */
    private List<ViewAppResource> appResources;

    @Override
    public String toString()
    {
        return "ViewAcountRefAppResource [accountId=" + accountId + ", loginId=" + loginId + ", accountName="
                + accountName + ", permitType=" + permitType + ", appResources=" + appResources + "]";
    }

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
