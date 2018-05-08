package com.omniselling.app.base.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.TypeReference;
import com.omniselling.app.base.biz.service.BizPubResPermitService;
import com.omniselling.app.base.common.ClientServiceConfig;
import com.omniselling.common.enumeration.BusinessResourceType;
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
import com.omniselling.ms.core.model.AccountPubResCond;
import com.omniselling.ms.core.model.AccountPubResPermission;
import com.omniselling.ms.core.model.PubResPermitCond;

/**
 * 实现接口：账户公共资源权限
 * 
 * @author Administrator
 *
 */
public class BizPubResPermitServiceImpl extends BizBaseImpl implements BizPubResPermitService
{
    private RemoteClientService remoteClientService;

    public void setRemoteClientService(RemoteClientService remoteClientService)
    {
        this.remoteClientService = remoteClientService;
    }

    @Override
    public BaseResponse<Boolean> createResPermission(PubResPermitCond cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        if (cond == null || cond.getAccountIds() == null || cond.getAccountIds().size() == 0
                || cond.getPubResourceIds() == null || cond.getPubResourceIds().size() == 0
                || !validBaseOperatorInfo(operatorInfo))
        {
            baseResponse.addError(
                    ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.BIZ,
                            LanguageCode.getByValue(operatorInfo.getLanguage()), "createResPermission biz param null"));
            return baseResponse;
        }

        ClientRequest<PubResPermitCond> requestParam = new ClientRequest<PubResPermitCond>();

        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        TypeReference<BaseResponse<Boolean>> type = new TypeReference<BaseResponse<Boolean>>()
        {
        };

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.PUBRESPERMIT_CREATERESPERMISSION, requestParam, type);

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
    public BaseResponse<List<AccountPubResPermission>> listPermissionByAcc(PubResPermitCond cond,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<AccountPubResPermission>> baseResponse = new BaseResponse<>();

        if (cond == null || cond.getAccountIds() == null || cond.getAccountIds().size() == 0
                || !validBaseOperatorInfo(operatorInfo))
        {
            baseResponse.addError(
                    ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.BIZ,
                            LanguageCode.getByValue(operatorInfo.getLanguage()), "listPermissionByAcc biz param null"));
            return baseResponse;
        }

        ClientRequest<PubResPermitCond> requestParam = new ClientRequest<PubResPermitCond>();

        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        TypeReference<BaseResponse<List<AccountPubResPermission>>> type = new TypeReference<BaseResponse<List<AccountPubResPermission>>>()
        {
        };

        BaseResponse<List<AccountPubResPermission>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.PUBRESPERMIT_LISTPERMISSIONBYACC,
                requestParam, type);

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());

        return baseResponse;
    }

    @Override
    public BaseResponse<List<AccountPubResPermission>> listPermissionByRes(PubResPermitCond cond,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<AccountPubResPermission>> baseResponse = new BaseResponse<>();

        if (cond == null || cond.getPubResourceIds() == null || cond.getPubResourceIds().size() == 0
                || !validBaseOperatorInfo(operatorInfo))
        {
            baseResponse.addError(
                    ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.BIZ,
                            LanguageCode.getByValue(operatorInfo.getLanguage()), "listPermissionByRes biz param null"));
            return baseResponse;
        }

        ClientRequest<PubResPermitCond> requestParam = new ClientRequest<PubResPermitCond>();

        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        TypeReference<BaseResponse<List<AccountPubResPermission>>> type = new TypeReference<BaseResponse<List<AccountPubResPermission>>>()
        {
        };

        BaseResponse<List<AccountPubResPermission>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.PUBRESPERMIT_LISTPERMISSIONBYRES,
                requestParam, type);

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());

        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> removeAllPermissionByAcc(PubResPermitCond cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        if (cond == null || cond.getAccountIds() == null || cond.getAccountIds().size() == 0
                || !validBaseOperatorInfo(operatorInfo))
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "removeAllPermissionByAcc biz param null"));
            return baseResponse;
        }

        ClientRequest<PubResPermitCond> requestParam = new ClientRequest<PubResPermitCond>();

        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        TypeReference<BaseResponse<Boolean>> type = new TypeReference<BaseResponse<Boolean>>()
        {
        };

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.PUBRESPERMIT_REMOVEALLPERMISSIONBYACC, requestParam, type);

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
    public BaseResponse<Boolean> removeAllPermissionByRes(PubResPermitCond cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        if (cond == null || cond.getPubResourceIds() == null || cond.getPubResourceIds().size() == 0
                || !validBaseOperatorInfo(operatorInfo))
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "removeAllPermissionByRes biz param null"));
            return baseResponse;
        }

        ClientRequest<PubResPermitCond> requestParam = new ClientRequest<PubResPermitCond>();

        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        TypeReference<BaseResponse<Boolean>> type = new TypeReference<BaseResponse<Boolean>>()
        {
        };

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.PUBRESPERMIT_REMOVEALLPERMISSIONBYRES, requestParam, type);

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
    public BaseResponse<Boolean> updatePermissions(Long accountId, BusinessResourceType resourceType,
            List<AccountPubResPermission> pubResPermissions, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        if (accountId == null || resourceType == null || operatorInfo == null || operatorInfo.getOperatorId() == null)
        {
            baseResponse.addError(
                    ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.VIEW,
                            LanguageCode.getByValue(operatorInfo.getLanguage()), " updatePermissions biz param null"));
            return baseResponse;
        }

        TypeReference<BaseResponse<Boolean>> type = new TypeReference<BaseResponse<Boolean>>()
        {
        };

        List<Long> accountIds = new ArrayList<>();
        accountIds.add(accountId);

        //按资源类型 分批次进行修改
        List<String> resourceTypes = new ArrayList<>();

        for (AccountPubResPermission accPubResPermission : pubResPermissions)
        {
            String pubresType = accPubResPermission.getResourceType();
            if (!resourceTypes.contains(pubresType))
            {
                resourceTypes.add(pubresType);
            }

        }

        if (resourceTypes != null && resourceTypes.size() > 0)
        {
            //循环，按批次来删除
            for (String pubResTypeString : resourceTypes)
            {

                List<Long> pubResourceIds = new ArrayList<>();
                for (AccountPubResPermission accPubResPermission : pubResPermissions)
                {
                    if (accPubResPermission == null || accPubResPermission.getPubResourceId() == null)
                    {
                        continue;
                    }

                    if (accPubResPermission.getResourceType().equals(pubResTypeString))
                    {
                        pubResourceIds.add(accPubResPermission.getPubResourceId());
                    }
                }

                ClientRequest<PubResPermitCond> requestParam = new ClientRequest<PubResPermitCond>();
                PubResPermitCond cond = new PubResPermitCond();

                cond.setAccountIds(accountIds);
                cond.setResourceType(pubResTypeString);
                cond.setPubResourceIds(pubResourceIds);
                requestParam.setData(cond);
                requestParam.setLanguage(operatorInfo.getLanguage());
                requestParam.setOperatorId(operatorInfo.getOperatorId());

                BaseResponse<Boolean> response = remoteClientService.postRequest(
                        ClientServiceConfig.SERVICENAME_CORESERVICE,
                        ClientServiceConfig.PUBRESPERMIT_UPDATEPERMISSIONSBYRESTYPE, requestParam, type);

                if (response.hasError() || !response.hasData() || !response.getData())
                {
                    baseResponse.addErrors(response.getErrors());
                    baseResponse.setData(false);
                    return baseResponse;
                }

            }
        }
        else
        {
            // 删除 账户下 该类型的资源
            ClientRequest<PubResPermitCond> requestParam = new ClientRequest<PubResPermitCond>();
            PubResPermitCond cond = new PubResPermitCond();

            cond.setAccountIds(accountIds);
            cond.setResourceType(resourceType.name());
            requestParam.setData(cond);
            requestParam.setLanguage(operatorInfo.getLanguage());
            requestParam.setOperatorId(operatorInfo.getOperatorId());

            BaseResponse<Boolean> response = remoteClientService.postRequest(
                    ClientServiceConfig.SERVICENAME_CORESERVICE,
                    ClientServiceConfig.PUBRESPERMIT_UPDATEPERMISSIONSBYRESTYPE, requestParam, type);

            if (response.hasError() || !response.hasData() || !response.getData())
            {
                baseResponse.addErrors(response.getErrors());
                baseResponse.setData(false);
                return baseResponse;
            }
        }

        baseResponse.setData(true);
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> deleteAccPubResPermitByIds(List<Long> accPubResPermitIds,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        if (accPubResPermitIds == null || accPubResPermitIds.size() == 0 || !validBaseOperatorInfo(operatorInfo))
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "deleteAccPubResPermitByIds biz param null"));
            return baseResponse;
        }

        ClientRequest<Long[]> requestParam = new ClientRequest<Long[]>();

        Long[] arr = (Long[]) accPubResPermitIds.toArray();

        requestParam.setData(arr);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        TypeReference<BaseResponse<Boolean>> type = new TypeReference<BaseResponse<Boolean>>()
        {
        };

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.PUBRESPERMIT_REMOVEALLPERMISSIONBYACCPUBRESIDS, requestParam, type);

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
    public BaseResponse<BasePageDetail<AccountPubResPermission>> listAccountPubRes(AccountPubResCond cond,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<AccountPubResPermission>> baseResponse = new BaseResponse<>();
        if (cond == null || !validBaseOperatorInfo(operatorInfo))
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        ClientRequest<AccountPubResCond> requestParam = new ClientRequest<AccountPubResCond>();

        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<BasePageDetail<AccountPubResPermission>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.PUBRESPERMIT_LISTACCOUNTPUBRES, requestParam, new TypeReference<BaseResponse<BasePageDetail<AccountPubResPermission>>>(){});

        baseResponse.addErrors(response.getErrors());

        baseResponse.setData(response.getData());
        return baseResponse;
    }
}
