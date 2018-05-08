package com.omniselling.common.model;

import java.io.Serializable;

import com.omniselling.common.enumeration.LanguageCode;

public class ErrorInfo implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 8253932013643398848L;

    /**
     * 出错代码
     */
    private String code;

    /**
     * 描述信息
     */
    private String desc;

    /**
     * 错误代码层级
     */
    private CATEGORY category = CATEGORY.UNDETERMINED;

    /**
     * 错误等级
     */
    private SEVERITY severity = SEVERITY.ERROR;

    /**
     * 语言代码
     */
    private LanguageCode language;

    /**
     * 错误代码层级
     */
    public static enum CATEGORY
    {
        VIEW, BIZ, DB, UNDETERMINED, MSVIEW
    };

    public static enum SEVERITY
    {
        INFO, WARN, ERROR
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public CATEGORY getCategory()
    {
        return category;
    }

    public void setCategory(CATEGORY category)
    {
        this.category = category;
    }

    public SEVERITY getSeverity()
    {
        return severity;
    }

    public void setSeverity(SEVERITY severity)
    {
        this.severity = severity;
    }

    public LanguageCode getLanguage()
    {
        return language;
    }

    public void setLanguage(LanguageCode language)
    {
        this.language = language;
    };


    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + "[" + code + ": " + desc + "]";
    }
  

}
