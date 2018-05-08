package com.omniselling.ms.core.model;

import java.util.Date;

/**
 * 账号公共资源权限
 * 
 * @author Administrator
 *
 */
public class AccountPubResPermission
{
    /**
     * 权限id
     */
    private Long id;
    /**
     * 账号编号
     */
    private Long accountId;
    /**
     * 账号信息
     */
    private String loginId;
    /**
     * 账户名称
     */
    private String accountName;
    /**
     * 账户父编号
     */
    private Long accountParentId;
    /**
     * 账号类型
     */
    private String accountType;
    /**
     * 账号类型标签
     */
    private String accountTypeLabel;
    /**
     * 公共资源编号
     */
    private Long pubResourceId;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源类型
     */
    private String resourceType;
    /**
     * 资源类型标签
     */
    private String resourceTypeLabel;
    /**
     * 发布编号
     */
    private String publishNum;
    /**
     * 发布时间(公开)
     */
    private Date publishDate;
    /**
     * 拥有者id，所属者id
     */
    private Long ownerId;
    /**
     * 资源对象id
     */
    private Long resourceObjectId;

    /**
     * 公共资源状态
     */
    private String resourceStatus;

    /**
     * 公共资源标签
     */
    private String resourceStatusLabel;
    /**
     * 失效日期
     */
    private Date invalidDate;
    /**
     * 权限类型
     */
    private String permitType;
    /**
     * 权限类型标签
     */
    private String permitTypeLabel;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public Long getAccountParentId()
    {
        return accountParentId;
    }

    public void setAccountParentId(Long accountParentId)
    {
        this.accountParentId = accountParentId;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public String getAccountTypeLabel()
    {
        return accountTypeLabel;
    }

    public void setAccountTypeLabel(String accountTypeLabel)
    {
        this.accountTypeLabel = accountTypeLabel;
    }

    public Long getPubResourceId()
    {
        return pubResourceId;
    }

    public void setPubResourceId(Long pubResourceId)
    {
        this.pubResourceId = pubResourceId;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    public String getResourceTypeLabel()
    {
        return resourceTypeLabel;
    }

    public void setResourceTypeLabel(String resourceTypeLabel)
    {
        this.resourceTypeLabel = resourceTypeLabel;
    }

    public String getPublishNum()
    {
        return publishNum;
    }

    public void setPublishNum(String publishNum)
    {
        this.publishNum = publishNum;
    }

    public Date getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate(Date publishDate)
    {
        this.publishDate = publishDate;
    }

    public Long getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
    }

    public Long getResourceObjectId()
    {
        return resourceObjectId;
    }

    public void setResourceObjectId(Long resourceObjectId)
    {
        this.resourceObjectId = resourceObjectId;
    }

    public String getResourceStatus()
    {
        return resourceStatus;
    }

    public void setResourceStatus(String resourceStatus)
    {
        this.resourceStatus = resourceStatus;
    }

    public String getResourceStatusLabel()
    {
        return resourceStatusLabel;
    }

    public void setResourceStatusLabel(String resourceStatusLabel)
    {
        this.resourceStatusLabel = resourceStatusLabel;
    }

    public Date getInvalidDate()
    {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate)
    {
        this.invalidDate = invalidDate;
    }

    public String getPermitType()
    {
        return permitType;
    }

    public void setPermitType(String permitType)
    {
        this.permitType = permitType;
    }

    public String getPermitTypeLabel()
    {
        return permitTypeLabel;
    }

    public void setPermitTypeLabel(String permitTypeLabel)
    {
        this.permitTypeLabel = permitTypeLabel;
    }

}