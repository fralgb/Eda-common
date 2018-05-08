package com.omniselling.app.base.biz.service.impl;

import java.util.List;

import com.alibaba.fastjson.TypeReference;
import com.omniselling.app.base.biz.service.BizAccAppResMgrService;
import com.omniselling.app.base.common.ClientServiceConfig;
import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ClientRequest;
import com.omniselling.common.service.RemoteClientService;
import com.omniselling.ms.core.model.AccountAppResCond;
import com.omniselling.ms.core.model.AccountAppResPermission;
import com.omniselling.ms.core.model.AppResPermitCond;

public class BizAccAppResMgrServiceImpl implements BizAccAppResMgrService
{
    private RemoteClientService remoteClientService;

    public void setRemoteClientService(RemoteClientService remoteClientService)
    {
        this.remoteClientService = remoteClientService;
    }

    @Override
    public BaseResponse<BasePageDetail<AccountAppResPermission>> listAccountAppRes(AccountAppResCond accountAppRes,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<AccountAppResPermission>> baseResponse = new BaseResponse<>();
        // 参数判断
        if (accountAppRes == null)
        {
            return baseResponse;
        }
        // 传入参数 RoleAppResPermit
        ClientRequest<AccountAppResCond> requestParam = new ClientRequest<AccountAppResCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(accountAppRes);
        // 请求返回
        TypeReference<BaseResponse<BasePageDetail<AccountAppResPermission>>> type = new TypeReference<BaseResponse<BasePageDetail<AccountAppResPermission>>>()
        {
        };
        BaseResponse<BasePageDetail<AccountAppResPermission>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.APPRESPERMIT_LISTACCAPPRESPERMIT,
                requestParam, type);
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
    public BaseResponse<Boolean> deleteRoleAppRes(List<Long> accAppresIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        // 参数判断
        if (accAppresIds == null)
        {
            return baseResponse;
        }
        // 传入参数 RoleAppResPermit
        ClientRequest<List<Long>> requestParam = new ClientRequest<List<Long>>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(accAppresIds);
        // 请求返回
        TypeReference<BaseResponse<Boolean>> type = new TypeReference<BaseResponse<Boolean>>()
        {
        };

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_DELETEACCAPPPERMIT, requestParam, type);
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
        baseResponse.setData(response.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> addAccountAppRes(AppResPermitCond accRoleAppRes, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        // 参数判断
        if (accRoleAppRes == null)
        {
            return baseResponse;
        }
        // 传入参数 RoleAppResPermit
        ClientRequest<AppResPermitCond> requestParam = new ClientRequest<AppResPermitCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(accRoleAppRes);
        // 请求返回
        TypeReference<BaseResponse<Boolean>> type = new TypeReference<BaseResponse<Boolean>>()
        {
        };
        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_CREATEACCAPPRESPERMISSIONS, requestParam, type);

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

        baseResponse.setData(response.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<List<BizEnumSpec>> listPermitTypes(BaseOperatorInfo operatorInfo)
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
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.APPRESPERMIT_LISTPERMITTYPES,
                requestParam, type);

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());
        return baseResponse;
    }

}
