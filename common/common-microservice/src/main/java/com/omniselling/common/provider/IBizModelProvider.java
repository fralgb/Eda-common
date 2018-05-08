package com.omniselling.common.provider;

import java.util.Collection;

import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BizBaseModel;

/** 
 * @author xslong 
 * @version 创建时间：Dec 23, 2016 11:10:28 AM 
 * 
*/

public interface IBizModelProvider<M extends BizBaseModel<I>, I>
{

    String startProcess(M bizModel, String wfName, BaseOperatorInfo o);

    I createModelTx(M bizModel, BaseOperatorInfo o);

    I createModelTxNew(M bizModel, BaseOperatorInfo o);

    M findById(I id);

    void updateModel(M model, BaseOperatorInfo o);

    void updateModelX(M model, BaseOperatorInfo o);

    void deleteModel(M model, BaseOperatorInfo o);

    void deleteModelX(M model, BaseOperatorInfo o);

    Collection<M> listByExample(M example);

    long countByExample(M example);

}
