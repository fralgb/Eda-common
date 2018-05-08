package com.omniselling.common.model;

import java.util.Date;

import com.omniselling.common.enumeration.OmniEventAction;

/**
 * omni event log
 * @author Atomic
 *
 */
public class OmniEventLogCondition extends BaseCondition<String>
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String guid;

    /**
     * 目前只记录START/COMPLETE 开始和完成
     */
    private OmniEventAction action;

    /**
     * 业务号
     */
    private String businessNum;

    /**
     * 事件名, 一般为等待事件的枚举
     */
    private String waitEventEnum;
    /**
     * 事件结果
     */
    private String resultEnum;

    /**
     * 创建人的accountId (开启人/完成人)
     */
    private Long createdBy;
    /**
     * 创建时间 (开始/完成时间)
     */
    private Date createdDateFrom;
    /**
     * 创建时间 (开始/完成时间)
     */
    private Date createdDateTo;


    public OmniEventAction getAction()
    {
        return action;
    }

    public void setAction(OmniEventAction action)
    {
        this.action = action;
    }

    public String getBusinessNum()
    {
        return businessNum;
    }

    public void setBusinessNum(String businessNum)
    {
        this.businessNum = businessNum;
    }

    public String getWaitEventEnum()
    {
        return waitEventEnum;
    }

    public void setWaitEventEnum(String waitEventEnum)
    {
        this.waitEventEnum = waitEventEnum;
    }

    public String getResultEnum()
    {
        return resultEnum;
    }

    public void setResultEnum(String resultEnum)
    {
        this.resultEnum = resultEnum;
    }

    public Long getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy)
    {
        this.createdBy = createdBy;
    }

    public Date getCreatedDateFrom()
    {
        return createdDateFrom;
    }

    public void setCreatedDateFrom(Date createdDateFrom)
    {
        this.createdDateFrom = createdDateFrom;
    }

    public Date getCreatedDateTo()
    {
        return createdDateTo;
    }

    public void setCreatedDateTo(Date createdDateTo)
    {
        this.createdDateTo = createdDateTo;
    }

    public String getGuid()
    {
        return guid;
    }

    public void setGuid(String guid)
    {
        this.guid = guid;
    }


}
