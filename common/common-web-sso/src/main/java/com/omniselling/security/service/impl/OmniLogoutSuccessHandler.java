package com.omniselling.security.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class OmniLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler
{

    private static final String REDIRECT_URL_PARAM = "redirect_url";

    private String redirectUrl = "";

    public void setRedirectUrl(String redirectUrl)
    {
        this.redirectUrl = redirectUrl;
    }

    /**
     * Builds the target URL according to the logic defined in the main class Javadoc.
     */
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response)
    {
        String targetUrl = super.determineTargetUrl(request, response);
        String url = request.getParameter(REDIRECT_URL_PARAM);
        if (redirectUrl.contains("?"))
        {
            url = redirectUrl + "&" + REDIRECT_URL_PARAM + "=" + url;
        }
        else
        {
            url = redirectUrl + "?" + REDIRECT_URL_PARAM + "=" + url;
        }

        try
        {
            targetUrl += "?service=" + URLEncoder.encode(url, "UTF-8");
        }
        catch (final UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }

        return targetUrl;
    }
}
