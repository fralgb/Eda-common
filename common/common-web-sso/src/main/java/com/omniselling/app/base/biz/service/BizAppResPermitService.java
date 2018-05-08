package com.omniselling.app.base.biz.service;

import java.util.List;

import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.ms.core.model.AccountAppResPermission;
import com.omniselling.ms.core.model.AppResPermitCond;
import com.omniselling.ms.core.model.AppResource;
import com.omniselling.ms.core.model.CheckAppResPermitCond;
import com.omniselling.ms.core.model.RoleAppResPermission;
import com.omniselling.ms.core.model.RoleAppResUpdateCond;

/**
 * 接口：系统应用资源权限 关联角色和账户
 *
 */
public interface BizAppResPermitService
{
    /**
     * 检查指定账户，角色是否拥有某资源的权限
     * 
     * @param cond
     *            账户，角色资源信息
     * @param operatorInfo
     *            前端传入参数
     * @return 操作成功，返回 true；操作失败，返回false
     */
    public BaseResponse<Boolean> checkResPermission(CheckAppResPermitCond cond, BaseOperatorInfo operatorInfo);

    /**
     * 创建角色资源权限信息
     * 
     * @param accRoleAppRes
     *            角色资源model
     * @param operatorInfo
     *            前端传入参数
     * @return 操作成功，返回 true；操作失败，返回false
     */
    public BaseResponse<Boolean> createRoleAppResPermissions(AppResPermitCond accRoleAppRes,
            BaseOperatorInfo operatorInfo);

    /**
     * 创建账户资源权限信息
     * 
     * @param accRoleAppRes
     *            账户资源权限model
     * @param operatorInfo
     *            前端传入参数
     * @return 操作成功，返回 true；操作失败，返回false
     */
    public BaseResponse<Boolean> createAccAppResPermissions(AppResPermitCond accRoleAppRes, BaseOperatorInfo operatorInfo);

    /**
     * 查询账户相应资源(应用名：appName可以指定 资源类型：resourceName)下被允许的资源权限
     * 
     * @param cond
     *            资源信息model
     * @param operatorInfo
     *            前端传入参数
     * @return 查询成功，返回 List<AppResource> 集合 或者null；查询失败，返回error
     */
    public BaseResponse<List<AppResource>> listAllowAppResourcesByAcc(CheckAppResPermitCond cond,
            BaseOperatorInfo operatorInfo);

    /**
     * 查询角色相应资源(应用名：appName可以指定 资源类型：resourceName)下被允许的资源权限
     * 
     * @param cond
     *            资源信息model
     * @param operatorInfo
     *            前端传入参数
     * @return 查询成功，返回 List<AppResource> 集合 或者null；查询失败，返回error
     */
    public BaseResponse<List<AppResource>> listAllowAppResourcesByRole(CheckAppResPermitCond cond,
            BaseOperatorInfo operatorInfo);

    /**
     * 查询指定资源下所有的角色资源权限信息
     * 
     * @param accRoleAppRes
     *            资源id或者ids
     * @param operatorInfo
     *            前端传入参数
     * @return 查询成功，返回 List<RoleAppResPermission> 集合 或者null；查询失败，返回error
     */
    public BaseResponse<List<RoleAppResPermission>> listRoleResPermissionsByRes(AppResPermitCond accRoleAppRes,
            BaseOperatorInfo operatorInfo);

    /**
     * 查询指定角色下所有的角色资源权限信息
     * 
     * @param accRoleAppRes
     *            角色id或ids
     * @param operatorInfo
     *            前端传入参数
     * @return 查询成功，返回 List<RoleAppResPermission> 集合 或者null；查询失败，返回error
     */
    public BaseResponse<List<RoleAppResPermission>> listRoleResPermissionsByRole(AppResPermitCond accRoleAppRes,
            BaseOperatorInfo operatorInfo);

    /**
     * 查询指定账户下所有的账户资源权限信息
     * 
     * @param accRoleAppRes
     *            账户id或者ids
     * @param operatorInfo
     *            前端传入参数
     * @return 查询成功，返回 List<AccountAppResPermission> 集合 或者null；查询失败，返回error
     */
    public BaseResponse<List<AccountAppResPermission>> listAccResPermissionsByAcc(AppResPermitCond accRoleAppRes,
            BaseOperatorInfo operatorInfo);

    /**
     * 删除角色系统应用资源权限
     * 
     * @param accRoleAppRes
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> removeAllPermissionsByRes(AppResPermitCond accRoleAppRes, BaseOperatorInfo operatorInfo);

    /**
     * 删除角色系统应用资源权限
     * 
     * @param accRoleAppRes
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> removeAllPermissionsByRole(AppResPermitCond accRoleAppRes, BaseOperatorInfo operatorInfo);

    /**
     * 删除账户下系统应用资源权限
     * 
     * @param accRoleAppRes
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> removeAllPermissionsByAcc(AppResPermitCond accRoleAppRes, BaseOperatorInfo operatorInfo);

    /**
     * 修改角色系统应用资源权限
     * @param roleAppResPermissions
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> updateRoleAppResPermissions(Long accountRoleId,RoleAppResUpdateCond roleAppResUpdateCond,
            BaseOperatorInfo operatorInfo);

    /**
     * 修改账户系统应用资源权限
     * 
     * @param accountAppResPermissions
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> updateAccAppResPermissions(Long accountId,List<AccountAppResPermission> accountAppResPermissions,
            BaseOperatorInfo operatorInfo);

}
