package com.omniselling.security.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * OmniUrl验证匹配模型
 * @author log
 *
 */
public class OmniRegexRequestMatcher  implements RequestMatcher 
{

    private RequestMatcher requestMatcher;
    
    private String pattern ;
    
    public OmniRegexRequestMatcher(String pattern)
    {
        requestMatcher = new AntPathRequestMatcher(pattern);
        this.pattern = pattern;
    }
    
    @Override
    public boolean matches(HttpServletRequest request)
    {
        return requestMatcher.matches(request);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof OmniRegexRequestMatcher){
            OmniRegexRequestMatcher matcher = (OmniRegexRequestMatcher) obj;
            return this.pattern.equals(matcher.pattern);
        }

        return false;
    }

}
