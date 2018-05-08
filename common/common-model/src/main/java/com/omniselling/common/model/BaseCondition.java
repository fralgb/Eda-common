package com.omniselling.common.model;

import java.util.Date;
import java.util.List;

/**
 * 基础查询条件
 * @author Atomic
 *
 */
public class BaseCondition<I> extends BasePageInfo
{

    private static final long serialVersionUID = 1L;
    private List<I> inIds;
    private Date createTimeFrom;
    private Date createTimeTo;
    private Date modifiedTimeFrom;
    private Date modifiedTimeTo;
    private Long createdById;
    private Long modifiedById;

    public List<I> getInIds()
    {
        return inIds;
    }

    public void setInIds(List<I> inIds)
    {
        this.inIds = inIds;
    }

    public Date getCreateTimeFrom()
    {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(Date createTimeFrom)
    {
        this.createTimeFrom = createTimeFrom;
    }

    public Date getCreateTimeTo()
    {
        return createTimeTo;
    }

    public void setCreateTimeTo(Date createTimeTo)
    {
        this.createTimeTo = createTimeTo;
    }

    public Date getModifiedTimeFrom()
    {
        return modifiedTimeFrom;
    }

    public void setModifiedTimeFrom(Date modifiedTimeFrom)
    {
        this.modifiedTimeFrom = modifiedTimeFrom;
    }

    public Date getModifiedTimeTo()
    {
        return modifiedTimeTo;
    }

    public void setModifiedTimeTo(Date modifiedTimeTo)
    {
        this.modifiedTimeTo = modifiedTimeTo;
    }

    public Long getCreatedById()
    {
        return createdById;
    }

    public void setCreatedById(Long createdById)
    {
        this.createdById = createdById;
    }

    public Long getModifiedById()
    {
        return modifiedById;
    }

    public void setModifiedById(Long modifiedById)
    {
        this.modifiedById = modifiedById;
    }

}
