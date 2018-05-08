package com.omniselling.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * OmniUrl资源权限验证实现类 可获取Url对应的角色列表
 * 
 * @author log
 *
 */
public class OmniSecurityMetadataSourceImpl
        implements FilterInvocationSecurityMetadataSource
{
    //该属性结构为Map<appResourceUrl,Collection<roleLabel>>
    private List<ConfigAttribute> initAttributes;
    
    private static String ROLE_IGNORE = "ROLE_GINOR";
    
    public OmniSecurityMetadataSourceImpl()
    {
        initAttributes = new ArrayList<>();
        initAttributes.add(new SecurityConfig(ROLE_IGNORE));
    }

    /**
     * 根据request
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
    {
        return Collections.unmodifiableList(initAttributes);
    }


    @Override
    public boolean supports(Class<?> clazz)
    {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes()
    {
        return Collections.unmodifiableList(initAttributes);
    }

}
