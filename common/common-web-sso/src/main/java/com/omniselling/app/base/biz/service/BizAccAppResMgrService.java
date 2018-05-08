package com.omniselling.app.base.biz.service;

import java.util.List;

import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.ms.core.model.AccountAppResCond;
import com.omniselling.ms.core.model.AccountAppResPermission;
import com.omniselling.ms.core.model.AppResPermitCond;

public interface BizAccAppResMgrService
{

    /**
     * 查询账号资源权限信息
     * 
     * @param viewRoleAppResCondition
     *            根据 roleName,resourceName,resourceType 查询角色资源信息
     * @param operatorInfo
     * @return 查询成功，返回 ViewRoleAppResource 集合或者 null，查询失败，返回错误信息
     */
    BaseResponse<BasePageDetail<AccountAppResPermission>> listAccountAppRes(AccountAppResCond accountAppRes,
            BaseOperatorInfo operatorInfo);

    /**
     * 根据 accAppresIds 删除账号资源权限信息
     * 
     * @param roleappresIds
     *            角色id
     * @param operatorInfo
     * @return 删除成功，返回 true，删除失败，返回false
     */
    BaseResponse<Boolean> deleteRoleAppRes(List<Long> accAppresIds, BaseOperatorInfo operatorInfo);

    /**
     * 添加账号资源权限
     * 
     * @param viewaccount
     *            角色及资源信息
     * @param operatorInfo
     * @return 添加成功，返回true；添加失败，返回false
     */
    BaseResponse<Boolean> addAccountAppRes(AppResPermitCond accRoleAppRes, BaseOperatorInfo operatorInfo);
    
    /**
     * 查询应用资源权限类型
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<BizEnumSpec>> listPermitTypes(BaseOperatorInfo operatorInfo);
}
