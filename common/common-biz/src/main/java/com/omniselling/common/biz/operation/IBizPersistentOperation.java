package com.omniselling.common.biz.operation;

import java.util.Collection;

import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageInfo;
import com.omniselling.common.model.BizBaseModel;

public interface IBizPersistentOperation<M extends BizBaseModel<I>, I> extends IBizDomainOperation
{
	M findById(I id);

	I createModelTx(M model, BaseOperatorInfo o);

	void updateModelTx(M model, BaseOperatorInfo o);

	void deleteModelTx(M model, BaseOperatorInfo o);

    void validateModel(M model);

	Collection<M> listByExample(M example);
	
	Collection<M> listByExample(M example , int embedRelation);

	Collection<M> listByExample(M example, BasePageInfo page);
	
	Collection<M> listByExample(M example, BasePageInfo page, int embedRelation);

	long countByExample(M example);
}
