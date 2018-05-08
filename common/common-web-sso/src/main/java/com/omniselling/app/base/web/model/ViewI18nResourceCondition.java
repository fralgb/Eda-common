package com.omniselling.app.base.web.model;

/**
 * i18n资源查询条件
 *
 */
public class ViewI18nResourceCondition
{
    /**
     * 应用系统名
     */
    private String applicationName;
    
    /**
     *  应用系统名模糊查询
     */
    private String applicationNameLike;
    /**
     * 国际化语言代码
     */
    private String languageCode;

    /**
     * 资源名
     */
    private String resourceKey;
    
    private String resourceKeyLike;
    // 分页信息
    private Integer offset;
    private Integer rowsPerPage;

    public String getApplicationName()
    {
        return applicationName;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public String getApplicationNameLike()
    {
        return applicationNameLike;
    }

    public void setApplicationNameLike(String applicationNameLike)
    {
        this.applicationNameLike = applicationNameLike;
    }

    public String getLanguageCode()
    {
        return languageCode;
    }

    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }

    public String getResourceKey()
    {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey)
    {
        this.resourceKey = resourceKey;
    }

    public String getResourceKeyLike()
    {
        return resourceKeyLike;
    }

    public void setResourceKeyLike(String resourceKeyLike)
    {
        this.resourceKeyLike = resourceKeyLike;
    }

    public Integer getOffset()
    {
        return offset;
    }

    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }

    public Integer getRowsPerPage()
    {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rowsPerPage)
    {
        this.rowsPerPage = rowsPerPage;
    }
}
