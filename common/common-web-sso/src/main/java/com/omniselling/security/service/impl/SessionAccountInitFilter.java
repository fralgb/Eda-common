package com.omniselling.security.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.omniselling.app.base.web.model.ViewSessionAccount;
import com.omniselling.app.base.web.service.ViewSecurityService;
import com.omniselling.common.Constant;
import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.security.service.model.OmniSecUser;

public class SessionAccountInitFilter extends GenericFilterBean
{
    private static Map<String, LanguageCode> ssoLocaleToOmniLocale = new HashMap<>();

    private Logger logger = Logger.getLogger(getClass());

    private ViewSecurityService viewSecurityService;

    public void setViewSecurityService(ViewSecurityService viewSecurityService)
    {
        this.viewSecurityService = viewSecurityService;
    }

    static
    {
        ssoLocaleToOmniLocale.put("en", LanguageCode.EN_US);
        ssoLocaleToOmniLocale.put("zh_cn", LanguageCode.ZH_CN);
        ssoLocaleToOmniLocale.put("pl", LanguageCode.PL_PL);

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ViewSessionAccount sessionAccount = (ViewSessionAccount) httpRequest.getSession().getAttribute(
        		Constant.SESSIONUSER);

        if (sessionAccount == null || StringUtils.isEmpty(sessionAccount.getLoginId()))
        {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String locale = null;
            if (auth.getPrincipal() instanceof OmniSecUser)
            {
                OmniSecUser user = (OmniSecUser) auth.getPrincipal();
                locale = user.getLocale();
            }
            String language = Constant.DefaultLanguage.getDbValue();
            if (StringUtils.isNotEmpty(locale) && ssoLocaleToOmniLocale.containsKey(locale))
            {
                language = ssoLocaleToOmniLocale.get(locale).getDbValue();
            }
            BaseResponse<ViewSessionAccount> res = viewSecurityService.getViewSessionAccount(auth.getName(),
            		Constant.SystemOperatorId.toString(), language);
            if (res.hasError())
            {
                logger.error(res.getErrorsToString());
                httpResponse.sendError(400, "can not access");
                return;
            }
            ViewSessionAccount account = res.getData();
            account.setLanguage(language);
            account.setWarehouseId(1L);
            account.setWarehouseName("深圳仓");
            account.setWarehouseCode("WH0001");
            httpRequest.getSession().setAttribute(Constant.SESSIONUSER, account);
        }

        chain.doFilter(httpRequest, httpResponse);
    }

}
