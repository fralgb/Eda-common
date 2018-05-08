package com.omniselling.app.base.web.model;

/**
 * View ViewLang model
 * 
 * in/out/both:out
 * 
 * @author wlq
 */
public class ViewLang
{
    // 国际化英文简称
    private String i18nCode;
    // 国际化汉语标识
    private String i18nCodeLabel;

    public String getI18nCode()
    {
        return i18nCode;
    }

    public void setI18nCode(String i18nCode)
    {
        this.i18nCode = i18nCode;
    }

    public String getI18nCodeLabel()
    {
        return i18nCodeLabel;
    }

    public void setI18nCodeLabel(String i18nCodeLabel)
    {
        this.i18nCodeLabel = i18nCodeLabel;
    }

}
