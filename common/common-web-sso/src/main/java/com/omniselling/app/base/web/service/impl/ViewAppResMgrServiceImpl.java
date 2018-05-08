package com.omniselling.app.base.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.omniselling.app.base.biz.service.BizAppResourceService;
import com.omniselling.app.base.common.AppResourceType;
import com.omniselling.app.base.web.model.ViewAppResource;
import com.omniselling.app.base.web.model.ViewAppResourceCondition;
import com.omniselling.app.base.web.model.ViewEnumSpec;
import com.omniselling.app.base.web.service.ViewAppResMgrService;
import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.model.ErrorInfo.SEVERITY;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.ms.core.model.AppResource;
import com.omniselling.ms.core.model.AppResourceCond;

/**
 * 应用资源服务
 * 
 * @author Administrator
 *
 */
public class ViewAppResMgrServiceImpl implements ViewAppResMgrService
{

    private BizAppResourceService bizAppResourceService;

    @Override
    public BaseResponse<BasePageDetail<ViewAppResource>> listAppResources(ViewAppResourceCondition viewAppResCond,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<ViewAppResource>> baseResponse = new BaseResponse<BasePageDetail<ViewAppResource>>();
        BasePageDetail<ViewAppResource> basePageDetail = new BasePageDetail<ViewAppResource>();
        // 参数判断
        if (viewAppResCond == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "viewAppResCond is null"));
            return baseResponse;
        }
        // mode 转换
        AppResourceCond cond = convertAppResourceCond(viewAppResCond);
        if (viewAppResCond.getOffset() != null && viewAppResCond.getRowsPerPage() != null)
        {
            cond.setOffset(viewAppResCond.getOffset());
            cond.setRowsPerPage(viewAppResCond.getRowsPerPage());
        }

        try
        {
            // 查询应用资源信息
            BaseResponse<BasePageDetail<AppResource>> dbResponse = bizAppResourceService.listAppResources(cond,
                    operatorInfo);
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

            List<AppResource> dbAppRes = dbResponse.getData().getDatas();
            List<ViewAppResource> list = new ArrayList<ViewAppResource>();
            for (AppResource appResource : dbAppRes)
            {
                ViewAppResource viewAppResource = convertViewAppResource(appResource);
                list.add(viewAppResource);
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

    @Override
    public BaseResponse<List<ViewAppResource>> listAllMenuResources(String applicationName,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewAppResource>> baseResponse = new BaseResponse<List<ViewAppResource>>();
        List<ViewAppResource> viewList = new ArrayList<ViewAppResource>();
        if (applicationName == null || applicationName.isEmpty() || operatorInfo == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        try
        {
            BaseResponse<List<AppResource>> dbResponse = bizAppResourceService.listAllMenuResources(applicationName,
                    operatorInfo);
            if (dbResponse.hasError())
            {
                baseResponse.addErrors(dbResponse.getErrors());
                return baseResponse;
            }
            if (!dbResponse.hasData())
            {
                return baseResponse;
            }
            for (AppResource appResource : dbResponse.getData())
            {
                if (AppResourceType.APP_MENU.name().equals(appResource.getResourceType())
                        || AppResourceType.APP_URL.name().equals(appResource.getResourceType()))
                {
                    ViewAppResource viewAppResource = new ViewAppResource();
                    BeanUtils.copyProperties(appResource, viewAppResource);
                    if (appResource.getResourceLabel() == null || appResource.getResourceLabel().isEmpty())
                    {
                        viewAppResource.setResourceLabel(appResource.getResourceName());
                    }
                    viewList.add(viewAppResource);
                }

            }
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        baseResponse.setData(viewList);
        return baseResponse;
    }

    @Override
    public BaseResponse<List<ViewAppResource>> listAllAppResources(ViewAppResourceCondition viewAppResourceCondition,
            BaseOperatorInfo operatorInfo)
    {

        BaseResponse<List<ViewAppResource>> baseResponse = new BaseResponse<List<ViewAppResource>>();
        //AppResourceCond cond = convertAppResourceCond(viewAppResourceCondition);
        AppResourceCond cond = new AppResourceCond();
        if (viewAppResourceCondition != null)
        {
            cond = convertAppResourceCond(viewAppResourceCondition);
        }

        try
        {
            BaseResponse<List<AppResource>> dbResponse = bizAppResourceService.listAllAppResources(cond, operatorInfo);
            if (dbResponse.hasError())
            {
                dbResponse.addErrors(dbResponse.getErrors());
                return baseResponse;
            }
            if (!dbResponse.hasData())
            {
                return baseResponse;
            }

            List<ViewAppResource> list = new ArrayList<ViewAppResource>();
            for (AppResource appResource : dbResponse.getData())
            {
                ViewAppResource viewAppResource = convertViewAppResource(appResource);
                list.add(viewAppResource);
            }
            baseResponse.setData(list);
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> saveAppResource(ViewAppResource viewAppResource, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        BaseResponse<Boolean> validateBaseResponse = viewAppResource.validate(operatorInfo.getLanguage());
        if (viewAppResource == null || validateBaseResponse.hasError())
        {
            String errorMsg = String.format("Parameter error");
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()), errorMsg));
            return baseResponse;
        }

        AppResource appResource = convertAppResource(viewAppResource);
        // id==null;则create
        if (viewAppResource.getId() == null)
        {
            try
            {
                BaseResponse<Long> createResponse = bizAppResourceService.createAppResource(appResource, operatorInfo);
                if (createResponse.hasError())
                {
                    baseResponse.addErrors(createResponse.getErrors());
                    return baseResponse;
                }
                baseResponse.setData(true);
                return baseResponse;
            }
            catch (Exception e)
            {
                baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, ErrorInfo.SEVERITY.ERROR,
                        ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
                return baseResponse;
            }
        }
        // id！=null;则修改
        try
        {
            BaseResponse<Boolean> updateResponse = bizAppResourceService.updateAppResource(appResource, operatorInfo);
            if (updateResponse.hasError())
            {
                baseResponse.addErrors(updateResponse.getErrors());
                return baseResponse;
            }
            baseResponse.setData(updateResponse.getData());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> deleteAppResource(Long appResoueceId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        if (appResoueceId == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            baseResponse.setData(false);
            return baseResponse;
        }
        List<Long> appResourceIds = Arrays.asList(appResoueceId);
        try
        {
            BaseResponse<Boolean> dbResponse = bizAppResourceService.removeAppResources(appResourceIds, operatorInfo);
            if (dbResponse.hasError())
            {
                baseResponse.addErrors(dbResponse.getErrors());
                return baseResponse;
            }
            baseResponse.setData(dbResponse.getData());
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        return baseResponse;
    }

    private ViewAppResource convertViewAppResource(AppResource appResource)
    {
        ViewAppResource viewAppResource = new ViewAppResource();
        BeanUtils.copyProperties(appResource, viewAppResource, new String[] { "resourceType" });
        viewAppResource.setResourceTypeCode(appResource.getResourceType());
        viewAppResource.setResourceType(appResource.getResourceType());
        viewAppResource.setResourceTypeLabel(appResource.getResourceTypeLabel());
        if (appResource.getResourceLabel() == null || appResource.getResourceLabel().isEmpty())
        {
            viewAppResource.setResourceLabel(viewAppResource.getResourceName());
        }
        if (appResource.getResourceTypeLabel() == null || appResource.getResourceTypeLabel().isEmpty())
        {
            viewAppResource.setResourceTypeLabel(appResource.getResourceType());
        }
        return viewAppResource;
    }

    private AppResourceCond convertAppResourceCond(ViewAppResourceCondition viewAppResCond)
    {
        if (viewAppResCond == null)
        {
            return null;
        }
        AppResourceCond appResourceCond = new AppResourceCond();
        appResourceCond.setResourceNameLike(viewAppResCond.getResourceNameLike());
        appResourceCond.setApplicationName(viewAppResCond.getApplicationName());
        appResourceCond.setApplicationNameLike(viewAppResCond.getApplicationNameLike());
        appResourceCond.setResourceUrlLike(viewAppResCond.getResourceUrlLike());
        appResourceCond.setResourceUrl(viewAppResCond.getResourceUrl());
        appResourceCond.setResourceName(viewAppResCond.getResourceName());
        appResourceCond.setResourceType(viewAppResCond.getResourceType());
        return appResourceCond;
    }

    private AppResource convertAppResource(ViewAppResource viewAppResource)
    {
        AppResource appResource = new AppResource();
        BeanUtils.copyProperties(viewAppResource, appResource, new String[] { "resourceType" });
        appResource.setResourceType(viewAppResource.getResourceType());
        return appResource;
    }

    public void setBizAppResourceService(BizAppResourceService bizAppResourceService)
    {
        this.bizAppResourceService = bizAppResourceService;
    }

    @Override
    public BaseResponse<List<ViewEnumSpec>> listAppResourceTypes(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<ViewEnumSpec>> baseResponse = new BaseResponse<>();
        List<ViewEnumSpec> list = new ArrayList<>();
        try
        {
            BaseResponse<List<BizEnumSpec>> bizBaseResponse = bizAppResourceService.listResourceTypes(operatorInfo);
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
    public BaseResponse<ViewAppResource> listAppResourceById(Long appResoueceId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<ViewAppResource> baseResponse = new BaseResponse<ViewAppResource>();
        // 判断
        if (appResoueceId == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()), "no parentid"));
            return baseResponse;
        }
        try
        {
            BaseResponse<AppResource> dbResponse = bizAppResourceService.listAppResourceById(appResoueceId,
                    operatorInfo);
            if (dbResponse.hasError())
            {
                baseResponse.addErrors(dbResponse.getErrors());
                return baseResponse;
            }
            if (!dbResponse.hasData())
            {
                return baseResponse;
            }
            ViewAppResource viewAppResource = convertViewAppResource(dbResponse.getData());
            baseResponse.setData(viewAppResource);
        }
        catch (Exception e)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.SYSTEM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()), e.toString()));
            return baseResponse;
        }
        return baseResponse;
    }

}
