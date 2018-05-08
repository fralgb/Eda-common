package com.omniselling.common.biz.operation;

import java.io.Serializable;

import com.omniselling.common.biz.workflow.WorkflowState;
import com.omniselling.common.model.BaseOperatorInfo;

public interface IBizWorkflowOperation<M extends Serializable> extends IBizDomainOperation
{

    static String DOMAIN_NAME = "WorkFlow";

    /**
     * 检查是否工作流已完成处理
     * @param processId
     * @return
     */
    boolean isFinished(String processId);

    /**
     * 检查是否能被取消(未实现)
     * @param processId
     * @return
     */
    boolean isCancelleable(String processId);

    /**
     * 启动工作流, 名称为wfName, M是业务模型, 也可以只是一个String的businessNum, 自己在Action里面去使用
     * @param model
     * @param wfName
     * @param operator
     * @return
     */
    String startProcess(M model, String wfName, BaseOperatorInfo operator);

    /**
     * 启动工作流并指定工作流pid, 注意pid需要唯一
     * @param model
     * @param wfName
     * @param pid
     * @param operator
     * @return
     */
    String startProcess(M model, String wfName, String pid, BaseOperatorInfo operator);

    /**
     * 从工作流的一个特定步骤stepName启动工作流,一般用于补救无法recover的工作流
     * @param model
     * @param wfName
     * @param firstStep 特定步骤
     * @param operator
     * @return
     */
    String startProcessFromStep(M model, String wfName, String firstStep, BaseOperatorInfo operator);

    /**
     * 从工作流的一个特定步骤stepName启动工作流,一般用于补救无法recover的工作流
     * @param model 业务模型
     * @param wfName 步骤名
     * @param pid 工作流instanceId
     * @param firstStep 特定步骤
     * @param operator
     * @return
     */
    String startProcessFromStep(M model, String wfName, String firstStep, String pid, BaseOperatorInfo operator);

    /**
     * 暂停工作流, 暂停的工作流不会往下处理action了, 所以不要发response
     * @param processId
     * @param operator
     */
    void suspendProcess(String processId, BaseOperatorInfo operator);

    /**
     * 恢复暂停的工作流
     * @param processId
     * @param operator
     */
    void resumeProcess(String processId, BaseOperatorInfo operator);

    /**
     * 获取工作流状态
     * @param schedulerInstanceId
     * @return
     */
    WorkflowState getProcessState(final String schedulerInstanceId);

    //
    //    /**
    //     * 一段時間以後啟動工作流, 指定pid, 注意, 不一定能啟動成功, 目前能指定1-30秒
    //     * @param model
    //     * @param wfName
    //     * @param pid
    //     * @param delaySec - 1~30
    //     * @param operator
    //     * @return processId
    //     */
    //    String scheduleProcess(M model, String wfName, String pid, int delaySec, BaseOperatorInfo operator);
    //
    //    /**
    //     * 一段時間以後啟動工作流, 指定pid, 注意, 不一定能啟動成功,目前能指定1-30秒
    //     * @param model
    //     * @param wfName
    //     * @param delaySec - 1~30
    //     * @param operator
    //     * @return processId
    //     */
    //    String scheduleProcess(M model, String wfName, int delaySec, BaseOperatorInfo operator);

}
