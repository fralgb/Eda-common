package com.omniselling.common.enumeration;

import com.omniselling.common.Enumeration;

/**
 * 
 * 国际化语言编码
 *
 */
public enum LanguageCode implements Enumeration
{
    /** 中文  */
    ZH_CN("zh-cn"),
    /** 英文  */
    EN_US("en-us"),
    /** 波兰文 */
    PL_PL("pl-pl");
    
    private String dbValue;
    
    private LanguageCode(String dbValue)
    {
        this.dbValue = dbValue;
    }
    
    /**
     * 根据db值获得对应的枚举值
     * 匹配不到返回null
     * @param dbValue
     * @return
     */
    public static LanguageCode getByValue(String value)
    {
        if(value == null || "".equals(value))
        {
            return null;
        }
        for (LanguageCode languageCode : LanguageCode.values())
        {
            if (languageCode.getDbValue().equalsIgnoreCase(value) || languageCode.name().equalsIgnoreCase(value))
            {
                return languageCode;
            }
        }
        return null;
    }

    public String getDbValue()
    {
        return dbValue;
    }

}