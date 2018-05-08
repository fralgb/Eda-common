package com.omniselling.app.base.biz.service.impl;

import java.util.List;

import com.alibaba.fastjson.TypeReference;
import com.omniselling.app.base.biz.service.BizPubRessourceService;
import com.omniselling.app.base.common.ClientServiceConfig;
import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ClientRequest;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.model.ErrorInfo.SEVERITY;
import com.omniselling.common.service.RemoteClientService;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.common.util.StringUtils;
import com.omniselling.ms.core.model.PubResource;
import com.omniselling.ms.core.model.PubResourceCond;

/**
 * 实现接口：公共资源管理
 * 
 * @author Administrator
 *
 */
public class BizPubRessourceServiceImpl extends BizBaseImpl implements BizPubRessourceService
{
    private RemoteClientService remoteClientService;

    public void setRemoteClientService(RemoteClientService remoteClientService)
    {
        this.remoteClientService = remoteClientService;
    }

    @Override
    public BaseResponse<BasePageDetail<PubResource>> listPubResourcesByCond(PubResourceCond cond,
            BaseOperatorInfo operatorInfo)
    {

        BaseResponse<BasePageDetail<PubResource>> baseResponse = new BaseResponse<>();
        if (cond == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "listPubResourcesByCond biz param null"));
            return baseResponse;
        }

        ClientRequest<PubResourceCond> requestParam = new ClientRequest<PubResourceCond>();
        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        
        BaseResponse<BasePageDetail<PubResource>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.PUBRESOURCE_LISTPUBRESOURCES,
                requestParam, new TypeReference<BaseResponse<BasePageDetail<PubResource>>>(){});
        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());

        return baseResponse;

    }

    @Override
    public BaseResponse<PubResource> getPubResourceById(Long resourceId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<PubResource> baseResponse = new BaseResponse<>();
        if (resourceId == null || !validBaseOperatorInfo(operatorInfo))
        {
            baseResponse.addError(
                    ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW,
                            LanguageCode.getByValue(operatorInfo.getLanguage()), "getPubResourceById biz param null"));
            return baseResponse;
        }
        ClientRequest<Long> requestParam = new ClientRequest<Long>();

        requestParam.setData(resourceId);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        
        BaseResponse<PubResource> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.PUBRESOURCE_GETPUBRESOURCEBYID,
                requestParam, new TypeReference<BaseResponse<PubResource>>(){});
        baseResponse.addErrors(response.getErrors());
        baseResponse.setData(response.getData());

        return baseResponse;
    }

    @Override
    public BaseResponse<PubResource> getPubResourceByPubNum(String publishNum, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<PubResource> baseResponse = new BaseResponse<>();

        if (publishNum == null || "".equals(publishNum) || !validBaseOperatorInfo(operatorInfo))
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "getPubResourceByPubNum biz param null"));
            return baseResponse;
        }

        ClientRequest<String> requestParam = new ClientRequest<String>();

        requestParam.setData(publishNum);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<PubResource> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.PUBRESOURCE_GETPUBRESOURCEBYPUBNUM,
                requestParam, new TypeReference<BaseResponse<PubResource>>(){});

        baseResponse.addErrors(response.getErrors());
        baseResponse.setData(response.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> createPubResource(PubResource pubResource, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        if (!validatePubResource(pubResource) || !validBaseOperatorInfo(operatorInfo))
        {
            baseResponse.addError(
                    ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW,
                            LanguageCode.getByValue(operatorInfo.getLanguage()), "createPubResource biz param null"));
            return baseResponse;
        }

        ClientRequest<PubResource> requestParam = new ClientRequest<PubResource>();

        requestParam.setData(pubResource);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.PUBRESOURCE_CREATEPUBRESOURCE, requestParam, new TypeReference<BaseResponse<Boolean>>(){});

        if (response.hasError() || !response.hasData() || !response.getData())
        {
            baseResponse.addErrors(response.getErrors());
            baseResponse.setData(false);
            return baseResponse;
        }

        baseResponse.setData(true);
        return baseResponse;
    }

    /**
     * 验证对象中必须属性是不是有
     * 
     * @param pubResource
     * @return
     */
    private boolean validatePubResource(PubResource pubResource)
    {
        if (pubResource.getResourceName() == null || "".equals(pubResource.getResourceName()))
        {
            return false;
        }
        if (pubResource.getOwnerId() == null)
        {
            return false;
        }
        if (pubResource.getResourceObjectId() == null)
        {
            return false;
        }
        if (pubResource.getResourceType() == null)
        {
            return false;
        }

        return true;
    }

    @Override
    public BaseResponse<Boolean> updatePubResource(PubResource pubResource, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        // 只允许修改字段 ResourceName PublishStatus 
        if (pubResource == null || StringUtils.isEmpty(pubResource.getResourceName())
                || pubResource.getResourceStatus() == null || !validBaseOperatorInfo(operatorInfo))
        {
            baseResponse.addError(
                    ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW,
                            LanguageCode.getByValue(operatorInfo.getLanguage()), "updatePubResource biz param null"));
            return baseResponse;
        }

        ClientRequest<PubResource> requestParam = new ClientRequest<PubResource>();

        requestParam.setData(pubResource);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.PUBRESOURCE_UPDATEPUBRESOURCE, requestParam, new TypeReference<BaseResponse<Boolean>>(){});

        if (response.hasError() || !response.hasData() || !response.getData())
        {
            baseResponse.addErrors(response.getErrors());
            baseResponse.setData(false);
            return baseResponse;
        }

        baseResponse.setData(true);
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> activePubResourcesById(List<Long> resourceIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        if (resourceIds == null || resourceIds.size() == 0 || operatorInfo == null
                || operatorInfo.getOperatorId() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "activePubResourcesById biz param null"));
            return baseResponse;
        }
        ClientRequest<Long[]> requestParam = new ClientRequest<Long[]>();

        Long[] longs = (Long[]) resourceIds.toArray();

        requestParam.setData(longs);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.PUBRESOURCE_ACTIVEPUBRESOURCESBYID, requestParam, new TypeReference<BaseResponse<Boolean>>(){});

        if (response.hasError() || !response.hasData() || !response.getData())
        {
            baseResponse.addErrors(response.getErrors());
            baseResponse.setData(false);
            return baseResponse;
        }

        baseResponse.setData(true);
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> disablePubResourcesById(List<Long> resourceIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        if (resourceIds == null || resourceIds.size() == 0 || operatorInfo == null
                || operatorInfo.getOperatorId() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        ClientRequest<Long[]> requestParam = new ClientRequest<Long[]>();

        Long[] longs = (Long[]) resourceIds.toArray();

        requestParam.setData(longs);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.PUBRESOURCE_DISABLEPUBRESOURCESBYID, requestParam,
                new TypeReference<BaseResponse<Boolean>>(){});

        if (response.hasError() || !response.hasData() || !response.getData())
        {
            baseResponse.addErrors(response.getErrors());
            baseResponse.setData(false);
            return baseResponse;
        }

        baseResponse.setData(true);
        return baseResponse;
    }

    @Override
    public BaseResponse<BasePageDetail<PubResource>> listPubResources(PubResourceCond cond,
            BaseOperatorInfo operatorInfo)
    {

        BaseResponse<BasePageDetail<PubResource>> baseResponse = new BaseResponse<>();
        if (cond == null || !validBaseOperatorInfo(operatorInfo))
        {
            baseResponse.addError(
                    ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW,
                            LanguageCode.getByValue(operatorInfo.getLanguage()), "listResources biz param error"));
            return baseResponse;
        }

        ClientRequest<PubResourceCond> requestParam = new ClientRequest<PubResourceCond>();
        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<BasePageDetail<PubResource>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.PUBRESOURCE_LISTPUBRESOURCES,
                requestParam, new TypeReference<BaseResponse<BasePageDetail<PubResource>>>(){});

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());

        return baseResponse;
    }

    @Override
    public BaseResponse<List<BizEnumSpec>> listResourceTypes(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<BizEnumSpec>> baseResponse = new BaseResponse<>();
        if (operatorInfo == null || operatorInfo.getLanguage() == null || "".equals(operatorInfo.getLanguage()))
        {
            baseResponse.addError(
                    ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.BIZ,
                            LanguageCode.getByValue(operatorInfo.getLanguage()), "listResourceTypes biz param null"));
            return baseResponse;
        }

        ClientRequest<String> requestParam = new ClientRequest<String>();
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<List<BizEnumSpec>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.PUBRESOURCE_LISTPUBRESOURCETYPES,
                requestParam, new TypeReference<BaseResponse<List<BizEnumSpec>>>(){});

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());

        return baseResponse;
    }

    @Override
    public BaseResponse<List<BizEnumSpec>> listPubResourceStatus(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<BizEnumSpec>> baseResponse = new BaseResponse<>();
        if (operatorInfo == null || operatorInfo.getLanguage() == null || "".equals(operatorInfo.getLanguage()))
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "listPubResourceStatus biz param null"));
            return baseResponse;
        }

        ClientRequest<String> requestParam = new ClientRequest<String>();
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<List<BizEnumSpec>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.PUBRESOURCE_LISTPUBRESOURCESTATUS,
                requestParam, new TypeReference<BaseResponse<List<BizEnumSpec>>>(){});

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());

        return baseResponse;
    }

}
