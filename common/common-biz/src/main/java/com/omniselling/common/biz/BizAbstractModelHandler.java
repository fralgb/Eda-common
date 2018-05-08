package com.omniselling.common.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.omniselling.common.biz.operation.IBizDomainOperation;
import com.omniselling.common.enumeration.OmniEventAction;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BizBaseModel;

@Scope("prototype")
public abstract class BizAbstractModelHandler<M extends BizBaseModel<I>, I> implements IBizModelHandler<M, I>
{
    private M model;
    private List<IBizDomainOperation> operations = new ArrayList<>();

    // private Map<String, StepAction> statusActionMap; // should have a statusAction, it has a status and its action will be null;

    @Override
    final public boolean hasOperation(Class<? extends IBizDomainOperation> opClass)
    {
        for (IBizDomainOperation usedOperator : operations)
        {
            if (usedOperator.getClass().getName().equals(opClass.getName()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    final public String startProcess(String wfName, BaseOperatorInfo o)
    {

        return getWorkflowOperation().startProcess(model, wfName, o);
    }

    @Override
    final public boolean hasOperation(String domain)
    {
        for (IBizDomainOperation op : operations)
        {
            if (op.getDomain().equals(domain))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    final public I createModelTx(BaseOperatorInfo o)
    {
        model.setCreatedBy(o.getOperatorId());
        model.setCreatedDate(new Date());
        model.setModifiedBy(o.getOperatorId());
        model.setModifiedDate(new Date());

        I id = getPersistentOperation().createModelTx(model, o);
        if (getEventLogOperation() != null)
            getEventLogOperation().logEvent(OmniEventAction.INIT, model, null, null, null, o);
        return id;
    }
    
    @Override
    final public I createModelTxNew(M m,BaseOperatorInfo o)
    {
        m.setCreatedBy(o.getOperatorId());
        m.setCreatedDate(new Date());
        m.setModifiedBy(o.getOperatorId());
        m.setModifiedDate(new Date());

        I id = getPersistentOperation().createModelTx(m, o);
        if (getEventLogOperation() != null)
            getEventLogOperation().logEvent(OmniEventAction.INIT, m, null, null, null, o);
        return id;
    }


    /*
    @Override
    public M findById(I id)
    {
    	return getPersistentOperation().findById(id);
    }
    */

    @Override
    public void updateModel(BaseOperatorInfo o)
    {
        model.setModifiedBy(o.getOperatorId());
        model.setModifiedDate(new Date());

        getPersistentOperation().updateModelTx(model, o);
    }

    @Override
    public void updateModelX(M m, BaseOperatorInfo o)
    {
        m.setModifiedBy(o.getOperatorId());
        m.setModifiedDate(new Date());

        getPersistentOperation().updateModelTx(m, o);
    }

    @Override
    public void deleteModel(BaseOperatorInfo o)
    {
        getPersistentOperation().deleteModelTx(model, o);
    }

    @Override
    public void deleteModelX(M m,BaseOperatorInfo o)
    {
        getPersistentOperation().deleteModelTx(m, o);
    }

    
    //abstract protected void 
    @Override
    public M getModel()
    {
        return model;
    }

    @Override
    public void setModel(M model)
    {
        this.model = model;
    }

    @Override
    public void validateModel()
    {
        getPersistentOperation().validateModel(model);
    }

    @Override
    public String startProcess(String wfName, String pid, BaseOperatorInfo o)
    {
        return getWorkflowOperation().startProcess(model, wfName, pid, o);
    }

}
