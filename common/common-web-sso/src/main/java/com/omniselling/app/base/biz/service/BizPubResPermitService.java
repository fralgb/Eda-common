package com.omniselling.app.base.biz.service;

import java.util.List;

import com.omniselling.common.enumeration.BusinessResourceType;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.ms.core.model.AccountPubResCond;
import com.omniselling.ms.core.model.AccountPubResPermission;
import com.omniselling.ms.core.model.PubResPermitCond;

/**
 * 接口：账户公共资源权限
 *
 */
public interface BizPubResPermitService
{
    /**
     * 创建账户公共资源权限
     * 
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> createResPermission(PubResPermitCond cond, BaseOperatorInfo operatorInfo);

    /**
     * 查询公共资源权限
     * 
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<AccountPubResPermission>> listPermissionByAcc(PubResPermitCond cond,
            BaseOperatorInfo operatorInfo);

    /**
     * 查询公共资源权限
     * 
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<AccountPubResPermission>> listPermissionByRes(PubResPermitCond cond,
            BaseOperatorInfo operatorInfo);

    /**
     * 查询公共资源权限
     * 
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> removeAllPermissionByAcc(PubResPermitCond cond, BaseOperatorInfo operatorInfo);

    /**
     * 查询公共资源权限
     * 
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> removeAllPermissionByRes(PubResPermitCond cond, BaseOperatorInfo operatorInfo);

    /**
     * 修改账户下的某资源类型的账户公共资源权限
     * 
     * @param accountId
     * @param resourceType
     * @param pubResPermissions
     *            如果为空或者pubResPermissions.size()==0,那么直接删除该用户的权限！否则修改账户资源权限
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> updatePermissions(Long accountId, BusinessResourceType resourceType,
            List<AccountPubResPermission> pubResPermissions, BaseOperatorInfo operatorInfo);

    /**
     * 删除账户公共资源权限
     * 
     * @param accPubResPermitIds
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> deleteAccPubResPermitByIds(List<Long> accPubResPermitIds,
            BaseOperatorInfo operatorInfo);

    /**
     * 查询账户公共资源
     * 
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<BasePageDetail<AccountPubResPermission>> listAccountPubRes(AccountPubResCond cond,
            BaseOperatorInfo operatorInfo);
}
