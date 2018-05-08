package com.omniselling.common.protocol;

/**
 * 对应用户终端发出的请求数据包
 */

public class ClientRequest<T> extends RequestWrapper<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户端访问token
	 */
	private String nonceToken;

	/**
	 * 操作者id 必填
	 */
	private Long operatorId;

	/**
	 * 操作者登录名
	 * 
	 */
	private String loginId;

	/**
	 * 操作语言
	 */
	private String language;

	/**
	 * 是否是异步请求
	 */
	private Boolean asyncRequest = new Boolean(false);

	public String getNonceToken() {
		return nonceToken;
	}

	public void setNonceToken(String nonceToken) {
		this.nonceToken = nonceToken;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getAsyncRequest() {
		return asyncRequest;
	}

	public void setAsyncRequest(Boolean asyncRequest) {
		this.asyncRequest = asyncRequest;
	}

}
