package com.omniselling.security.service.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.omniselling.security.service.model.OmniSecUser;

public class CreateMockAuthenticationFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth.getPrincipal()  == null){
            
            OmniSecUser secUser = new OmniSecUser("admin", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            auth = new TestingAuthenticationToken(secUser,null);
            auth.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(auth);
            
        }
        
        chain.doFilter(httpRequest, httpResponse);
    }

}
