package com.omniselling.ms.core.model;

import java.util.Date;
import java.util.List;

import com.omniselling.common.model.BasePageInfo;

public class AccountPubResourceCond extends BasePageInfo
{
    /**
     * 账户编号集合
     */
    private List<Long> accountIds;
    /**
     * 公共资源编号集合
     */
    private List<Long> pubResourceIds;
    /**
     * enum 系统应用资源状态集合 ResourcePermitType 允许操作 ALLOW, 不允许操作 NOTALLOW;
     */
    private List<String> permitTypes;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色名称模糊查询
     */
    private String roleNameLike;
    /**
     * 账户公共资源权限编号
     */
    private Long accpubrespermitId;

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
     * enum 公共资源类型 
     * @see com.omniselling.common.Enumeration.BusinessResourceType
     */
    private String resourceType;

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

    public List<Long> getPubResourceIds()
    {
        return pubResourceIds;
    }

    public void setPubResourceIds(List<Long> pubResourceIds)
    {
        this.pubResourceIds = pubResourceIds;
    }

    public List<String> getPermitTypes()
    {
        return permitTypes;
    }

    public void setPermitTypes(List<String> permitTypes)
    {
        this.permitTypes = permitTypes;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleNameLike()
    {
        return roleNameLike;
    }

    public void setRoleNameLike(String roleNameLike)
    {
        this.roleNameLike = roleNameLike;
    }

    public Long getAccpubrespermitId()
    {
        return accpubrespermitId;
    }

    public void setAccpubrespermitId(Long accpubrespermitId)
    {
        this.accpubrespermitId = accpubrespermitId;
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

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
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

    @Override
    public String toString()
    {
        return "AccountPubResourceCond [accountIds=" + accountIds + ", pubResourceIds=" + pubResourceIds
                + ", permitTypes=" + permitTypes + ", roleName=" + roleName + ", roleNameLike=" + roleNameLike
                + ", accpubrespermitId=" + accpubrespermitId + ", resourceName=" + resourceName + ", resourceNameLike="
                + resourceNameLike + ", publishNum=" + publishNum + ", publishNumLike=" + publishNumLike
                + ", publishDateFrom=" + publishDateFrom + ", publishDateTo=" + publishDateTo + ", resourceType="
                + resourceType + ", resourceStatus=" + resourceStatus + ", invalidDateFrom=" + invalidDateFrom
                + ", invalidDateTo=" + invalidDateTo + "]";
    }
}
