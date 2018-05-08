package com.omniselling.app.base.web.model;

import java.util.List;

public class ViewAccountRoleAccess
{
    private Long accountId;
    /**
     * 账户角色
     */
    private List<ViewAccountRole> accountroles;
    /**
     * 白名单资源信息
     */
    private List<ViewAppResource> whiteResAccess;
    /**
     * 黑名单资源信息
     */
    private List<ViewAppResource> blackResAccess;

    @Override
    public String toString()
    {
        return "ViewAccountRoleAccess [accountId=" + accountId + ", accountroles=" + accountroles + ", whiteResAccess="
                + whiteResAccess + ", blackResAccess=" + blackResAccess + "]";
    }

    public Long getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

    public List<ViewAccountRole> getAccountroles()
    {
        return accountroles;
    }

    public void setAccountroles(List<ViewAccountRole> accountroles)
    {
        this.accountroles = accountroles;
    }

    public List<ViewAppResource> getWhiteResAccess()
    {
        return whiteResAccess;
    }

    public void setWhiteResAccess(List<ViewAppResource> whiteResAccess)
    {
        this.whiteResAccess = whiteResAccess;
    }

    public List<ViewAppResource> getBlackResAccess()
    {
        return blackResAccess;
    }

    public void setBlackResAccess(List<ViewAppResource> blackResAccess)
    {
        this.blackResAccess = blackResAccess;
    }

}
