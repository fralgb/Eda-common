package com.omniselling.app.base.web.model;

public class ViewEnumSpec
{
    private String code;

    private String label;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    @Override
    public String toString()
    {
        return "ViewEnumSpec [code=" + code + ", label=" + label + "]";
    }
}
