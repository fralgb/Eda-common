package com.omniselling.app.base.web.model;

import java.util.List;
import java.util.Map;

import com.omniselling.common.enumeration.LanguageCode;

/**
 * 
 * 账号信息 保存到session中
 *
 */
public class ViewSessionAccount {
	/**
	 * 账号id
	 */
	private Long accountId;
	/**
	 * 账号登录id
	 */
	private String loginId;
	/**
	 * 客户编码
	 */
	private String customerCode;
	/**
	 * 仓库Id
	 */
	private Long warehouseId;
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	/**
	 * 仓库编码
	 */
	private String warehouseCode;
	/**
	 * 账号email
	 */
	private String email;

	/**
	 * 父账号id
	 */
	private Long parentId;

	/**
	 * 账号名称
	 */
	private String accountName;

	/**
	 * 当前使用语言
	 */
	private String language = LanguageCode.ZH_CN.getDbValue();
	// TODO 测试 给一个 默认语言中文
	/**
	 * 每页记录条数
	 */
	private Integer rowsPerPage;

	private Integer offSet;

	/**
	 * 是否已经认证
	 */
	private Boolean verified;

	/**
	 * 账号类型 主账号：MAIN， 子账号：SUB
	 */
	private String accountType;

	/**
	 * 快捷菜单
	 */
	private List<String> shortCuts;

	/**
	 * app 资源信息
	 */
	private List<ViewAppResource> appResources;

	/**
	 * 账号参数设置
	 */
	private Map<String, Object> accountSettingMap;
	/**
	 * 暂时添加字段(调试)
	 */
	Integer refreshRate = 300;
	Integer expiredTime = 6000;
	String unitSystem = "kms";
	String systemLang = "zh-CN";
	String packListLang = "zh-CN";
	boolean channelFulfil = true;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getParentId() {
		return parentId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public List<String> getShortCuts() {
		return shortCuts;
	}

	public void setShortCuts(List<String> shortCuts) {
		this.shortCuts = shortCuts;
	}

	public List<ViewAppResource> getAppResources() {
		return appResources;
	}

	public void setAppResources(List<ViewAppResource> appResources) {
		this.appResources = appResources;
	}

	public Map<String, Object> getAccountSettingMap() {
		return accountSettingMap;
	}

	public void setAccountSettingMap(Map<String, Object> accountSettingMap) {
		this.accountSettingMap = accountSettingMap;
	}

	public Integer getRefreshRate() {
		return refreshRate;
	}

	public void setRefreshRate(Integer refreshRate) {
		this.refreshRate = refreshRate;
	}

	public Integer getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Integer expiredTime) {
		this.expiredTime = expiredTime;
	}

	public String getUnitSystem() {
		return unitSystem;
	}

	public void setUnitSystem(String unitSystem) {
		this.unitSystem = unitSystem;
	}

	public String getSystemLang() {
		return systemLang;
	}

	public void setSystemLang(String systemLang) {
		this.systemLang = systemLang;
	}

	public String getPackListLang() {
		return packListLang;
	}

	public void setPackListLang(String packListLang) {
		this.packListLang = packListLang;
	}

	public boolean isChannelFulfil() {
		return channelFulfil;
	}

	public void setChannelFulfil(boolean channelFulfil) {
		this.channelFulfil = channelFulfil;
	}

	public Integer getOffSet() {
		return offSet;
	}

	public void setOffSet(Integer offSet) {
		this.offSet = offSet;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String toString() {
		return "ViewSessionAccount [accountId=" + accountId + ", loginId=" + loginId + ", email="
				+ email + ", parentId=" + parentId + ", accountName=" + accountName + ", language="
				+ language + ", rowsPerPage=" + rowsPerPage + ", verified=" + verified
				+ ", accountType=" + accountType + ", shortCuts=" + shortCuts + ", appResources="
				+ appResources + ", accountSettingMap=" + accountSettingMap + ", customerCode="
				+ customerCode + ", warehouseId=" + warehouseId + ", warehouseName="
				+ warehouseName + ", warehouseCode=" + warehouseCode + "]";
	}
}
