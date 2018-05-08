package com.omniselling.app.base.biz.service.impl;

import java.util.List;

import com.alibaba.fastjson.TypeReference;
import com.omniselling.app.base.biz.service.BizAccountService;
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
import com.omniselling.ms.core.model.AccountCond;

public class BizAccountServiceImpl implements BizAccountService
{
    private RemoteClientService remoteClientService;

    public void setRemoteClientService(RemoteClientService remoteClientService)
    {
        this.remoteClientService = remoteClientService;
    }

    @Override
    public BaseResponse<Boolean> activeAccounts(List<Long> accountIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();
        ClientRequest<List<Long>> requestParam = new ClientRequest<List<Long>>();
        requestParam.setData(accountIds);
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        BaseResponse<Boolean> coreRes = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNT_ACTIVEACCOUNT, requestParam, new TypeReference<BaseResponse<Boolean>>()
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
    public BaseResponse<Boolean> disableAccounts(List<Long> accountIds, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();

        ClientRequest<List<Long>> requestParam = new ClientRequest<List<Long>>();
        requestParam.setData(accountIds);
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        BaseResponse<Boolean> coreRes = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNT_DISABLEACCOUNT, requestParam, new TypeReference<BaseResponse<Boolean>>()
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
    public BaseResponse<Boolean> createAccount(Account account, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();

        ClientRequest<Account> requestParam = new ClientRequest<Account>();
        requestParam.setData(account);
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        BaseResponse<Boolean> coreRes = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNT_CREATEACCOUNT, requestParam, new TypeReference<BaseResponse<Boolean>>()
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
    public BaseResponse<Boolean> updateAccount(Account account, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();

        ClientRequest<Account> requestParam = new ClientRequest<Account>();
        requestParam.setData(account);
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        BaseResponse<Boolean> coreRes = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNT_UPDATEACCOUNT, requestParam, new TypeReference<BaseResponse<Boolean>>()
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
    public BaseResponse<Account> getAccountById(Long accountId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Account> baseRes = new BaseResponse<Account>();

        ClientRequest<Long> requestParam = new ClientRequest<Long>();
        requestParam.setData(accountId);
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        BaseResponse<Account> coreRes = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNT_GETACCOUNTBYID, requestParam, new TypeReference<BaseResponse<Account>>()
                {
                });
        if (coreRes.hasError())
        {
            baseRes.addErrors(coreRes.getErrors());
            return baseRes;
        }
        Account account = coreRes.getData();
        baseRes.setData(account);
        return baseRes;
    }

    @Override
    public BaseResponse<Account> getParentAccount(Long accountId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Account> baseRes = new BaseResponse<Account>();

        ClientRequest<Long> requestParam = new ClientRequest<Long>();
        requestParam.setData(accountId);
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        BaseResponse<Account> coreRes = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNT_GETPARENTACCOUNTBYID, requestParam,
                new TypeReference<BaseResponse<Account>>()
                {
                });
        if (coreRes.hasError())
        {
            baseRes.addErrors(coreRes.getErrors());
            return baseRes;
        }
        Account account = coreRes.getData();
        baseRes.setData(account);
        return baseRes;
    }

    @Override
    public BaseResponse<List<Account>> listSubAccount(Long accountId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<Account>> baseRes = new BaseResponse<List<Account>>();

        ClientRequest<Long> requestParam = new ClientRequest<Long>();
        requestParam.setData(accountId);
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        BaseResponse<List<Account>> coreRes = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.ACCOUNT_LISTSUBACCOUNTSBYID,
                requestParam, new TypeReference<BaseResponse<List<Account>>>()
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
    public BaseResponse<BasePageDetail<Account>> listAccounts(AccountCond accCond, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<BasePageDetail<Account>> baseRes = new BaseResponse<BasePageDetail<Account>>();

        ClientRequest<AccountCond> requestParam = new ClientRequest<AccountCond>();
        requestParam.setData(accCond);
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());

        BaseResponse<BasePageDetail<Account>> coreRes = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.ACCOUNT_LISTACCOUNTS, requestParam,
                new TypeReference<BaseResponse<BasePageDetail<Account>>>()
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
    public BaseResponse<Account> getAccountByLoginId(String loginId, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Account> baseRes = new BaseResponse<Account>();

        ClientRequest<String> requestParam = new ClientRequest<String>();
        requestParam.setData(loginId);
        requestParam.setOperatorId(operatorInfo.getOperatorId());
        requestParam.setLanguage(operatorInfo.getLanguage());
        BaseResponse<Account> coreRes = remoteClientService.postRequest(ClientServiceConfig.SERVICENAME_CORESERVICE,
                ClientServiceConfig.ACCOUNT_GETACCOUNTBYLOGINID, requestParam,
                new TypeReference<BaseResponse<Account>>()
                {
                });
        if (coreRes.hasError())
        {
            baseRes.addErrors(coreRes.getErrors());
            return baseRes;
        }
        Account account = coreRes.getData();
        baseRes.setData(account);
        return baseRes;
    }

    @Override
    public BaseResponse<List<BizEnumSpec>> listResourcePermitType(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<BizEnumSpec>> baseResponse = new BaseResponse<>();
        if (operatorInfo == null || operatorInfo.getLanguage() == null || "".equals(operatorInfo.getLanguage()))
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "listResourcePermitType biz param null"));
            return baseResponse;
        }

        ClientRequest<String> requestParam = new ClientRequest<String>();
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<List<BizEnumSpec>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.ACCOUNT_LISTRESOURCEPERMITTYPE,
                requestParam, new TypeReference<BaseResponse<List<BizEnumSpec>>>()
                {
                });

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());

        return baseResponse;
    }

    @Override
    public BaseResponse<List<BizEnumSpec>> listAccountStatus(BaseOperatorInfo operatorInfo)
    {
        BaseResponse<List<BizEnumSpec>> baseResponse = new BaseResponse<>();
        if (operatorInfo == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.BIZ, LanguageCode.EN_US));
            return baseResponse;
        }

        ClientRequest<String> requestParam = new ClientRequest<String>();
        requestParam.setData("");
        requestParam.setLanguage(operatorInfo.getLanguage());
        requestParam.setOperatorId(operatorInfo.getOperatorId());

        BaseResponse<List<BizEnumSpec>> response = remoteClientService.postRequest(
                ClientServiceConfig.SERVICENAME_CORESERVICE, ClientServiceConfig.ACCOUNT_LISTACCOUNTSTATUS,
                requestParam, new TypeReference<BaseResponse<List<BizEnumSpec>>>()
                {
                });

        baseResponse.setData(response.getData());
        baseResponse.addErrors(response.getErrors());

        return baseResponse;
    }
}
