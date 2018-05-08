package com.omniselling.app.base.web.model;

import java.util.List;

/**
 * 用户公共资源
 * 
 * @author Administrator
 *
 */
public class ViewAccountRefPubResource
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
     * 公共资源
     */
    private List<ViewPublicResource> publicResources;

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

    public List<ViewPublicResource> getPublicResources()
    {
        return publicResources;
    }

    public void setPublicResources(List<ViewPublicResource> publicResources)
    {
        this.publicResources = publicResources;
    }

    @Override
    public String toString()
    {
        return "ViewAccountRefPubResource [accountId=" + accountId + ", loginId=" + loginId + ", accountName="
                + accountName + ", publicResources=" + publicResources + "]";
    }

    
}
