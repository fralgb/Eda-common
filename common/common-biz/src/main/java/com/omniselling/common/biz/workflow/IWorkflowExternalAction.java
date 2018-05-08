package com.omniselling.common.biz.workflow;

import java.io.Serializable;

/**
 * 注意基類BaseWorkflowAdapterImpl
 * @author Atomic
 *
 * @param <M>
 */
public interface IWorkflowExternalAction<M extends Serializable>
{
    /**
     * 執行動作
     * @param bizModel
     * @param cid - 系統生成的唯一id用來標識此次交互, 不強制要求使用, 如果願意也可以生成自己的cid放到ActionAck裡面
     * @return ActionAck - 如果ActionAck.needWaiting = true 則只是發起了一個任務, 需要等待調用方的回復(notify),
     *  否則 ActionAck.response是返回值
     */
    ActionAck execute(final M bizModel, final String cid);
}
