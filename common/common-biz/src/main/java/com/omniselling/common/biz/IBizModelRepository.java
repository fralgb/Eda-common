package com.omniselling.common.biz;

import java.util.Collection;

import com.omniselling.common.model.BasePageInfo;
import com.omniselling.common.model.BizBaseModel;

/**
 * 定义业务模型查询
 * 
 * @author xslong
 * @time Dec 28, 2016 7:27:35 PM
 */
public interface IBizModelRepository
{

    /**
     * 根据ID查询，会把业务模型中嵌套的其它业务模型都找到
     * 
     * @param bizModelClass
     * @param id
     * @return
     */
    <M extends BizBaseModel<ID>, ID> M findById(Class<M> bizModelClass, ID id);

    /**
     * 把condition不为空的属性当做条件查询
     * 
     * @param bizModelClass
     * @param condition
     * @param loadEmbedBizModel
     *            是否加载被嵌套的业务模型
     * @return
     * 
     *         <M extends BizBaseModel<ID>, ID> Collection<M> listByExample(Class<M> bizModelClass, M example , boolean
     *         loadEmbedBizModel);
     */

    <M extends BizBaseModel<ID>, ID> Collection<M> listByExample(Class<M> bizModelClass, M example);

    /**
     * 把condition不为空的属性当做条件查询，会把业务模型中嵌套的其它业务模型都找到
     * 
     * @param bizModelClass
     * @param condition
     * @param basePageInfo
     *            分页信息
     * @param loadEmbedBizModel
     *            是否加载被嵌套的业务模型
     * @return
     * 
     *         <M extends BizBaseModel<ID>, ID> Collection<M> listByExample(Class<M> bizModelClass, M example,
     *         BasePageInfo basePageInfo , boolean loadEmbedBizModel);
     */
    <M extends BizBaseModel<ID>, ID> Collection<M> listByExample(Class<M> bizModelClass, M example,
            BasePageInfo basePageInfo);

    <M extends BizBaseModel<ID>, ID> long countByExample(Class<M> bizModelClass, M example);

}
