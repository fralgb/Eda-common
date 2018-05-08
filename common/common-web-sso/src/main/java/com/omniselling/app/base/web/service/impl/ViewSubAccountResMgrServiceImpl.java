package com.omniselling.app.base.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.omniselling.app.base.biz.service.BizAccAppResMgrService;
import com.omniselling.app.base.web.model.ViewAccount;
import com.omniselling.app.base.web.model.ViewAccountAppResCondition;
import com.omniselling.app.base.web.model.ViewAccountAppResource;
import com.omniselling.app.base.web.model.ViewAccountCondition;
import com.omniselling.app.base.web.model.ViewAcountRefAppResource;
import com.omniselling.app.base.web.model.ViewAppResource;
import com.omniselling.app.base.web.model.ViewEnumSpec;
import com.omniselling.app.base.web.service.ViewAccAppResMgrService;
import com.omniselling.app.base.web.service.ViewAccountMgrService;
import com.omniselling.app.base.web.service.ViewAppResMgrService;
import com.omniselling.app.base.web.service.ViewSubAccountResMgrService;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.model.ErrorInfo.SEVERITY;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.common.util.StringUtils;
import com.omniselling.ms.core.model.AccountAppResCond;
import com.omniselling.ms.core.model.AccountAppResPermission;
import com.omniselling.ms.core.model.AppResPermitCond;

public class ViewSubAccountResMgrServiceImpl implements ViewSubAccountResMgrService
{
    private ViewAccountMgrService viewAccountMgrService;
    private ViewAppResMgrService viewAppResMgrService;
    private ViewAccAppResMgrService viewAccAppResMgrService;
    private BizAccAppResMgrService bizAccAppResMgrService;

    @Override
    public BaseResponse<BasePageDetail<ViewAccountAppResource>> listSubAccAppRes(ViewAccountAppResCondition vAccResCond,
            BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<BasePageDetail<ViewAccountAppResource>> baseRes = new BaseResponse<>();
        BasePageDetail<ViewAccountAppResource> basePageDetail = new BasePageDetail<ViewAccountAppResource>();

        Long accountId = baseOperatorInfo.getOperatorId();
        BaseResponse<List<ViewAccount>> vAccRes = viewAccountMgrService.listSubAccount(accountId, baseOperatorInfo);
        if (vAccRes.hasError())
        {
            baseRes.addErrors(vAccRes.getErrors());
            return baseRes;
        }
        AccountAppResCond accAppRes = new AccountAppResCond();
        BeanUtils.copyProperties(vAccResCond, accAppRes, new String[] { "resourceType", "accountIds" });
        accAppRes.setOffset(vAccResCond.getOffset());
        accAppRes.setRowsPerPage(vAccResCond.getRowsPerPage());
        if (!StringUtils.isEmpty(vAccResCond.getResourceType()))
        {
            List<String> resourceTypes = Arrays.asList(vAccResCond.getResourceType());
            accAppRes.setResourceTypes(resourceTypes);
        }
        if(vAccRes.hasData()){
            List<ViewAccount> vAccounts = vAccRes.getData();
            List<Long> accountIds = new ArrayList<>();
            for (ViewAccount account : vAccounts)
            {
                accountIds.add(account.getAccountId());
            }
            accAppRes.setAccountIds(accountIds);
        }
        BaseResponse<BasePageDetail<AccountAppResPermission>> dbResponse = bizAccAppResMgrService
                .listAccountAppRes(accAppRes, baseOperatorInfo);

        if (dbResponse.hasError())
        {
            baseRes.addErrors(dbResponse.getErrors());
            return baseRes;
        }
        if (dbResponse.getData().getDatas() == null)
        {
            basePageDetail.setTotal(0);
            baseRes.setData(basePageDetail);
            return baseRes;
        }
        List<ViewAccountAppResource> list = new ArrayList<ViewAccountAppResource>();
        for (AccountAppResPermission accAppResPer : dbResponse.getData().getDatas())
        {
            ViewAccountAppResource viewAccountAppRes = new ViewAccountAppResource();
            BeanUtils.copyProperties(accAppResPer, viewAccountAppRes);
            if (StringUtils.isEmpty(accAppResPer.getResourceTypeLabel()))
            {
                viewAccountAppRes.setResourceTypeLabel(accAppResPer.getResourceType());
            }
            list.add(viewAccountAppRes);
        }
        basePageDetail.setDatas(list);
        basePageDetail.setOffset(dbResponse.getData().getOffset());
        basePageDetail.setRowsPerPage(dbResponse.getData().getRowsPerPage());
        basePageDetail.setTotal(dbResponse.getData().getTotal());
        baseRes.setData(basePageDetail);

        return baseRes;
    }

    @Override
    public BaseResponse<BasePageDetail<ViewAccountAppResource>> listMainAccAppRes(
            ViewAccountAppResCondition vAccResCond, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<BasePageDetail<ViewAccountAppResource>> baseRes = new BaseResponse<>();
        BasePageDetail<ViewAccountAppResource> basePageDetail = new BasePageDetail<ViewAccountAppResource>();

        Long accountId = baseOperatorInfo.getOperatorId();
        AccountAppResCond accAppRes = new AccountAppResCond();
        BeanUtils.copyProperties(vAccResCond, accAppRes, new String[] { "resourceType", "accountIds" });
        accAppRes.setOffset(vAccResCond.getOffset());
        accAppRes.setRowsPerPage(vAccResCond.getRowsPerPage());
        accAppRes.setAccountIds(Arrays.asList(accountId));
        if (!StringUtils.isEmpty(vAccResCond.getResourceType()))
        {
            List<String> resourceTypes = Arrays.asList(vAccResCond.getResourceType());
            accAppRes.setResourceTypes(resourceTypes);
        }
        BaseResponse<BasePageDetail<AccountAppResPermission>> dbResponse = bizAccAppResMgrService
                .listAccountAppRes(accAppRes, baseOperatorInfo);

        if (dbResponse.hasError())
        {
            baseRes.addErrors(dbResponse.getErrors());
            return baseRes;
        }
        if (dbResponse.getData().getDatas() == null)
        {
            basePageDetail.setTotal(0);
            baseRes.setData(basePageDetail);
            return baseRes;
        }
        List<ViewAccountAppResource> list = new ArrayList<ViewAccountAppResource>();
        for (AccountAppResPermission accAppResPer : dbResponse.getData().getDatas())
        {
            ViewAccountAppResource viewAccountAppRes = new ViewAccountAppResource();
            BeanUtils.copyProperties(accAppResPer, viewAccountAppRes);
            if (StringUtils.isEmpty(accAppResPer.getResourceTypeLabel()))
            {
                viewAccountAppRes.setResourceTypeLabel(accAppResPer.getResourceType());
            }
            list.add(viewAccountAppRes);
        }
        basePageDetail.setDatas(list);
        basePageDetail.setOffset(dbResponse.getData().getOffset());
        basePageDetail.setRowsPerPage(dbResponse.getData().getRowsPerPage());
        basePageDetail.setTotal(dbResponse.getData().getTotal());
        baseRes.setData(basePageDetail);
        return baseRes;
    }

    @Override
    public BaseResponse<Boolean> deleteSubAccAppRes(List<Long> accResRefIds, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();
        if (accResRefIds == null || accResRefIds.isEmpty())
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
        }

        baseRes = viewAccAppResMgrService.deleteAccountAppRes(accResRefIds, baseOperatorInfo);
        return baseRes;
    }

    @Override
    public BaseResponse<Boolean> addSubAccAppRes(ViewAcountRefAppResource vAccRefAppRes, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        if (vAccRefAppRes == null || vAccRefAppRes.getAccountId() == null
                || vAccRefAppRes.getAppResources().get(0).getId() == null || vAccRefAppRes.getPermitType() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()), "Parameter is null"));
            return baseResponse;
        }
        AppResPermitCond appResPermitCond = new AppResPermitCond();

        List<Long> accountIds = Arrays.asList(vAccRefAppRes.getAccountId());
        appResPermitCond.setAccountIds(accountIds);
        List<Long> appResourceIds = new ArrayList<Long>();
        for (ViewAppResource viewAppResource : vAccRefAppRes.getAppResources())
        {
            appResourceIds.add(viewAppResource.getAppResourceId());
        }
        appResPermitCond.setAppResourceId(appResourceIds);
        appResPermitCond.setPermitType(vAccRefAppRes.getPermitType());
        
        BaseResponse<Boolean> baseRes = bizAccAppResMgrService.addAccountAppRes(appResPermitCond, operatorInfo);
        return baseRes;
    }

    @Override
    public BaseResponse<List<ViewAccount>> listSubAccounts(String loginIdLike, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<List<ViewAccount>> baseRes = new BaseResponse<List<ViewAccount>>();
        ViewAccountCondition vAccountCond = new ViewAccountCondition();
        vAccountCond.setLoginIdLike(loginIdLike);
        vAccountCond.setParentId(baseOperatorInfo.getOperatorId());
        BaseResponse<BasePageDetail<ViewAccount>> vAccRes = viewAccountMgrService.listAccounts(vAccountCond,
                baseOperatorInfo);
        if (vAccRes.hasError())
        {
            baseRes.setErrors(vAccRes.getErrors());
        }
        if (vAccRes.hasData())
        {
            baseRes.setData(vAccRes.getData().getDatas());
        }
        return baseRes;
    }

    @Override
    public BaseResponse<List<ViewEnumSpec>> listAppResTypes(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewEnumSpec>> baseRes = new BaseResponse<List<ViewEnumSpec>>();
        baseRes = viewAppResMgrService.listAppResourceTypes(operatorInfo);
        return baseRes;
    }

    public void setViewAccountMgrService(ViewAccountMgrService viewAccountMgrService)
    {
        this.viewAccountMgrService = viewAccountMgrService;
    }

    public void setViewAppResMgrService(ViewAppResMgrService viewAppResMgrService)
    {
        this.viewAppResMgrService = viewAppResMgrService;
    }

    public void setViewAccAppResMgrService(ViewAccAppResMgrService viewAccAppResMgrService)
    {
        this.viewAccAppResMgrService = viewAccAppResMgrService;
    }

    public void setBizAccAppResMgrService(BizAccAppResMgrService bizAccAppResMgrService)
    {
        this.bizAccAppResMgrService = bizAccAppResMgrService;
    }
}
