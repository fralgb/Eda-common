package com.omniselling.app.base.web.model;

import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.enumeration.MsAppErrorCode;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.common.util.StringUtils;

/**
 * 用户角色
 * 
 * @author Administrator
 *
 */
public class ViewAccountRole
{
    /**
     * 编号
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色标签
     */
    private String roleLabel;
    /**
     * 角色状态
     */
    private String roleStatus;
    /**
     * 角色状态标签
     */
    private String roleStatusLabel;
    /**
     * 角色描述
     */
    private String roleDescription;

    /**
     * 定义参数长度
     */
    private static int[] PARAM_LENGTH = {100,2048};
    @Override
    public String toString()
    {
        return "ViewAccountRole [id=" + id + ", roleName=" + roleName + ", roleLabel=" + roleLabel + ", roleStatus="
                + roleStatus + ", roleStatusLabel=" + roleStatusLabel + ", roleDescription=" + roleDescription + "]";
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleLabel()
    {
        return roleLabel;
    }

    public void setRoleLabel(String roleLabel)
    {
        this.roleLabel = roleLabel;
    }

    public String getRoleStatus()
    {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus)
    {
        this.roleStatus = roleStatus;
    }

    public String getRoleStatusLabel()
    {
        return roleStatusLabel;
    }

    public void setRoleStatusLabel(String roleStatusLabel)
    {
        this.roleStatusLabel = roleStatusLabel;
    }

    public String getRoleDescription()
    {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription)
    {
        this.roleDescription = roleDescription;
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
        baseRes.setData(true);
        return baseRes;
    }
}
