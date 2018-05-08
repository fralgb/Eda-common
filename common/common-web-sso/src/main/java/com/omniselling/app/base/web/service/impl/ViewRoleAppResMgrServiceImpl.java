package com.omniselling.app.base.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.omniselling.app.base.biz.service.BizRoleAppResMgrService;
import com.omniselling.app.base.web.model.ViewAcountRoleRefAppResource;
import com.omniselling.app.base.web.model.ViewAppResource;
import com.omniselling.app.base.web.model.ViewRoleAppResCondition;
import com.omniselling.app.base.web.model.ViewRoleAppResource;
import com.omniselling.app.base.web.service.ViewRoleAppResMgrService;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.model.ErrorInfo.SEVERITY;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.ms.core.model.AppResPermitCond;
import com.omniselling.ms.core.model.RoleAppResPermission;
import com.omniselling.ms.core.model.RoleAppResCond;

public class ViewRoleAppResMgrServiceImpl implements ViewRoleAppResMgrService
{

    private BizRoleAppResMgrService bizRoleAppResMgrService;

    @Override
    public BaseResponse<BasePageDetail<ViewRoleAppResource>> listRoleAppRes(ViewRoleAppResCondition viewRoleAppResCond,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<ViewRoleAppResource>> baseResponse = new BaseResponse<>();
        BasePageDetail<ViewRoleAppResource> basePageDetail = new BasePageDetail<ViewRoleAppResource>();
        if (viewRoleAppResCond == null || viewRoleAppResCond.getOffset() == null
                || viewRoleAppResCond.getRowsPerPage() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "viewRoleAppResCondition or Offset or RowsPerPage is null"));
            return baseResponse;
        }
        //转化
        RoleAppResCond roleAppResPermitCond = convertRoleAppResCond(viewRoleAppResCond);
        
        try
        {
            BaseResponse<BasePageDetail<RoleAppResPermission>> dbResponse = bizRoleAppResMgrService.listRoleAppRes(
                    roleAppResPermitCond, operatorInfo);
            if (dbResponse.hasError())
            {
                baseResponse.addErrors(dbResponse.getErrors());
                return baseResponse;
            }
            if (!dbResponse.hasData() || dbResponse.getData().getDatas() == null
                    || dbResponse.getData().getDatas().isEmpty())
            {
                basePageDetail.setTotal(0);
                baseResponse.setData(basePageDetail);
                return baseResponse;
            }

            List<ViewRoleAppResource> list = new ArrayList<ViewRoleAppResource>();
            for (RoleAppResPermission roleAppRes : dbResponse.getData().getDatas())
            {
                ViewRoleAppResource viewRoleApp = new ViewRoleAppResource();
                BeanUtils.copyProperties(roleAppRes, viewRoleApp,
                        new String[] { "accountRoleName", "accountRoleLabel" });
                viewRoleApp.setRoleName(roleAppRes.getAccountRoleName());
                viewRoleApp.setRoleLabel(roleAppRes.getAccountRoleLabel());
                if (roleAppRes.getAccountRoleLabel() == null || roleAppRes.getAccountRoleLabel().isEmpty())
                {
                    viewRoleApp.setRoleLabel(roleAppRes.getAccountRoleName());
                }
                if (roleAppRes.getResourceTypeLabel() == null || roleAppRes.getResourceTypeLabel().isEmpty())
                {
                    viewRoleApp.setResourceTypeLabel(roleAppRes.getResourceType());
                }
                list.add(viewRoleApp);
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
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        return baseResponse;
    }

    private RoleAppResCond convertRoleAppResCond(ViewRoleAppResCondition viewRoleAppResCond)
    {
        RoleAppResCond roleAppResPermitCond = new RoleAppResCond();
        BeanUtils.copyProperties(viewRoleAppResCond, roleAppResPermitCond, new String[] { "resourceTypes" });
        List<String> resourceTypes = new ArrayList<String>();
        resourceTypes.add(viewRoleAppResCond.getResourceType());
        roleAppResPermitCond.setResourceTypes(resourceTypes);
        roleAppResPermitCond.setOffset(viewRoleAppResCond.getOffset());
        roleAppResPermitCond.setRowsPerPage(viewRoleAppResCond.getRowsPerPage());
        return roleAppResPermitCond;
    }

    @Override
    public BaseResponse<Boolean> deleteRoleAppRes(List<Long> roleappresIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        if (roleappresIds == null || roleappresIds.size() == 0)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "roleappresIds is null"));
            return baseResponse;
        }
        BaseResponse<Boolean> dbResponse = bizRoleAppResMgrService.deleteRoleAppRes(roleappresIds, operatorInfo);
        if (dbResponse.hasError() || !dbResponse.hasData())
        {
            baseResponse.addErrors(dbResponse.getErrors());
            return baseResponse;
        }
        baseResponse.setData(dbResponse.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> addRoleAppRes(ViewAcountRoleRefAppResource viewaccount, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        if (viewaccount == null || viewaccount.getAccountRoleId() == null
                || viewaccount.getAppResources().get(0).getId() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "accountRoleId or appResourceIds  is null"));
            return baseResponse;
        }

        AppResPermitCond accRoleAppRes = new AppResPermitCond();
        List<Long> accountRoleIds = Arrays.asList(viewaccount.getAccountRoleId());
        accRoleAppRes.setAccountRoleIds(accountRoleIds);
        List<Long> appResourceIds = new ArrayList<Long>();
        for (ViewAppResource viewAppResource : viewaccount.getAppResources())
        {
            appResourceIds.add(viewAppResource.getId());
        }
        accRoleAppRes.setAppResourceId(appResourceIds);

        BaseResponse<Boolean> dbResponse = bizRoleAppResMgrService.addRoleAppRes(accRoleAppRes, operatorInfo);

        if (dbResponse.hasError() || !dbResponse.hasData())
        {
            baseResponse.addErrors(dbResponse.getErrors());
            return baseResponse;
        }

        baseResponse.setData(dbResponse.getData());
        return baseResponse;
    }

    public void setBizRoleAppResMgrService(BizRoleAppResMgrService bizRoleAppResMgrService)
    {
        this.bizRoleAppResMgrService = bizRoleAppResMgrService;
    }

}
