package com.omniselling.app.base.biz.service.impl;

import java.util.List;

import com.alibaba.fastjson.TypeReference;
import com.omniselling.app.base.biz.service.BizI18nResourceService;
import com.omniselling.app.base.common.ClientServiceConfig;
import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ClientRequest;
import com.omniselling.common.service.RemoteClientService;
import com.omniselling.ms.core.model.I18nResLangAndKeys;
import com.omniselling.ms.core.model.I18nResource;
import com.omniselling.ms.core.model.I18nResourceCond;
import com.omniselling.ms.core.model.I18nResourceKey;
import com.omniselling.ms.core.model.I18nResourceKeyCond;

public class BizI18nResourceServiceImpl implements BizI18nResourceService
{
    private RemoteClientService remoteClientService;

    public void setRemoteClientService(RemoteClientService remoteClientService)
    {
        this.remoteClientService = remoteClientService;
    }

    @Override
    public BaseResponse<Boolean> createI18nRes(List<I18nResource> i18nResources, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();

        ClientRequest<List<I18nResource>> requestParam = new ClientRequest<List<I18nResource>>();
        requestParam.setData(i18nResources);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        BaseResponse<Boolean> coreRes = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.I18NRESOURCE_CREATEI18NRESOURCES, requestParam,
                new TypeReference<BaseResponse<Boolean>>()
                {
                });
        if (coreRes.hasError())
        {
            baseRes.addErrors(coreRes.getErrors());
            return baseRes;
        }
        baseRes.setData(coreRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<BasePageDetail<I18nResource>> listI18nRes(I18nResourceCond cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<I18nResource>> baseRes = new BaseResponse<BasePageDetail<I18nResource>>();

        ClientRequest<I18nResourceCond> requestParam = new ClientRequest<I18nResourceCond>();
        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        BaseResponse<BasePageDetail<I18nResource>> coreRes = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.I18NRESOURCE_LISTI18NRESOURCES,
                requestParam, new TypeReference<BaseResponse<BasePageDetail<I18nResource>>>()
                {
                });
        if (coreRes.hasError())
        {
            baseRes.addErrors(coreRes.getErrors());
            return baseRes;
        }
        BasePageDetail<I18nResource> viewI18nResList = coreRes.getData();
        baseRes.setData(viewI18nResList);
        return baseRes;
    }

    @Override
    public BaseResponse<I18nResource> getI18nResById(Long resourceId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<I18nResource> baseRes = new BaseResponse<I18nResource>();

        ClientRequest<Long> requestParam = new ClientRequest<Long>();
        requestParam.setData(resourceId);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        BaseResponse<I18nResource> coreRes = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.I18NRESOURCE_GETI18NRESOURCEBYID,
                requestParam, new TypeReference<BaseResponse<I18nResource>>()
                {
                });
        if (coreRes.hasError())
        {
            baseRes.addErrors(coreRes.getErrors());
            return baseRes;
        }
        baseRes.setData(coreRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<List<I18nResource>> listI18nResByIds(List<Long> resourceIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<I18nResource>> baseRes = new BaseResponse<List<I18nResource>>();

        ClientRequest<List<Long>> requestParam = new ClientRequest<List<Long>>();
        requestParam.setData(resourceIds);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        BaseResponse<List<I18nResource>> coreRes = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.I18NRESOURCE_LISTI18NRESBYIDS,
                requestParam, new TypeReference<BaseResponse<List<I18nResource>>>()
                {
                });
        if (coreRes.hasError())
        {
            baseRes.addErrors(coreRes.getErrors());
            return baseRes;
        }
        baseRes.setData(coreRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<List<I18nResource>> listI18nResByLangAndKeys(I18nResLangAndKeys resLangAndKeys,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<I18nResource>> baseRes = new BaseResponse<List<I18nResource>>();

        ClientRequest<I18nResLangAndKeys> requestParam = new ClientRequest<I18nResLangAndKeys>();
        requestParam.setData(resLangAndKeys);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        BaseResponse<List<I18nResource>> coreRes = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.I18NRESOURCE_LISTI18NRESBYLANGANDKEYS,
                requestParam, new TypeReference<BaseResponse<List<I18nResource>>>()
                {
                });
        if (coreRes.hasError())
        {
            baseRes.addErrors(coreRes.getErrors());
            return baseRes;
        }
        baseRes.setData(coreRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<Boolean> deleteI18nResByLangAndKeys(I18nResLangAndKeys resLangAndKeys,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();

        ClientRequest<I18nResLangAndKeys> requestParam = new ClientRequest<I18nResLangAndKeys>();
        requestParam.setData(resLangAndKeys);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        BaseResponse<Boolean> coreRes = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.I18NRESOURCE_DELETEI18NRESBYLANGANDKEYS, requestParam,
                new TypeReference<BaseResponse<Boolean>>()
                {
                });
        if (coreRes.hasError())
        {
            baseRes.addErrors(coreRes.getErrors());
            return baseRes;
        }
        baseRes.setData(coreRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<Boolean> updateI18nRes(List<I18nResource> i18nResources, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();

        ClientRequest<List<I18nResource>> requestParam = new ClientRequest<List<I18nResource>>();
        requestParam.setData(i18nResources);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        BaseResponse<Boolean> coreRes = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.I18NRESOURCE_UPDATEI18NRES, requestParam,
                new TypeReference<BaseResponse<Boolean>>()
                {
                });
        if (coreRes.hasError())
        {
            baseRes.addErrors(coreRes.getErrors());
            return baseRes;
        }
        baseRes.setData(coreRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<BasePageDetail<I18nResourceKey>> listI18nResourceKeys(I18nResourceKeyCond keyCond,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<I18nResourceKey>> baseRes = new BaseResponse<BasePageDetail<I18nResourceKey>>();

        ClientRequest<I18nResourceKeyCond> requestParam = new ClientRequest<I18nResourceKeyCond>();
        requestParam.setData(keyCond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        BaseResponse<BasePageDetail<I18nResourceKey>> coreRes = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.I18NRESOURCE_LISTI18NRESOURCEKEYS,
                requestParam, new TypeReference<BaseResponse<BasePageDetail<I18nResourceKey>>>()
                {
                });
        if (coreRes.hasError())
        {
            baseRes.addErrors(coreRes.getErrors());
            return baseRes;
        }
        BasePageDetail<I18nResourceKey> data = coreRes.getData();
        baseRes.setData(data);
        return baseRes;
    }

    @Override
    public BaseResponse<List<BizEnumSpec>> listLanguageTypes(BaseOperatorInfo baseOperatorInfo)
    {

        BaseResponse<List<BizEnumSpec>> baseResponse = new BaseResponse<>();
        if (baseOperatorInfo.getLanguage().isEmpty())
        {
            return baseResponse;
        }

        ClientRequest<String> requestParam = new ClientRequest<String>();
        requestParam.setLanguage(baseOperatorInfo.getLanguage());
        requestParam.setOperatorId(baseOperatorInfo.getOperatorId());

        TypeReference<BaseResponse<List<BizEnumSpec>>> type = new TypeReference<BaseResponse<List<BizEnumSpec>>>()
        {
        };

        BaseResponse<List<BizEnumSpec>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.I18NRESOURCE_LISTLANGUAGETYPES,
                requestParam, type);

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());
        return baseResponse;
    }

}
