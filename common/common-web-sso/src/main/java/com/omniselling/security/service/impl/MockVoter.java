package com.omniselling.security.service.impl;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

public class MockVoter implements AccessDecisionVoter<Object>
{

    @Override
    public boolean supports(ConfigAttribute attribute)
    {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return ACCESS_GRANTED;
    }

}
