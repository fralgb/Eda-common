package com.omniselling.app.base.web.service;

import java.util.List;

import com.omniselling.app.base.web.model.ViewEnumSpec;
import com.omniselling.app.base.web.model.ViewPublicResource;
import com.omniselling.app.base.web.model.ViewPublicResourceCondition;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;

/**
 * 接口: 公共资源管理
 * 
 * @author Administrator
 *
 */
public interface ViewPubResMgrService
{
    /**
     * 查询公共资源
     * 
     * @param condition
     * @param operatorInfo
     * @return
     */
    public BaseResponse<BasePageDetail<ViewPublicResource>> listResources(ViewPublicResourceCondition condition,
            BaseOperatorInfo operatorInfo);

    //    /**
    //     * 查询公共资源
    //     * 
    //     * @param condition
    //     *            查询条件
    //     * @return 公共资源列表或者错误信息
    //     */
    //    public BaseResponse<List<ViewPublicResource>> listResourcesByCond(ViewPublicResourceCondition condition,
    //            BaseOperatorInfo operatorInfo);

    /**
     * 查询公共资源根据公共资源编号
     * 
     * @param pubResourceId
     *            公共资源id
     * @return 公共资源详细信息或者错误信息
     */
    public BaseResponse<ViewPublicResource> resourceDetail(Long pubResourceId, BaseOperatorInfo operatorInfo);

    /**
     * 激活公共资源根据公共资源编号
     * 
     * @param resourceIds
     *            资源ids
     * @return true 激活/禁用成功，false 激活/禁用失败
     */
    public BaseResponse<Boolean> activeResource(List<Long> resourceIds, BaseOperatorInfo operatorInfo);

    /**
     * 禁用公共资源根据公共资源编号
     * 
     * @param resourceIds
     *            资源ids
     * @return true 禁用成功，false 禁用失败
     */
    public BaseResponse<Boolean> disableResource(List<Long> resourceIds, BaseOperatorInfo operatorInfo);

    /**
     * 查询公共资源类型
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<ViewEnumSpec>> listResourceTypes(BaseOperatorInfo operatorInfo);

    /**
     * 查询公共资源状态
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<ViewEnumSpec>> listPubResourceStatus(BaseOperatorInfo operatorInfo);

    //    /**
    //     * 删除公共资源权限
    //     * 
    //     * @param accPubResPermitIds
    //     *            账户公共资源权限编号
    //     * @param operatorInfo
    //     * @return
    //     */
    //    public BaseResponse<Boolean> deleteAccountPubRes(List<Long> accPubResPermitIds, BaseOperatorInfo operatorInfo);
    //
    //    /**
    //     * 添加账户公共资源权限
    //     * 
    //     * @param cond
    //     * @param operatorInfo
    //     * @return
    //     */
    //    public BaseResponse<Boolean> addAccountPubRes(ViewAccountPubResource cond, BaseOperatorInfo operatorInfo);
    //
    //    /**
    //     * 查询账号公共资源
    //     * 
    //     * @param condition
    //     * @param operatorInfo
    //     * @return
    //     */
    //    public BaseResponse<BasePageDetail<ViewAccountPubResource>> listAccountPubRes(ViewAccountPubResCondition condition,
    //            BaseOperatorInfo operatorInfo);

}
