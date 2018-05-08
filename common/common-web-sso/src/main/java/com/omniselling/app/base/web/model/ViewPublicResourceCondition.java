package com.omniselling.app.base.web.model;

import java.sql.Date;

/**
 * 公共资源
 * 
 * @author baby
 *
 */
public class ViewPublicResourceCondition
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
     * 资源类型码// for IN
     */
    private String resourceTypeCode;
    /**
     * 资源状态// for IN
     */
    private String resourceStatus;
    /**
     * 发布日期// for IN
     */
    private String publishDate;
    /**
     * 发布编号// for IN
     */
    private String publishNum;// for IN
    /**
     * 物主名称// for IN
     */
    private String ownerName;
    /**
     * 资源名称// for IN
     */
    private String resourceName;
    
    private String resourceNameLike;
    /**
     * 验证日期// for IN
     */
    private String invalidDate;

    private Integer offset;
    
    private Integer rowsPerPage;

    public String getResourceTypeCode()
    {
        return resourceTypeCode;
    }

    public void setResourceTypeCode(String resourceTypeCode)
    {
        this.resourceTypeCode = resourceTypeCode;
    }

    public String getResourceStatus()
    {
        return resourceStatus;
    }

    public void setResourceStatus(String resourceStatus)
    {
        this.resourceStatus = resourceStatus;
    }

    public String getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate(String publishDate)
    {
        this.publishDate = publishDate;
    }

    public String getPublishNum()
    {
        return publishNum;
    }

    public void setPublishNum(String publishNum)
    {
        this.publishNum = publishNum;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getInvalidDate()
    {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate)
    {
        this.invalidDate = invalidDate;
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

    public Long getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
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

    public Integer getOffset()
    {
        return offset;
    }

    public void setOffset(Integer offSet)
    {
        this.offset = offSet;
    }

    public Integer getRowsPerPage()
    {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rowsPerPage)
    {
        this.rowsPerPage = rowsPerPage;
    }

    @Override
    public String toString()
    {
        return "ViewPublicResourceCondition [invalidDateFrom=" + invalidDateFrom + ", invalidDateTo=" + invalidDateTo
                + ", ownerId=" + ownerId + ", publishDateFrom=" + publishDateFrom + ", publishDateTo=" + publishDateTo
                + ", resourceTypeCode=" + resourceTypeCode + ", resourceStatus=" + resourceStatus + ", publishDate="
                + publishDate + ", publishNum=" + publishNum + ", ownerName=" + ownerName + ", resourceName="
                + resourceName + ", resourceNameLike=" + resourceNameLike + ", invalidDate=" + invalidDate + ", offset="
                + offset + ", rowsPerPage=" + rowsPerPage + "]";
    }

    public String getResourceNameLike()
    {
        return resourceNameLike;
    }

    public void setResourceNameLike(String resourceNameLike)
    {
        this.resourceNameLike = resourceNameLike;
    }

}
