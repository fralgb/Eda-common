package com.omniselling.app.base.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.TypeReference;
import com.omniselling.app.base.biz.service.BizAppResPermitService;
import com.omniselling.app.base.common.ClientServiceConfig;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ClientRequest;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.model.ErrorInfo.SEVERITY;
import com.omniselling.common.service.RemoteClientService;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.ms.core.model.AccountAppResPermission;
import com.omniselling.ms.core.model.AppResPermitCond;
import com.omniselling.ms.core.model.AppResource;
import com.omniselling.ms.core.model.CheckAppResPermitCond;
import com.omniselling.ms.core.model.RoleAppResPermission;
import com.omniselling.ms.core.model.RoleAppResUpdateCond;

public class BizAppResPermitServiceImpl extends BizBaseImpl implements BizAppResPermitService
{

    private RemoteClientService remoteClientService;

    @Override
    public BaseResponse<Boolean> checkResPermission(CheckAppResPermitCond cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        // 参数判断
        if (cond == null || !validBaseOperatorInfo(operatorInfo))
        {
            // TODO ERROR
            return baseResponse;
        }

        // 返回

        // 传入参数
        ClientRequest<CheckAppResPermitCond> requestParam = new ClientRequest<CheckAppResPermitCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(cond);

        // 请求返回
        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_CHECKRESPERMISSION, requestParam, new TypeReference<BaseResponse<Boolean>>(){});

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
    public BaseResponse<Boolean> createRoleAppResPermissions(AppResPermitCond accRoleAppRes, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        // 参数判断
        if (accRoleAppRes == null || !validBaseOperatorInfo(operatorInfo))
        {
            // TODO error
            return baseResponse;
        }

        // 传入参数
        ClientRequest<AppResPermitCond> requestParam = new ClientRequest<AppResPermitCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(accRoleAppRes);

        // 请求返回
        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_CREATEROLEAPPRESPERMISSIONS, requestParam,  new TypeReference<BaseResponse<Boolean>>(){});

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
    public BaseResponse<Boolean> createAccAppResPermissions(AppResPermitCond accRoleAppRes, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        // 参数判断
        if (accRoleAppRes == null || !validBaseOperatorInfo(operatorInfo))
        {
            // error
            return baseResponse;
        }

        // 传入参数
        ClientRequest<AppResPermitCond> requestParam = new ClientRequest<AppResPermitCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(accRoleAppRes);

        // 请求返回
        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_CREATEACCAPPRESPERMISSIONS, requestParam, new TypeReference<BaseResponse<Boolean>>(){});

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
    public BaseResponse<List<AppResource>> listAllowAppResourcesByAcc(CheckAppResPermitCond cond,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<AppResource>> baseResponse = new BaseResponse<List<AppResource>>();
        // 参数判断
        if (cond == null || !validBaseOperatorInfo(operatorInfo))
        {
            // error
            return baseResponse;
        }

        // 传入参数
        ClientRequest<CheckAppResPermitCond> requestParam = new ClientRequest<CheckAppResPermitCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(cond);

        // 请求返回
        BaseResponse<List<AppResource>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_LISTALLOWAPPRESOURCESBYACC, requestParam, new TypeReference<BaseResponse<List<AppResource>>>(){});

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
    public BaseResponse<List<AppResource>> listAllowAppResourcesByRole(CheckAppResPermitCond cond,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<AppResource>> baseResponse = new BaseResponse<List<AppResource>>();
        // 参数判断
        if (cond == null || !validBaseOperatorInfo(operatorInfo))
        {
            // error
            return baseResponse;
        }

        // 传入参数
        ClientRequest<CheckAppResPermitCond> requestParam = new ClientRequest<CheckAppResPermitCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(cond);

        // 请求返回
        BaseResponse<List<AppResource>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_LISTALLOWAPPRESOURCESBYROLE, requestParam, new TypeReference<BaseResponse<List<AppResource>>>(){});

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
    public BaseResponse<List<RoleAppResPermission>> listRoleResPermissionsByRes(AppResPermitCond accRoleAppRes,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<RoleAppResPermission>> baseResponse = new BaseResponse<List<RoleAppResPermission>>();
        // 参数判断
        if (accRoleAppRes == null || !validBaseOperatorInfo(operatorInfo))
        {
            //TODO error
            return baseResponse;
        }

        // 传入参数
        ClientRequest<AppResPermitCond> requestParam = new ClientRequest<AppResPermitCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(accRoleAppRes);

        // 请求返回
        BaseResponse<List<RoleAppResPermission>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_LISTROLERESPERMISSIONSBYRES, requestParam, new TypeReference<BaseResponse<List<RoleAppResPermission>>>(){});

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
    public BaseResponse<List<RoleAppResPermission>> listRoleResPermissionsByRole(AppResPermitCond accRoleAppRes,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<RoleAppResPermission>> baseResponse = new BaseResponse<List<RoleAppResPermission>>();
        // 参数判断
        if (accRoleAppRes == null || !validBaseOperatorInfo(operatorInfo))
        {
            // error
            return baseResponse;
        }

        // 传入参数
        ClientRequest<AppResPermitCond> requestParam = new ClientRequest<AppResPermitCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(accRoleAppRes);

        // 请求返回
        BaseResponse<List<RoleAppResPermission>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_LISTROLERESPERMISSIONSBYROLE, requestParam,  new TypeReference<BaseResponse<List<RoleAppResPermission>>>(){});

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
    public BaseResponse<List<AccountAppResPermission>> listAccResPermissionsByAcc(AppResPermitCond accRoleAppRes,
            BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<AccountAppResPermission>> baseResponse = new BaseResponse<List<AccountAppResPermission>>();
        // 参数判断
        if (accRoleAppRes == null || !validBaseOperatorInfo(operatorInfo))
        {
            // error
            return baseResponse;
        }

        // 传入参数
        ClientRequest<AppResPermitCond> requestParam = new ClientRequest<AppResPermitCond>();
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setData(accRoleAppRes);

        // 请求返回
        BaseResponse<List<AccountAppResPermission>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_LISTACCRESPERMISSIONSBYACC, requestParam, new TypeReference<BaseResponse<List<AccountAppResPermission>>>(){});

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
    public BaseResponse<Boolean> removeAllPermissionsByRes(AppResPermitCond accRoleAppRes, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();

        if (accRoleAppRes == null || accRoleAppRes.getAppResourceIds() == null
                || accRoleAppRes.getAppResourceIds().size() == 0 || !validBaseOperatorInfo(operatorInfo))
        {
            // TODO error
            return baseResponse;
        }

        ClientRequest<AppResPermitCond> requestParam = new ClientRequest<AppResPermitCond>();

        requestParam.setData(accRoleAppRes);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_REMOVEALLPERMISSIONSBYRES, requestParam, new TypeReference<BaseResponse<Boolean>>(){});

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> removeAllPermissionsByRole(AppResPermitCond accRoleAppRes, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();

        if (accRoleAppRes == null || accRoleAppRes.getAccountRoleIds() == null
                || accRoleAppRes.getAccountRoleIds().size() == 0 || !validBaseOperatorInfo(operatorInfo))
        {
            // TODO error
            return baseResponse;
        }

        ClientRequest<AppResPermitCond> requestParam = new ClientRequest<AppResPermitCond>();

        requestParam.setData(accRoleAppRes);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_REMOVEALLPERMISSIONSBYROLE, requestParam, new TypeReference<BaseResponse<Boolean>>(){});

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> removeAllPermissionsByAcc(AppResPermitCond accRoleAppRes, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();

        if (accRoleAppRes == null || accRoleAppRes.getAccountIds() == null || accRoleAppRes.getAccountIds().size() == 0
                || !validBaseOperatorInfo(operatorInfo))
        {
            // TODO error
            return baseResponse;
        }

        ClientRequest<AppResPermitCond> requestParam = new ClientRequest<AppResPermitCond>();

        requestParam.setData(accRoleAppRes);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<Boolean> response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.APPRESPERMIT_REMOVEALLPERMISSIONSBYACC, requestParam, new TypeReference<BaseResponse<Boolean>>(){});

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> updateRoleAppResPermissions(Long accountRoleId,
            RoleAppResUpdateCond roleAppResUpdateCond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();

        if (accountRoleId == null || !validBaseOperatorInfo(operatorInfo))
        {
            // TODO error
            return baseResponse;
        }
        BaseResponse<Boolean> response = null;

        //删除
        if (roleAppResUpdateCond == null || roleAppResUpdateCond.getRoleAppResPermits().size()==0)
        {
            ClientRequest<AppResPermitCond> requestParam = new ClientRequest<AppResPermitCond>();
            AppResPermitCond accRoleRes = new AppResPermitCond();
            List<Long> accountRoleIds = new ArrayList<Long>();
            accountRoleIds.add(accountRoleId);
            accRoleRes.setAccountRoleIds(accountRoleIds);
            requestParam.setData(accRoleRes);
            requestParam.setLanguage(operatorInfo.getLanguage());
            requestParam.setOperatorId(operatorInfo.getOperatorId());

            response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                    ClientServiceConfig.APPRESPERMIT_REMOVEALLPERMISSIONSBYROLE, requestParam,  new TypeReference<BaseResponse<Boolean>>(){});
        }
        else
        {
            //==========================更新角色资源======================================
            ClientRequest<RoleAppResUpdateCond> requestParam = new ClientRequest<RoleAppResUpdateCond>();
            requestParam.setData(roleAppResUpdateCond);
            requestParam.setLanguage(operatorInfo.getLanguage());
            requestParam.setOperatorId(operatorInfo.getOperatorId());

            response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                    ClientServiceConfig.APPRESPERMIT_UPDATEROLEAPPRESPERMISSIONS, requestParam, new TypeReference<BaseResponse<Boolean>>(){});
            //==========================更新角色资源======================================
        }

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> updateAccAppResPermissions(Long accountId,
            List<AccountAppResPermission> accountAppResPermissions, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        BaseResponse<Boolean> response = null;

        if (accountId == null || !validBaseOperatorInfo(operatorInfo) )
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseResponse;
        }

        //删除
        if (accountAppResPermissions == null || accountAppResPermissions.size() == 0)
        {
            ClientRequest<Long> requestParam = new ClientRequest<Long>();

            requestParam.setData(accountId);
            requestParam.setLanguage(operatorInfo.getLanguage());
            requestParam.setOperatorId(operatorInfo.getOperatorId());

            response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                    ClientServiceConfig.APPRESPERMIT_REMOVEALLPERMISSIONSBYACC, requestParam, new TypeReference<BaseResponse<Boolean>>(){});
        }
        else
        {
            ClientRequest<List<AccountAppResPermission>> requestParam = new ClientRequest<List<AccountAppResPermission>>();
            requestParam.setData(accountAppResPermissions);
            requestParam.setLanguage(operatorInfo.getLanguage());
            requestParam.setOperatorId(operatorInfo.getOperatorId());

            response = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                    ClientServiceConfig.APPRESPERMIT_UPDATEACCAPPRESPERMISSIONS, requestParam, new TypeReference<BaseResponse<Boolean>>(){});
        }

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());
        return baseResponse;
    }

    public void setRemoteClientService(RemoteClientService remoteClientService)
    {
        this.remoteClientService = remoteClientService;
    }

}
