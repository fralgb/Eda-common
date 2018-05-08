package com.omniselling.app.base.web.model;

/**
 * 资源类型
 * 
 * @author baby
 *
 */
public class ViewResourceType
{
    /**
     * 码
     */
    private String resourceType;
    /**
     * 显示值
     */
    private String resourceTypeLabel;
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
