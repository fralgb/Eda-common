package com.omniselling.app.base.web.model;

/**
 * 用户登录请求参数model
 *
 */
public class ViewAccountSignin
{

    /**
     * 用户登录名
     * email 或 loginId
     */
    private String loginName;
    
    /**
     * 用户密码
     */
    private String password;
    
    /**
     * 登录验证码
     */
    private String verifyCode;
    
    /**
     * 页面显示语言
     */
    private String language;

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getVerifyCode()
    {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode)
    {
        this.verifyCode = verifyCode;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

}
