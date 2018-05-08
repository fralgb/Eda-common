package com.omniselling.app.base.biz.service;

import java.util.List;

import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.ms.core.model.PubResource;
import com.omniselling.ms.core.model.PubResourceCond;

/**
 * 接口：公共资源管理
 *
 */
public interface BizPubRessourceService
{

    /**
     * 查询公共资源
     * 
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<BasePageDetail<PubResource>> listPubResources(PubResourceCond cond,
            BaseOperatorInfo operatorInfo);

    /**
     * 查询公共资源
     * 
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<BasePageDetail<PubResource>> listPubResourcesByCond(PubResourceCond cond,
            BaseOperatorInfo operatorInfo);

    /**
     * 查询公共资源根据公共资源编号
     * 
     * @param resourceId
     * @param operatorInfo
     * @return
     */
    public BaseResponse<PubResource> getPubResourceById(Long resourceId, BaseOperatorInfo operatorInfo);

    /**
     * 查询公共资源管理
     * 
     * @param publishNum
     * @param operatorInfo
     * @return
     */
    public BaseResponse<PubResource> getPubResourceByPubNum(String publishNum, BaseOperatorInfo operatorInfo);

    /**
     * 创建公共资源
     * 
     * @param pubResource
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> createPubResource(PubResource pubResource, BaseOperatorInfo operatorInfo);

    /**
     * 修改公共资源
     * 
     * @param pubResource
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> updatePubResource(PubResource pubResource, BaseOperatorInfo operatorInfo);

    /**
     * 激活公共资源根据公共资源编号
     * 
     * @param resourceIds
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> activePubResourcesById(List<Long> resourceIds, BaseOperatorInfo operatorInfo);

    /**
     * 禁用公共资源根据公共资源编号
     * 
     * @param resourceIds
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> disablePubResourcesById(List<Long> resourceIds, BaseOperatorInfo operatorInfo);

    /**
     * 查询公共资源类型
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<BizEnumSpec>> listResourceTypes(BaseOperatorInfo operatorInfo);

    /**
     * 查询公共资源状态
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<BizEnumSpec>> listPubResourceStatus(BaseOperatorInfo operatorInfo);

}
