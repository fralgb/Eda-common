package com.omniselling.app.base.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.omniselling.app.base.biz.service.BizPubRessourceService;
import com.omniselling.app.base.web.model.ViewEnumSpec;
import com.omniselling.app.base.web.model.ViewPublicResource;
import com.omniselling.app.base.web.model.ViewPublicResourceCondition;
import com.omniselling.app.base.web.service.ViewPubResMgrService;
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
import com.omniselling.ms.core.model.PubResource;
import com.omniselling.ms.core.model.PubResourceCond;

/**
 * 实现: 公共资源管理
 * 
 * @author Administrator
 *
 */
public class ViewPubResMgrServiceImpl implements ViewPubResMgrService
{

    private BizPubRessourceService bizPubRessourceService;

    public void setBizPubRessourceService(BizPubRessourceService bizPubRessourceService)
    {
        this.bizPubRessourceService = bizPubRessourceService;
    }

    @Override
    public BaseResponse<BasePageDetail<ViewPublicResource>> listResources(ViewPublicResourceCondition condition,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<ViewPublicResource>> baseResponse = new BaseResponse<>();

        if (condition == null || operatorInfo.getOperatorId() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "listResources view param error"));
            return baseResponse;
        }
        List<ViewPublicResource> list = new ArrayList<>();

        PubResourceCond cond = convertPubResourceCond(condition);

        BaseResponse<BasePageDetail<PubResource>> bizBaseResponse = null;
        try
        {
            bizBaseResponse = bizPubRessourceService.listPubResources(cond, operatorInfo);
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_LISTPUBRESOURCES_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, e, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        if (bizBaseResponse.hasError())
        {
            baseResponse.addErrors(bizBaseResponse.getErrors());
            return baseResponse;
        }
        BasePageDetail<PubResource> detailData = bizBaseResponse.getData();
        if (detailData == null)
        {
            return baseResponse;
        }
        BasePageDetail<ViewPublicResource> basePageDetail = new BasePageDetail<>();
        List<PubResource> bizList = detailData.getDatas();
        basePageDetail.setTotal(detailData.getTotal());
        baseResponse.setData(basePageDetail);

        if (bizList != null && bizList.size() > 0)
        {
            for (PubResource pubResource : bizList)
            {
                if (pubResource == null)
                {
                    continue;
                }
                list.add(convertViewPublicResource(pubResource));
            }
        }

        basePageDetail.setOffset(condition.getOffset());
        basePageDetail.setRowsPerPage(condition.getRowsPerPage());
        basePageDetail.setDatas(list);
        return baseResponse;
    }

    // @Override
    // public BaseResponse<List<ViewPublicResource>> listResourcesByCond(ViewPublicResourceCondition condition,
    // BaseOperatorInfo operatorInfo)
    // {
    // BaseResponse<List<ViewPublicResource>> baseResponse = new BaseResponse<>();
    //
    // List<ViewPublicResource> resultList = new ArrayList<>();
    // List<PubResource> bizList = new ArrayList<>();
    // if (condition == null || operatorInfo.getOperatorId() == null || operatorInfo.getLanguage() == null
    // || "".equals(operatorInfo.getLanguage()))
    // {
    // baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
    // ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
    // return baseResponse;
    // }
    //
    // PubResourceCond cond = fromToPubResourceCond(condition);
    //
    // try
    // {
    // BaseResponse<BasePageDetail<PubResource>> bizBaseResponse = bizPubRessourceService
    // .listPubResourcesByCond(cond, operatorInfo);
    // if (bizBaseResponse.hasError())
    // {
    // baseResponse.addErrors(bizBaseResponse.getErrors());
    // return baseResponse;
    // }
    // BasePageDetail<PubResource> iterable = bizBaseResponse.getData();
    // if (iterable != null)
    // {
    // bizList = iterable.getDatas();
    // }
    // }
    // catch (Exception e)
    // {
    // baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_LISTPUBRESOURCESBYCOND_ERROR,
    // SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
    // e.getMessage()));
    // }
    //
    // if (condition != null && bizList.size() > 0)
    // {
    // for (PubResource pubResource : bizList)
    // {
    // if (pubResource == null)
    // {
    // continue;
    // }
    // resultList.add(fromToViewPublicResource(pubResource));
    // }
    // }
    //
    // baseResponse.setData(resultList);
    // return baseResponse;
    // }

    @Override
    public BaseResponse<ViewPublicResource> resourceDetail(Long pubResourceId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<ViewPublicResource> baseResponse = new BaseResponse<>();
        PubResource pubResource = null;

        if (pubResourceId == null || operatorInfo.getOperatorId() == null || operatorInfo.getLanguage() == null
                || "".equals(operatorInfo.getLanguage()))
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }
        try
        {
            BaseResponse<PubResource> bizBaseResponse = bizPubRessourceService.getPubResourceById(pubResourceId,
                    operatorInfo);
            if (bizBaseResponse.hasError())
            {
                baseResponse.addErrors(bizBaseResponse.getErrors());
                return baseResponse;
            }
            pubResource = bizBaseResponse.getData();
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_GETPUBRESOURCEBYID_ERROR,
                    SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    e.getMessage()));
        }

        if (pubResource == null)
        {
            return baseResponse;
        }

        ViewPublicResource viewPublicResource = convertViewPublicResource(pubResource);

        baseResponse.setData(viewPublicResource);
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> activeResource(List<Long> resourceIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();

        if (resourceIds == null || resourceIds.size() == 0 || operatorInfo.getOperatorId() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "activeResource view param null"));
            return baseResponse;
        }
        try
        {
            BaseResponse<Boolean> bizBaseResponse = bizPubRessourceService.activePubResourcesById(resourceIds,
                    operatorInfo);

            baseResponse.setData(bizBaseResponse.getData());
            baseResponse.addErrors(bizBaseResponse.getErrors());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_ACTIVEPUBRESOURCESBYID_ERROR,
                    SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "activeResource view error"));
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> disableResource(List<Long> resourceIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();

        if (resourceIds == null || resourceIds.size() == 0 || operatorInfo.getOperatorId() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "disableResource view param null"));
            return baseResponse;
        }

        try
        {
            BaseResponse<Boolean> bizBaseResponse = bizPubRessourceService.disablePubResourcesById(resourceIds,
                    operatorInfo);
            baseResponse.setData(bizBaseResponse.getData());
            baseResponse.addErrors(bizBaseResponse.getErrors());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_DISABLEPUBRESOURCESBYID_ERROR,
                    SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "disableResource view error"));
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<List<ViewEnumSpec>> listResourceTypes(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewEnumSpec>> baseResponse = new BaseResponse<>();
        List<ViewEnumSpec> list = new ArrayList<>();
        List<BizEnumSpec> bizList = new ArrayList<>();

        try
        {
            BaseResponse<List<BizEnumSpec>> bizBaseResponse = bizPubRessourceService.listResourceTypes(operatorInfo);
            if (bizBaseResponse.hasError())
            {
                baseResponse.addErrors(bizBaseResponse.getErrors());
                return baseResponse;
            }
            bizList = bizBaseResponse.getData();

        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_LISTPUBRESOURCETYPES_ERROR,
                    SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "listResourceTypes view error"));
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
    public BaseResponse<List<ViewEnumSpec>> listPubResourceStatus(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewEnumSpec>> baseResponse = new BaseResponse<>();
        List<ViewEnumSpec> list = new ArrayList<>();
        List<BizEnumSpec> bizList = new ArrayList<>();

        try
        {
            BaseResponse<List<BizEnumSpec>> bizBaseResponse = bizPubRessourceService
                    .listPubResourceStatus(operatorInfo);
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
                    "listPubResourceStatus view error"));
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

    private ViewPublicResource convertViewPublicResource(PubResource pubResource)
    {
        ViewPublicResource viewPublicResource = new ViewPublicResource();
        BeanUtils.copyProperties(pubResource, viewPublicResource);
        viewPublicResource.setResourceTypeCode(pubResource.getResourceType());
        if (pubResource.getResourceType() != null)
        {
            viewPublicResource.setResourceType(pubResource.getResourceType());
        }
        if (pubResource.getResourceTypeLabel() == null)
        {
            viewPublicResource.setResourceStatusLabel(pubResource.getResourceType());
        }
        return viewPublicResource;
    }

    private PubResourceCond convertPubResourceCond(ViewPublicResourceCondition condition)
    {
        PubResourceCond pubResourceCond = new PubResourceCond();
        BeanUtils.copyProperties(condition, pubResourceCond);
        return pubResourceCond;
    }

    // @Override
    // public BaseResponse<Boolean> deleteAccountPubRes(List<Long> accPubResPermitIds, BaseOperatorInfo operatorInfo)
    // {
    // BaseResponse<Boolean> baseResponse = new BaseResponse<>();
    //
    // if (accPubResPermitIds == null || accPubResPermitIds.size() == 0 || operatorInfo.getOperatorId() == null)
    // {
    // baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
    // ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
    // "deleteAccountPubRes view param null"));
    // return baseResponse;
    // }
    //
    // try
    // {
    // BaseResponse<Boolean> bizBaseResponse = bizPubResPermitService
    // .deleteAccPubResPermitByIds(accPubResPermitIds, operatorInfo);
    // baseResponse.setData(bizBaseResponse.getData());
    // baseResponse.addErrors(bizBaseResponse.getErrors());
    // }
    // catch (Exception e)
    // {
    // baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_DELETEACCPUBRESPERMITBYIDS_ERROR,
    // SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
    // "deleteAccountPubRes view error"));
    // }
    //
    // return baseResponse;
    // }

    // @Override
    // public BaseResponse<Boolean> addAccountPubRes(ViewAccountPubResource cond, BaseOperatorInfo operatorInfo)
    // {
    // BaseResponse<Boolean> baseResponse = new BaseResponse<>();
    //
    // if (cond == null || operatorInfo.getOperatorId() == null)
    // {
    // baseResponse.addError(
    // ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW,
    // LanguageCode.getByValue(operatorInfo.getLanguage()), "addAccountPubRes view param null"));
    // return baseResponse;
    // }
    //
    // try
    // {
    // PubResPermitCond pubResPermitCond = fromToPubResPermitCond(cond);
    //
    // BaseResponse<Boolean> bizBaseResponse = bizPubResPermitService.createResPermission(pubResPermitCond,
    // operatorInfo);
    //
    // baseResponse.setData(bizBaseResponse.getData());
    // baseResponse.addErrors(bizBaseResponse.getErrors());
    // }
    // catch (Exception e)
    // {
    // baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_ADDACCOUNTPUBRES_ERROR, SEVERITY.ERROR,
    // ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
    // "addAccountPubRes view error"));
    // }
    //
    // return baseResponse;
    // }

    // private PubResPermitCond fromToPubResPermitCond(ViewAccountPubResource cond)
    // {
    // PubResPermitCond permitCond = new PubResPermitCond();
    // List<Long> accontIds = new ArrayList<>();
    // accontIds.add(cond.getAccountId());
    // permitCond.setAccountIds(accontIds);
    //
    // List<Long> pubResourceIds = new ArrayList<>();
    // pubResourceIds.add(cond.getPubResourceId());
    // permitCond.setPubResourceIds(pubResourceIds);
    //
    // return permitCond;
    // }

    // @Override
    // public BaseResponse<BasePageDetail<ViewAccountPubResource>> listAccountPubRes(ViewAccountPubResCondition
    // condition,
    // BaseOperatorInfo operatorInfo)
    // {
    // BaseResponse<BasePageDetail<ViewAccountPubResource>> baseResponse = new BaseResponse<>();
    //
    // if (condition == null || operatorInfo.getOperatorId() == null)
    // {
    // baseResponse.addError(
    // ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW,
    // LanguageCode.getByValue(operatorInfo.getLanguage()), "listAccountPubRes view param error"));
    // return baseResponse;
    // }
    // List<ViewAccountPubResource> list = new ArrayList<>();
    //
    // AccountPubResourceCond cond = fromToAccountPubResourceCond(condition);
    //
    // //TODO
    // // cond.setLimit(condition.getRowsPerPage());
    // // cond.setOffset(operatorInfo.getOffset());
    //
    // List<AccountPubResource> bizList = new ArrayList<>();
    // BasePageDetail<ViewAccountPubResource> basePageDetail = new BasePageDetail<>();
    //
    // try
    // {
    // BaseResponse<BasePageDetail<AccountPubResource>> bizBaseResponse = bizPubResPermitService
    // .listAccountPubRes(cond, operatorInfo);
    //
    // if (bizBaseResponse.hasError())
    // {
    // baseResponse.addErrors(bizBaseResponse.getErrors());
    // return baseResponse;
    // }
    // BasePageDetail<AccountPubResource> iterable = bizBaseResponse.getData();
    //
    // if (iterable != null)
    // {
    // bizList = iterable.getDatas();
    // basePageDetail.setOffset(iterable.getOffset());
    // basePageDetail.setRowsPerPage(iterable.getRowsPerPage());
    // basePageDetail.setTotal(iterable.getTotal());
    //
    // baseResponse.setData(basePageDetail);
    // }
    // }
    // catch (Exception e)
    // {
    // baseResponse.addError(ErrorInfoBuilder.build(MsAppErrorCode.PUBRES_LISTPUBRESOURCES_ERROR, SEVERITY.ERROR,
    // ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
    // "listAccountPubRes view error"));
    // }
    //
    // if (bizList != null && bizList.size() > 0)
    // {
    // for (AccountPubResource accPubRes : bizList)
    // {
    // if (accPubRes == null)
    // {
    // continue;
    // }
    //
    // list.add(fromToViewAccountPubResource(accPubRes));
    // }
    // }
    //
    // basePageDetail.setDatas(list);
    // return baseResponse;
    // }

    // private AccountPubResourceCond fromToAccountPubResourceCond(ViewAccountPubResCondition condition)
    // {
    // AccountPubResourceCond accPubResourceCond = new AccountPubResourceCond();
    //
    // List<Long> accountIds = new ArrayList<>();
    // accountIds.add(condition.getAccountId());
    // accPubResourceCond.setAccountIds(accountIds);
    // //accPubResourceCond.setAccpubrespermitId(condition.getAccPubResPermitId());
    //
    // List<String> permitTypes = new ArrayList<>();
    // //permitTypes.add(condition.getPermitType());
    //
    // // TODO
    // // accPubResourceCond.setInvalidDateFrom(condition.getI);
    // // accPubResourceCond.setInvalidDateTo(invalidDateTo);
    // // accPubResourceCond.setLimit(limit);
    // // accPubResourceCond.setOffset(offset);
    // accPubResourceCond.setPermitTypes(permitTypes);
    // // accPubResourceCond.setPublishDateFrom(condition.getP);
    // // accPubResourceCond.setPublishDateTo(publishDateTo);
    // accPubResourceCond.setPublishNum(condition.getPublishNum());
    // // accPubResourceCond.setPublishNumLike(publishNumLike);
    // List<Long> pubResourceIds = new ArrayList<>();
    // //pubResourceIds.add(condition.getPubResourceId());
    //
    // accPubResourceCond.setPubResourceIds(pubResourceIds);
    // //accPubResourceCond.setResourceName(condition.getPubResourceName());
    // // accPubResourceCond.setResourceNameLike(resourceNameLike);
    // //accPubResourceCond.setResourceStatus(condition.getResourceStatus());
    // //accPubResourceCond.setResourceType(condition.getResourceType());
    // //accPubResourceCond.setRoleName(condition.getRoleName());
    // // accPubResourceCond.setRoleNameLike(roleNameLike);
    //
    // return accPubResourceCond;
    // }

    // private ViewAccountPubResource fromToViewAccountPubResource(AccountPubResource accPubRes)
    // {
    // // TODO Auto-generated method stub
    // ViewAccountPubResource viewModel = new ViewAccountPubResource();
    // viewModel.setAccountId(accPubRes.getAccountId());
    // viewModel.setAccountName(accPubRes.getAccountName());
    // viewModel.setAccPubResPermitId(accPubRes.getAccPubResPermitId());
    // viewModel.setLoginId(accPubRes.getLoginId());
    // viewModel.setOwnerId(accPubRes.getOwnerId());
    // viewModel.setPermitType(accPubRes.getPermitType());
    // // TODO 来自 i18n
    // if (accPubRes.getPermitType() != null)
    // {
    // viewModel.setPermitTypeLabel(accPubRes.getPermitType().name());
    // }
    //
    // viewModel.setPublishDate(accPubRes.getPublishDate());
    // viewModel.setPublishNum(accPubRes.getPublishNum());
    // viewModel.setPubResourceId(accPubRes.getPubResourceId());
    // viewModel.setPubResourceName(accPubRes.getPubResourceName());
    // viewModel.setResourceObjectId(accPubRes.getResourceObjectId());
    // viewModel.setResourceStatus(accPubRes.getResourceStatus());
    // // TODO 来自 i18n
    // if (accPubRes.getResourceStatus() != null)
    // {
    // viewModel.setResourceStatusLabel(accPubRes.getResourceStatus().name());
    // }
    //
    // viewModel.setResourceType(accPubRes.getResourceType());
    // // TODO 来自 i18n
    // if (accPubRes.getResourceType() != null)
    // {
    // viewModel.setResourceTypeLabel(accPubRes.getResourceType().name());
    // }
    //
    // viewModel.setRoleName(accPubRes.getRoleName());
    //
    // return viewModel;
    // }

}
