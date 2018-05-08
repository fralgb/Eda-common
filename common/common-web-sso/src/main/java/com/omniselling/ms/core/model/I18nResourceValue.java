package com.omniselling.ms.core.model;

/**
 * 国际化资源value信息
 *
 */
public class I18nResourceValue
{

    /**
     * 国际化语言代码
     */
    private String languageCode;

    /**
     * 资源国际化语言
     */
    private String resourceValue;

    public String getLanguageCode()
    {
        return languageCode;
    }

    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }

    public String getResourceValue()
    {
        return resourceValue;
    }

    public void setResourceValue(String resourceValue)
    {
        this.resourceValue = resourceValue;
    }

}
