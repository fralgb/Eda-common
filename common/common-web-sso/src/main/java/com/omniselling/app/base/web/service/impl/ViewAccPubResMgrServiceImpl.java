package com.omniselling.app.base.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.omniselling.app.base.biz.service.BizPubResPermitService;
import com.omniselling.app.base.web.model.ViewAccountPubResCondition;
import com.omniselling.app.base.web.model.ViewAccountPubResource;
import com.omniselling.app.base.web.model.ViewAccountRefPubResource;
import com.omniselling.app.base.web.model.ViewPublicResource;
import com.omniselling.app.base.web.service.ViewAccPubResMgrService;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.enumeration.MsAppErrorCode;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.model.ErrorInfo.SEVERITY;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.ms.core.model.AccountPubResPermission;
import com.omniselling.ms.core.model.AccountPubResCond;
import com.omniselling.ms.core.model.PubResPermitCond;

public class ViewAccPubResMgrServiceImpl implements ViewAccPubResMgrService
{

    private BizPubResPermitService bizPubResPermitService;

    public void setBizPubResPermitService(BizPubResPermitService bizPubResPermitService)
    {
        this.bizPubResPermitService = bizPubResPermitService;
    }

    @Override
    public BaseResponse<Boolean> deleteAccountPubRes(List<Long> accPubResPermitIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();

        if (accPubResPermitIds == null || accPubResPermitIds.size() == 0 || operatorInfo.getOperatorId() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        try
        {
            BaseResponse<Boolean> bizBaseResponse = bizPubResPermitService.deleteAccPubResPermitByIds(
                    accPubResPermitIds, operatorInfo);
            baseResponse.setData(bizBaseResponse.getData());
            baseResponse.addErrors(bizBaseResponse.getErrors());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_DELETEACCPUBRESPERMITBYIDS_ERROR,
                    SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, e, LanguageCode.getByValue(operatorInfo.getLanguage())));
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> addAccountPubRes(ViewAccountRefPubResource cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();

        if (cond == null || cond.getAccountId() == null || cond.getPublicResources() == null
                || cond.getPublicResources().isEmpty() || operatorInfo.getOperatorId() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        PubResPermitCond pubResPermitCond = new PubResPermitCond();
        List<Long> accountIds = new ArrayList<>();
        accountIds.add(cond.getAccountId());
        List<Long> pubResourceIds = new ArrayList<>();
        List<ViewPublicResource> viewPublicResources = cond.getPublicResources();
        for (ViewPublicResource viewPublicResource : viewPublicResources)
        {
            pubResourceIds.add(viewPublicResource.getId());
        }
        pubResPermitCond.setAccountIds(accountIds);
        pubResPermitCond.setPubResourceIds(pubResourceIds);
        
        try
        {
            BaseResponse<Boolean> bizBaseResponse = bizPubResPermitService.createResPermission(pubResPermitCond,
                    operatorInfo);

            baseResponse.setData(bizBaseResponse.getData());
            baseResponse.addErrors(bizBaseResponse.getErrors());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_ADDACCOUNTPUBRES_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<BasePageDetail<ViewAccountPubResource>> listAccountPubRes(ViewAccountPubResCondition condition,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<ViewAccountPubResource>> baseResponse = new BaseResponse<>();

        if (condition == null || operatorInfo.getOperatorId() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        AccountPubResCond accountPubResPermitCond = new AccountPubResCond();
        BeanUtils.copyProperties(condition, accountPubResPermitCond);
        if (condition.getAccountId() != null)
        {
            List<Long> accountIds = new ArrayList<>();
            accountIds.add(condition.getAccountId());
            accountPubResPermitCond.setAccountIds(accountIds);
        }
        if (!StringUtils.isEmpty(condition.getResourceType()))
        {
            List<String> resourceTypes = new ArrayList<>();
            resourceTypes.add(condition.getResourceType());
            accountPubResPermitCond.setResourceTypes(resourceTypes);
        }

        BaseResponse<BasePageDetail<AccountPubResPermission>> bizBaseResponse = null;
        try
        {
            bizBaseResponse = bizPubResPermitService.listAccountPubRes(accountPubResPermitCond, operatorInfo);

            if (bizBaseResponse.hasError())
            {
                baseResponse.addErrors(bizBaseResponse.getErrors());
                return baseResponse;
            }
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_LISTPUBRESOURCES_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, e, LanguageCode.getByValue(operatorInfo.getLanguage())));
        }

        BasePageDetail<AccountPubResPermission> detailData = bizBaseResponse.getData();
        BasePageDetail<ViewAccountPubResource> basePageDetail = new BasePageDetail<>();
        if (detailData != null)
        {
            basePageDetail.setTotal(detailData.getTotal());
            baseResponse.setData(basePageDetail);
            List<ViewAccountPubResource> list = new ArrayList<>();
            List<AccountPubResPermission> bizList = detailData.getDatas();
            if (bizList != null && bizList.size() > 0)
            {
                for (AccountPubResPermission accPubRes : bizList)
                {
                    list.add(tranferToViewAccountPubResource(accPubRes));
                }
            }
            basePageDetail.setDatas(list);
        }

        basePageDetail.setOffset(condition.getOffset());
        basePageDetail.setRowsPerPage(condition.getRowsPerPage());
        return baseResponse;
    }

    private ViewAccountPubResource tranferToViewAccountPubResource(AccountPubResPermission accountPubResPermission)
    {
        ViewAccountPubResource viewPubResource = new ViewAccountPubResource();
        BeanUtils.copyProperties(accountPubResPermission, viewPubResource);
        if (accountPubResPermission.getAccountTypeLabel() == null)
        {
            viewPubResource.setAccountTypeLabel(accountPubResPermission.getAccountType());
        }
        if (accountPubResPermission.getPermitTypeLabel() == null)
        {
            viewPubResource.setPermitTypeLabel(accountPubResPermission.getPermitType());
        }
        if (accountPubResPermission.getResourceStatusLabel() == null)
        {
            viewPubResource.setResourceStatusLabel(accountPubResPermission.getResourceStatus());
        }
        if (accountPubResPermission.getAccountTypeLabel() == null)
        {
            viewPubResource.setAccountTypeLabel(accountPubResPermission.getAccountType());
        }
        return viewPubResource;
    }

}
