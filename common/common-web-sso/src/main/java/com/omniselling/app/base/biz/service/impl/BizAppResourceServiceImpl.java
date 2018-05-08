package com.omniselling.app.base.biz.service.impl;

import java.util.List;

import com.alibaba.fastjson.TypeReference;
import com.omniselling.app.base.biz.service.BizAppResourceService;
import com.omniselling.app.base.common.AppResourceType;
import com.omniselling.app.base.common.ClientServiceConfig;
import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ClientRequest;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.service.RemoteClientService;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.ms.core.model.AppResource;
import com.omniselling.ms.core.model.AppResourceCond;

public class BizAppResourceServiceImpl implements BizAppResourceService
{

    private RemoteClientService remoteClientService;

    @Override
    public BaseResponse<BasePageDetail<AppResource>> listAppResources(AppResourceCond cond,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<AppResource>> baseResponse = new BaseResponse<BasePageDetail<AppResource>>();
        // 参数判断
        if (cond == null)
        {
            return baseResponse;
        }
        // 传入参数 BizAppResourceCond
        ClientRequest<AppResourceCond> requestParam = new ClientRequest<AppResourceCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(cond);

        // 请求返回
        BaseResponse<BasePageDetail<AppResource>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.APPRESOURCE_LISTAPPRESOURCES,
                requestParam, new TypeReference<BaseResponse<BasePageDetail<AppResource>>>()
                {
                });

        // 判断错误，异常
        if (response.hasError())
        {
            baseResponse.addErrors(response.getErrors());
            return baseResponse;
        }
        if (!response.hasData())
        {
            return baseResponse;
        }
        // 正常
        baseResponse.setData(response.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<List<AppResource>> listAllAppResources(AppResourceCond cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<AppResource>> baseResponse = new BaseResponse<List<AppResource>>();
        // 传入参数 BizAppResourceCond
        ClientRequest<AppResourceCond> requestParam = new ClientRequest<AppResourceCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(cond);

        // 请求返回
        BaseResponse<List<AppResource>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.APPRESOURCE_LISTALLAPPRESOURCES,
                requestParam, new TypeReference<BaseResponse<List<AppResource>>>()
                {
                });

        // 判断错误，异常
        if (response.hasError())
        {
            baseResponse.addErrors(response.getErrors());
            return baseResponse;
        }
        if (!response.hasData())
        {
            return baseResponse;
        }
        // 正常
        baseResponse.setData(response.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<Long> createAppResource(AppResource appResource, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Long> baseResponse = new BaseResponse<Long>();

        // 参数判断
        if (appResource == null || operatorInfo == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage()), "Parameter is null"));
            return baseResponse;
        }

        // 传入参数 BizAppResourceCond
        ClientRequest<AppResource> requestParam = new ClientRequest<AppResource>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(appResource);

        // 请求返回
        BaseResponse<Long> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESOURCE_CREATEAPPRESOURCE, requestParam, new TypeReference<BaseResponse<Long>>()
                {
                });

        // 判断错误，异常
        if (response.hasError())
        {
            baseResponse.addErrors(response.getErrors());
            return baseResponse;
        }

        // 正常
        baseResponse.setData(response.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> updateAppResource(AppResource appResource, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();

        // 参数判断
        if (appResource == null || operatorInfo == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage()), "Parameter is null"));
            return baseResponse;
        }

        // 传入参数 BizAppResourceCond
        ClientRequest<AppResource> requestParam = new ClientRequest<AppResource>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(appResource);

        // 请求返回
        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESOURCE_UPDATEAPPRESOURCE, requestParam,
                new TypeReference<BaseResponse<Boolean>>()
                {
                });

        // 判断错误，异常
        if (response.hasError())
        {
            baseResponse.addErrors(response.getErrors());
            return baseResponse;
        }

        // 正常
        baseResponse.setData(response.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<AppResource> getAppResourceById(Long appResourceId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<AppResource> baseResponse = new BaseResponse<AppResource>();
        // 参数判断
        if (appResourceId == null || operatorInfo == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage()), "Parameter is null"));
            return baseResponse;
        }

        // 传入参数 BizAppResourceCond
        ClientRequest<Long> requestParam = new ClientRequest<Long>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(appResourceId);

        // 请求返回
        BaseResponse<AppResource> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.APPRESOURCE_GETAPPRESOURCEBYID,
                requestParam, new TypeReference<BaseResponse<AppResource>>()
                {
                });

        // 判断错误，异常
        if (response.hasError())
        {
            baseResponse.addErrors(response.getErrors());
            return baseResponse;
        }

        // 正常
        baseResponse.setData(response.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<List<AppResource>> listAppResourceByIds(List<Long> appResourceIds,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<AppResource>> baseResponse = new BaseResponse<>();

        // 参数判断
        if (appResourceIds == null || appResourceIds.isEmpty() || operatorInfo == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage()), "Parameter is null"));
            return baseResponse;
        }

        // 传入参数 BizAppResourceCond
        ClientRequest<List<Long>> requestParam = new ClientRequest<List<Long>>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(appResourceIds);

        // 请求返回
        BaseResponse<List<AppResource>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.APPRESOURCE_LISTAPPRESOURCEBYIDS,
                requestParam, new TypeReference<BaseResponse<List<AppResource>>>()
                {
                });

        // 判断错误，异常
        if (response.hasError())
        {
            baseResponse.addErrors(response.getErrors());
            return baseResponse;
        }

        // 正常
        baseResponse.setData(response.getData());
        return baseResponse;
    }

    @SuppressWarnings("null")
    @Override
    public BaseResponse<List<AppResource>> listAppResourceByNames(String applicationName, List<String> appResourceNames,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<AppResource>> baseResponse = new BaseResponse<>();

        // 参数判断
        if ((applicationName.isEmpty() || applicationName.isEmpty() || applicationName == null) && operatorInfo == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage()), "Parameter is null"));
            return baseResponse;
        }

        // 传入参数 BizAppResourceCond
        ClientRequest<List<String>> requestParam = new ClientRequest<List<String>>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(appResourceNames);

        // 请求返回
        BaseResponse<List<AppResource>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.APPRESOURCE_LISTAPPRESOURCEBYNAMES,
                requestParam, new TypeReference<BaseResponse<List<AppResource>>>()
                {
                });

        // 判断错误，异常
        if (response.hasError())
        {
            baseResponse.addErrors(response.getErrors());
            return baseResponse;
        }

        // 正常
        baseResponse.setData(response.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<List<AppResource>> listAllMenuResources(String applicationName, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<AppResource>> baseResponse = new BaseResponse<>();

        // 参数判断
        if (applicationName.isEmpty() || operatorInfo == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage()), "Parameter is null"));
            return baseResponse;
        }

        // 传入参数 BizAppResourceCond
        ClientRequest<AppResourceCond> requestParam = new ClientRequest<AppResourceCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        AppResourceCond appResourceCond = new AppResourceCond();
        appResourceCond.setApplicationName(applicationName);
        appResourceCond.setResourceType(AppResourceType.APP_MENU.name());
        requestParam.setData(appResourceCond);

        // 请求返回
        BaseResponse<BasePageDetail<AppResource>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.APPRESOURCE_LISTAPPRESOURCES,
                requestParam, new TypeReference<BaseResponse<BasePageDetail<AppResource>>>()
                {
                });

        // 判断错误，异常
        if (response.hasError())
        {
            baseResponse.addErrors(response.getErrors());
            return baseResponse;
        }
        if (!response.hasData())
        {
            return baseResponse;
        }

        // 正常
        baseResponse.setData(response.getData().getDatas());
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> removeAppResources(List<Long> appResourceIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();

        // 参数判断
        if (appResourceIds == null || appResourceIds.size() == 0 || operatorInfo == null)
        {
            baseResponse.setData(null);
            return baseResponse;
        }

        // 传入参数 BizAppResourceCond
        ClientRequest<List<Long>> requestParam = new ClientRequest<List<Long>>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(appResourceIds);

        // 请求返回
        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESOURCE_REMOVEAPPRESOURCES, requestParam,
                new TypeReference<BaseResponse<Boolean>>()
                {
                });

        // 判断错误，异常
        if (response.hasError())
        {
            baseResponse.addErrors(response.getErrors());
            return baseResponse;
        }

        // 正常
        baseResponse.setData(response.getData());
        return baseResponse;
    }

    public void setRemoteClientService(RemoteClientService remoteClientService)
    {
        this.remoteClientService = remoteClientService;
    }

    public RemoteClientService getRemoteClientService()
    {
        return remoteClientService;
    }

    @Override
    public BaseResponse<List<BizEnumSpec>> listResourceTypes(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<BizEnumSpec>> baseResponse = new BaseResponse<>();
        if (operatorInfo.getLanguage().isEmpty())
        {
            return baseResponse;
        }

        ClientRequest<String> requestParam = new ClientRequest<String>();
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        TypeReference<BaseResponse<List<BizEnumSpec>>> type = new TypeReference<BaseResponse<List<BizEnumSpec>>>()
        {
        };

        BaseResponse<List<BizEnumSpec>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.APPRESOURCE_LISTAPPRESOURCETYPES,
                requestParam, type);

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());
        return baseResponse;
    }

    @Override
    public BaseResponse<AppResource> listAppResourceById(Long appResoueceId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<AppResource> baseResponse = new BaseResponse<>();
        if (appResoueceId == null)
        {
            return baseResponse;
        }

        ClientRequest<Long> requestParam = new ClientRequest<Long>();
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setData(appResoueceId);

        TypeReference<BaseResponse<AppResource>> type = new TypeReference<BaseResponse<AppResource>>()
        {
        };

        BaseResponse<AppResource> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.APPRESOURCE_GETAPPRESOURCEBYID,
                requestParam, type);

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());
        return baseResponse;
    }

}
