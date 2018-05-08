package com.omniselling.app.base.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.omniselling.app.base.biz.service.BizAccountRoleService;
import com.omniselling.app.base.biz.service.BizAppResPermitService;
import com.omniselling.app.base.common.ResourcePermitType;
import com.omniselling.app.base.web.model.ViewAccount;
import com.omniselling.app.base.web.model.ViewAccountRole;
import com.omniselling.app.base.web.model.ViewAccountRoleCondition;
import com.omniselling.app.base.web.model.ViewAccountRoleRefAccount;
import com.omniselling.app.base.web.model.ViewAcountRoleRefAppResource;
import com.omniselling.app.base.web.model.ViewAppResource;
import com.omniselling.app.base.web.model.ViewEnumSpec;
import com.omniselling.app.base.web.service.ViewRoleMgrService;
import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.enumeration.MsAppErrorCode;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.model.ErrorInfo.SEVERITY;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.ms.core.model.Account;
import com.omniselling.ms.core.model.AccountRole;
import com.omniselling.ms.core.model.AccountRoleCond;
import com.omniselling.ms.core.model.AccountRoleIdNameCond;
import com.omniselling.ms.core.model.AppResPermitCond;
import com.omniselling.ms.core.model.RoleAppResPermission;
import com.omniselling.ms.core.model.RoleAppResPermit;
import com.omniselling.ms.core.model.RoleAppResUpdateCond;
import com.omniselling.ms.core.model.RoleRefAccountsCond;

public class ViewRoleMgrServiceImpl implements ViewRoleMgrService
{

    private BizAccountRoleService bizAccountRoleService;

    private BizAppResPermitService bizAppResPermitService;

    @Override
    public BaseResponse<BasePageDetail<ViewAccountRole>> listRoles(ViewAccountRoleCondition viewAccountRoleCondition,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<ViewAccountRole>> baseResponse = new BaseResponse<BasePageDetail<ViewAccountRole>>();
        BasePageDetail<ViewAccountRole> viewRoles = new BasePageDetail<ViewAccountRole>();
        try
        {
            // ======================创建参数，model转换==========================
            AccountRoleCond cond = convertAccountRoleCond(viewAccountRoleCondition);
            cond.setOffset(viewAccountRoleCondition.getOffset());
            cond.setRowsPerPage(viewAccountRoleCondition.getRowsPerPage());
            // ======================创建参数，model转换==========================
            BaseResponse<BasePageDetail<AccountRole>> bizResp = bizAccountRoleService.listRoles(cond, operatorInfo);
            if (bizResp.hasError())
            {
                baseResponse.addErrors(bizResp.getErrors());
                return baseResponse;
            }
            if (!bizResp.hasData())
            {
                // 如果没有查询出数据，不报错
                baseResponse.setData(viewRoles);
                return baseResponse;
            }
            List<ViewAccountRole> viewList = new ArrayList<ViewAccountRole>();
            BasePageDetail<AccountRole> rolePage = bizResp.getData();
            for (AccountRole accountRole : rolePage.getDatas())
            {
                viewList.add(convertViewAccountRole(accountRole));
            }
            viewRoles.setDatas(viewList);
            viewRoles.setOffset(rolePage.getOffset());
            viewRoles.setRowsPerPage(rolePage.getRowsPerPage());
            viewRoles.setTotal(rolePage.getTotal());
            baseResponse.setData(viewRoles);
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        return baseResponse;
    }

    private ViewAccountRole convertViewAccountRole(AccountRole accountRole)
    {
        if (accountRole == null)
        {
            return null;
        }
        ViewAccountRole viewRole = new ViewAccountRole();
        viewRole.setId(accountRole.getId());
        viewRole.setRoleName(accountRole.getRoleName());
        viewRole.setRoleLabel(accountRole.getRoleLabel());
        if (accountRole.getRoleLabel() == null || accountRole.getRoleLabel().isEmpty())
        {
            viewRole.setRoleLabel(accountRole.getRoleName());
        }
        viewRole.setRoleStatus(accountRole.getRoleStatus());
        viewRole.setRoleStatusLabel(accountRole.getRoleStatusLabel());
        if (accountRole.getRoleStatusLabel() == null || accountRole.getRoleStatusLabel().isEmpty())
        {
            viewRole.setRoleStatusLabel(accountRole.getRoleStatus());
        }
        viewRole.setRoleDescription(accountRole.getRoleDescription());
        return viewRole;
    }

    private AccountRoleCond convertAccountRoleCond(ViewAccountRoleCondition viewAccountRoleCondition)
    {
        if (viewAccountRoleCondition == null)
        {
            return null;
        }
        AccountRoleCond cond = new AccountRoleCond();
        cond.setOffset(viewAccountRoleCondition.getRowsPerPage());
        cond.setOffset(viewAccountRoleCondition.getOffset());
        cond.setRoleName(viewAccountRoleCondition.getRoleName());
        cond.setRoleNameLike(viewAccountRoleCondition.getRoleNameLike());
        cond.setRoleStatus(viewAccountRoleCondition.getRoleStatus());
        return cond;
    }

    private AccountRole convertAccountRole(ViewAccountRole viewAccountRole)
    {
        AccountRole accountRole = new AccountRole();
        accountRole.setId(viewAccountRole.getId());
        accountRole.setRoleName(viewAccountRole.getRoleName());
        accountRole.setRoleLabel(viewAccountRole.getRoleLabel());
        accountRole.setRoleStatus(viewAccountRole.getRoleStatus());
        accountRole.setRoleStatusLabel(viewAccountRole.getRoleStatusLabel());
        accountRole.setRoleDescription(viewAccountRole.getRoleDescription());
        return accountRole;
    }

    @Override
    public BaseResponse<Boolean> saveRole(ViewAccountRole viewAccountRole, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        baseResponse.setData(Boolean.FALSE);
        try
        {
            // ======================创建参数，model转换==========================
            AccountRole accountRole = convertAccountRole(viewAccountRole);
            // ======================创建参数，model转换==========================
            BaseResponse<Boolean> bizResp = bizAccountRoleService.updateRole(accountRole, operatorInfo);
            if (bizResp.hasError())
            {
                baseResponse.addErrors(bizResp.getErrors());
                return baseResponse;
            }
            baseResponse.setData(bizResp.getData());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<ViewAccountRole> getRoleDetail(Long accountRoleId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<ViewAccountRole> baseResponse = new BaseResponse<ViewAccountRole>();
        try
        {
            if (accountRoleId == null)
            {
                baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
                return baseResponse;
            }
            BaseResponse<AccountRole> bizResp = bizAccountRoleService.getRoleById(accountRoleId, operatorInfo);
            if (bizResp.hasError())
            {
                baseResponse.addErrors(bizResp.getErrors());
                return baseResponse;
            }
            if (!bizResp.hasData())
            {
                return baseResponse;
            }
            ViewAccountRole viewAccountRole = convertViewAccountRole(bizResp.getData());
            baseResponse.setData(viewAccountRole);
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> deleteRole(Long accountRoleId, BaseOperatorInfo operatorInfo)
    {
        // TODO 待更新问题(结果，暂时不做，等待后期确定)
        return null;
    }

    @Override
    public BaseResponse<ViewAccountRoleRefAccount> fetchAccountByRole(Long accountRoleId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<ViewAccountRoleRefAccount> baseResponse = new BaseResponse<ViewAccountRoleRefAccount>();
        try
        {
            // ======================创建参数，model转换==========================
            if (accountRoleId == null)
            {
                baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
                return baseResponse;
            }
            AccountRoleIdNameCond cond = new AccountRoleIdNameCond();
            List<Long> accountRoleIds = new ArrayList<Long>();
            accountRoleIds.add(accountRoleId);
            cond.setAccountRoleIds(accountRoleIds);
            // ======================创建参数，model转换==========================
            BaseResponse<AccountRole> bizRoleResp = bizAccountRoleService.getRoleById(accountRoleId, operatorInfo);
            if (bizRoleResp.hasError() || !bizRoleResp.hasData())
            {
                baseResponse.addErrors(bizRoleResp.getErrors());
                return baseResponse;
            }
            BaseResponse<List<Account>> bizResp = bizAccountRoleService.listAccountsByRoles(cond, operatorInfo);
            if (bizResp.hasError())
            {
                baseResponse.addErrors(bizResp.getErrors());
                return baseResponse;
            }
            List<Account> accountList = bizResp.getData();
            ViewAccountRoleRefAccount roleRefAccount = convertViewAcountRoleRefAccount(accountList, bizRoleResp
                    .getData().getRoleName(), bizRoleResp.getData().getId());
            baseResponse.setData(roleRefAccount);
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        return baseResponse;
    }

    private ViewAccountRoleRefAccount convertViewAcountRoleRefAccount(List<Account> accountList, String roleName,
            Long roleId)
    {
        ViewAccountRoleRefAccount viewRef = new ViewAccountRoleRefAccount();
        viewRef.setAccountRoleId(roleId);
        viewRef.setRoleName(roleName);
        if (accountList == null || accountList.isEmpty())
        {
            return viewRef;
        }
        List<ViewAccount> appAccounts = new ArrayList<ViewAccount>();
        for (Account account : accountList)
        {
            ViewAccount viewAccount = new ViewAccount();
            String[] ignoreProperties = { "id" };
            BeanUtils.copyProperties(account, viewAccount, ignoreProperties);
            viewAccount.setAccountId(account.getId());
            appAccounts.add(viewAccount);
        }
        viewRef.setAppAccounts(appAccounts);
        return viewRef;
    }

    @Override
    public BaseResponse<Boolean> saveRoleRefAccount(ViewAccountRoleRefAccount viewAcountRoleRefAccount,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        baseResponse.setData(Boolean.FALSE);
        // ======================创建参数，model转换==========================
        List<ViewAccount> accountList = viewAcountRoleRefAccount.getAppAccounts();
        RoleRefAccountsCond cond = new RoleRefAccountsCond();
        List<Long> accountIds = new ArrayList<Long>();
        cond.setAccountIds(accountIds);
        cond.setAccountRoleId(viewAcountRoleRefAccount.getAccountRoleId());
        cond.setRoleName(viewAcountRoleRefAccount.getRoleName());
        if (accountList != null && !accountList.isEmpty())
        {
            for (ViewAccount viewAccount : accountList)
            {
                accountIds.add(viewAccount.getAccountId());
            }
        }
        // ======================创建参数，model转换==========================
        try
        {
            BaseResponse<Boolean> bizResp = bizAccountRoleService.updateAccountsByRole(cond, operatorInfo);
            if (bizResp.hasError())
            {
                baseResponse.addErrors(bizResp.getErrors());
                return baseResponse;
            }
            baseResponse.setData(bizResp.getData());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> activeRole(Long accountRoleId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        baseResponse.setData(Boolean.FALSE);
        try
        {
            if (accountRoleId == null)
            {
                baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
                return baseResponse;
            }
            // ======================创建参数，model转换==========================
            List<Long> roleIds = new ArrayList<Long>();
            roleIds.add(accountRoleId);
            // ======================创建参数，model转换==========================
            BaseResponse<Boolean> bizResp = bizAccountRoleService.activeRoles(roleIds, operatorInfo);
            if (bizResp.hasError())
            {
                baseResponse.addErrors(bizResp.getErrors());
                return baseResponse;
            }
            baseResponse.setData(bizResp.getData());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> disableRole(Long accountRoleId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        baseResponse.setData(Boolean.FALSE);
        try
        {
            if (accountRoleId == null)
            {
                baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
                return baseResponse;
            }
            // ======================创建参数，model转换==========================
            List<Long> roleIds = new ArrayList<Long>();
            roleIds.add(accountRoleId);
            // ======================创建参数，model转换==========================
            BaseResponse<Boolean> bizResp = bizAccountRoleService.disableRoles(roleIds, operatorInfo);
            if (bizResp.hasError())
            {
                baseResponse.addErrors(bizResp.getErrors());
                return baseResponse;
            }
            baseResponse.setData(bizResp.getData());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<ViewAcountRoleRefAppResource> fetchAppResourcesByRole(Long accountRoleId,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<ViewAcountRoleRefAppResource> baseResponse = new BaseResponse<ViewAcountRoleRefAppResource>();
        if (accountRoleId == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        AppResPermitCond accRoleAppRes = new AppResPermitCond();
        accRoleAppRes.setAccountRoleIds(Arrays.asList(accountRoleId));

        BaseResponse<List<RoleAppResPermission>> dbResponse = new BaseResponse<List<RoleAppResPermission>>();

        ViewAcountRoleRefAppResource viewAcountRole = new ViewAcountRoleRefAppResource();
        viewAcountRole.setAccountRoleId(accountRoleId);
        try
        {
            // 查询角色资源权限
            dbResponse = bizAppResPermitService.listRoleResPermissionsByRole(accRoleAppRes, operatorInfo);

            if (!dbResponse.hasData())
            {
                baseResponse.setData(null);
                return baseResponse;
            }
            List<ViewAppResource> roleAppList = new ArrayList<ViewAppResource>();
            for (RoleAppResPermission roleAppResPermi : dbResponse.getData())
            {
                ViewAppResource viewAppResource = new ViewAppResource();
                BeanUtils.copyProperties(roleAppResPermi, viewAppResource);
                viewAppResource.setId(roleAppResPermi.getAppResourceId());
                roleAppList.add(viewAppResource);
            }

            viewAcountRole.setAppResources(roleAppList);
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        baseResponse.setData(viewAcountRole);
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> saveRoleRefAppResources(ViewAcountRoleRefAppResource viewAcountRoleRefAppResource,
            BaseOperatorInfo operatorInfo)
    {

        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        try
        {
            Long accountRoleId = viewAcountRoleRefAppResource.getAccountRoleId();
            // ==============================先创建角色=========================
            // 如果传入的角色id不存在，则创建这个角色
            if (accountRoleId == null)
            {
                AccountRole accountRole = new AccountRole();
                accountRole.setRoleName(viewAcountRoleRefAppResource.getRoleName());
                accountRole.setRoleDescription(viewAcountRoleRefAppResource.getRoleDescription());
                BaseResponse<Long> bizRoleResp = bizAccountRoleService.createRole(accountRole, operatorInfo);
                if (bizRoleResp.hasError())
                {
                    baseResponse.addErrors(bizRoleResp.getErrors());
                    return baseResponse;
                }
                if (bizRoleResp.hasData() && bizRoleResp.getData() != 0)
                {
                    accountRoleId = bizRoleResp.getData();
                }
            }
            else
            {
                // 如果有角色id，则进行修改操作
                AccountRole accountRole = new AccountRole();
                accountRole.setId(accountRoleId);
                accountRole.setRoleName(viewAcountRoleRefAppResource.getRoleName());
                if (viewAcountRoleRefAppResource.getRoleStatus() != null)
                {
                    accountRole.setRoleStatus(viewAcountRoleRefAppResource.getRoleStatus());
                }
                accountRole.setRoleDescription(viewAcountRoleRefAppResource.getRoleDescription());
                BaseResponse<Boolean> bizRoleResp = bizAccountRoleService.updateRole(accountRole, operatorInfo);
                if (bizRoleResp.hasError())
                {
                    baseResponse.addErrors(bizRoleResp.getErrors());
                    return baseResponse;
                }
            }
            // ==============================先创建角色=========================
            RoleAppResUpdateCond roleAppResUpdateCond = new RoleAppResUpdateCond();
            List<RoleAppResPermit> condList = new ArrayList<>();
            roleAppResUpdateCond.setRoleId(accountRoleId);
            for (ViewAppResource viewAppResource : viewAcountRoleRefAppResource.getAppResources())
            {
                RoleAppResPermit roleAppResPermit = new RoleAppResPermit();
                roleAppResPermit.setRoleId(accountRoleId);
                roleAppResPermit.setAppResourceId(viewAppResource.getId());
                roleAppResPermit.setPermitType(ResourcePermitType.ALLOW.name());
                condList.add(roleAppResPermit);
            }
            roleAppResUpdateCond.setRoleAppResPermits(condList);

            BaseResponse<Boolean> dbresResponse = bizAppResPermitService.updateRoleAppResPermissions(accountRoleId,
                    roleAppResUpdateCond, operatorInfo);
            if (dbresResponse.hasError())
            {
                baseResponse.addErrors(dbresResponse.getErrors());
                return baseResponse;
            }
            baseResponse.setData(dbresResponse.getData());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        return baseResponse;
    }

    public void setBizAccountRoleService(BizAccountRoleService bizAccountRoleService)
    {
        this.bizAccountRoleService = bizAccountRoleService;
    }

    public void setBizAppResPermitService(BizAppResPermitService bizAppResPermitService)
    {
        this.bizAppResPermitService = bizAppResPermitService;
    }

    @Override
    public BaseResponse<List<ViewEnumSpec>> listAccountroleStatus(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewEnumSpec>> baseResponse = new BaseResponse<>();
        List<ViewEnumSpec> list = new ArrayList<>();
        List<BizEnumSpec> bizList = new ArrayList<>();

        try
        {
            BaseResponse<List<BizEnumSpec>> bizBaseResponse = bizAccountRoleService.listAccountroleStatus(operatorInfo);
            if (bizBaseResponse.hasError())
            {
                baseResponse.addErrors(bizBaseResponse.getErrors());
                return baseResponse;
            }
            bizList = bizBaseResponse.getData();

        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_LISTPUBRESOURCESTATUS_ERROR,
                    SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "listAccountroleStatus view error"));
        }

        if (bizList != null && bizList.size() > 0)
        {
            for (BizEnumSpec enumSpec : bizList)
            {
                ViewEnumSpec spec = new ViewEnumSpec();
                spec.setCode(enumSpec.getCode());
                spec.setLabel(enumSpec.getLabel());

                list.add(spec);
            }
            baseResponse.setData(list);
        }

        return baseResponse;
    }

}
