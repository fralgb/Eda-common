package com.omniselling.common.biz.workflow;

import com.omniselling.common.ErrorCodeEnum;

/**
 * User defined exception to stop flow execution with the context and error message
 * @author Atomic
 *
 */
public class WorkflowCancelException extends WorkflowException
{
    private static final long serialVersionUID = 1;

    public WorkflowCancelException(ErrorCodeEnum codeEnum, Throwable cause, String context, String cid)
    {
        super(codeEnum, cause, context, cid);
    }

    public WorkflowCancelException(ErrorCodeEnum codeEnum, String message, String context, String cid)
    {
        super(codeEnum, message, context, cid);
    }

}
