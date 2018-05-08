package com.omniselling.common.biz.workflow;

import java.io.Serializable;
import java.util.Date;

/**
 * 用于回复的时候返回一个事件的处理结果
 * @author Atomic
 *
 */
public class ActionResponse implements Serializable
{

    private static final long serialVersionUID = 1L;
    /**
     * 事件id
     */
    private String id;
    /**
     * 对应事件的关联ID(correlationId), 应该跟跟等待事件的关联ID一致
     */
    private final String correlationId;

    /**
     * 发送此回复的对象的关联ID,sender正在等待此ID, 可以为空 optional
     */
    private String senderCId;
    /**
     * 发送此回复的对象(sender)的关联Event的id,sender正在等待此事件的回复, 可以为空 optional
     */
    private Long senderEventId;

    /**
     * 回复结果的枚举, 应该是waitEventEnum所允许的结果
     */
    private final String resultEnum;
    /**
     * 处理的时间
     */
    private Date processedTime;
    /**
     * 处理人的accountId
     */
    private Long processedBy;
    /**
     * 额外的处理说明,可以为空
     */
    private String note;


    /**
     * 是否跳過 - 目前沒有實現
     */
    private boolean rollforward;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSenderCId()
    {
        return senderCId;
    }

    public void setSenderCId(String senderCId)
    {
        this.senderCId = senderCId;
    }

    public Long getSenderEventId()
    {
        return senderEventId;
    }

    public void setSenderEventId(Long senderEventId)
    {
        this.senderEventId = senderEventId;
    }

    public Date getProcessedTime()
    {
        return processedTime;
    }

    public void setProcessedTime(Date processedTime)
    {
        this.processedTime = processedTime;
    }

    public Long getProcessedBy()
    {
        return processedBy;
    }

    public void setProcessedBy(Long processedBy)
    {
        this.processedBy = processedBy;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public boolean isRollforward()
    {
        return rollforward;
    }

    public void setRollforward(boolean rollforward)
    {
        this.rollforward = rollforward;
    }

    public String getCorrelationId()
    {
        return correlationId;
    }

    public String getResultEnum()
    {
        return resultEnum;
    }

    public ActionResponse(String correlationId, String resultEnum)
    {
        super();
        this.correlationId = correlationId;
        this.resultEnum = resultEnum;
    }

    @Override
    public String toString()
    {
        return "ActionResponse [id=" + id + ", correlationId=" + correlationId + ", senderCId=" + senderCId
                + ", senderEventId=" + senderEventId + ", resultEnum=" + resultEnum + ", processedTime="
                + processedTime
                + ", processedBy=" + processedBy + ", note=" + note + ", rollforward=" + rollforward + "]";
    }
}
