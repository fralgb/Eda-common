package com.omniselling.common.biz.workflow;

import com.omniselling.common.ErrorCodeEnum;
import com.omniselling.common.protocol.OmniException;

public class WorkflowRuntimeException extends OmniException
{

    private static final long serialVersionUID = 1L;
    private String context;
    private String cid;


    public WorkflowRuntimeException(ErrorCodeEnum codeEnum, String message, String context, String cid)
    {
        super(codeEnum, message);
        this.setCid(cid);
        this.setContext(context);
    }


    public WorkflowRuntimeException(ErrorCodeEnum codeEnum, Throwable cause, String context, String cid)
    {
        super(codeEnum, cause);
        this.setCid(cid);
        this.setContext(context);
    }

    public WorkflowRuntimeException(ErrorCodeEnum codeEnum, String msg, Throwable cause, String context, String cid)
    {
        super(codeEnum, msg, cause);
        this.setCid(cid);
        this.setContext(context);
    }

    public String getContext()
    {
        return context;
    }

    public void setContext(String context)
    {
        this.context = context;
    }

    public String getCid()
    {
        return cid;
    }

    public void setCid(String cid)
    {
        this.cid = cid;
    }
}
