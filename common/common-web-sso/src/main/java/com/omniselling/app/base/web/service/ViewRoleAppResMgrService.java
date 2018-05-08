package com.omniselling.app.base.web.service;

import java.util.List;

import com.omniselling.app.base.web.model.ViewAcountRoleRefAppResource;
import com.omniselling.app.base.web.model.ViewRoleAppResCondition;
import com.omniselling.app.base.web.model.ViewRoleAppResource;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;

public interface ViewRoleAppResMgrService
{

    /**
     * 查询角色资源权限信息
     * 
     * @param viewRoleAppResCondition
     *            根据 roleName,resourceName,resourceType 查询角色资源信息
     * @param operatorInfo
     * @return 查询成功，返回 ViewRoleAppResource 集合或者 null，查询失败，返回错误信息
     */
    BaseResponse<BasePageDetail<ViewRoleAppResource>> listRoleAppRes(ViewRoleAppResCondition viewRoleAppResCondition,
            BaseOperatorInfo operatorInfo);

    /**
     * 根据 roleIds 删除角色资源权限信息
     * 
     * @param roleappresIds
     *            角色id
     * @param operatorInfo
     * @return 删除成功，返回 true，删除失败，返回false
     */
    BaseResponse<Boolean> deleteRoleAppRes(List<Long> roleappresIds, BaseOperatorInfo operatorInfo);

    /**
     * 添加角色资源权限
     * 
     * @param viewaccount
     *            角色及资源信息
     * @param operatorInfo
     * @return 添加成功，返回true；添加失败，返回false
     */
    BaseResponse<Boolean> addRoleAppRes(ViewAcountRoleRefAppResource viewaccount, BaseOperatorInfo operatorInfo);
}
