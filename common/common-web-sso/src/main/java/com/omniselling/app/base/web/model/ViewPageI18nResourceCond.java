package com.omniselling.app.base.web.model;

public class ViewPageI18nResourceCond
{
    /**
     * 应用系统名
     */
    private String applicationName;
    /**
     * 国际化语言代码
     */
    private String languageCode;

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

    @Override
    public String toString()
    {
        return "ViewBasicI18nResourceCond [applicationName=" + applicationName + ", languageCode=" + languageCode + "]";
    }

}
