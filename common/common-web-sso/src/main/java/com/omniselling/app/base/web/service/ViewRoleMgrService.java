/**
 * 
 */
package com.omniselling.app.base.web.service;

import java.util.List;

import com.omniselling.app.base.web.model.ViewAccountRole;
import com.omniselling.app.base.web.model.ViewAccountRoleCondition;
import com.omniselling.app.base.web.model.ViewAccountRoleRefAccount;
import com.omniselling.app.base.web.model.ViewAcountRoleRefAppResource;
import com.omniselling.app.base.web.model.ViewEnumSpec;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;

/**
 * 角色管理接口
 * 
 * @author baby
 * 
 * 
 *
 */
public interface ViewRoleMgrService
{

    /**
     * 根据查询条件获取所有role
     * 
     * @param viewAccountRoleCondition
     * @param basePerPageInfo
     * @return
     */
    public BaseResponse<BasePageDetail<ViewAccountRole>> listRoles(ViewAccountRoleCondition viewAccountRoleCondition,
            BaseOperatorInfo operatorInfo);

    /**
     * 保存对角色的修改
     * 
     * @param viewAccountRole
     * @param basePerPageInfo
     * @return
     */
    public BaseResponse<Boolean> saveRole(ViewAccountRole viewAccountRole, BaseOperatorInfo operatorInfo);

    /**
     * 查看角色信息
     * 
     * @param accountRoleId
     * @param basePerPageInfo
     * @return
     */
    public BaseResponse<ViewAccountRole> getRoleDetail(Long accountRoleId, BaseOperatorInfo operatorInfo);

    /**
     * 删除角色信息
     * 
     * @param accountRoleId
     * @param basePerPageInfo
     * @return
     */
    public BaseResponse<Boolean> deleteRole(Long accountRoleId, BaseOperatorInfo operatorInfo);

    /**
     * 获取拥有某角色的主帐号
     * 
     * @param accountRoleId
     * @param basePerPageInfo
     * @return
     */
    public BaseResponse<ViewAccountRoleRefAccount> fetchAccountByRole(Long accountRoleId, BaseOperatorInfo operatorInfo);

    /**
     * 保存对角色对应主帐号的修改
     * 
     * @param viewAcountRoleRefAccount
     * @param basePerPageInfo
     * @return
     */
    public BaseResponse<Boolean> saveRoleRefAccount(ViewAccountRoleRefAccount viewAcountRoleRefAccount,
            BaseOperatorInfo operatorInfo);

    /**
     * 激活指定角色
     * 
     * @param accountRoleId
     * @param basePerPageInfo
     * @return
     */
    public BaseResponse<Boolean> activeRole(Long accountRoleId, BaseOperatorInfo operatorInfo);

    /**
     * 禁用指定角色
     * 
     * @param accountRoleId
     * @param basePerPageInfo
     * @return
     */
    public BaseResponse<Boolean> disableRole(Long accountRoleId, BaseOperatorInfo operatorInfo);

    /**
     * 获取指定角色的应用权限资源
     * 
     * @param accountRoleId
     *            账户角色id
     * @param basePerPageInfo
     *            前端传入model
     * @return
     */
    public BaseResponse<ViewAcountRoleRefAppResource> fetchAppResourcesByRole(Long accountRoleId,
            BaseOperatorInfo operatorInfo);

    /**
     * 保存指定角色的权限资源
     * 
     * @param viewAcountRoleRefAppResource
     *            角色资源权限信息
     * @param basePerPageInfo
     *            前端传入model
     * @return
     */
    public BaseResponse<Boolean> saveRoleRefAppResources(ViewAcountRoleRefAppResource viewAcountRoleRefAppResource,
            BaseOperatorInfo operatorInfo);

    /**
     * 查询角色状态
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<ViewEnumSpec>> listAccountroleStatus(BaseOperatorInfo operatorInfo);
}
