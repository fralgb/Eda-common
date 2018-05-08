package com.omniselling.ms.core.model;

import java.util.List;

/**
 * 公共资源权限 账号id和公共资源id参数条件
 *
 */
public class PubResPermitCond
{

    /**
     * 账号id
     */
    private List<Long> accountIds;

    /**
     * 公共资源id
     */
    private List<Long> pubResourceIds;
    
    /**
     * 公共资源资源类型
     */
    private String resourceType;

    public List<Long> getAccountIds()
    {
        return accountIds;
    }

    public void setAccountIds(List<Long> accountIds)
    {
        this.accountIds = accountIds;
    }

    public List<Long> getPubResourceIds()
    {
        return pubResourceIds;
    }

    public void setPubResourceIds(List<Long> pubResourceIds)
    {
        this.pubResourceIds = pubResourceIds;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    @Override
    public String toString()
    {
        return "PubResPermitCond [accountIds=" + accountIds + ", pubResourceIds=" + pubResourceIds + ", resourceType="
                + resourceType + "]";
    }

}
