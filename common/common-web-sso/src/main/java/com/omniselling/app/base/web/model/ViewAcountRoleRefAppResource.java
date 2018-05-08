package com.omniselling.app.base.web.model;

import java.util.List;

import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.enumeration.MsAppErrorCode;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.common.util.StringUtils;

public class ViewAcountRoleRefAppResource
{
    /**
     * 账户角色id
     */
    private Long accountRoleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDescription;
    /**
     * 角色状态
     */
    private String roleStatus;
    /**
     * 用户资源列表
     */
    private List<ViewAppResource> appResources;
    /**
     * 定义参数长度
     */
    private static int[] PARAM_LENGTH = {100,2048,4000};
    @Override
    public String toString()
    {
        return "ViewAcountRoleRefAppResource [accountRoleId=" + accountRoleId + ", roleName=" + roleName
                + ", roleDescription=" + roleDescription + ", roleStatus=" + roleStatus + ", appResources="
                + appResources + "]";
    }

    public Long getAccountRoleId()
    {
        return accountRoleId;
    }

    public void setAccountRoleId(Long accountRoleId)
    {
        this.accountRoleId = accountRoleId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleDescription()
    {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription)
    {
        this.roleDescription = roleDescription;
    }

    public String getRoleStatus()
    {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus)
    {
        this.roleStatus = roleStatus;
    }

    public List<ViewAppResource> getAppResources()
    {
        return appResources;
    }

    public void setAppResources(List<ViewAppResource> appResources)
    {
        this.appResources = appResources;
    }

    public BaseResponse<Boolean> validate(String language)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<>();
        if (StringUtils.isEmpty(roleName))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNTROLE_ROLENAME_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (roleName.length() > PARAM_LENGTH[0])
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNTROLE_ROLENAME_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (StringUtils.isEmpty(roleStatus))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNTROLE_ROLESTATUS_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (roleStatus.length() > PARAM_LENGTH[0])
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNTROLE_ROLESTATUS_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (StringUtils.isEmpty(roleDescription))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNTROLE_ROLEDESCRIPTION_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (roleDescription.length() > PARAM_LENGTH[1])
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNTROLE_ROLEDESCRIPTION_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        for (ViewAppResource vAppResource : appResources)
        {
            if (StringUtils.isEmpty(vAppResource.getResourceName()))
            {
                baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_RESOURCENAME_EMPTY, ErrorInfo.SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                return baseRes;
            }
            if (vAppResource.getResourceName().length() > PARAM_LENGTH[0])
            {
                baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_RESOURCENAME_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                return baseRes;
            }
            if (StringUtils.isEmpty(vAppResource.getApplicationName()))
            {
                baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_APPLICATIONNAME_EMPTY, ErrorInfo.SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                return baseRes;
            }
            if (vAppResource.getApplicationName().length() > PARAM_LENGTH[0])
            {
                baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_APPLICATIONNAME_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                return baseRes;
            }
            if (StringUtils.isEmpty(vAppResource.getResourceType()))
            {
                baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_RESOURCETYPE_EMPTY, ErrorInfo.SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                return baseRes;
            }
            if (vAppResource.getResourceType().length() > PARAM_LENGTH[0])
            {
                baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_RESOURCETYPE_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                return baseRes;
            }
            
            //以下不是必入项，如果有值，只需要判断其长度,其他model一样
            if (!StringUtils.isEmpty(vAppResource.getResourceUrl()))
            {
                if (vAppResource.getResourceUrl().length() > PARAM_LENGTH[2])
                {
                    baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_RESOURCEURL_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                            ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                    return baseRes;
                }
            }
            if (!StringUtils.isEmpty(vAppResource.getResourceGroup()))
            {
                if (vAppResource.getResourceGroup().length() > PARAM_LENGTH[0])
                {
                    baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.APPRESOURCE_RESOURCEGROUP_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                            ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                    return baseRes;
                }
            }
        }
        baseRes.setData(true);
        return baseRes;
    }
}
