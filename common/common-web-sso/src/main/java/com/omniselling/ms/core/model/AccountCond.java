package com.omniselling.ms.core.model;

import com.omniselling.common.model.BasePageInfo;

public class AccountCond extends BasePageInfo
{
    //账号id
    private String loginId;
    //同上，用于模糊查询
    private String loginIdLike;
    //账户类型
    private String accountType;
    //账户名称
    private String accountName;
    //同上，用于模糊查询
    private String accountNameLike;
    //父id
    private Long parentId;
    //手机号码
    private String mobileNumber;
    //同上
    private String mobileNumberLike;
    //电话号码
    private String phoneNumber;
    //同上
    private String phoneNumberLike;
    //联系号码
    private String contactNumber;
    //同上
    private String contactNumberLike;
    //传真
    private String faxNumber;
    //同上
    private String faxNumberLike;
    //邮箱
    private String email;
    //同上
    private String emailLike;
    //链接
    private String siteLink;
    //微信号
    private String webchatId;
    //同上
    private String webchatIdLike;
    //qq号码
    private String qqId;
    //同上
    private String qqIdLike;
    //账号状态
    private String accountStatus;
    //账号的角色
    private String accountRole;

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    public String getLoginIdLike()
    {
        return loginIdLike;
    }

    public void setLoginIdLike(String loginIdLike)
    {
        this.loginIdLike = loginIdLike;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
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

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumberLike()
    {
        return mobileNumberLike;
    }

    public void setMobileNumberLike(String mobileNumberLike)
    {
        this.mobileNumberLike = mobileNumberLike;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberLike()
    {
        return phoneNumberLike;
    }

    public void setPhoneNumberLike(String phoneNumberLike)
    {
        this.phoneNumberLike = phoneNumberLike;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public String getContactNumberLike()
    {
        return contactNumberLike;
    }

    public void setContactNumberLike(String contactNumberLike)
    {
        this.contactNumberLike = contactNumberLike;
    }

    public String getFaxNumber()
    {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber)
    {
        this.faxNumber = faxNumber;
    }

    public String getFaxNumberLike()
    {
        return faxNumberLike;
    }

    public void setFaxNumberLike(String faxNumberLike)
    {
        this.faxNumberLike = faxNumberLike;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmailLike()
    {
        return emailLike;
    }

    public void setEmailLike(String emailLike)
    {
        this.emailLike = emailLike;
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

    public String getWebchatIdLike()
    {
        return webchatIdLike;
    }

    public void setWebchatIdLike(String webchatIdLike)
    {
        this.webchatIdLike = webchatIdLike;
    }

    public String getQqId()
    {
        return qqId;
    }

    public void setQqId(String qqId)
    {
        this.qqId = qqId;
    }

    public String getQqIdLike()
    {
        return qqIdLike;
    }

    public void setQqIdLike(String qqIdLike)
    {
        this.qqIdLike = qqIdLike;
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

}
