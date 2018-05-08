package com.omniselling.ms.core.model;

import java.util.List;

/**
 * 账号应用资源更新
 * 
 *
 */
public class AccountAppResUpdateCond
{

    /**
     * 指定更新的账号id
     */
    private Long accountId;
    /**
     * 指定更新的资源id
     */
    private Long appResourceId;
    /**
     * 待更新的账号应用资源关系
     */
    private List<AccountAppResPermit> accountAppResPermits;

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

    public List<AccountAppResPermit> getAccountAppResPermits()
    {
        return accountAppResPermits;
    }

    public void setAccountAppResPermits(List<AccountAppResPermit> accountAppResPermits)
    {
        this.accountAppResPermits = accountAppResPermits;
    }

}
