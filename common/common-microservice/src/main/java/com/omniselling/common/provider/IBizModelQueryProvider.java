package com.omniselling.common.provider;

import java.util.Collection;

import com.omniselling.common.model.BaseCondition;
import com.omniselling.common.model.BizBaseModel;

public interface IBizModelQueryProvider<M extends BizBaseModel<I>, S extends BaseCondition<I>,I>
{
    public long countModels(S condition);

    public Collection<M> listByCondition(S condition);

}
