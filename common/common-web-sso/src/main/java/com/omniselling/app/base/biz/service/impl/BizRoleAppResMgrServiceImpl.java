package com.omniselling.app.base.biz.service.impl;

import java.util.List;

import com.alibaba.fastjson.TypeReference;
import com.omniselling.app.base.biz.service.BizRoleAppResMgrService;
import com.omniselling.app.base.common.ClientServiceConfig;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ClientRequest;
import com.omniselling.common.service.RemoteClientService;
import com.omniselling.ms.core.model.AppResPermitCond;
import com.omniselling.ms.core.model.RoleAppResPermission;
import com.omniselling.ms.core.model.RoleAppResCond;

public class BizRoleAppResMgrServiceImpl implements BizRoleAppResMgrService
{

    private RemoteClientService remoteClientService;

    public void setRemoteClientService(RemoteClientService remoteClientService)
    {
        this.remoteClientService = remoteClientService;
    }

    @Override
    public BaseResponse<BasePageDetail<RoleAppResPermission>> listRoleAppRes(RoleAppResCond roleAppResCond,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<RoleAppResPermission>> baseResponse = new BaseResponse<>();
        // 参数判断
        if (roleAppResCond == null)
        {
            return baseResponse;
        }
        // 传入参数 RoleAppResPermit
        ClientRequest<RoleAppResCond> requestParam = new ClientRequest<RoleAppResCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(roleAppResCond);
        // 请求返回
        TypeReference<BaseResponse<BasePageDetail<RoleAppResPermission>>> type = new TypeReference<BaseResponse<BasePageDetail<RoleAppResPermission>>>()
        {
        };

        BaseResponse<BasePageDetail<RoleAppResPermission>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.APPRESPERMIT_LISTROLEAPPRESPERMIT,
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
    public BaseResponse<Boolean> deleteRoleAppRes(List<Long> roleAppresIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        // 参数判断
        if (roleAppresIds == null)
        {
            return baseResponse;
        }
        // 传入参数 RoleAppResPermit
        ClientRequest<List<Long>> requestParam = new ClientRequest<List<Long>>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(roleAppresIds);
        // 请求返回
        TypeReference<BaseResponse<Boolean>> type = new TypeReference<BaseResponse<Boolean>>()
        {
        };

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_DELETEROLEAPPPERMIT, requestParam, type);
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
    public BaseResponse<Boolean> addRoleAppRes(AppResPermitCond viewaccount, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        // 参数判断
        if (viewaccount == null)
        {
            return baseResponse;
        }
        // 传入参数 RoleAppResPermit
        ClientRequest<AppResPermitCond> requestParam = new ClientRequest<AppResPermitCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(viewaccount);
        // 请求返回
        TypeReference<BaseResponse<Boolean>> type = new TypeReference<BaseResponse<Boolean>>()
        {
        };
        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_CREATEROLEAPPRESPERMISSIONS, requestParam, type);

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

}
