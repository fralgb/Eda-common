package com.omniselling.ms.core.model;

import java.util.List;

/**
 * 账号信息
 * 用于业务层的实现
 * 
 * @author wlq
 *
 */
public class Account
{
    /**
     * 账号id
     * accountId
     */
    private Long id;
    /**
     * 登录id
     */
    private String loginId;
    /**
     * 账号名称
     */
    private String accountName;
    /**
     * 临时标志
     */
    private String nonceToken;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 账号类型
     */
    private String accountType;
    /**
     * 密码
     */
    private String password;
    /**
     * 加密后的密码
     */
    private String passwordHash;
    /**
     * 密码随机数
     */
    private String passwordSalt;
    /**
     * 账号状态
     */
    private String accountStatus;
    /**
     * 帐户状态标签
     */
    private String accountStatusLabel;
    /**
     * 邮件
     */
    private String email;
    /**
     * 手机号
     */
    private String mobileNumber;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 联系电话
     */
    private String contactNumber;
    /**
     * 传真
     */
    private String faxNumber;
    /**
     * QQ账号
     */
    private String qqId;
    /**
     * 站点链接
     */
    private String siteLink;
    /**
     * 微信账号
     */
    private String webchatId;
    /**
     * 账号对应的系统角色
     */
    public List<AccountRole> bizAccountRoles;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
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
    
    public String getNonceToken()
    {
        return nonceToken;
    }
    
    public void setNonceToken(String nonceToken)
    {
        this.nonceToken = nonceToken;
    }
    
    public Long getParentId()
    {
        return parentId;
    }
    
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }
    
    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }
    

    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getPasswordHash()
    {
        return passwordHash;
    }
    
    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }
    
    public String getPasswordSalt()
    {
        return passwordSalt;
    }
    
    public void setPasswordSalt(String passwordSalt)
    {
        this.passwordSalt = passwordSalt;
    }
    
    public String getAccountStatus()
    {
        return accountStatus;
    }
    
    public void setAccountStatus(String accountStatus)
    {
        this.accountStatus = accountStatus;
    }
    
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getMobileNumber()
    {
        return mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }
    
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public String getContactNumber()
    {
        return contactNumber;
    }
    
    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }
    
    public String getFaxNumber()
    {
        return faxNumber;
    }
    
    public void setFaxNumber(String faxNumber)
    {
        this.faxNumber = faxNumber;
    }
    
    public String getQqId()
    {
        return qqId;
    }
    
    public void setQqId(String qqId)
    {
        this.qqId = qqId;
    }
    
    public String getSiteLink()
    {
        return siteLink;
    }
    
    public void setSiteLink(String siteLink)
    {
        this.siteLink = siteLink;
    }
    
    public String getWebchatId()
    {
        return webchatId;
    }
    
    public void setWebchatId(String webchatId)
    {
        this.webchatId = webchatId;
    }
    
    public List<AccountRole> getBizAccountRoles()
    {
        return bizAccountRoles;
    }
    
    public void setBizAccountRoles(List<AccountRole> bizAccountRoles)
    {
        this.bizAccountRoles = bizAccountRoles;
    }

    public String getAccountStatusLabel()
    {
        return accountStatusLabel;
    }

    public void setAccountStatusLabel(String accountStatusLabel)
    {
        this.accountStatusLabel = accountStatusLabel;
    }
}
