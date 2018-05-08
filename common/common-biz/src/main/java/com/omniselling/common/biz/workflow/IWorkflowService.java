package com.omniselling.common.biz.workflow;

import java.io.Serializable;

import com.omniselling.common.biz.operation.IBizWorkflowOperation;

/**
 * 此interface跟IBizWorkflowOperation一樣, 單純是為了命名
 * @author Atomic
 *
 * @param <M>
 */
public interface IWorkflowService<M extends Serializable> extends IBizWorkflowOperation<M>
{

}
