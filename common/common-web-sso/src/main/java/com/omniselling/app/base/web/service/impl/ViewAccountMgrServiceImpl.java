package com.omniselling.app.base.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.omniselling.app.base.biz.service.BizAccountRoleService;
import com.omniselling.app.base.biz.service.BizAccountService;
import com.omniselling.app.base.biz.service.BizAppResPermitService;
import com.omniselling.app.base.biz.service.BizPubResPermitService;
import com.omniselling.app.base.biz.service.BizPubRessourceService;
import com.omniselling.app.base.common.AccountRoleStatus;
import com.omniselling.app.base.common.ResourcePermitType;
import com.omniselling.app.base.web.model.ViewAccount;
import com.omniselling.app.base.web.model.ViewAccountCondition;
import com.omniselling.app.base.web.model.ViewAccountPubResCondition;
import com.omniselling.app.base.web.model.ViewAccountPubResource;
import com.omniselling.app.base.web.model.ViewAccountRefPubResource;
import com.omniselling.app.base.web.model.ViewAccountRole;
import com.omniselling.app.base.web.model.ViewAccountRoleAccess;
import com.omniselling.app.base.web.model.ViewAppResource;
import com.omniselling.app.base.web.model.ViewEnumSpec;
import com.omniselling.app.base.web.model.ViewPublicResource;
import com.omniselling.app.base.web.model.ViewResourceType;
import com.omniselling.app.base.web.model.ViewRolePermit;
import com.omniselling.app.base.web.service.ViewAccountMgrService;
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
import com.omniselling.ms.core.model.AccountAppResPermission;
import com.omniselling.ms.core.model.AccountCond;
import com.omniselling.ms.core.model.AccountPubResPermission;
import com.omniselling.ms.core.model.AccountRefRolesCond;
import com.omniselling.ms.core.model.AccountRole;
import com.omniselling.ms.core.model.AppResPermitCond;
import com.omniselling.ms.core.model.PubResPermitCond;
import com.omniselling.ms.core.model.PubResource;
import com.omniselling.ms.core.model.PubResourceCond;
import com.omniselling.ms.core.model.RoleAppResPermission;

public class ViewAccountMgrServiceImpl implements ViewAccountMgrService
{
    private BizAccountService bizAccountService;
    private BizAccountRoleService bizAccountRoleService;
    private BizAppResPermitService bizAppResPermitService;
    private BizPubRessourceService bizPubRessourceService;
    private BizPubResPermitService bizPubResPermitService;

    @Override
    public BaseResponse<BasePageDetail<ViewAccount>> listAccounts(ViewAccountCondition vAccountCond,
            BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<BasePageDetail<ViewAccount>> baseRes = new BaseResponse<BasePageDetail<ViewAccount>>();
        BasePageDetail<ViewAccount> vAccountPer = new BasePageDetail<ViewAccount>();
        if (baseOperatorInfo.getOperatorId() == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }
        // TODO，temp to do
        if (vAccountCond == null)
        {
            vAccountCond = new ViewAccountCondition();
        }
        AccountCond accountCond = new AccountCond();
        BeanUtils.copyProperties(vAccountCond, accountCond);
        BaseResponse<BasePageDetail<Account>> bizRes = bizAccountService.listAccounts(accountCond, baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        if (!bizRes.hasData() || bizRes.getData().getDatas() == null || bizRes.getData().getDatas().isEmpty())
        {
            vAccountPer.setTotal(0);
            baseRes.setData(vAccountPer);
            return baseRes;
        }
        List<ViewAccount> viewAccounts = new LinkedList<>();
        for (Account account : bizRes.getData().getDatas())
        {
            ViewAccount vAccount = new ViewAccount();
            BeanUtils.copyProperties(account, vAccount, new String[] { "id" });
            vAccount.setAccountId(account.getId());
            if (account.getBizAccountRoles() == null || account.getBizAccountRoles().isEmpty())
            {
                viewAccounts.add(vAccount);
                continue;
            }
            List<ViewAccountRole> vAccRoles = new ArrayList<>();
            for (AccountRole accountRole : account.getBizAccountRoles())
            {
                ViewAccountRole vAccRole = new ViewAccountRole();
                BeanUtils.copyProperties(accountRole, vAccRole);
                if (accountRole.getRoleLabel() == null || accountRole.getRoleLabel().isEmpty())
                {
                    vAccRole.setRoleLabel(accountRole.getRoleName());
                }
                vAccRoles.add(vAccRole);
            }
            vAccount.setAccountRoles(vAccRoles);
            viewAccounts.add(vAccount);
        }
        vAccountPer.setDatas(viewAccounts);
        vAccountPer.setOffset(bizRes.getData().getOffset());
        vAccountPer.setRowsPerPage(bizRes.getData().getRowsPerPage());
        vAccountPer.setTotal(bizRes.getData().getTotal());
        baseRes.setData(vAccountPer);
        return baseRes;
    }

    @Override
    public BaseResponse<ViewAccount> getAccount(BaseOperatorInfo operatorInfo, Long accountId)
    {
        BaseResponse<ViewAccount> baseRes = new BaseResponse<ViewAccount>();
        if (accountId == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseRes;
        }
        BaseResponse<Account> bizRes = bizAccountService.getAccountById(accountId, operatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        Account account = bizRes.getData();
        ViewAccount vAccount = new ViewAccount();
        BeanUtils.copyProperties(account, vAccount, new String[] { "id" });
        vAccount.setAccountId(account.getId());
        if (account.getBizAccountRoles() == null || account.getBizAccountRoles().isEmpty())
        {
            baseRes.setData(vAccount);
            return baseRes;
        }
        List<ViewAccountRole> vAccRoles = new ArrayList<ViewAccountRole>();
        for (AccountRole accRole : account.getBizAccountRoles())
        {
            ViewAccountRole vAccRole = new ViewAccountRole();
            BeanUtils.copyProperties(accRole, vAccRole);
            if (accRole.getRoleLabel() == null || accRole.getRoleLabel().isEmpty())
            {
                vAccRole.setRoleLabel(accRole.getRoleName());
            }
            vAccRoles.add(vAccRole);
        }
        vAccount.setAccountRoles(vAccRoles);
        baseRes.setData(vAccount);
        return baseRes;
    }

    @Override
    public BaseResponse<Boolean> saveAccount(ViewAccount vAccount, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();
        if (vAccount == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }

        // 校验字段长度和是否为空
        baseRes = vAccount.validate(baseOperatorInfo.getLanguage());

        if (baseRes.hasError())
        {
            return baseRes;
        }
        Account account = new Account();
        BeanUtils.copyProperties(vAccount, account, new String[] { "accountId" });
        account.setId(vAccount.getAccountId());
        BaseResponse<Boolean> bizRes = bizAccountService.createAccount(account, baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        baseRes.setData(bizRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<Boolean> activeAccount(String accountId, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();
        if (accountId == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }
        List<Long> accountIds = new ArrayList<>();
        accountIds.add(Long.valueOf(accountId));
        BaseResponse<Boolean> bizRes = bizAccountService.activeAccounts(accountIds, baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        baseRes.setData(bizRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<Boolean> disableAccount(String accountId, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();
        if (accountId == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }
        List<Long> accountIds = new ArrayList<>();
        accountIds.add(Long.valueOf(accountId));
        BaseResponse<Boolean> bizRes = bizAccountService.disableAccounts(accountIds, baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        baseRes.setData(bizRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<Boolean> updateAccount(ViewAccount vAccount, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();
        if (vAccount == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }

        // 校验字段长度和是否为空
        baseRes = vAccount.validate(baseOperatorInfo.getLanguage());

        if (baseRes.hasError())
        {
            return baseRes;
        }
        Account account = new Account();
        BeanUtils.copyProperties(vAccount, account, new String[] { "accountId" });
        account.setId(vAccount.getAccountId());
        BaseResponse<Boolean> bizRes = bizAccountService.updateAccount(account, baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        baseRes.setData(bizRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<ViewAccount> getParentAccount(Long accountId, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<ViewAccount> baseRes = new BaseResponse<ViewAccount>();
        if (accountId == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }
        BaseResponse<Account> bizRes = bizAccountService.getParentAccount(accountId, baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        ViewAccount viewAccount = new ViewAccount();
        BeanUtils.copyProperties(bizRes.getData(), viewAccount);
        baseRes.setData(viewAccount);
        return baseRes;
    }

    @Override
    public BaseResponse<List<ViewAccount>> listSubAccount(Long accountId, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<List<ViewAccount>> baseRes = new BaseResponse<List<ViewAccount>>();
        if (accountId == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }

        BaseResponse<List<Account>> bizRes = bizAccountService.listSubAccount(accountId, baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        List<ViewAccount> viewAccounts = new ArrayList<>();
        for (Account account : bizRes.getData())
        {
            ViewAccount viewAccount = new ViewAccount();
            BeanUtils.copyProperties(account, viewAccount, new String[] { "accountId" });
            viewAccount.setAccountId(account.getId());
            viewAccounts.add(viewAccount);
        }
        baseRes.setData(viewAccounts);
        return baseRes;
    }

    @Override
    public BaseResponse<List<ViewAccountRole>> fetchActiveRoles(BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<List<ViewAccountRole>> baseRes = new BaseResponse<List<ViewAccountRole>>();
        List<String> roleStatusList = Arrays.asList(new String[] { AccountRoleStatus.ACTIVE.name() });
        BaseResponse<List<AccountRole>> bizRes = bizAccountRoleService.listAllRoles(baseOperatorInfo, roleStatusList);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        baseRes.setData(transferViewAccountRoles(bizRes.getData()));
        return baseRes;
    }

    @Override
    public BaseResponse<List<ViewAccountRole>> fetchAllRoles(BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<List<ViewAccountRole>> baseRes = new BaseResponse<List<ViewAccountRole>>();
        List<String> roleStatusList = Arrays
                .asList(new String[] { AccountRoleStatus.ACTIVE.name(), AccountRoleStatus.DISABLE.name() });
        BaseResponse<List<AccountRole>> bizRes = bizAccountRoleService.listAllRoles(baseOperatorInfo, roleStatusList);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        baseRes.setData(transferViewAccountRoles(bizRes.getData()));
        return baseRes;
    }

    private List<ViewAccountRole> transferViewAccountRoles(List<AccountRole> accountRoles)
    {
        List<ViewAccountRole> viewAccountRoles = new ArrayList<>();
        if (accountRoles == null || accountRoles.isEmpty())
        {
            return viewAccountRoles;
        }
        for (AccountRole accountRole : accountRoles)
        {
            ViewAccountRole viewAccountRole = new ViewAccountRole();
            BeanUtils.copyProperties(accountRole, viewAccountRole);
            if (accountRole.getRoleLabel() == null || accountRole.getRoleLabel().isEmpty())
            {
                viewAccountRole.setRoleLabel(accountRole.getRoleName());
            }
            viewAccountRoles.add(viewAccountRole);
        }
        return viewAccountRoles;
    }

    @Override
    public BaseResponse<ViewAccountRoleAccess> fetchAccountRoleAccess(BaseOperatorInfo operatorInfo, Long accountId)
    {
        BaseResponse<ViewAccountRoleAccess> baseResponse = new BaseResponse<ViewAccountRoleAccess>();
        if (accountId == null || operatorInfo == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        ViewAccountRoleAccess viewAccRole = new ViewAccountRoleAccess();
        viewAccRole.setAccountId(accountId);
        List<Long> accountIds = Arrays.asList(accountId);
        // 查询账户id对应的角色id
        BaseResponse<List<AccountRole>> bizRoleRes = bizAccountRoleService.listRolesByAccounts(accountIds,
                operatorInfo);
        if (bizRoleRes.hasError())
        {
            baseResponse.addErrors(bizRoleRes.getErrors());
            return baseResponse;
        }
        if (!bizRoleRes.hasData())
        {
            baseResponse.setData(null);
            return baseResponse;
        }
        // 账户对应资源信息
        List<ViewAccountRole> viewAccRoleList = new ArrayList<ViewAccountRole>();
        for (AccountRole accountRole : bizRoleRes.getData())
        {
            ViewAccountRole viewAccountRole = new ViewAccountRole();
            BeanUtils.copyProperties(accountRole, viewAccountRole);
            viewAccRoleList.add(viewAccountRole);
        }
        viewAccRole.setAccountroles(viewAccRoleList);

        // 查询账户所有资源权限信息
        AppResPermitCond accRoleAppRes = new AppResPermitCond();
        accRoleAppRes.setAccountIds(accountIds);

        BaseResponse<List<AccountAppResPermission>> bizAccAppRes = bizAppResPermitService
                .listAccResPermissionsByAcc(accRoleAppRes, operatorInfo);
        if (bizAccAppRes.hasError())
        {
            baseResponse.addErrors(bizAccAppRes.getErrors());
            return baseResponse;
        }
        if (!bizAccAppRes.hasData() || bizAccAppRes.getData() == null)
        {
            baseResponse.setData(viewAccRole);
            return baseResponse;
        }
        // 保存黑白名单
        List<ViewAppResource> whiteResAccess = new ArrayList<ViewAppResource>();
        List<ViewAppResource> blackResAccess = new ArrayList<ViewAppResource>();
        for (AccountAppResPermission accAppPermit : bizAccAppRes.getData())
        {
            ViewAppResource viewAppResource = new ViewAppResource();
            BeanUtils.copyProperties(accAppPermit, viewAppResource);
            viewAppResource.setId(accAppPermit.getAppResourceId());
            viewAppResource.setResourceType(accAppPermit.getResourceType());
            if (ResourcePermitType.ALLOW.name().equals(accAppPermit.getPermitType()))
            {
                // viewAppResource.setPermitTypeCode(ResourcePermitType.ALLOW.name());
                whiteResAccess.add(viewAppResource);
            }
            if (ResourcePermitType.NOTALLOW.name().equals(accAppPermit.getPermitType()))
            {
                // viewAppResource.setPermitTypeCode(ResourcePermitType.NOTALLOW.name());
                blackResAccess.add(viewAppResource);
            }

        }
        viewAccRole.setWhiteResAccess(whiteResAccess);
        viewAccRole.setBlackResAccess(blackResAccess);

        baseResponse.setData(viewAccRole);
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> saveRoles(ViewAccountRoleAccess viewAccountRoleAccess, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        if (viewAccountRoleAccess == null || viewAccountRoleAccess.getAccountId() == null || operatorInfo == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        Long accountId = viewAccountRoleAccess.getAccountId();
        AccountRefRolesCond cond = new AccountRefRolesCond();
        List<Long> accountRoleIds = new ArrayList<>();
        cond.setAccountId(accountId);
        cond.setAccountRoleIds(accountRoleIds);

        List<ViewAccountRole> accountroles = viewAccountRoleAccess.getAccountroles();
        if (accountroles != null && !accountroles.isEmpty())
        {
            for (ViewAccountRole viewAccountRole : accountroles)
            {
                accountRoleIds.add(viewAccountRole.getId());
            }
        }
        BaseResponse<Boolean> bizRes = bizAccountRoleService.updateRolesByAccount(cond, operatorInfo);
        if (bizRes.hasError())
        {
            baseResponse.addErrors(bizRes.getErrors());
            return baseResponse;
        }
        baseResponse.setData(bizRes.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> saveWhiteAccess(ViewAccountRoleAccess viewAccountRoleAccess,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        if (viewAccountRoleAccess == null || viewAccountRoleAccess.getAccountId() == null || operatorInfo == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        // accountId
        Long accountId = viewAccountRoleAccess.getAccountId();

        List<AccountAppResPermission> accountAppResPermissions = new ArrayList<>();
        // resourceId
        for (ViewAppResource viewAppResource : viewAccountRoleAccess.getWhiteResAccess())
        {
            if (viewAppResource == null || viewAppResource.getId() == null)
            {
                baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
                return baseResponse;
            }
            AccountAppResPermission accountAppResPermission = new AccountAppResPermission();
            accountAppResPermission.setAppResourceId(viewAppResource.getId());
            accountAppResPermission.setPermitType(ResourcePermitType.ALLOW.name());
            accountAppResPermission.setAccountId(accountId);
            accountAppResPermissions.add(accountAppResPermission);
        }

        BaseResponse<Boolean> bizRes = bizAppResPermitService.updateAccAppResPermissions(accountId,
                accountAppResPermissions, operatorInfo);
        if (bizRes.hasError())
        {
            baseResponse.addErrors(bizRes.getErrors());
            return baseResponse;
        }
        baseResponse.setData(bizRes.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> savePermitType(ViewAccountRoleAccess viewAccountRoleAccess,
            BaseOperatorInfo baseOperatorInfo)
    {

        return null;
    }

    @Override
    public BaseResponse<Boolean> saveBlackAccess(ViewAccountRoleAccess viewAccountRoleAccess,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        if (viewAccountRoleAccess == null || viewAccountRoleAccess.getAccountId() == null || operatorInfo == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        Long accountId = viewAccountRoleAccess.getAccountId();

        List<AccountAppResPermission> accountAppResPermissions = new ArrayList<>();

        for (ViewAppResource viewAppResource : viewAccountRoleAccess.getBlackResAccess())
        {
            if (viewAppResource == null || viewAppResource.getId() == null)
            {
                continue;
            }

            AccountAppResPermission accountAppResPermission = new AccountAppResPermission();
            accountAppResPermission.setAccountId(accountId);
            accountAppResPermission.setAppResourceId(viewAppResource.getId());
            accountAppResPermission.setPermitType(ResourcePermitType.NOTALLOW.name());

            accountAppResPermissions.add(accountAppResPermission);
        }

        BaseResponse<Boolean> bizRes = bizAppResPermitService.updateAccAppResPermissions(accountId,
                accountAppResPermissions, operatorInfo);
        if (bizRes.hasError())
        {
            baseResponse.addErrors(bizRes.getErrors());
            return baseResponse;
        }
        baseResponse.setData(bizRes.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<List<ViewRolePermit>> fetchRolePermit(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewRolePermit>> baseResponse = new BaseResponse<List<ViewRolePermit>>();
        List<ViewRolePermit> viewList = new ArrayList<ViewRolePermit>();
        // 查询出所有的角色id
        List<String> roleStatusList = Arrays
                .asList(new String[] { AccountRoleStatus.ACTIVE.name(), AccountRoleStatus.DISABLE.name() });
        BaseResponse<List<AccountRole>> bizRes = bizAccountRoleService.listAllRoles(operatorInfo, roleStatusList);
        if (bizRes.hasError())
        {
            baseResponse.addErrors(bizRes.getErrors());
            return baseResponse;
        }
        if (!bizRes.hasData())
        {
            return baseResponse;
        }
        // 角色ids
        List<Long> accountRoleIds = new ArrayList<Long>();
        // // roleLabel
        // Map<Long, String> roleLabelMap = new HashMap<Long, String>();
        // for (AccountRole accountRole : bizRes.getData())
        // {
        // if (accountRole.getRoleStatus().equals("ACTIVE"))
        // {
        // accountRoleIds.add(accountRole.getId());
        // roleLabelMap.put(accountRole.getId(), accountRole.getRoleLabel());
        // }
        // }

        // 根据角色id，查询所有的角色权限信息
        if (accountRoleIds == null || accountRoleIds.size() == 0)
        {
            return baseResponse;
        }

        AppResPermitCond accRoleAppRes = new AppResPermitCond();
        accRoleAppRes.setAccountRoleIds(accountRoleIds);
        BaseResponse<List<RoleAppResPermission>> dbAppRpleRes = bizAppResPermitService
                .listRoleResPermissionsByRole(accRoleAppRes, operatorInfo);
        if (dbAppRpleRes.hasError())
        {
            baseResponse.addErrors(dbAppRpleRes.getErrors());
            return baseResponse;
        }
        if (!dbAppRpleRes.hasData())
        {
            return baseResponse;
        }
        for (AccountRole accountRole : bizRes.getData())
        {
            ViewRolePermit viewRolePermit = new ViewRolePermit();
            viewRolePermit.setRoleId(accountRole.getId());
            viewRolePermit.setRoleName(accountRole.getRoleName());
            viewRolePermit.setRoleLabel(accountRole.getRoleLabel());
            if (accountRole.getRoleLabel() == null || accountRole.getRoleLabel().isEmpty())
            {
                viewRolePermit.setRoleLabel(accountRole.getRoleName());
            }
            List<ViewAppResource> appResources = new ArrayList<ViewAppResource>();

            for (RoleAppResPermission roleAppResPermit : dbAppRpleRes.getData())
            {
                if (accountRole.getId() == roleAppResPermit.getAccountRoleId())
                {
                    ViewAppResource viewAppResource = convertViewRolePermit(roleAppResPermit);
                    appResources.add(viewAppResource);
                }
            }
            viewRolePermit.setAppResources(appResources);
            viewList.add(viewRolePermit);
        }
        baseResponse.setData(viewList);
        return baseResponse;
    }

    private ViewAppResource convertViewRolePermit(RoleAppResPermission roleAppResPermit)
    {
        ViewAppResource viewAppResource = new ViewAppResource();
        viewAppResource.setId(roleAppResPermit.getAppResourceId());
        viewAppResource.setResourceName(roleAppResPermit.getResourceName());
        viewAppResource.setApplicationName(roleAppResPermit.getApplicationName());
        viewAppResource.setResourceGroup(roleAppResPermit.getResourceGroup());
        viewAppResource.setResourceType(roleAppResPermit.getResourceType());
        // viewAppResource.setResourceTypeCode(roleAppResPermit.getResourceType());
        viewAppResource.setResourceOrder(roleAppResPermit.getResourceOrder());
        if (roleAppResPermit.getResourceLabel() == null || roleAppResPermit.getResourceLabel().isEmpty())
        {
            viewAppResource.setResourceLabel(roleAppResPermit.getResourceName());
        }
        viewAppResource.setResourceLabel(roleAppResPermit.getResourceLabel());
        return viewAppResource;
    }

    @Override
    public BaseResponse<List<ViewPublicResource>> fetchPubResourcesByAccount(Long accountId,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewPublicResource>> baseRes = new BaseResponse<>();
        List<ViewPublicResource> resultList = new ArrayList<>();
        if (accountId == null || operatorInfo == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseRes;
        }

        PubResPermitCond cond = new PubResPermitCond();
        List<Long> accountIds = new ArrayList<>();
        accountIds.add(accountId);
        cond.setAccountIds(accountIds);
        BaseResponse<List<AccountPubResPermission>> bizRes = bizPubResPermitService.listPermissionByAcc(cond,
                operatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
        }
        List<AccountPubResPermission> bizList = bizRes.getData();

        if (bizList != null && bizList.size() != 0)
        {
            for (AccountPubResPermission accountPubResPermission : bizList)
            {
                if (accountPubResPermission == null)
                {
                    continue;
                }
                resultList.add(fromToViewPublicResourceShow(accountPubResPermission));
            }
        }

        baseRes.setData(resultList);
        return baseRes;
    }

    private ViewPublicResource fromToViewPublicResourceShow(AccountPubResPermission model)
    {
        ViewPublicResource viewPublicResource = new ViewPublicResource();
        viewPublicResource.setId(model.getPubResourceId());
        viewPublicResource.setOwnerId(model.getAccountId());
        viewPublicResource.setResourceName(model.getResourceName());
        viewPublicResource.setResourceStatus(model.getResourceStatus());
        viewPublicResource.setResourceStatusLabel(model.getResourceType());
        viewPublicResource.setResourceTypeCode(model.getResourceType());
        viewPublicResource.setResourceTypeLabel(model.getResourceType());
        return viewPublicResource;
    }

    @Override
    public BaseResponse<List<ViewResourceType>> fetchPubResourceType(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewResourceType>> baseRes = new BaseResponse<>();
//        ViewResourceTypeCond importRequest = new ViewResourceTypeCond();
//        // TODO 直接查枚举,需动态加载
//        BaseResponse<BasePageDetail<ViewResourceType>> viewRes = viewPubResTypeMgrService.listTypes(importRequest,
//                operatorInfo);
//        if (viewRes.hasError())
//        {
//            baseRes.addErrors(viewRes.getErrors());
//            return baseRes;
//        }
//        BasePageDetail<ViewResourceType> basePageDetail = viewRes.getData();
//        List<ViewResourceType> viewList = basePageDetail.getDatas();

//        baseRes.setData(viewList);
        return baseRes;
    }

    @Override
    public BaseResponse<List<ViewPublicResource>> fetchPubResourcesByType(String resourceTypeId,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewPublicResource>> baseRes = new BaseResponse<>();
        List<ViewPublicResource> resultList = new ArrayList<>();
        if (resourceTypeId == null || operatorInfo == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseRes;
        }

        PubResourceCond cond = new PubResourceCond();
        cond.setResourceType(resourceTypeId);

        BaseResponse<BasePageDetail<PubResource>> bizRes = bizPubRessourceService.listPubResources(cond, operatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        BasePageDetail<PubResource> basePageDetail = bizRes.getData();

        List<PubResource> bizList = basePageDetail.getDatas();

        for (PubResource pubResource : bizList)
        {
            if (pubResource == null)
            {
                continue;
            }
            resultList.add(fromToViewPublicResource(pubResource));
        }
        baseRes.setData(resultList);
        return baseRes;
    }

    private ViewPublicResource fromToViewPublicResource(PubResource pubResource)
    {
        ViewPublicResource viewPublicResource = new ViewPublicResource();
        viewPublicResource.setId(pubResource.getId());
        viewPublicResource.setInvalidDate(pubResource.getInvalidDate());
        viewPublicResource.setOwnerId(pubResource.getOwnerId());
        viewPublicResource.setOwnerName(pubResource.getOwnerName());
        viewPublicResource.setPublishDate(pubResource.getPublishDate());
        viewPublicResource.setPublishNum(pubResource.getPublishNum());
        viewPublicResource.setResourceName(pubResource.getResourceName());
        viewPublicResource.setResourceObjectId(pubResource.getResourceObjectId());
        viewPublicResource.setResourceStatus(pubResource.getResourceStatus());
        viewPublicResource.setResourceStatusLabel(pubResource.getResourceStatusLabel());
        viewPublicResource.setResourceTypeCode(pubResource.getResourceType());
        viewPublicResource.setResourceTypeLabel(pubResource.getResourceTypeLabel());

        return viewPublicResource;
    }

    @Override
    public BaseResponse<Boolean> saveAccountResources(ViewAccountRefPubResource viewAccPubRes,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();

        // if (viewAccPubRes == null || viewAccPubRes.getResourceType() == null
        // || "".equals(viewAccPubRes.getResourceType()) || viewAccPubRes.getAccountId() == null
        // || operatorInfo == null || operatorInfo.getOperatorId() == null)
        // {
        // baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
        // ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
        // "saveAccountResources view param null"));
        // return baseResponse;
        // }

        List<AccountPubResPermission> pubResPermissions = new ArrayList<>();
        Long accountId = viewAccPubRes.getAccountId();

        for (ViewPublicResource viewModel : viewAccPubRes.getPublicResources())
        {

            if (viewModel == null || viewModel.getId() == null || viewModel.getResourceTypeCode() == null)
            {
                continue;
            }

            AccountPubResPermission accountPubRes = new AccountPubResPermission();
            accountPubRes.setPubResourceId(viewModel.getId());
            accountPubRes.setAccountId(accountId);

            accountPubRes.setResourceType(viewModel.getResourceTypeCode());

            pubResPermissions.add(accountPubRes);
        }

        try
        {
            // BaseResponse<Boolean> bizRes = bizPubResPermitService.updatePermissions(accountId,
            // PubResourceType.valueOf(viewAccPubRes.getResourceTypeCode()), pubResPermissions, operatorInfo);
            BaseResponse<Boolean> bizRes = bizPubResPermitService.updatePermissions(accountId, null, pubResPermissions,
                    operatorInfo);

            baseResponse.setData(bizRes.getData());
            baseResponse.addErrors(bizRes.getErrors());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_UPDATEPERMISSIONSBYRESTYPE_ERROR,
                    SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "saveAccountResources view error"));
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<List<ViewPublicResource>> fetchPubResources(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewPublicResource>> baseRes = new BaseResponse<>();
        List<ViewPublicResource> resultList = new ArrayList<>();
        PubResourceCond cond = new PubResourceCond();

        BaseResponse<BasePageDetail<PubResource>> bizRes = bizPubRessourceService.listPubResources(cond, operatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
        }
        BasePageDetail<PubResource> basePageDetail = bizRes.getData();

        List<PubResource> bizList = basePageDetail.getDatas();
        for (PubResource pubResource : bizList)
        {
            if (pubResource == null)
            {
                continue;
            }
            resultList.add(fromToViewPublicResource(pubResource));
        }

        baseRes.setData(resultList);
        baseRes.addErrors(bizRes.getErrors());
        return baseRes;
    }

    public void setBizAccountService(BizAccountService bizAccountService)
    {
        this.bizAccountService = bizAccountService;
    }

    public void setBizAccountRoleService(BizAccountRoleService bizAccountRoleService)
    {
        this.bizAccountRoleService = bizAccountRoleService;
    }

    public void setBizAppResPermitService(BizAppResPermitService bizAppResPermitService)
    {
        this.bizAppResPermitService = bizAppResPermitService;
    }

    public void setBizPubRessourceService(BizPubRessourceService bizPubRessourceService)
    {
        this.bizPubRessourceService = bizPubRessourceService;
    }

    public void setBizPubResPermitService(BizPubResPermitService bizPubResPermitService)
    {
        this.bizPubResPermitService = bizPubResPermitService;
    }

    @Override
    public BaseResponse<List<ViewEnumSpec>> listAccountStatus(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewEnumSpec>> baseResponse = new BaseResponse<>();
        List<ViewEnumSpec> list = new ArrayList<>();
        List<BizEnumSpec> bizList = new ArrayList<>();

        try
        {
            BaseResponse<List<BizEnumSpec>> bizBaseResponse = bizAccountService.listAccountStatus(operatorInfo);
            if (bizBaseResponse.hasError())
            {
                baseResponse.addErrors(bizBaseResponse.getErrors());
                return baseResponse;
            }
            bizList = bizBaseResponse.getData();

        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_LISTACCOUNTSTATUS_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "listAccountStatus view error"));
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

    @Override
    public BaseResponse<List<ViewAccount>> listAllAccountsList(String loginId, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<List<ViewAccount>> baseResponse = new BaseResponse<List<ViewAccount>>();
        List<ViewAccount> list = new LinkedList<>();
        if (loginId == null || "".equals(loginId))
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseResponse;
        }
        AccountCond accountCond = new AccountCond();
        accountCond.setLoginIdLike(loginId);

        BaseResponse<BasePageDetail<Account>> bizRes = bizAccountService.listAccounts(accountCond, baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseResponse.addErrors(bizRes.getErrors());
            return baseResponse;
        }

        BasePageDetail<Account> basePageDetail = bizRes.getData();

        if (basePageDetail.getDatas() != null && basePageDetail.getDatas().size() > 0)
        {
            for (Account account : basePageDetail.getDatas())
            {
                ViewAccount viewAccount = new ViewAccount();
                viewAccount.setAccountId(account.getId());
                viewAccount.setLoginId(account.getLoginId());

                list.add(viewAccount);
            }
        }
        baseResponse.setData(list);
        return baseResponse;
    }

    @Override
    public BaseResponse<List<ViewEnumSpec>> listResourcePermitType(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewEnumSpec>> baseResponse = new BaseResponse<>();
        List<ViewEnumSpec> list = new ArrayList<>();
        List<BizEnumSpec> bizList = new ArrayList<>();

        try
        {
            BaseResponse<List<BizEnumSpec>> bizBaseResponse = bizAccountService.listResourcePermitType(operatorInfo);
            if (bizBaseResponse.hasError())
            {
                baseResponse.addErrors(bizBaseResponse.getErrors());
                return baseResponse;
            }
            bizList = bizBaseResponse.getData();

        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.ACCOUNT_LISTRESOURCEPERMITTYPE_ERROR,
                    SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "listResourcePermitType view error"));
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

    @Override
    public BaseResponse<BasePageDetail<ViewAccountPubResource>> listAccountPubRes(ViewAccountPubResCondition condition,
            BaseOperatorInfo operatorInfo)
    {
        // TODO Auto-generated method stub
        BaseResponse<BasePageDetail<ViewAccountPubResource>> baseResponse = new BaseResponse<>();

        if (condition == null || operatorInfo.getOperatorId() == null || operatorInfo.getLanguage() == null
                || "".equals(operatorInfo.getLanguage()))
        {
            baseResponse.addError(
                    ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW,
                            LanguageCode.getByValue(operatorInfo.getLanguage()), "listAccountPubRes view param error"));
            return baseResponse;
        }
        // List<ViewPublicResource> list = new ArrayList<>();
        //
        // PubResourceCond cond = fromToPubResourceCond(condition);
        //
        // cond.setRowsPerPage(operatorInfo.getRowsPerPage());
        // cond.setOffset(operatorInfo.getOffset());
        //
        // List<PubResource> bizList = new ArrayList<>();
        // BasePageDetail<ViewPublicResource> basePageDetail = new BasePageDetail<>();
        //
        // try
        // {
        // BaseResponse<BasePageDetail<PubResource>> bizBaseResponse = bizPubRessourceService.listPubResources(cond,
        // operatorInfo);
        // if (bizBaseResponse.hasError())
        // {
        // baseResponse.addErrors(bizBaseResponse.getErrors());
        // return baseResponse;
        // }
        // BasePageDetail<PubResource> iterable = bizBaseResponse.getData();
        //
        // if (iterable != null)
        // {
        // bizList = iterable.getDatas();
        // basePageDetail.setOffset(iterable.getOffset());
        // basePageDetail.setRowsPerPage(iterable.getRowsPerPage());
        // basePageDetail.setTotal(iterable.getTotal());
        // baseResponse.setData(basePageDetail);
        // }
        // }
        // catch (Exception e)
        // {
        // baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_LISTPUBRESOURCES_ERROR, SEVERITY.ERROR,
        // ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
        // "listResources view error"));
        // }
        //
        // if (bizList != null && bizList.size() > 0)
        // {
        // for (PubResource pubResource : bizList)
        // {
        // if (pubResource == null)
        // {
        // continue;
        // }
        // list.add(fromToViewPublicResource(pubResource));
        // }
        // }

        // basePageDetail.setDatas(list);
        return baseResponse;
    }

}
