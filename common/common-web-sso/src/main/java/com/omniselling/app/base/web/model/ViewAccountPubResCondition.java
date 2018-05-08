package com.omniselling.app.base.web.model;

/**
 * 账号公共资源权限管理条件
 *
 */
public class ViewAccountPubResCondition
{
    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 账号loginId
     */
    private String loginId;
    /**
     * 账号loginId模糊查询
     */
    private String loginIdLike;
    /**
     * 账号名称
     */
    private String accountName;
    /**
     * 账号名称模糊查询
     */
    private String accountNameLike;
    /**
     * 公共资源发布编号
     */
    private String publishNum;
    /**
     * 公共资源发布编号模糊查询
     */
    private String publishNumLike;
    /**
     * 拥有者编号
     */
    private Long ownerId;
    /**
     * 公共资源类型
     */
    private String resourceType;
    /**
     * 公共资源名称
     */
    private String resourceName;
    /**
     * 公共资源名称模糊查询
     */
    private String resourceNameLike;

    /**
     * 账号配置的每一页记录数
     */
    private Integer rowsPerPage;
    /**
     * 开始记录数
     */
    private Integer offset;

    public Long getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    public String getLoginIdLike()
    {
        return loginIdLike;
    }

    public void setLoginIdLike(String loginIdLike)
    {
        this.loginIdLike = loginIdLike;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getAccountNameLike()
    {
        return accountNameLike;
    }

    public void setAccountNameLike(String accountNameLike)
    {
        this.accountNameLike = accountNameLike;
    }

    public String getPublishNum()
    {
        return publishNum;
    }

    public void setPublishNum(String publishNum)
    {
        this.publishNum = publishNum;
    }

    public String getPublishNumLike()
    {
        return publishNumLike;
    }

    public void setPublishNumLike(String publishNumLike)
    {
        this.publishNumLike = publishNumLike;
    }

    public Long getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getResourceNameLike()
    {
        return resourceNameLike;
    }

    public void setResourceNameLike(String resourceNameLike)
    {
        this.resourceNameLike = resourceNameLike;
    }

    public Integer getRowsPerPage()
    {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rowsPerPage)
    {
        this.rowsPerPage = rowsPerPage;
    }

    public Integer getOffset()
    {
        return offset;
    }

    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }

}
