package com.omniselling.common.model;

import java.util.Date;

import com.omniselling.common.enumeration.OmniEventAction;

/**
 * omni event to communicate between systems
 * @author Atomic
 *
 */
public class OmniEventLog extends BizBaseModel<String>
{
	private static final long serialVersionUID = 1L;

	private String id;
	
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
     * 詳細的log資料
     */
    private String logData;

    /**
     * 创建人的accountId (开启人/完成人)
     */
    private Long createdBy;
    /**
     * 创建时间 (开始/完成时间)
     */
    private Date createdDate;
    /**
     * 其它说明
     */
    private String note;

    public String getGuid()
    {
        return guid;
    }

    public void setGuid(String guid)
    {
        this.guid = guid;
    }

    public String getBusinessNum()
    {
        return businessNum;
    }

    public void setBusinessNum(String businessNum)
    {
        this.businessNum = businessNum;
    }

    public Long getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy)
    {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
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

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }


    public OmniEventAction getAction()
    {
        return action;
    }

    public void setAction(OmniEventAction action)
    {
        this.action = action;
    }


    public String getLogData()
    {
        return logData;
    }

    public void setLogData(String logData)
    {
        this.logData = logData;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
}
