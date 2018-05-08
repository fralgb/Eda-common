package com.omniselling.common.biz.workflow;

import com.omniselling.common.ErrorCodeEnum;

/**
 * User defined exception to stop flow execution with the context and error message
 * @author Atomic
 *
 */
public class WorkflowTerminationException extends WorkflowException
{
    private static final long serialVersionUID = 1;

    public WorkflowTerminationException(ErrorCodeEnum codeEnum, Throwable cause, String context, String cid)
    {
        super(codeEnum, cause, context, cid);
    }

    public WorkflowTerminationException(ErrorCodeEnum codeEnum, String message, String context, String cid)
    {
        super(codeEnum, message, context, cid);
    }

}
