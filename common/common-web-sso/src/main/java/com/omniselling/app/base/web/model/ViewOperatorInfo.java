package com.omniselling.app.base.web.model;

public class ViewOperatorInfo
{
    private String language;
    private Long operatorId;
    private String operatorName;
    private Integer offSize;
    private Integer limit;

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public Long getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getOperatorName()
    {
        return operatorName;
    }

    public void setOperatorName(String name)
    {
        this.operatorName = name;
    }

    public Integer getOffSize()
    {
        return offSize;
    }

    public void setOffSize(Integer offSize)
    {
        this.offSize = offSize;
    }

    public Integer getLimit()
    {
        return limit;
    }

    public void setLimit(Integer limit)
    {
        this.limit = limit;
    }

}
