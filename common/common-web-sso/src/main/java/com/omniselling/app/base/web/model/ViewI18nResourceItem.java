package com.omniselling.app.base.web.model;

import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.enumeration.MsAppErrorCode;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.common.util.StringUtils;

/**
 * View ViewI18nResourceItem model
 * 
 * in/out/both:out
 * 
 * @author wlq
 */

public class ViewI18nResourceItem
{
    // 资源名
    private String resourceKey;
    // 资源内容
    private String resourceValue;
    // 资源组
    private String resoureGroup;
    // 国际化英文简称
    private String i18nCode;
    
    private static int PARAM_LANGTH = 100;

    public String getResourceKey()
    {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey)
    {
        this.resourceKey = resourceKey;
    }

    public String getResourceValue()
    {
        return resourceValue;
    }

    public void setResourceValue(String resourceValue)
    {
        this.resourceValue = resourceValue;
    }

    public String getResoureGroup()
    {
        return resoureGroup;
    }

    public void setResoureGroup(String resoureGroup)
    {
        this.resoureGroup = resoureGroup;
    }

    public String getI18nCode()
    {
        return i18nCode;
    }

    public void setI18nCode(String i18nCode)
    {
        this.i18nCode = i18nCode;
    }
    public BaseResponse<Boolean> validate(String language)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<>();
        if (StringUtils.isEmpty(resourceValue))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.I18N_RESOURCEVALUE_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (resourceValue.length() > 4000)
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.I18N_RESOURCEVALUE_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (StringUtils.isEmpty(resourceKey))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.I18N_RESOURCEKEY_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (resourceKey.length() > PARAM_LANGTH)
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.I18N_RESOURCEKEY_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        baseRes.setData(true);
        return baseRes;
    }
}
