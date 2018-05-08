package com.omniselling.app.base.common;

import com.omniselling.common.Enumeration;

/**
 * 
 * 系统资源类型
 *
 */
public enum AppResourceType implements Enumeration
{
    /** 菜单资源   */
    APP_MENU, 
    /** url资源 */
    APP_URL, 
    /** 页面元素资源 */
    APP_ELEMENT, 
    /** 业务权限定义资源 */
    APP_SPEC;
}
