package com.omniselling.app.base.web.service;

import java.util.List;

import com.omniselling.app.base.web.model.ViewAccountRole;
import com.omniselling.app.base.web.model.ViewAppResource;
import com.omniselling.app.base.web.model.ViewSecAccount;
import com.omniselling.app.base.web.model.ViewSessionAccount;
import com.omniselling.common.model.BaseResponse;

/**
 * 资源权限接口
 * @author log
 * @version 1.0
 */
public interface ViewSecurityService {

	/**
	 * 获取系统所有角色
	 */
	public BaseResponse<List<ViewAccountRole>> listAllRoles();

	/**
	 * 根据角色获取资源
	 * 
	 * @param roleId
	 */
	public BaseResponse<List<ViewAppResource>> listAppResByRoleId(Long roleId);
	
	/**
     * 根据用户登录账号获取用户资源权限
     * 
     * @param loginId
     */
    public BaseResponse<ViewSecAccount> getSecAccountByLoginId(String loginId);
    
    /**
     * 根据登录用户获取账号信息
     * @param loginId
     * @return
     */
    public BaseResponse<ViewSessionAccount> getViewSessionAccount(String loginId,String operationId,String language);

}