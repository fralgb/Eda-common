package com.omniselling.security.service.impl;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import com.omniselling.security.service.model.OmniSecUser;

public class OmniBlackVoter implements AccessDecisionVoter<Object>
{

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
        int result = ACCESS_GRANTED;
        Collection<? extends GrantedAuthority> authorities = user.getBlackResAccess();

        for (GrantedAuthority auth : authorities)
        {
            if (invocation.getRequestUrl().equalsIgnoreCase(auth.getAuthority()))
            {
                return ACCESS_DENIED;
            }
        }

        return result;
    }

}
