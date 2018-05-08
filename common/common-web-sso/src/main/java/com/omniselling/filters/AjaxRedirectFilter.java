package com.omniselling.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AjaxRedirectFilter implements Filter
{
	
	private Logger logger = LogManager.getLogger(getClass());

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        // TODO Auto-generated method stub
        /**
         * 将客户端的源响应流包装成新的实例
         * 给后面的filter处理
         * 后面的filter处理完成后，如果是302(redirect)状态
         * 则改写掉源响应流的status为200,并返回自定义的code和redirect url
         */
        CustomWrapperedResponse wrapResponse = new CustomWrapperedResponse((HttpServletResponse)response); 
        chain.doFilter(request, wrapResponse);
        
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        String requestType = httpRequest.getHeader("x-requested-with");
        String requestType2 = this.getRequestTypeFormAccessControlRequestHeaders(httpRequest);
        
        //如果是redirect
        if(wrapResponse.getSendRedirect()){
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            
            //非跨域情况获取 XMLHttpRequest
            if((requestType != null && "XMLHttpRequest".equals(requestType)) 
                    
                    //跨域的情况下是不能获取到 XMLHttpRequest，获取自定义的请求标志判断
                    || (requestType2 != null && "x-requested-with".equals(requestType2))){
                
                String responseJson = "{\"code\" : \"redirect\", \"url\" : \"" + wrapResponse.getRedirectLoction() + "\"}";
                httpResponse.setStatus(200);
                httpResponse.setContentType("application/json;charset=UTF-8");
                httpResponse.setHeader("Cache-Control", "no-cache");
                httpResponse.setHeader("Cache-Control", "no-store");
                httpResponse.setHeader("Location", null);
                httpResponse.setContentLength(responseJson.length());
                
                httpResponse.setContentLength(responseJson.getBytes("UTF-8").length);
                ServletOutputStream out = httpResponse.getOutputStream(); 
                out.write(responseJson.getBytes("UTF-8"));
                out.flush();
                
            }else {
                httpResponse.sendRedirect(wrapResponse.getRedirectLoction());
            }
        }
    }
    
    /**
     * 从 Access-Control-Request-Headers 请求头， 获取ajax请求标志
     * @param httpRequest
     * @return
     */
    private String getRequestTypeFormAccessControlRequestHeaders(HttpServletRequest httpRequest){
        String headerStr = httpRequest.getHeader("Access-Control-Request-Headers");
        if(headerStr != null){
            String[] headerArray = headerStr.split(",");
            for(String s : headerArray){
                s = s.trim();
                if("x-requested-with".equalsIgnoreCase(s)){
                    return s;
                }
            }
        }
        return null;
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException
    {
    	logger.debug("AjaxRedirectFilter 初始化......");
    }
    
    
    
    
    
    
    
    /**
     * 内部类
     * @author LiZhongZhi
     *
     */
    protected class CustomWrapperedResponse extends HttpServletResponseWrapper { 
        private Boolean sendRedirect =false;
        private String redirectLoction = "";
        public CustomWrapperedResponse(HttpServletResponse resp) throws IOException { 
            super(resp); 
        } 
        
        @Override
        public void sendRedirect(String location) throws IOException
        {
            this.sendRedirect = true;
            this.redirectLoction = location;
        }

        public Boolean getSendRedirect()
        {
            return sendRedirect;
        }

        public void setSendRedirect(Boolean sendRedirect)
        {
            this.sendRedirect = sendRedirect;
        }

        public String getRedirectLoction()
        {
            return redirectLoction;
        }

        public void setRedirectLoction(String redirectLoction)
        {
            this.redirectLoction = redirectLoction;
        }
    } 

}
