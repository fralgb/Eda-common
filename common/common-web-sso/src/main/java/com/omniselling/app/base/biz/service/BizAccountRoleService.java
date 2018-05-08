package com.omniselling.app.base.biz.service;

import java.util.List;

import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.ms.core.model.Account;
import com.omniselling.ms.core.model.AccountRefRolesCond;
import com.omniselling.ms.core.model.AccountRole;
import com.omniselling.ms.core.model.AccountRoleCond;
import com.omniselling.ms.core.model.AccountRoleIdNameCond;
import com.omniselling.ms.core.model.RoleRefAccountsCond;

/**
 * 
 * 账号角色服务
 *
 */
public interface BizAccountRoleService
{
    /**
     * 根据条件查询账号角色信息
     * 
     * @param cond
     *            查询条件
     * @param operatorInfo
     * @return
     */
    public BaseResponse<BasePageDetail<AccountRole>> listRoles(AccountRoleCond cond, BaseOperatorInfo operatorInfo);

    /**
     * 查询所有角色信息
     * 
     * @param operatorInfo
     * @param roleStatusList
     * @return
     */
    public BaseResponse<List<AccountRole>> listAllRoles(BaseOperatorInfo operatorInfo, List<String> roleStatusList);

    /**
     * 根据id查询角色信息
     * 
     * @param roleId
     *            角色id
     * @param operatorInfo
     * @return
     */
    public BaseResponse<AccountRole> getRoleById(Long roleId, BaseOperatorInfo operatorInfo);

    /**
     * 创建角色
     * 
     * @param accountRole
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Long> createRole(AccountRole accountRole, BaseOperatorInfo operatorInfo);

    /**
     * 更新角色信息
     * 
     * @param accountRole
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> updateRole(AccountRole accountRole, BaseOperatorInfo operatorInfo);

    /**
     * 通过角色名称查询角色信息
     * 
     * @param roleName
     * @param operatorInfo
     * @return
     */
    public BaseResponse<AccountRole> getRoleByName(String roleName, BaseOperatorInfo operatorInfo);

    /**
     * 通过角色id或者名称查询对应的账号信息
     * 
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<Account>> listAccountsByRoles(AccountRoleIdNameCond cond, BaseOperatorInfo operatorInfo);

    /**
     * 通过账号id 查询对应的角色信息
     * 
     * @param accountIds
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<AccountRole>> listRolesByAccounts(List<Long> accountIds, BaseOperatorInfo operatorInfo);

//    /**
//     * 修改账号与角色之间的关系
//     * 
//     * @param accountRoleRelations
//     * @param operatorInfo
//     * @return
//     */
//    public BaseResponse<Boolean> updateAccountRoleRelation(Long accountId,List<AccountRoleRelation> accountRoleRelations,
//            BaseOperatorInfo operatorInfo);

    /**
     * 通过角色id或者名称移除账号信息（关联表）
     * 
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> removeAccountsByRole(RoleRefAccountsCond cond, BaseOperatorInfo operatorInfo);

    /**
     * 通过账号id或者账号名称移除对应的角色信息（关联表）
     * 
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> removeRolesByAccount(AccountRefRolesCond cond, BaseOperatorInfo operatorInfo);
    
    /**
     * 更新角色对应的账号关联信息
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> updateAccountsByRole(RoleRefAccountsCond cond, BaseOperatorInfo operatorInfo);

    /**
     * 更新账号对应的角色关联信息
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> updateRolesByAccount(AccountRefRolesCond cond, BaseOperatorInfo operatorInfo);
    /**
     * 激活角色
     * 
     * @param roleIds
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> activeRoles(List<Long> roleIds, BaseOperatorInfo operatorInfo);

    /**
     * 禁用角色
     * 
     * @param roleIds
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> disableRoles(List<Long> roleIds, BaseOperatorInfo operatorInfo);

    /**
     * 查询角色状态
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<BizEnumSpec>> listAccountroleStatus(BaseOperatorInfo operatorInfo);
}
