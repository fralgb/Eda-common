package com.omniselling.app.base.web.model;

import java.util.List;

/**
 * @author log
 * @version 1.0
 */
public class ViewSecAccount
{

    /**
     * 账户角色，对应多个角色
     */
    private List<ViewAccountRole> accountRoles;
    /**
     * 黑名单资源信息
     */
    private List<ViewAppResource> blackResAccess;
    /**
     * 账号登录id
     */
    private String loginId;
    /**
     * 白名单资源信息
     */
    private List<ViewAppResource> whiteResAccess;

    public ViewSecAccount()
    {

    }

    public List<ViewAccountRole> getAccountRoles()
    {
        return accountRoles;
    }

    public void setAccountRoles(List<ViewAccountRole> accountRoles)
    {
        this.accountRoles = accountRoles;
    }

    public List<ViewAppResource> getBlackResAccess()
    {
        return blackResAccess;
    }

    public void setBlackResAccess(List<ViewAppResource> blackResAccess)
    {
        this.blackResAccess = blackResAccess;
    }

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    public List<ViewAppResource> getWhiteResAccess()
    {
        return whiteResAccess;
    }

    public void setWhiteResAccess(List<ViewAppResource> whiteResAccess)
    {
        this.whiteResAccess = whiteResAccess;
    }

}