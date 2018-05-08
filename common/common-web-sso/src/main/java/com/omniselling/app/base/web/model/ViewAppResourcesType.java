package com.omniselling.app.base.web.model;

public class ViewAppResourcesType
{
    private String resourceType;
    private String resourceTypeLabel;

    @Override
    public String toString()
    {
        return "ViewAppResourcesType [resourceTypeCode=" + resourceType + ", resourceTypeLabel=" + resourceTypeLabel
                + "]";
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
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
