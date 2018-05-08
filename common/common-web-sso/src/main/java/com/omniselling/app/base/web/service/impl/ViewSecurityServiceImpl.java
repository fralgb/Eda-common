package com.omniselling.app.base.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.omniselling.app.base.biz.service.BizAccountRoleService;
import com.omniselling.app.base.biz.service.BizAccountService;
import com.omniselling.app.base.biz.service.BizAppResPermitService;
import com.omniselling.app.base.common.AccountRoleStatus;
import com.omniselling.app.base.common.AppResourceType;
import com.omniselling.app.base.common.OmniConstant;
import com.omniselling.app.base.web.model.ViewAccountRole;
import com.omniselling.app.base.web.model.ViewAppResource;
import com.omniselling.app.base.web.model.ViewSecAccount;
import com.omniselling.app.base.web.model.ViewSessionAccount;
import com.omniselling.app.base.web.service.ViewSecurityService;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.ms.core.model.Account;
import com.omniselling.ms.core.model.AccountRole;
import com.omniselling.ms.core.model.AppResPermitCond;
import com.omniselling.ms.core.model.AppResource;
import com.omniselling.ms.core.model.CheckAppResPermitCond;
import com.omniselling.ms.core.model.RoleAppResPermission;

public class ViewSecurityServiceImpl implements ViewSecurityService
{
	
	private String omniAppName;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private BizAccountRoleService bizAccountRoleService;

    private BizAppResPermitService bizAppResPermitService;

    private BizAccountService bizAccountService;

    public void setBizAccountRoleService(BizAccountRoleService bizAccountRoleService)
    {
        this.bizAccountRoleService = bizAccountRoleService;
    }

    public void setBizAppResPermitService(BizAppResPermitService bizAppResPermitService)
    {
        this.bizAppResPermitService = bizAppResPermitService;
    }

    public void setBizAccountService(BizAccountService bizAccountService)
    {
        this.bizAccountService = bizAccountService;
    }

    @Override
    public BaseResponse<List<ViewAccountRole>> listAllRoles()
    {
        BaseResponse<List<ViewAccountRole>> response = new BaseResponse<List<ViewAccountRole>>();
        List<String> roleStatusList = Arrays.asList(new String[] { AccountRoleStatus.ACTIVE.name() });
        ViewSessionAccount viewSessionAccount = new ViewSessionAccount();
        BaseResponse<List<AccountRole>> bizRes = bizAccountRoleService.listAllRoles(
                getSysOperator(viewSessionAccount.getLanguage()), roleStatusList);
        if (bizRes.hasError())
        {
            response.addErrors(bizRes.getErrors());
            return response;
        }

        if (!bizRes.hasData())
        {
            return response;
        }
        List<ViewAccountRole> viewList = new ArrayList<ViewAccountRole>();
        List<AccountRole> accountRoles = bizRes.getData();
        for (AccountRole accountRole : accountRoles)
        {
            ViewAccountRole viewRole = new ViewAccountRole();
            BeanUtils.copyProperties(accountRole, viewRole);
            viewList.add(viewRole);
        }
        response.setData(viewList);
        return response;
    }

    @Override
    public BaseResponse<List<ViewAppResource>> listAppResByRoleId(Long roleId)
    {
        BaseResponse<List<ViewAppResource>> response = new BaseResponse<List<ViewAppResource>>();
        if (roleId == null)
        {
            return response;
        }
        AppResPermitCond accRoleAppRes = new AppResPermitCond();
        List<Long> accountRoleIds = new ArrayList<>();
        accountRoleIds.add(roleId);
        accRoleAppRes.setAccountRoleIds(accountRoleIds);
        ViewSessionAccount viewSessionAccount = new ViewSessionAccount();
        BaseResponse<List<RoleAppResPermission>> bizRes = bizAppResPermitService.listRoleResPermissionsByRole(
                accRoleAppRes, getSysOperator(viewSessionAccount.getLanguage()));
        if (bizRes.hasError())
        {
            response.addErrors(bizRes.getErrors());
            return response;
        }
        if (!bizRes.hasData())
        {
            return response;
        }
        List<ViewAppResource> viewList = new ArrayList<ViewAppResource>();
        List<RoleAppResPermission> appResPermits = bizRes.getData();
        for (RoleAppResPermission roleAppResPermission : appResPermits)
        {
            ViewAppResource viewAppResource = new ViewAppResource();
            BeanUtils.copyProperties(roleAppResPermission, viewAppResource);
            viewAppResource.setId(roleAppResPermission.getAppResourceId());
            viewList.add(viewAppResource);
        }
        response.setData(viewList);
        return response;
    }

    @Override
    public BaseResponse<ViewSecAccount> getSecAccountByLoginId(String loginId)
    {
        BaseResponse<ViewSecAccount> response = new BaseResponse<ViewSecAccount>();
        if (StringUtils.isEmpty(loginId))
        {
            return response;
        }
        ViewSessionAccount viewSessionAccount = new ViewSessionAccount();
        BaseResponse<Account> bizRes = bizAccountService.getAccountByLoginId(loginId,
                getSysOperator(viewSessionAccount.getLanguage()));
        if (bizRes.hasError())
        {
            response.addErrors(bizRes.getErrors());
            return response;
        }

        if (!bizRes.hasData())
        {
            return response;
        }
        ViewSecAccount viewSecAccount = new ViewSecAccount();
        Account account = bizRes.getData();
        viewSecAccount.setLoginId(account.getLoginId());
        response.setData(viewSecAccount);

        CheckAppResPermitCond cond = new CheckAppResPermitCond();
        cond.setAccountId(account.getId());
        cond.setApplicationName(this.omniAppName);
        BaseResponse<List<AppResource>> appPerallowReses = bizAppResPermitService.listAllowAppResourcesByAcc(cond,
                getSysOperator(viewSessionAccount.getLanguage()));
        if (!appPerallowReses.hasError() && appPerallowReses.hasData())
        {
            List<ViewAppResource> whiteResAccess = new ArrayList<ViewAppResource>();
            List<ViewAppResource> blackResAccess = new ArrayList<ViewAppResource>();
            List<AppResource> appResources = appPerallowReses.getData();
            for (AppResource appResource : appResources)
            {
                ViewAppResource viewAppResource = new ViewAppResource();
                BeanUtils.copyProperties(appResource, viewAppResource);
                viewAppResource.setId(appResource.getId());
                whiteResAccess.add(viewAppResource);
            }

            viewSecAccount.setBlackResAccess(blackResAccess);
            viewSecAccount.setWhiteResAccess(whiteResAccess);
        }
        else
        {
            logger.error(appPerallowReses.getErrorsToString());
        }

        return response;

    }

    @Override
    public BaseResponse<ViewSessionAccount> getViewSessionAccount(String loginId, String operationId, String language)
    {
        BaseResponse<ViewSessionAccount> response = new BaseResponse<ViewSessionAccount>();
        if (StringUtils.isEmpty(loginId))
        {
            return response;
        }

        BaseResponse<Account> bizRes = bizAccountService.getAccountByLoginId(loginId, getSysOperator(language));
        if (bizRes.hasError())
        {
            response.addErrors(bizRes.getErrors());
            return response;
        }

        if (!bizRes.hasData())
        {
            return response;
        }

        ViewSessionAccount viewSessionAccount = new ViewSessionAccount();
        Account account = bizRes.getData();
        BeanUtils.copyProperties(account, viewSessionAccount);
        viewSessionAccount.setAccountId(account.getId());
        response.setData(viewSessionAccount);

        CheckAppResPermitCond cond = new CheckAppResPermitCond();
        cond.setAccountId(account.getId());
        cond.setApplicationName(this.omniAppName);
        BaseResponse<List<AppResource>> resourceRes = bizAppResPermitService.listAllowAppResourcesByAcc(cond,
                getSysOperator(language));
        if (!resourceRes.hasError() && resourceRes.hasData())
        {
            List<ViewAppResource> viewAppResources = new ArrayList<ViewAppResource>();
            List<AppResource> appResources = resourceRes.getData();
            for (AppResource appResource : appResources)
            {
                ViewAppResource viewAppResource = new ViewAppResource();
                BeanUtils.copyProperties(appResource, viewAppResource);
                viewAppResource.setResourceType(appResource.getResourceType());
                viewAppResource.setResourceTypeCode(appResource.getResourceType());
                if (AppResourceType.APP_MENU.name().equals(appResource.getResourceType()))
                {

                    fillMemuResourceName(viewAppResource);
                }
                viewAppResources.add(viewAppResource);
            }
            viewSessionAccount.setAppResources(viewAppResources);
        }

        return response;
    }

    private void fillMemuResourceName(ViewAppResource viewAppResource)
    {
        String resourceName = viewAppResource.getResourceName();
        // 只保留最后一级菜单名
        if (!StringUtils.isEmpty(resourceName))
        {
            int index = resourceName.lastIndexOf(".");
            if (index != -1 || index != resourceName.length())
            {
                viewAppResource.setResourceName(resourceName.substring(index + 1, resourceName.length()));
            }
        }
    }

    private BaseOperatorInfo getSysOperator(String language)
    {
        BaseOperatorInfo operatorInfo = new BaseOperatorInfo();
        operatorInfo.setOperatorId(OmniConstant.SystemOperatorId);
        operatorInfo.setLanguage(language);
        return operatorInfo;
    }

	public String getOmniAppName() {
		return omniAppName;
	}

	public void setOmniAppName(String omniAppName) {
		this.omniAppName = omniAppName;
	}
    
}
