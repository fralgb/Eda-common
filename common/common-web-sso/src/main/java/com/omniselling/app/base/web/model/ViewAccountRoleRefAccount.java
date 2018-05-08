package com.omniselling.app.base.web.model;

import java.util.List;

public class ViewAccountRoleRefAccount
{
	/**
	 * 账户角色id
	 */
	private Long accountRoleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 用户列表
	 */
	private List<ViewAccount> appAccounts;

	@Override
	public String toString() {
		return "ViewAcountRoleRefAccount [accountRoleId=" + accountRoleId
				+ ", roleName=" + roleName + ", appAccounts=" + appAccounts
				+ "]";
	}

	public Long getAccountRoleId() {
		return accountRoleId;
	}

	public void setAccountRoleId(Long accountRoleId) {
		this.accountRoleId = accountRoleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<ViewAccount> getAppAccounts() {
		return appAccounts;
	}

	public void setAppAccounts(List<ViewAccount> appAccounts) {
		this.appAccounts = appAccounts;
	}

}
