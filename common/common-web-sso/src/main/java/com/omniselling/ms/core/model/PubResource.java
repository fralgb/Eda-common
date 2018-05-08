package com.omniselling.ms.core.model;

import java.util.Date;

/**
 * 公共资源
 * 
 * @author Administrator
 *
 */
public class PubResource
{

    /**
     * 公共资源id
     */
    private Long id;
    /**
     * 资源名称
     */
    private String resourceName;
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
     * 拥有者
     */
    private String ownerName;
    /**
     * 资源对象id
     */
    private Long resourceObjectId;
    /**
     * 资源类型
     */
    private String resourceType;
    /**
     * 资源类型
     */
    private String resourceTypeLabel;
    
    /**
     * 公共资源状态
     */
    private String resourceStatus;
    /**
     * 资源状态
     */
    private String resourceStatusLabel;
    /**
     * 失效日期
     */
    private Date invalidDate;
    
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public String getResourceName()
    {
        return resourceName;
    }
    
    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
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
    
    public Date getInvalidDate()
    {
        return invalidDate;
    }
    
    public void setInvalidDate(Date invalidDate)
    {
        this.invalidDate = invalidDate;
    }

    @Override
    public String toString()
    {
        return "PubResource [id=" + id + ", resourceName=" + resourceName + ", publishNum=" + publishNum
                + ", publishDate=" + publishDate + ", ownerId=" + ownerId + ", ownerName=" + ownerName
                + ", resourceObjectId=" + resourceObjectId + ", resourceType=" + resourceType + ", resourceTypeLabel="
                + resourceTypeLabel + ", resourceStatusLabel=" + resourceStatusLabel + ", publishStatus="
                + resourceStatus + ", invalidDate=" + invalidDate + "]";
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getResourceTypeLabel()
    {
        return resourceTypeLabel;
    }

    public void setResourceTypeLabel(String resourceTypeLabel)
    {
        this.resourceTypeLabel = resourceTypeLabel;
    }

    public String getResourceStatusLabel()
    {
        return resourceStatusLabel;
    }

    public void setResourceStatusLabel(String resourceStatusLabel)
    {
        this.resourceStatusLabel = resourceStatusLabel;
    }

}