package com.omniselling.security.j2ee;


import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class OmniJ2eePreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter
{

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request)
    {
        Object principal = request.getUserPrincipal() == null ? null : request.getUserPrincipal();
        if (logger.isDebugEnabled())
        {
            logger.debug("PreAuthenticated J2EE principal: " + principal);
        }
        return principal;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request)
    {
        return "N/A";
    }

}
