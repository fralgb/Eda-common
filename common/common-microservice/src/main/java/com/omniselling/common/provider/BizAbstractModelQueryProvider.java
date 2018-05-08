package com.omniselling.common.provider;

import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.google.common.collect.Lists;
import com.omniselling.common.model.BaseCondition;
import com.omniselling.common.model.BizBaseModel;
import com.omniselling.common.repository.util.QueryDSLRepositoryFactory;
import com.omniselling.microservice.MicroService;
import com.querydsl.core.types.Predicate;

/**
 * @author xslong
 * @version 创建时间：Dec 23, 2016 11:16:04 AM
 * 
 */

public abstract class BizAbstractModelQueryProvider<M extends BizBaseModel<I>, S extends BaseCondition<I>, I>
        extends BizAbstractModelProvider<M, I>implements IBizModelProvider<M, I>, IBizModelQueryProvider<M, S, I>
{
    @MicroService
    @Override
    public Collection<M> listByCondition(S condition)
    {
        Iterable<M> datas = null;
        QueryDslPredicateExecutor<M> executor = QueryDSLRepositoryFactory.getRepository(getBizModelClass());
        if (condition.getPage() != null)
        {
            Pageable page = new PageRequest(condition.getPage(), condition.getRowsPerPage());
            datas = executor.findAll(buildPredicate(condition), page);
        }
        else
        {
            datas = executor.findAll(buildPredicate(condition));
        }
        if (datas == null)
        {
            return null;
        }
        return Lists.newArrayList(datas);
    }

    @MicroService
    @Override
    public long countModels(S condition)
    {
        Predicate exp = buildPredicate(condition);
        return QueryDSLRepositoryFactory.getRepository(getBizModelClass()).count(exp);
    }

    protected abstract Predicate buildPredicate(S condition);
}
