package com.omniselling.app.base.web.model;

import java.util.Date;

import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.enumeration.MsAppErrorCode;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.common.util.StringUtils;

/**
 * 公共资源
 * 
 * @author baby
 *
 */
public class ViewPublicResource
{
    /**
     * 资源编号
     */
    private Long id;

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
     * 资源类型码
     */
    private String resourceTypeCode;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 物主名称
     */
    private String ownerName;
    /**
     * 物主编号
     */
    private Long ownerId;
    /**
     * 发布编号
     */
    private String publishNum;
    /**
     * 发布日期
     */
    private Date publishDate;
    /**
     * 资源状态
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

    @Override
    public String toString()
    {
        return "ViewPublicResource [id=" + id + ", resourceObjectId=" + resourceObjectId + ", resourceType="
                + resourceType + ", resourceTypeLabel=" + resourceTypeLabel + ", resourceTypeCode=" + resourceTypeCode
                + ", resourceName=" + resourceName + ", ownerName=" + ownerName + ", ownerId=" + ownerId
                + ", publishNum=" + publishNum + ", publishDate=" + publishDate + ", resourceStatus=" + resourceStatus
                + ", resourceStatusLabel=" + resourceStatusLabel + ", invalidDate=" + invalidDate + "]";
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getResourceObjectId()
    {
        return resourceObjectId;
    }

    public void setResourceObjectId(Long resourceObjectId)
    {
        this.resourceObjectId = resourceObjectId;
    }

    public String getResourceTypeCode()
    {
        return resourceTypeCode;
    }

    public void setResourceTypeCode(String resourceTypeCode)
    {
        this.resourceTypeCode = resourceTypeCode;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

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

    public Date getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate(Date publishDate)
    {
        this.publishDate = publishDate;
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

    public String getResourceStatusLabel()
    {
        return resourceStatusLabel;
    }

    public void setResourceStatusLabel(String resourceStatusLabel)
    {
        this.resourceStatusLabel = resourceStatusLabel;
    }

    public BaseResponse<Boolean> validate(String language)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<>();
        if (StringUtils.isEmpty(resourceType))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_RESOURCETYPE_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (resourceType.length() > 100)
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_RESOURCETYPE_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        
        if (StringUtils.isEmpty(resourceName))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_RESOURCENAME_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (resourceName.length() > 255)
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_RESOURCENAME_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        
        if (ownerId == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_OWNERID_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        
        if (StringUtils.isEmpty(publishNum))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_PUBLISHNUM_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (publishNum.length() > 100)
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_PUBLISHNUM_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        
        if (StringUtils.isEmpty(resourceStatus))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_RESOURCESTATUS_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (resourceStatus.length() > 100)
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_RESOURCESTATUS_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        
        baseRes.setData(true);
        return baseRes;
    }
}
