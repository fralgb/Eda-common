package com.omniselling.app.base.web.model;

public class ViewResourceInfo
{
    private Long resourceId; // chAccId, crAccId, warehouseId
    private String resourceType; // channel, courier, warehouse
    private String name; // chAccName, crAccName, whName

    public Long getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(Long resourceId)
    {
        this.resourceId = resourceId;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
