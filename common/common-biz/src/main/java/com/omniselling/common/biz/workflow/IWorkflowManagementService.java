package com.omniselling.common.biz.workflow;

import java.util.List;

import com.omniselling.common.model.BaseOperatorInfo;

public interface IWorkflowManagementService
{

    /**
     * 獲取process裡面的data, JSON 格式的String
     * @param processId
     * @return
     */
    String getProcessData(String processId);

    /**
     * 驗證workflow是否有效
     * @param wfName
     * @param graph - 是否繪圖, 默認不返回
     * @return
     */
    WorkflowValidationResult validateWorkflow(String wfName, boolean graph);

    /**
     * 驗證workflow是否有效
     * @param wfName
     * @return
     */
    WorkflowValidationResult validateWorkflow(String wfName);

    /**
     * 把workflow繪製出來,必須使用Courier New字體
     * @param wfName
     * @return
     */
    String graphWorkflow(String wfName);

    /**
     * 把workflow繪製出來,必須使用Courier New字體
     * @param wfName
     * @param from - 從指定位置繪製
     * @return
     */
    String graphWorkflowFrom(String wfName, String fromStep);

    /**
     * 終止流程, 如果此流程包含子工作流則所有的子工作流也會被終止 
     * @param processId
     * @param note
     * @param operator
     */
    void terminateProcess(String processId, String note, BaseOperatorInfo operator);

    boolean isProcessActive(String processId);

    WorkflowState getProcessState(final String schedulerInstanceId);

    /**
     * 找到一個process所有正在等待的cid
     * @param processId
     * @return
     */
    List<String> listCidByProcessId(String processId);

    /**
     * 找到一個cid對應的processId
     * @param cid
     * @return
     */
    String getProcessIdByCid(String cid);

    boolean restartErrorProcesses(List<String> processIds, BaseOperatorInfo operator);

}
