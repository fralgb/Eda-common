package com.omniselling.common.biz.workflow;

public interface IWorkflowEngineNotifier
{
    /**
     * 動作的反饋信息給工作流
     * notify with remote exception, 注意 BaseWorkflowAdapterImpl 已經實現了這個接口, 可以override去增加自己的功能
     * @param actionResponse
     * @param exception - optional
     */
    void notifyAction(ActionResponse actionResponse, Exception e);
}
