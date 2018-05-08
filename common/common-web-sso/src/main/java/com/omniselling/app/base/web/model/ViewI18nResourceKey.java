package com.omniselling.app.base.web.model;

import java.util.List;

import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.enumeration.MsAppErrorCode;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.common.util.StringUtils;

/**
 * View ViewI18nResource model
 * 
 * in/out/both:out
 * 
 * @author wlq
 */
public class ViewI18nResourceKey
{
    // 应用名称
    private String applicationName;
    // 资源名
    private String resourceKey;
    // 资源组
    private String resoureGroup;
    // 资源选项
    private List<ViewI18nResourceItem> i18nResourceItems;
    //定义参数长度
    private static int[] PARAM_LANGTH = {100,4000};

    public String getApplicationName()
    {
        return applicationName;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public String getResourceKey()
    {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey)
    {
        this.resourceKey = resourceKey;
    }

    public String getResoureGroup()
    {
        return resoureGroup;
    }

    public void setResoureGroup(String resoureGroup)
    {
        this.resoureGroup = resoureGroup;
    }

    public List<ViewI18nResourceItem> getI18nResourceItems()
    {
        return i18nResourceItems;
    }

    public void setI18nResourceItems(List<ViewI18nResourceItem> i18nResourceItems)
    {
        this.i18nResourceItems = i18nResourceItems;
    }

    public BaseResponse<Boolean> validate(String language)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<>();
        if (StringUtils.isEmpty(applicationName))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.I18N_APPLICATIONNAME_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (applicationName.length() > PARAM_LANGTH[0])
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.I18N_APPLICATIONNAME_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (StringUtils.isEmpty(resourceKey))
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.I18N_RESOURCEKEY_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (resourceKey.length() > PARAM_LANGTH[0])
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.I18N_RESOURCEKEY_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        if (i18nResourceItems == null || i18nResourceItems.isEmpty())
        {
            baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.I18N_I18NRESOURCEITEMS_EMPTY, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
            return baseRes;
        }
        for (ViewI18nResourceItem vI18nResourceItem : i18nResourceItems)
        {
            if (StringUtils.isEmpty(vI18nResourceItem.getResourceValue()))
            {
                baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.I18N_RESOURCEVALUE_EMPTY, ErrorInfo.SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                return baseRes;
            }
            if (vI18nResourceItem.getResourceValue().length() > PARAM_LANGTH[1])
            {
                baseRes.addError(ErrorInfoBuilder.build(MsAppErrorCode.I18N_RESOURCEVALUE_TOOLONG, ErrorInfo.SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(language)));
                return baseRes;
            }
        }
        baseRes.setData(true);
        return baseRes;
    }
}
