package com.omniselling.common.biz.workflow;

import com.omniselling.common.Enumeration;

public enum WorkflowState implements Enumeration
{
    NOTFOUND,
    RUNNING,
    WAITING,

    FINISHED,
    INVALID,
    ERROR
}
