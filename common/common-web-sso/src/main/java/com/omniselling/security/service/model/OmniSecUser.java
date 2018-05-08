package com.omniselling.security.service.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * spring security 用户模型
 * 
 * @author log
 *
 */
public class OmniSecUser implements UserDetails
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_PASSWORD = "no password";

    private User user;
    
    private String locale;

    private Collection<GrantedAuthority> whiteResAccess;

    private Collection<GrantedAuthority> blackResAccess;

    public OmniSecUser(String username,  Collection<GrantedAuthority> roleAuthorities,
            Collection<GrantedAuthority> whiteResAccess, Collection<GrantedAuthority> blackResAccess)
    {
        this(username, true, true, true, true, roleAuthorities, whiteResAccess, blackResAccess);
    }

    public OmniSecUser(String username,  boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> roleAuthorities,
            Collection<GrantedAuthority> whiteResAccess, Collection<GrantedAuthority> blackResAccess)
    {

        if (username == null)
        {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
        
        this.user = new User(username, DEFAULT_PASSWORD, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                roleAuthorities);
        this.whiteResAccess = whiteResAccess;
        this.blackResAccess = blackResAccess;
    }
    
    public String getLocale()
    {
        return locale;
    }
    
    public void setLocale(String locale)
    {
        this.locale = locale;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities()
    {
        return user.getAuthorities();
    }

    public Collection<GrantedAuthority> getBlackResAccess()
    {
        return blackResAccess;
    }

    public Collection<GrantedAuthority> getWhiteResAccess()
    {
        return whiteResAccess;
    }

    @Override
    public String getPassword()
    {
        return null;
    }

    @Override
    public String getUsername()
    {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled()
    {
        return user.isEnabled();
    }

}
