package com.omniselling.app.base.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.omniselling.app.base.biz.service.BizAccAppResMgrService;
import com.omniselling.app.base.biz.service.BizAccountService;
import com.omniselling.app.base.web.model.ViewAccount;
import com.omniselling.app.base.web.model.ViewAccountAppResCondition;
import com.omniselling.app.base.web.model.ViewAccountAppResource;
import com.omniselling.app.base.web.model.ViewAcountRefAppResource;
import com.omniselling.app.base.web.model.ViewAppResource;
import com.omniselling.app.base.web.model.ViewEnumSpec;
import com.omniselling.app.base.web.service.ViewAccAppResMgrService;
import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.model.ErrorInfo.SEVERITY;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.common.util.StringUtils;
import com.omniselling.ms.core.model.Account;
import com.omniselling.ms.core.model.AccountAppResCond;
import com.omniselling.ms.core.model.AccountAppResPermission;
import com.omniselling.ms.core.model.AccountCond;
import com.omniselling.ms.core.model.AppResPermitCond;

public class ViewAccAppResMgrServiceImpl implements ViewAccAppResMgrService
{
    private BizAccAppResMgrService bizAccAppResMgrService;
    private BizAccountService bizAccountService;

    public void setBizAccAppResMgrService(BizAccAppResMgrService bizAccAppResMgrService)
    {
        this.bizAccAppResMgrService = bizAccAppResMgrService;
    }

    public void setBizAccountService(BizAccountService bizAccountService)
    {
        this.bizAccountService = bizAccountService;
    }

    @Override
    public BaseResponse<BasePageDetail<ViewAccountAppResource>> listAccountAppRes(
            ViewAccountAppResCondition vAccAppResCond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<ViewAccountAppResource>> baseResponse = new BaseResponse<>();
        BasePageDetail<ViewAccountAppResource> basePageDetail = new BasePageDetail<ViewAccountAppResource>();
        if (vAccAppResCond == null || vAccAppResCond.getOffset() == null || vAccAppResCond.getRowsPerPage() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()), "Parameter is null"));
            return baseResponse;
        }

        AccountAppResCond accAppRes = new AccountAppResCond();
        BeanUtils.copyProperties(vAccAppResCond, accAppRes, new String[] { "resourceType" });
        accAppRes.setOffset(vAccAppResCond.getOffset());
        accAppRes.setRowsPerPage(vAccAppResCond.getRowsPerPage());
        if (!StringUtils.isEmpty(vAccAppResCond.getResourceType()))
        {
            List<String> resourceTypes = Arrays.asList(vAccAppResCond.getResourceType());
            accAppRes.setResourceTypes(resourceTypes);
        }

        try
        {
            BaseResponse<BasePageDetail<AccountAppResPermission>> dbResponse = bizAccAppResMgrService
                    .listAccountAppRes(accAppRes, operatorInfo);
            if (dbResponse.hasError())
            {
                baseResponse.addErrors(dbResponse.getErrors());
                return baseResponse;
            }
            if (dbResponse.getData() == null || dbResponse.getData().getDatas() == null
                    || dbResponse.getData().getDatas().isEmpty())
            {
                basePageDetail.setTotal(0);
                baseResponse.setData(basePageDetail);
                return baseResponse;
            }
            List<ViewAccountAppResource> list = new ArrayList<ViewAccountAppResource>();
            for (AccountAppResPermission accAppResPer : dbResponse.getData().getDatas())
            {
                ViewAccountAppResource viewAccountAppRes = new ViewAccountAppResource();
                BeanUtils.copyProperties(accAppResPer, viewAccountAppRes);
                viewAccountAppRes.setResourceTypeLabel(accAppResPer.getResourceTypeLabel());
                if (accAppResPer.getResourceTypeLabel() == null || accAppResPer.getResourceTypeLabel().isEmpty())
                {
                    viewAccountAppRes.setResourceTypeLabel(accAppResPer.getResourceType());
                }
                list.add(viewAccountAppRes);
            }
            basePageDetail.setDatas(list);
            basePageDetail.setOffset(dbResponse.getData().getOffset());
            basePageDetail.setRowsPerPage(dbResponse.getData().getRowsPerPage());
            basePageDetail.setTotal(dbResponse.getData().getTotal());
            baseResponse.setData(basePageDetail);
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()), e.toString()));
            return baseResponse;
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> deleteAccountAppRes(List<Long> accAppresIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        if (accAppresIds == null || accAppresIds.size() == 0)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()), "Parameter is null"));
            return baseResponse;
        }
        try
        {
            BaseResponse<Boolean> dbResponse = bizAccAppResMgrService.deleteRoleAppRes(accAppresIds, operatorInfo);
            if (dbResponse.hasError() || !dbResponse.hasData())
            {
                baseResponse.addErrors(dbResponse.getErrors());
                return baseResponse;
            }
            baseResponse.setData(dbResponse.getData());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()), e.toString()));
            return baseResponse;
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> addAccountAppRes(ViewAcountRefAppResource viewAccount, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        if (viewAccount == null || viewAccount.getAccountId() == null
                || viewAccount.getAppResources().get(0).getId() == null || viewAccount.getPermitType() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()), "Parameter is null"));
            return baseResponse;
        }

        AppResPermitCond accRoleAppRes = new AppResPermitCond();
        List<Long> accountIds = Arrays.asList(viewAccount.getAccountId());
        accRoleAppRes.setAccountIds(accountIds);
        List<Long> appResourceIds = new ArrayList<Long>();
        for (ViewAppResource viewAppResource : viewAccount.getAppResources())
        {
            appResourceIds.add(viewAppResource.getId());
        }
        accRoleAppRes.setAppResourceId(appResourceIds);
        accRoleAppRes.setPermitType(viewAccount.getPermitType());
        try
        {
            BaseResponse<Boolean> dbResponse = bizAccAppResMgrService.addAccountAppRes(accRoleAppRes, operatorInfo);

            if (dbResponse.hasError() || !dbResponse.hasData())
            {
                baseResponse.addErrors(dbResponse.getErrors());
                return baseResponse;
            }

            baseResponse.setData(dbResponse.getData());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()), e.toString()));
            return baseResponse;
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<List<ViewEnumSpec>> listPermitTypes(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewEnumSpec>> baseResponse = new BaseResponse<>();
        List<ViewEnumSpec> list = new ArrayList<>();

        try
        {
            BaseResponse<List<BizEnumSpec>> bizBaseResponse = bizAccAppResMgrService.listPermitTypes(operatorInfo);
            if (bizBaseResponse.hasError())
            {
                baseResponse.addErrors(bizBaseResponse.getErrors());
                return baseResponse;
            }
            if (!bizBaseResponse.hasData() || bizBaseResponse.getData().size() == 0)
            {
                return baseResponse;
            }
            for (BizEnumSpec enumSpec : bizBaseResponse.getData())
            {
                ViewEnumSpec spec = new ViewEnumSpec();
                spec.setCode(enumSpec.getCode());
                if (enumSpec.getLabel() == null || enumSpec.getLabel().isEmpty())
                {
                    spec.setLabel(enumSpec.getCode());
                }
                spec.setLabel(enumSpec.getLabel());

                list.add(spec);
            }
            baseResponse.setData(list);

        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()), e.toString()));
            return baseResponse;
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<List<ViewAccount>> fetchAccount(BaseOperatorInfo operatorInfo, String loginIdLike)
    {
        BaseResponse<List<ViewAccount>> baseResponse = new BaseResponse<List<ViewAccount>>();
        // 设置类型是主账号
        String accountType = "MAIN";
        AccountCond accCond = new AccountCond();
        accCond.setAccountType(accountType);
        accCond.setLoginIdLike(loginIdLike);
        try
        {
            // 查询主账号信息
            BaseResponse<BasePageDetail<Account>> dbResponse = bizAccountService.listAccounts(accCond, operatorInfo);
            if (dbResponse.hasError())
            {
                baseResponse.addErrors(dbResponse.getErrors());
                return baseResponse;
            }

            List<ViewAccount> list = new ArrayList<ViewAccount>();
            List<Account> dbList = dbResponse.getData().getDatas();
            if (dbList != null && dbList.size() > 0)
            {
                for (Account account : dbList)
                {
                    ViewAccount viewAccount = convertViewAccount(account);
                    list.add(viewAccount);
                }
            }
            baseResponse.setData(list);
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        return baseResponse;
    }

    private ViewAccount convertViewAccount(Account account)
    {
        ViewAccount viewAccount = new ViewAccount();
        viewAccount.setAccountId(account.getId());
        viewAccount.setAccountName(account.getAccountName());
        viewAccount.setAccountStatus(account.getAccountStatus());
        viewAccount.setAccountStatusLabel(account.getAccountStatusLabel());
        viewAccount.setAccountType(account.getAccountType());
        viewAccount.setLoginId(account.getLoginId());
        return viewAccount;
    }

}
