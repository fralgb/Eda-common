package com.omniselling.app.base.web.model;

import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.enumeration.MsAppErrorCode;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.common.util.StringUtils;

/**
 * for out
 * 
 * @author huang huancheng
 *
 */
public class ViewAppResource
{
    /**
     * 应用资源id(只返回资源本身信息用)
     */
    private Long id;
    /**
     * 应用资源id(只返回资源账户关联信息用)
     */
    private Long appResourceId;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源标签
     */
    private String resourceLabel;
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 主id
     */
    private Long parentId;
    /**
     * 资源路径
     */
    private String resourceUrl;
    /**
     * 资源分组
     */
    private String resourceGroup;
    /**
     * 资源编码类型
     */
    private String resourceType;
    /**
     * 资源编码类型
     */
    private String resourceTypeCode;
    /**
     * 资源编码类型标签
     */
    private String resourceTypeLabel;
    /**
     * 资源顺序
     */
    private Integer resourceOrder;

    /**
     * 定义参数长度
     */
    private static int[] PARAM_LENGTH = { 100, 4000 };

    @Override
    public String toString()
    {
        return "ViewAppResource [id=" + id + ", resourceName=" + resourceName + ", resourceLabel=" + resourceLabel
                + ", applicationName=" + applicationName + ", parentId=" + parentId + ", resourceUrl=" + resourceUrl
                + ", resourceGroup=" + resourceGroup + ", resourceType=" + resourceType + ", resourceTypeLabel="
                + resourceTypeLabel + ", resourceOrder=" + resourceOrder + "]";
    }

    public Long getId()
    {
        return id;
    }

    public Long getAppResourceId()
    {
        return appResourceId;
    }

    public void setAppResourceId(Long appResourceId)
    {
        this.appResourceId = appResourceId;
    }

    public String getResourceTypeLabel()
    {
        return resourceTypeLabel;
    }

    public void setResourceTypeLabel(String resourceTypeLabel)
    {
        this.resourceTypeLabel = resourceTypeLabel;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getResourceLabel()
    {
        return resourceLabel;
    }

    public String getResourceTypeCode()
    {
        return resourceTypeCode;
    }

    public void setResourceTypeCode(String resourceTypeCode)
    {
        this.resourceTypeCode = resourceTypeCode;
    }

    public void setResourceLabel(String resourceLabel)
    {
        this.resourceLabel = resourceLabel;
    }

    public String getApplicationName()
    {
        return applicationName;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getResourceUrl()
    {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl)
    {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceGroup()
    {
        return resourceGroup;
    }

    public void setResourceGroup(String resourceGroup)
    {
        this.resourceGroup = resourceGroup;
    }

    public Integer getResourceOrder()
    {
        return resourceOrder;
    }

    public void setResourceOrder(Integer resourceOrder)
    {
        this.resourceOrder = resourceOrder;
    }

    public BaseResponse<Boolean> validate(String language)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<>();
        if (StringUtils.isEmpty(resourceName))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_RESOURCENAME_EMPTY,
                    ErrorInfo.SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (resourceName.length() > PARAM_LENGTH[0])
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_RESOURCENAME_TOOLONG,
                    ErrorInfo.SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (StringUtils.isEmpty(applicationName))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_APPLICATIONNAME_EMPTY,
                    ErrorInfo.SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (applicationName.length() > PARAM_LENGTH[0])
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_APPLICATIONNAME_TOOLONG,
                    ErrorInfo.SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (StringUtils.isEmpty(resourceType))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_RESOURCETYPE_EMPTY,
                    ErrorInfo.SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (resourceType.length() > PARAM_LENGTH[0])
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_RESOURCETYPE_TOOLONG,
                    ErrorInfo.SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }

        // 以下不是必入项，如果有值，只需要判断其长度,其他model一样
        if (!StringUtils.isEmpty(resourceUrl))
        {
            if (resourceUrl.length() > PARAM_LENGTH[1])
            {
                baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_RESOURCEURL_TOOLONG,
                        ErrorInfo.SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                return baseRes;
            }
        }
        if (!StringUtils.isEmpty(resourceGroup))
        {
            if (resourceGroup.length() > PARAM_LENGTH[0])
            {
                baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_RESOURCEGROUP_TOOLONG,
                        ErrorInfo.SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                return baseRes;
            }
        }
        baseRes.setData(true);
        return baseRes;
    }
}
