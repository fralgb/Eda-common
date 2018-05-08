package com.omniselling.app.base.web.service;

import com.omniselling.app.base.web.model.ViewSessionAccount;
import com.omniselling.common.model.BaseResponse;

/**
 * 
 * 用户登录服务
 *
 */
public interface ViewAccountSigninService
{
    /**
     * 通过登录名和密码获取用户信息
     * 用于登录验证
     * @param loginName 登录名（登录id或注册电子邮件）
     * @param password 密码
     * @param language 登录界面语言
     * @param ip 登录者客户端ip地址
     * @return
     */
    public BaseResponse<ViewSessionAccount> authAccount(String loginName, String password,
            String language, String ip);

    /**
     * 退出登录
     * @param loginId 用户登录id
     * @param ip
     * @return
     */
    public BaseResponse<Boolean> signout(String loginId, String ip);

    /**
     * 获得登录失败次数
     * @param loginName 登录名（登录id或注册电子邮件）
     * @param ip 登录者客户端ip地址
     * @return
     */
    public BaseResponse<Integer> countFailedLogin(String loginName, String ip);

}
