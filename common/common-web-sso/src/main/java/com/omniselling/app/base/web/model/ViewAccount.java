package com.omniselling.app.base.web.model;

import java.util.List;

import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.enumeration.MsAppErrorCode;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.common.util.StringUtils;

/**
 * 用户model
 * @author Administrator
 */
public class ViewAccount
{
    /**
     * 帐户id
     */
    private Long accountId;
    /**
     * 登录id
     */
    private String loginId;
    /**
     * 帐户名称
     */
    private String accountName;
    /**
     * email
     */
    private String email;
    /**
     * 帐户状态
     */
    private String accountStatus;
    /**
     * 帐户状态标签
     */
    private String accountStatusLabel;
    /**
     * 帐户上级id
     */
    private Long parentId;
    /**
     * 帐户密码
     */
    private String password;

    private String accountType;
    /**
     * 帐户角色 对应多个角色
     */
    private List<ViewAccountRole> accountRoles;
    /**
     * 定义参数长度
     */
    private static int[] PARAM_LENGTH= {30,100,255,1000};
    
    @Override
    public String toString()
    {
        return "ViewAccount [accountId=" + accountId + ", loginId=" + loginId + ", accountName=" + accountName
                + ", email=" + email + ", accountStatus=" + accountStatus + ", accountStatusLabel="
                + accountStatusLabel + ", accountRoles=" + accountRoles + ", parentId=" + parentId + ", password="
                + password + "]";
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

    public String getAccountStatusLabel()
    {
        return accountStatusLabel;
    }

    public void setAccountStatusLabel(String accountStatusLabel)
    {
        this.accountStatusLabel = accountStatusLabel;
    }

    public List<ViewAccountRole> getAccountRoles()
    {
        return accountRoles;
    }

    public void setAccountRoles(List<ViewAccountRole> accountRoles)
    {
        this.accountRoles = accountRoles;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }
    public BaseResponse<Boolean> validate(String language)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<>();
        if (StringUtils.isEmpty(loginId))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNT_LOGINID_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (loginId.length() > PARAM_LENGTH[1])
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNT_LOGINID_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (StringUtils.isEmpty(accountName))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNT_ACCOUNTNAME_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (accountName.length() > PARAM_LENGTH[3])
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNT_ACCOUNTNAME_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (!StringUtils.isEmpty(password))
        {
            if (password.length() > PARAM_LENGTH[0])
            {
                baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNT_PASSWORD_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                return baseRes;
            }
        }
        if (StringUtils.isEmpty(email))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNT_EMAIL_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (email.length() > PARAM_LENGTH[2])
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNT_EMAIL_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        baseRes.setData(true);
        return baseRes;
    }
}
