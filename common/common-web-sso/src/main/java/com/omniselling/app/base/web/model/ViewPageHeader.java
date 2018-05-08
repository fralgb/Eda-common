package com.omniselling.app.base.web.model;

public class ViewPageHeader
{
    private String name;
    private String label;
    private Integer seq;
    private Boolean hiddenFlag;

    public ViewPageHeader()
    {

    }

    public ViewPageHeader(String name, String label, Integer seq, Boolean hiddenFlag)
    {
        this.name = name;
        this.label = label;
        this.seq = seq;
        this.hiddenFlag = (hiddenFlag == null ? false : hiddenFlag);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public Integer getSeq()
    {
        return seq;
    }

    public void setSeq(Integer seq)
    {
        this.seq = seq;
    }

    public Boolean getHiddenFlag()
    {
        return hiddenFlag;
    }

    public void setHiddenFlag(Boolean hiddenFlag)
    {
        this.hiddenFlag = hiddenFlag;
    }

}
