package com.omniselling.app.base.web.model;

/**
 * 用户条件
 * 
 * @author Administrator
 *
 */
public class ViewAccountCondition
{
    /**
     * login id for in
     */
    private String loginId;
    /**
     * loginLike id for in
     */
    private String loginIdLike;
    /**
     * 帐户名称 for in
     */
    private String accountName;
    /**
     * 帐户名称 for in
     */
    private String accountNameLike;
    /**
     * email for in
     */
    private String email;

    /**
     * email关键字
     */
    private String emailLike;
    /**
     * 账号状态for in
     */
    private String accountStatus;
    /**
     * 账号类型
     */
    private String accountType;
    /**
     * 账号类型(模糊)
     */
    private String accountTypeLike;
    /**
     * 帐户角色 for in
     */
    private String accountRole;
    /**
     * 分页
     */
    private Integer offset;
    private Integer rowsPerPage;

    /**
     * 对于子账号存在parentId
     */
    private Long parentId;

    @Override
    public String toString()
    {
        return "ViewAccountCondition [loginId=" + loginId + ", loginIdLike=" + loginIdLike + ", accountName="
                + accountName + ", accountNameLike=" + accountNameLike + ", email=" + email + ", accountStatus="
                + accountStatus + ", accountType=" + accountType + ", accountTypeLike=" + accountTypeLike
                + ", accountRole=" + accountRole + ", offset=" + offset + ", rowsPerPage=" + rowsPerPage + ", parentId="
                + parentId + "]";
    }

    public String getEmailLike()
    {
        return emailLike;
    }

    public void setEmailLike(String emailLike)
    {
        this.emailLike = emailLike;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
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

    public String getAccountNameLike()
    {
        return accountNameLike;
    }

    public void setAccountNameLike(String accountNameLike)
    {
        this.accountNameLike = accountNameLike;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAccountStatus()
    {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus)
    {
        this.accountStatus = accountStatus;
    }

    public String getAccountRole()
    {
        return accountRole;
    }

    public void setAccountRole(String accountRole)
    {
        this.accountRole = accountRole;
    }

    public Integer getOffset()
    {
        return offset;
    }

    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }

    public Integer getRowsPerPage()
    {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rowsPerPage)
    {
        this.rowsPerPage = rowsPerPage;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public String getAccountTypeLike()
    {
        return accountTypeLike;
    }

    public void setAccountTypeLike(String accountTypeLike)
    {
        this.accountTypeLike = accountTypeLike;
    }

    public String getLoginIdLike()
    {
        return loginIdLike;
    }

    public void setLoginIdLike(String loginIdLike)
    {
        this.loginIdLike = loginIdLike;
    }

}
