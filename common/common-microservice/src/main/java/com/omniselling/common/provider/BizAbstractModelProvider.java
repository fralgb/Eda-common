package com.omniselling.common.provider;

import java.util.Collection;

import javax.annotation.Resource;

import com.omniselling.common.biz.IBizModelHandler;
import com.omniselling.common.biz.IBizModelRepository;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BizBaseModel;
import com.omniselling.microservice.MicroService;

/** 
 * @author xslong 
 * @version 创建时间：Dec 23, 2016 11:16:04 AM 
 * 
*/

public abstract class BizAbstractModelProvider<M extends BizBaseModel<I>, I> implements IBizModelProvider<M, I>
{

    protected abstract IBizModelHandler<M, I> getBizModelHandler();

    protected abstract Class<M> getBizModelClass();

    @Resource
    private IBizModelRepository bizModelRepository;

    @Override
    @MicroService
    public String startProcess(M bizModel, String wfName, BaseOperatorInfo o)
    {
        IBizModelHandler<M, I> bizModelHandler = getBizModelHandler();
        bizModelHandler.setModel(bizModel);
        return bizModelHandler.startProcess(wfName, o);
    }

    @Override
    @MicroService
    public I createModelTx(M bizModel, BaseOperatorInfo o)
    {
        IBizModelHandler<M, I> bizModelHandler = getBizModelHandler();
        bizModelHandler.setModel(bizModel);
        return bizModelHandler.createModelTx(o);
    }

    @Override
    @MicroService
    public I createModelTxNew(M bizModel, BaseOperatorInfo o)
    {
        IBizModelHandler<M, I> bizModelHandler = getBizModelHandler();
        return bizModelHandler.createModelTxNew(bizModel, o);
    }

    @Override
    @MicroService
    public M findById(I id)
    {
        return bizModelRepository.findById(getBizModelClass(), id);
    }

    @Override
    @MicroService
    public void updateModel(M model, BaseOperatorInfo o)
    {
        IBizModelHandler<M, I> bizModelHandler = getBizModelHandler();
        bizModelHandler.setModel(model);
        bizModelHandler.updateModel(o);
    }

    @Override
    @MicroService
    public void updateModelX(M model, BaseOperatorInfo o)
    {
        IBizModelHandler<M, I> bizModelHandler = getBizModelHandler();
        bizModelHandler.updateModelX(model, o);
    }

    @Override
    @MicroService
    public void deleteModel(M model, BaseOperatorInfo o)
    {
        IBizModelHandler<M, I> bizModelHandler = getBizModelHandler();
        bizModelHandler.setModel(model);
        bizModelHandler.deleteModel(o);
    }

    @Override
    @MicroService
    public void deleteModelX(M model, BaseOperatorInfo o)
    {
        IBizModelHandler<M, I> bizModelHandler = getBizModelHandler();
        bizModelHandler.deleteModelX(model, o);
    }

    @Override
    @MicroService
    public Collection<M> listByExample(M example)
    {
        return bizModelRepository.listByExample(getBizModelClass(), example);
    }

    @Override
    @MicroService
    public long countByExample(M example)
    {
        return bizModelRepository.countByExample(getBizModelClass(), example);
    }

}
