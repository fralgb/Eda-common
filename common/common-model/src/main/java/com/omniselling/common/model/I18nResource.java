package com.omniselling.common.model;

/**
 * 国际化资源信息
 *
 */
public class I18nResource 
{

	/**
	 * 国际化资源id
	 */
	private Long id;
	
	/**
	 * 应用系统名
	 */
	private String applicationName;
	
	/**
	 * 国际化语言代码
	 */
	private String languageCode;
	
	/**
	 * 资源名
	 */
	private String resourceKey;
	
	/**
	 * 资源国际化语言
	 */
	private String resourceValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	public String getResourceValue() {
		return resourceValue;
	}

	public void setResourceValue(String resourceValue) {
		this.resourceValue = resourceValue;
	}

}
