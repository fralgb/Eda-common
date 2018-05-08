package com.omniselling.common.biz.operation;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageInfo;
import com.omniselling.common.model.BizBaseModel;
import com.omniselling.common.repository.UnsupportJPAMethodException;
import com.omniselling.common.repository.UnsupportJPAModelQueryException;
import com.omniselling.common.repository.util.EntityCrudUtil;
import com.omniselling.common.repository.util.ExampleQueryRepositoryFactory;
import com.omniselling.common.repository.util.PersistentUtils4JPA;

public abstract class BizAbstractPersistentOperation<M extends BizBaseModel<I>, I>
        implements IBizPersistentOperation<M, I>
{

    @SuppressWarnings("rawtypes")
    public abstract Class<? extends BizBaseModel> getBizModelClass();

    @SuppressWarnings("unchecked")
    @Override
    public M findById(I id)
    {
        return (M) EntityCrudUtil.getById(getBizModelClass(), (Serializable) id);
    }

    @Transactional
    @Override
    public I createModelTx(M model, BaseOperatorInfo o)
    {
        if (model.getCreatedBy() == null)
        {
            model.setCreatedBy(o.getOperatorId());
        }
        if (model.getCreatedDate() == null)
        {
            model.setCreatedDate(new Date());
        }
        if (model.getModifiedBy() == null)
        {
            model.setModifiedBy(o.getOperatorId());
        }
        if (model.getModifiedDate() == null)
        {
            model.setModifiedDate(new Date());
        }
        PersistentUtils4JPA.resolvAllModel(model, true, o);
        EntityCrudUtil.save(model);
        return model.getId();
    }

    @Transactional
    @Override
    public void updateModelTx(M model, BaseOperatorInfo o)
    {
        if (model.getModifiedBy() == null)
        {
            model.setModifiedBy(o.getOperatorId());
        }
        if (model.getModifiedDate() == null)
        {
            model.setModifiedDate(new Date());
        }
        PersistentUtils4JPA.resolvAllModel(model, false, o);
        EntityCrudUtil.save(model);
    }

    @Transactional
    @Override
    public void deleteModelTx(M model, BaseOperatorInfo o)
    {
        EntityCrudUtil.deleteByEntity(model);
    }

    @Override
    public Collection<M> listByExample(M condition)
    {
        QueryByExampleExecutor<M> repo = ExampleQueryRepositoryFactory.getRepository(condition.getClass());
        if (repo == null)
        {
            throw new UnsupportJPAModelQueryException(
                    "Can't find QueryByExampleExecutor for class " + condition.getClass().getName());
        }

        Example<M> ex = Example.of(condition);
        Iterable<M> datas = repo.findAll(ex);

        return Lists.newArrayList(datas);
    }

    @Override
    public Collection<M> listByExample(M condition, BasePageInfo page)
    {
        QueryByExampleExecutor<M> repo = ExampleQueryRepositoryFactory.getRepository(condition.getClass());
        if (repo == null)
        {
            throw new UnsupportJPAModelQueryException(
                    "Can't find QueryByExampleExecutor for class " + condition.getClass().getName());
        }

        Pageable pageRequest = new PageRequest(page.getPage(), page.getRowsPerPage());

        Example<M> ex = Example.of(condition);
        Iterable<M> datas = repo.findAll(ex, pageRequest);

        return Lists.newArrayList(datas);
    }

    @Override
    public Collection<M> listByExample(M condition, int embedRelation)
    {
        return listByExample(condition);
    }

    @Override
    public Collection<M> listByExample(M condition, BasePageInfo page, int embedRelation)
    {
        return listByExample(condition, page);
    }

    @Override
    public long countByExample(M condition)
    {
        QueryByExampleExecutor<M> repo = ExampleQueryRepositoryFactory.getRepository(condition.getClass());
        if (repo == null)
        {
            throw new UnsupportJPAModelQueryException(
                    "Can't find QueryByExampleExecutor for class " + condition.getClass().getName());
        }

        Example<M> ex = Example.of(condition);

        return repo.count(ex);
    }

    @Override
    public String getDomain()
    {
        return getBizModelClass().getName();
    }

    @Override
    public void validateModel(M model)
    {
        throw new UnsupportJPAMethodException("validateModel");
    }

}
