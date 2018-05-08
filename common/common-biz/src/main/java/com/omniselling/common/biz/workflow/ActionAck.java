package com.omniselling.common.biz.workflow;

import java.io.Serializable;

/**
 * 用于回复的时候返回一个事件的处理结果
 * @author Atomic
 *
 */
public class ActionAck implements Serializable
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
     * 是否需要等待,如果需要等待則工作流會等待事件處理完以後的notify, 如果不需要等待, 應該返回actionResponse
     * 默認為true
     */
    private boolean needWaiting = true;

    /**
     * 只有needWaiting==false的情況下才會有值
     */
    private ActionResponse actionResponse;

    /**
     * 將整個workflow設成能取消與否
     */
    private Boolean flowCancellable;

    public ActionAck(String correlationId)
    {
        super();
        this.correlationId = correlationId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCorrelationId()
    {
        return correlationId;
    }

    public boolean isNeedWaiting()
    {
        return needWaiting;
    }

    public ActionResponse getActionResponse()
    {
        return actionResponse;
    }

    public void setActionResponse(ActionResponse actionResponse)
    {
        this.actionResponse = actionResponse;
    }

    public void setNeedWaiting(boolean needWaiting)
    {
        this.needWaiting = needWaiting;
    }

    public Boolean getFlowCancellable()
    {
        return flowCancellable;
    }

    public void setFlowCancellable(Boolean flowCancellable)
    {
        this.flowCancellable = flowCancellable;
    }

}
