package com.omniselling.common.biz.workflow;

public interface IWorkflowTimeoutAction<M>
{
    /**
     * called when a workflow step is timeout on wait, 
     * @param bizBaseModel
     * @param step
     */
    boolean notify(M bizBaseModel, String step);
}
