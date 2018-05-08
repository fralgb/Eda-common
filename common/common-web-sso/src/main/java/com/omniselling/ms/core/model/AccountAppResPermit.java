package com.omniselling.ms.core.model;

/**
 * 账号应用资源权限
 *
 */
public class AccountAppResPermit
{
    /**
     * 账号id
     */
    private Long accountId;
    /**
     * 应用资源id
     */
    private Long appResourceId;
    /**
     * 权限类型
     */
    private String permitType;

    public Long getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

    public Long getAppResourceId()
    {
        return appResourceId;
    }

    public void setAppResourceId(Long appResourceId)
    {
        this.appResourceId = appResourceId;
    }

    public String getPermitType()
    {
        return permitType;
    }

    public void setPermitType(String permitType)
    {
        this.permitType = permitType;
    }
}
