package com.omniselling.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.omniselling.app.base.web.model.ViewAccountRole;
import com.omniselling.app.base.web.model.ViewAppResource;
import com.omniselling.app.base.web.model.ViewSecAccount;
import com.omniselling.app.base.web.service.ViewSecurityService;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.security.service.model.OmniSecUser;

public class OmniSecUserDetailServiceImpl implements UserDetailsService
{

    private Logger logger = Logger.getLogger(getClass());

    private ViewSecurityService viewSecurityService;

    public void setViewSecurityService(ViewSecurityService viewSecurityService)
    {
        this.viewSecurityService = viewSecurityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        BaseResponse<ViewSecAccount> response = viewSecurityService.getSecAccountByLoginId(username);
        if (response.hasError())
        {
            logger.error(response.getErrorsToString());
        }

        ViewSecAccount viewSecAccount = response.getData();
        if (viewSecAccount == null)
        {
            logger.error("can't get viewSecAccount from currentapp by loginId " + username);
            return null;
        }
        
        Collection<GrantedAuthority> whiteAuths = getWhiteAuths(viewSecAccount);
        Collection<GrantedAuthority> blackAuths = getBlackAuths(viewSecAccount);
        Collection<GrantedAuthority> roleAuths = getRoleAuths(viewSecAccount);
        String loginId = viewSecAccount.getLoginId();
        
        return new OmniSecUser(loginId, roleAuths, whiteAuths, blackAuths);
    }
    
    private Collection<GrantedAuthority> getRoleAuths(ViewSecAccount viewSecAccount)
    {
        Collection<GrantedAuthority> auths = new ArrayList<>();

        if (viewSecAccount.getAccountRoles() != null)
        {
            for (ViewAccountRole role : viewSecAccount.getAccountRoles())
            {
                GrantedAuthority auth = new SimpleGrantedAuthority(role.getRoleLabel());
                auths.add(auth);
            }
        }

        return auths;
    }

    private Collection<GrantedAuthority> getWhiteAuths(ViewSecAccount viewSecAccount)
    {
        Collection<GrantedAuthority> whiteAuths = buildAuthsFromRes(viewSecAccount.getWhiteResAccess());
        
        return whiteAuths;
    }
    
    private Collection<GrantedAuthority> getBlackAuths(ViewSecAccount viewSecAccount)
    {
        Collection<GrantedAuthority> blackAuths = buildAuthsFromRes(viewSecAccount.getBlackResAccess());
        
        return blackAuths;
    }

    private Collection<GrantedAuthority> buildAuthsFromRes(List<ViewAppResource> resources)
    {
        Collection<GrantedAuthority> auths = new ArrayList<>();

        if (resources != null)
        {
            for (ViewAppResource resource : resources)
            {
                if(StringUtils.isEmpty(resource.getResourceUrl()))
                {
                    continue;
                }
                GrantedAuthority auth = new SimpleGrantedAuthority(resource.getResourceUrl());
                auths.add(auth);
            }
        }

        return auths;
    }

}
