package com.omniselling.ms.core.model;

import java.sql.Date;

import com.omniselling.common.model.BasePageInfo;

/**
 * 公共资源查询条件
 *
 */
public class PubResourceCond extends BasePageInfo
{
    /**
     * 失效开始时间
     */
    private Date invalidDateFrom;
    /**
     * 失效结束时间
     */
    private Date invalidDateTo;
    /**
     * 拥有者id，所属者id
     */
    private Long ownerId;
    /**
     * 资源发布开始时间
     */
    private Date publishDateFrom;
    /**
     * 资源发布结束时间
     */
    private Date publishDateTo;
    /**
     * 发布编号
     */
    private String publishNum;
    /**
     * 发布状态
     */
    private String resourceStatus;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源名称
     */
    private String resourceNameLike;

    /**
     * 资源类型
     */
    private String resourceType;

    public Long getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
    }

    public String getPublishNum()
    {
        return publishNum;
    }

    public void setPublishNum(String publishNum)
    {
        this.publishNum = publishNum;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
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

    public String getResourceStatus()
    {
        return resourceStatus;
    }

    public void setResourceStatus(String resourceStatus)
    {
        this.resourceStatus = resourceStatus;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    public String getResourceNameLike()
    {
        return resourceNameLike;
    }

    public void setResourceNameLike(String resourceNameLike)
    {
        this.resourceNameLike = resourceNameLike;
    }

    @Override
    public String toString()
    {
        return "PubResourceCond [invalidDateFrom=" + invalidDateFrom + ", invalidDateTo=" + invalidDateTo + ", ownerId="
                + ownerId + ", publishDateFrom=" + publishDateFrom + ", publishDateTo=" + publishDateTo
                + ", publishNum=" + publishNum + ", resourceStatus=" + resourceStatus + ", resourceName=" + resourceName
                + ", resourceNameLike=" + resourceNameLike + ", resourceType=" + resourceType
                + ", getOrderByString()=" + getOrderByString() + ", getRowsPerPage()=" + getRowsPerPage()
                + ", getOffset()=" + getOffset() + "]";
    }

}
