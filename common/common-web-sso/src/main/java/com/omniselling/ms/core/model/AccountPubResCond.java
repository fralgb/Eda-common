package com.omniselling.ms.core.model;

import java.util.Date;
import java.util.List;

import com.omniselling.common.model.BasePageInfo;

public class AccountPubResCond extends BasePageInfo
{
    /**
     * 账户编号集合
     */
    private List<Long> accountIds;
    /**
     * 账号loginid
     */
    private String loginId;

    /**
     * 账号loginid模糊查询
     */
    private String loginIdLike;

    /**
     * 账号名称
     */
    private String accountName;

    /**
     * 账号名称模糊查询
     */
    private String accountNameLike;
    /**
     * 公共资源编号集合
     */
    private List<Long> pubResourceIds;
    /**
     * enum 系统应用资源状态集合 ResourcePermitType 允许操作 ALLOW, 不允许操作 NOTALLOW;
     */
    private String permitType;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源名称
     */
    private String resourceNameLike;

    /**
     * 发布编号 模糊查询
     */
    private String publishNum;
    /**
     * 发布编号 模糊查询
     */
    private String publishNumLike;

    /**
     * 发布日期开始
     */
    private Date publishDateFrom;
    /**
     * 发布日期至
     */
    private Date publishDateTo;

    /**
     * enum 资源类型 
     * @see com.omniselling.common.Enumeration.BusinessResourceType
     */
    private List<String> resourceTypes;

    /**
     * enum 公共资源状态 PubResourceStatus 有效可用的公共资源状态 ACTIVE, 无效不可用的公共资源状态 DISABLE;
     */
    private String resourceStatus;

    /**
     * 失效日期开始
     */
    private Date invalidDateFrom;
    /**
     * 失效日期至
     */
    private Date invalidDateTo;

    public List<Long> getAccountIds()
    {
        return accountIds;
    }

    public void setAccountIds(List<Long> accountIds)
    {
        this.accountIds = accountIds;
    }

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

    public List<Long> getPubResourceIds()
    {
        return pubResourceIds;
    }

    public void setPubResourceIds(List<Long> pubResourceIds)
    {
        this.pubResourceIds = pubResourceIds;
    }

    public String getPermitType()
    {
        return permitType;
    }

    public void setPermitType(String permitType)
    {
        this.permitType = permitType;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getResourceNameLike()
    {
        return resourceNameLike;
    }

    public void setResourceNameLike(String resourceNameLike)
    {
        this.resourceNameLike = resourceNameLike;
    }

    public String getPublishNum()
    {
        return publishNum;
    }

    public void setPublishNum(String publishNum)
    {
        this.publishNum = publishNum;
    }

    public String getPublishNumLike()
    {
        return publishNumLike;
    }

    public void setPublishNumLike(String publishNumLike)
    {
        this.publishNumLike = publishNumLike;
    }

    public Date getPublishDateFrom()
    {
        return publishDateFrom;
    }

    public void setPublishDateFrom(Date publishDateFrom)
    {
        this.publishDateFrom = publishDateFrom;
    }

    public Date getPublishDateTo()
    {
        return publishDateTo;
    }

    public void setPublishDateTo(Date publishDateTo)
    {
        this.publishDateTo = publishDateTo;
    }

    public List<String> getResourceTypes()
    {
        return resourceTypes;
    }

    public void setResourceTypes(List<String> resourceTypes)
    {
        this.resourceTypes = resourceTypes;
    }

    public String getResourceStatus()
    {
        return resourceStatus;
    }

    public void setResourceStatus(String resourceStatus)
    {
        this.resourceStatus = resourceStatus;
    }

    public Date getInvalidDateFrom()
    {
        return invalidDateFrom;
    }

    public void setInvalidDateFrom(Date invalidDateFrom)
    {
        this.invalidDateFrom = invalidDateFrom;
    }

    public Date getInvalidDateTo()
    {
        return invalidDateTo;
    }

    public void setInvalidDateTo(Date invalidDateTo)
    {
        this.invalidDateTo = invalidDateTo;
    }

}
