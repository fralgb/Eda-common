package com.omniselling.common.repository;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.omniselling.common.biz.IBizModelRepository;
import com.omniselling.common.model.BasePageInfo;
import com.omniselling.common.model.BizBaseModel;
import com.omniselling.common.repository.util.EntityCrudUtil;
import com.omniselling.common.repository.util.ExampleQueryRepositoryFactory;

@Service("jpaRepository")
public class JPARepositoryImpl implements IBizModelRepository
{

    @Override
    public <M extends BizBaseModel<ID>, ID> M findById(Class<M> bizModelClass, ID id)
    {

        return EntityCrudUtil.getById(bizModelClass, (Serializable) id);
    }

    @Override
    public <M extends BizBaseModel<ID>, ID> Collection<M> listByExample(Class<M> bizModelClass, M example)
    {

        QueryByExampleExecutor<M> executor = ExampleQueryRepositoryFactory.getRepository(bizModelClass);
        if (executor == null)
        {
            throw new UnsupportJPAModelQueryException(
                    "Can't find QueryByExampleExecutor for class " + bizModelClass.getName());
        }
        Example<M> ex = Example.of(example);
        Iterable<M> datas = executor.findAll(ex);

        return Lists.newArrayList(datas);

    }

    @Override
    public <M extends BizBaseModel<ID>, ID> Collection<M> listByExample(Class<M> bizModelClass, M example,
            BasePageInfo basePageInfo)
    {
        QueryByExampleExecutor<M> executor = ExampleQueryRepositoryFactory.getRepository(bizModelClass);
        if (executor == null)
        {
            throw new UnsupportJPAModelQueryException(
                    "Can't find QueryByExampleExecutor for class " + bizModelClass.getName());
        }
        Example<M> ex = Example.of(example);
        Pageable page = new PageRequest(basePageInfo.getPage(), basePageInfo.getRowsPerPage());
        Iterable<M> datas = executor.findAll(ex, page);
        return Lists.newArrayList(datas);
    }

    @Override
    public <M extends BizBaseModel<ID>, ID> long countByExample(Class<M> bizModelClass, M example)
    {
        QueryByExampleExecutor<M> executor = ExampleQueryRepositoryFactory.getRepository(bizModelClass);
        if (executor == null)
        {
            throw new UnsupportJPAModelQueryException(
                    "Can't find QueryByExampleExecutor for class " + bizModelClass.getName());
        }
        Example<M> ex = Example.of(example);

        return executor.count(ex);
    }

}
