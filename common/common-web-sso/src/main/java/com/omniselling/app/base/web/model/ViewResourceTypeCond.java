package com.omniselling.app.base.web.model;

public class ViewResourceTypeCond
{
    /**
     * 资源类型code for in
     */
    private String resourceTypeCode;

    /**
     * 显示值 ：（类型名称）
     */
    private String resourceTypeLabel;

    @Override
    public String toString()
    {
        return "ViewResourceTypeCondition [resourceTypeCode=" + resourceTypeCode + ", resourceTypeLabel="
                + resourceTypeLabel + "]";
    }

    public String getResourceTypeCode()
    {
        return resourceTypeCode;
    }

    public void setResourceTypeCode(String resourceTypeCode)
    {
        this.resourceTypeCode = resourceTypeCode;
    }

    public String getResourceTypeLabel()
    {
        return resourceTypeLabel;
    }

    public void setResourceTypeLabel(String resourceTypeLabel)
    {
        this.resourceTypeLabel = resourceTypeLabel;
    }

}
