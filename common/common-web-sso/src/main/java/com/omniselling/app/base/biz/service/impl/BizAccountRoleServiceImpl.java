package com.omniselling.app.base.biz.service.impl;

import java.util.List;

import com.alibaba.fastjson.TypeReference;
import com.omniselling.app.base.biz.service.BizAccountRoleService;
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
import com.omniselling.ms.core.model.Account;
import com.omniselling.ms.core.model.AccountRefRolesCond;
import com.omniselling.ms.core.model.AccountRole;
import com.omniselling.ms.core.model.AccountRoleCond;
import com.omniselling.ms.core.model.AccountRoleIdNameCond;
import com.omniselling.ms.core.model.RoleRefAccountsCond;

public class BizAccountRoleServiceImpl implements BizAccountRoleService
{

    private RemoteClientService remoteClientService;

    public void setRemoteClientService(RemoteClientService remoteClientService)
    {
        this.remoteClientService = remoteClientService;
    }

    @Override
    public BaseResponse<BasePageDetail<AccountRole>> listRoles(AccountRoleCond cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<AccountRole>> baseResponse = new BaseResponse<BasePageDetail<AccountRole>>();

        // 传入参数
        ClientRequest<AccountRoleCond> requestParam = new ClientRequest<AccountRoleCond>();
        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        // requestParam.setLoginId();
        // requestParam.setNonceToken();
        // 请求返回
        baseResponse = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNTROLE_LISTROLES, requestParam, new TypeReference<BaseResponse<BasePageDetail<AccountRole>>>(){});
        return baseResponse;
    }

    @Override
    public BaseResponse<List<AccountRole>> listAllRoles(BaseOperatorInfo operatorInfo, List<String> roleStatusList)
    {
        BaseResponse<List<AccountRole>> baseResponse = new BaseResponse<List<AccountRole>>();
        // 传入参数
        ClientRequest<List<String>> requestParam = new ClientRequest<List<String>>();
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setData(roleStatusList);

        // 请求返回
        baseResponse = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNTROLE_LISTALLROLES, requestParam, new TypeReference<BaseResponse<List<AccountRole>>>(){});
        return baseResponse;
    }

    @Override
    public BaseResponse<AccountRole> getRoleById(Long roleId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<AccountRole> baseResponse = new BaseResponse<AccountRole>();
        // 传入参数
        ClientRequest<Long> requestParam = new ClientRequest<Long>();
        requestParam.setData(roleId);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        // requestParam.setLoginId();
        // requestParam.setNonceToken();
        // 请求返回
        baseResponse = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNTROLE_GETROLEBYID, requestParam, new TypeReference<BaseResponse<AccountRole>>(){});
        return baseResponse;
    }

    @Override
    public BaseResponse<Long> createRole(AccountRole accountRole, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Long> baseResponse = new BaseResponse<Long>();

        // 传入参数
        ClientRequest<AccountRole> requestParam = new ClientRequest<AccountRole>();
        requestParam.setData(accountRole);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        // requestParam.setLoginId();
        // requestParam.setNonceToken();
        // 请求返回
        baseResponse = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNTROLE_CREATEROLE, requestParam, new TypeReference<BaseResponse<Long>>(){});
        return baseResponse;
    }

    @Override
    public BaseResponse<AccountRole> getRoleByName(String roleName, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<AccountRole> baseResponse = new BaseResponse<AccountRole>();

        // 传入参数
        ClientRequest<String> requestParam = new ClientRequest<String>();
        requestParam.setData(roleName);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        // requestParam.setLoginId();
        // requestParam.setNonceToken();
        // 请求返回
        baseResponse = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNTROLE_GETROLEBYNAME, requestParam, new TypeReference<BaseResponse<AccountRole>>(){});
        return baseResponse;
    }

    @Override
    public BaseResponse<List<Account>> listAccountsByRoles(AccountRoleIdNameCond cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<Account>> baseResponse = new BaseResponse<List<Account>>();

        // 传入参数
        ClientRequest<AccountRoleIdNameCond> requestParam = new ClientRequest<AccountRoleIdNameCond>();
        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        // requestParam.setLoginId();
        // requestParam.setNonceToken();
        // 请求返回
        baseResponse = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNTROLE_LISTACCOUNTSBYROLE, requestParam,  new TypeReference<BaseResponse<List<Account>>>(){});
        return baseResponse;
    }

    @Override
    public BaseResponse<List<AccountRole>> listRolesByAccounts(List<Long> accountIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<AccountRole>> baseResponse = new BaseResponse<List<AccountRole>>();

        // 传入参数
        ClientRequest<List<Long>> requestParam = new ClientRequest<List<Long>>();
        requestParam.setData(accountIds);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        // requestParam.setLoginId();
        // requestParam.setNonceToken();
        // 请求返回
        BaseResponse<List<AccountRole>> baseRes = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNTROLE_LISTROLESBYACCOUNTS, requestParam, new TypeReference<BaseResponse<List<AccountRole>>>(){});
        baseResponse.setData(baseRes.getData());
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> removeAccountsByRole(RoleRefAccountsCond cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();

        // 传入参数
        ClientRequest<RoleRefAccountsCond> requestParam = new ClientRequest<RoleRefAccountsCond>();
        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        // requestParam.setLoginId();
        // requestParam.setNonceToken();
        // 请求返回
        baseResponse = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNTROLE_REMOVEACCOUNTSBYROLE, requestParam, new TypeReference<BaseResponse<Boolean>>(){});
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> removeRolesByAccount(AccountRefRolesCond cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();

        // 传入参数
        ClientRequest<AccountRefRolesCond> requestParam = new ClientRequest<AccountRefRolesCond>();
        requestParam.setData(cond);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        // requestParam.setLoginId();
        // requestParam.setNonceToken();
        // 请求返回
        baseResponse = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNTROLE_REMOVEROLESBYACCOUNT, requestParam, new TypeReference<BaseResponse<Boolean>>(){});
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> updateRole(AccountRole accountRole, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();

        // 传入参数
        ClientRequest<AccountRole> requestParam = new ClientRequest<AccountRole>();
        requestParam.setData(accountRole);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        // requestParam.setLoginId();
        // requestParam.setNonceToken();
        // 请求返回
        baseResponse = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNTROLE_UPDATEACCOUNTROLE, requestParam, new TypeReference<BaseResponse<Boolean>>(){});
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> activeRoles(List<Long> roleIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();

        // 传入参数
        ClientRequest<List<Long>> requestParam = new ClientRequest<List<Long>>();
        requestParam.setData(roleIds);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        // requestParam.setLoginId();
        // requestParam.setNonceToken();
        // 请求返回
        baseResponse = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNTROLE_ACTIVEROLES, requestParam, new TypeReference<BaseResponse<Boolean>>(){});
        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> disableRoles(List<Long> roleIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();

        // 传入参数
        ClientRequest<List<Long>> requestParam = new ClientRequest<List<Long>>();
        requestParam.setData(roleIds);
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        // requestParam.setLoginId();
        // requestParam.setNonceToken();
        // 请求返回
        baseResponse = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNTROLE_DISABLEROLES, requestParam, new TypeReference<BaseResponse<Boolean>>(){});
        return baseResponse;
    }

    @Override
    public BaseResponse<List<BizEnumSpec>> listAccountroleStatus(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<BizEnumSpec>> baseResponse = new BaseResponse<>();
        if (operatorInfo == null ||operatorInfo.getLanguage() == null || "".equals(operatorInfo.getLanguage()))
        {
            baseResponse.addError(
                    ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR, ErrorInfo.CATEGORY.BIZ,
                            LanguageCode.getByValue(operatorInfo.getLanguage()), "listAccountroleStatus biz param null"));
            return baseResponse;
        }

        ClientRequest<String> requestParam = new ClientRequest<String>();
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<List<BizEnumSpec>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.ACCOUNTROLE_LISTACCOUNTROLESTATUS,
                requestParam, new TypeReference<BaseResponse<List<BizEnumSpec>>>(){});

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());

        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> updateAccountsByRole(RoleRefAccountsCond cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> response = new BaseResponse<Boolean>();
        if(cond == null || operatorInfo == null)
        {
            response.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return response;
        }
        
        ClientRequest<RoleRefAccountsCond> requestParam = new ClientRequest<RoleRefAccountsCond>();
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setData(cond);
        
        //微服务调用
        BaseResponse<Boolean> remoteResponse = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.ACCOUNTROLE_UPDATEACCOUNTSBYROLE,
                requestParam, new TypeReference<BaseResponse<Boolean>>(){});
        
        response.setErrors(remoteResponse.getErrors());
        response.setData(remoteResponse.getData());
        return response;
    }

    @Override
    public BaseResponse<Boolean> updateRolesByAccount(AccountRefRolesCond cond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> response = new BaseResponse<Boolean>();
        if(cond == null || operatorInfo == null)
        {
            response.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return response;
        }
        
        ClientRequest<AccountRefRolesCond> requestParam = new ClientRequest<AccountRefRolesCond>();
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setData(cond);

        //微服务调用
        BaseResponse<Boolean> remoteResponse = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.ACCOUNTROLE_UPDATEROLESBYACCOUNT,
                requestParam, new TypeReference<BaseResponse<Boolean>>(){});
        
        response.setErrors(remoteResponse.getErrors());
        response.setData(remoteResponse.getData());
        return response;
    }
}
