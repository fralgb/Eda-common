package com.omniselling.common.biz;

import java.util.Map;

import com.omniselling.common.biz.auditlog.IBizAuditLogService;
import com.omniselling.common.biz.operation.IBizDomainOperation;
import com.omniselling.common.biz.operation.IBizPersistentOperation;
import com.omniselling.common.biz.operation.IBizWorkflowOperation;
import com.omniselling.common.biz.workflow.StepAction;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BizBaseModel;

/** 
 * @author xslong 
 * @version 创建时间：Dec 23, 2016 10:34:39 AM 
 * 
*/

public interface IBizModelHandler<M extends BizBaseModel<I>, I>
{

	M getModel();

	void setModel(M model);

	boolean hasOperation(Class<? extends IBizDomainOperation> opClass);

	boolean hasOperation(String domain);

    String startProcess(String wfName, BaseOperatorInfo o);

    /**
     * 
     * @param wfName
     * @param pid 開啟工作流并指定此工作流的pid
     * @param o
     * @return
     */
    String startProcess(String wfName, String pid, BaseOperatorInfo o);

	IBizPersistentOperation<M, I> getPersistentOperation();

	IBizAuditLogService<M, I> getEventLogOperation();

    IBizWorkflowOperation<M> getWorkflowOperation();
	
	Map<String, StepAction> getStatusActionMap();
	
	//  PersistentOperation

	I createModelTx(BaseOperatorInfo o);
	
	I createModelTxNew(M m,BaseOperatorInfo o);
	
	//	public M findById(I id);

    void updateModel(BaseOperatorInfo o);
    
    void updateModelX(M m,BaseOperatorInfo o);

    void deleteModel(BaseOperatorInfo o);
    
    void deleteModelX(M m,BaseOperatorInfo o);

    void validateModel();

   // Collection<M> listByCondition(M condition);

   // long countModels(M condition);

}
