package com.omniselling.security.service.impl;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import com.omniselling.security.service.model.OmniSecUser;

public class OmniWhiteVoter implements AccessDecisionVoter<Object>
{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private List<String> defaultWhiteList;

    public void setDefaultWhiteList(List<String> defaultWhiteList)
    {
        this.defaultWhiteList = defaultWhiteList;
    }

    @Override
    public boolean supports(ConfigAttribute attribute)
    {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz)
    {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes)
    {
        OmniSecUser user = (OmniSecUser) authentication.getPrincipal();
        FilterInvocation invocation = (FilterInvocation) object;
        int result = ACCESS_DENIED;
        Collection<? extends GrantedAuthority> authorities = user.getWhiteResAccess();
        if (logger.isDebugEnabled())
        {
            logger.info(invocation.getRequestUrl());
        }
        for (GrantedAuthority auth : authorities)
        {
            if (invocation.getRequestUrl().equalsIgnoreCase(auth.getAuthority()))
            {
                return ACCESS_GRANTED;
            }
        }
        if (defaultWhiteList != null && !defaultWhiteList.isEmpty())
        {
            String url = invocation.getRequestUrl();
            if (url.contains("?"))
            {
                url = url.substring(0, url.indexOf("?"));
            }
            if (defaultWhiteList.contains(url))
            {
                return ACCESS_GRANTED;
            }
        }

        return result;
    }

}
