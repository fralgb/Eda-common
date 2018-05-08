package com.omniselling.app.base.web.model;

import java.util.List;

/**
 * 页面国际化资源
 */
public class ViewI18nPageResource
{
    // 应用名称
    private String applicationName;
    // 资源名
    private String languageCode;
    // 资源选项
    private List<ViewI18nResourceItem> i18nResourceItems;

    public String getApplicationName()
    {
        return applicationName;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public String getLanguageCode()
    {
        return languageCode;
    }

    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }

    public List<ViewI18nResourceItem> getI18nResourceItems()
    {
        return i18nResourceItems;
    }

    public void setI18nResourceItems(List<ViewI18nResourceItem> i18nResourceItems)
    {
        this.i18nResourceItems = i18nResourceItems;
    }
    
}
