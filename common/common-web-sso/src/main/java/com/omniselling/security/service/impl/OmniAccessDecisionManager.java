package com.omniselling.security.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.core.Authentication;

public class OmniAccessDecisionManager extends AffirmativeBased
{

    private AccessDecisionVoter<Object> blackVoter;

    
    public OmniAccessDecisionManager(List<AccessDecisionVoter<?>> decisionVoters)
    {
        super(decisionVoters);
    }

    public void setBlackVoter(AccessDecisionVoter<Object> blackVoter)
    {
        this.blackVoter = blackVoter;
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException
    {
        if (blackVoter.vote(authentication, object, configAttributes) == AccessDecisionVoter.ACCESS_GRANTED)
        {
            super.decide(authentication, object, configAttributes);
        }
        else
        {
            throw new AccessDeniedException(
                    messages.getMessage("OmniAccessDecisionManager.accessDenied", "Access is denied"));
        }
    }
}
