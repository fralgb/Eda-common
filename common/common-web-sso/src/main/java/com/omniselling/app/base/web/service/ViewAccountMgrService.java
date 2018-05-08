package com.omniselling.app.base.web.service;

import java.util.List;

import com.omniselling.app.base.web.model.ViewAccount;
import com.omniselling.app.base.web.model.ViewAccountCondition;
import com.omniselling.app.base.web.model.ViewAccountPubResCondition;
import com.omniselling.app.base.web.model.ViewAccountPubResource;
import com.omniselling.app.base.web.model.ViewAccountRefPubResource;
import com.omniselling.app.base.web.model.ViewAccountRole;
import com.omniselling.app.base.web.model.ViewAccountRoleAccess;
import com.omniselling.app.base.web.model.ViewEnumSpec;
import com.omniselling.app.base.web.model.ViewPublicResource;
import com.omniselling.app.base.web.model.ViewResourceType;
import com.omniselling.app.base.web.model.ViewRolePermit;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;

/**
 * 用户资源管理接口
 * 
 * @author wlq
 */
public interface ViewAccountMgrService
{
    /**
     * 获取子账户信息
     * 
     * @param accountId
     * @param basePage
     * @return
     */
    public BaseResponse<List<ViewAccount>> listSubAccount(Long accountId, BaseOperatorInfo baseOperatorInfo);

    /**
     * 获取父账户信息
     * 
     * @author wlq
     * @param accountId
     * @param basePage
     * @return
     */
    public BaseResponse<ViewAccount> getParentAccount(Long accountId, BaseOperatorInfo baseOperatorInfo);

    /**
     * 更新账号
     * 
     * @param viewAccount
     * @param basePage
     * @author wlq
     * @return
     */
    public BaseResponse<Boolean> updateAccount(ViewAccount viewAccount, BaseOperatorInfo baseOperatorInfo);

    /**
     * 查询所有账号
     * 
     * @param viewAccountCondition
     * @param basePage
     * @return
     */
    public BaseResponse<BasePageDetail<ViewAccount>> listAccounts(ViewAccountCondition viewAccountCondition,
            BaseOperatorInfo baseOperatorInfo);

    /**
     * 获取Role的下拉列表
     * 
     * @param basePage
     * @return
     */
    public BaseResponse<List<ViewAccountRole>> fetchActiveRoles(BaseOperatorInfo baseOperatorInfo);
    
    /**
     * 获取所有角色信息
     * @param baseOperatorInfo
     * @return
     */
    public BaseResponse<List<ViewAccountRole>> fetchAllRoles(BaseOperatorInfo baseOperatorInfo);

    /**
     * 根据id查看账号详情
     * 
     * @param accountId
     * @return
     */
    public BaseResponse<ViewAccount> getAccount(BaseOperatorInfo baseOperatorInfo, Long accountId);

    /**
     * 保存账号信息所做的修改
     * 
     * @param viewAccount
     * @param basePage
     * @return
     */
    public BaseResponse<Boolean> saveAccount(ViewAccount viewAccount, BaseOperatorInfo baseOperatorInfo);

    /**
     * 查询账户所有的资源权限列表
     * 
     * @param accountId
     *            账户id
     * @return
     */
    public BaseResponse<ViewAccountRoleAccess> fetchAccountRoleAccess(BaseOperatorInfo baseOperatorInfo,
            Long accountId);

    /**
     * 保存账号角色的修改(就是修改账户角色关系)
     * 
     * @param viewAccountRoleAccess
     * @param basePage
     * @return
     */
    public BaseResponse<Boolean> saveRoles(ViewAccountRoleAccess viewAccountRoleAccess,
            BaseOperatorInfo baseOperatorInfo);

    /**
     * 保存账号白名单的修改(就是修改账户系统应用资源权限)
     * 
     * @param viewAccountRoleAccess
     * @param basePage
     * @return
     */
    public BaseResponse<Boolean> saveWhiteAccess(ViewAccountRoleAccess viewAccountRoleAccess,
            BaseOperatorInfo baseOperatorInfo);

    /**
     * 保存账户黑白名单资源
     * 
     * @param viewAccountRoleAccess
     *            ，accountId：账户id；petmitType：资源类型；resourceId：资源id
     * @param baseOperatorInfo
     * @return
     */
    public BaseResponse<Boolean> savePermitType(ViewAccountRoleAccess viewAccountRoleAccess,
            BaseOperatorInfo baseOperatorInfo);

    /**
     * 保存账号黑名单的修改(就是修改账户系统应用资源权限)
     * 
     * @param viewAccountRoleAccess
     * @param basePage
     * @return
     */
    public BaseResponse<Boolean> saveBlackAccess(ViewAccountRoleAccess viewAccountRoleAccess,
            BaseOperatorInfo baseOperatorInfo);

    /**
     * 查询
     * 
     * @param basePage
     * @return
     */
    public BaseResponse<List<ViewRolePermit>> fetchRolePermit(BaseOperatorInfo operatorInfo);

    /**
     * 获取全部资源
     * 
     * @param basePage
     * @return
     */
    public BaseResponse<List<ViewPublicResource>> fetchPubResources(BaseOperatorInfo operatorInfo);

    /**
     * 根据id获取拥有的所有公有资源
     * 
     * @param viewAccountRoleAccess
     * @param basePage
     * @return
     */
    public BaseResponse<List<ViewPublicResource>> fetchPubResourcesByAccount(Long accountId,
            BaseOperatorInfo operatorInfo);

    /**
     * 获取资源类型的下拉列表
     * 
     * @param basePage
     * @return
     */
    public BaseResponse<List<ViewResourceType>> fetchPubResourceType(BaseOperatorInfo operatorInfo);

    /**
     * 获取某一类型的全部资源
     * 
     * @param resourceTypeId
     * @param basePage
     * @return
     */
    public BaseResponse<List<ViewPublicResource>> fetchPubResourcesByType(String resourceTypeId,
            BaseOperatorInfo operatorInfo);

    /**
     * 修改账户下的某资源类型的账户公共资源权限
     * 
     * @param viewAccountPublicResource
     * @param basePage
     * @return
     */
    public BaseResponse<Boolean> saveAccountResources(ViewAccountRefPubResource viewAccountPublicResource,
            BaseOperatorInfo operatorInfo);

    /**
     * 激活指定用户
     * 
     * @param accountId
     * @param basePage
     * @return
     */
    public BaseResponse<Boolean> activeAccount(String accountId, BaseOperatorInfo baseOperatorInfo);

    /**
     * 禁用指定用户
     * 
     * @param accountId
     * @param basePage
     * @return
     */
    public BaseResponse<Boolean> disableAccount(String accountId, BaseOperatorInfo baseOperatorInfo);

    /**
     * 查询账户状态
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<ViewEnumSpec>> listAccountStatus(BaseOperatorInfo operatorInfo);

    /**
     * 查询所有账户
     * 
     * @param loginId
     * @param basePage
     * @return
     */
    public BaseResponse<List<ViewAccount>> listAllAccountsList(String loginId, BaseOperatorInfo basePage);

    /**
     * 查询账户黑名名单状态
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<ViewEnumSpec>> listResourcePermitType(BaseOperatorInfo operatorInfo);

    /**
     * 查询账号公共资源
     * 
     * @param condition
     * @param operatorInfo
     * @return
     */
    public BaseResponse<BasePageDetail<ViewAccountPubResource>> listAccountPubRes(ViewAccountPubResCondition condition,
            BaseOperatorInfo operatorInfo);
}
