package com.omniselling.app.base.web.service;

import com.omniselling.app.base.web.model.ViewSessionUser;
import com.omniselling.common.model.ServerResponse;

public interface ViewAuthService
{
    public ServerResponse<ViewSessionUser> getSessionUser(String email, String password, String language, String ip);
    
    public String signout(String loginId, String ip);
    
    public Integer countFailedLogin(String loginId);
}
