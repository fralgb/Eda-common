package com.omniselling.app.base.common;

/**
 * 
 * 微服务地址配置常量
 *
 */
public class ClientServiceConfig
{
    /**
     * 微服务名配置
     */

    /** 基础微服务 */
    public static String SERVICENAME_CORESERVICE = "coreservice";

    /**
     * 账号信息微服务
     * 
     * @author wlq
     */
    /** 根据账号黑白名单 */
    public static String ACCOUNT_LISTRESOURCEPERMITTYPE = "account/listresourcepermittype";

    /** 查询账户状态 */
    public static String ACCOUNT_LISTACCOUNTSTATUS = "account/listaccountstatus";
    /** 根据条件账号查询账号列表 */
    public static String ACCOUNT_LISTACCOUNTS = "account/listaccounts";
    /** 根据id查询账号信息 */
    public static String ACCOUNT_GETACCOUNTBYID = "account/getaccountbyid";
    /** 根据loginid查询账号信息 */
    public static String ACCOUNT_GETACCOUNTBYLOGINID = "account/getaccountbyloginid";
    /** 根据id查询父账号信息 */
    public static String ACCOUNT_GETPARENTACCOUNTBYID = "account/getparentaccountbyid";
    /** 根据id查询子账号信息 */
    public static String ACCOUNT_LISTSUBACCOUNTSBYID = "account/listsubaccountsbyid";
    /** 创建账号 */
    public static String ACCOUNT_CREATEACCOUNT = "account/createaccount";
    /** 更新账号 */
    public static String ACCOUNT_UPDATEACCOUNT = "account/updateaccount";
    /** 激活账号 */
    public static String ACCOUNT_ACTIVEACCOUNT = "account/activeaccount";
    /** 禁用账号 */
    public static String ACCOUNT_DISABLEACCOUNT = "account/disableaccount";

    // //////////////////////////////////////////////
    /**
     * i18n资源服务
     * 
     * @author wlq
     */
    /** 创建i18n国际化资源 */
    public static String I18NRESOURCE_CREATEI18NRESOURCES = "i18nresource/createi18nresources";
    /** 查询i18n国际化资源信息 */
    public static String I18NRESOURCE_LISTI18NRESOURCES = "i18nresource/listi18nresources";
    /** 查询i18n国际化资源信息 */
    public static String I18NRESOURCE_LISTI18NRESOURCEKEYS = "i18nresource/listi18nresourcekeys";
    /** 根据id获取i18n国际化资源 */
    public static String I18NRESOURCE_GETI18NRESOURCEBYID = "i18nresource/geti18nresourcebyid";
    /** 根据id列表获取i18n国际化资源 */
    public static String I18NRESOURCE_LISTI18NRESBYIDS = "i18nresource/listI18nResByIds";
    /** 根据语言和资源key获取国际化资源信息 */
    public static String I18NRESOURCE_LISTI18NRESBYLANGANDKEYS = "i18nresource/listi18nresbylangandkeys";
    /** 根据语言和资源key删除国际化资源信息 */
    public static String I18NRESOURCE_DELETEI18NRESBYLANGANDKEYS = "i18nresource/deletei18nresbylangandkeys";
    /** 根据资源id列表删除国际化资源信息 */
    public static String I18NRESOURCE_DELETEI18NRESOURCESBYIDS = "i18nresource/deletei18nresourcesbyids";
    /** 更新i18n资源信息 */
    public static String I18NRESOURCE_UPDATEI18NRES = "i18nresource/updatei18nres";
    /** 根据应用系统名获取国际化资源信息 */
    public static String I18NRESOURCE_LISTI18NRESBYAPPNAMES = "i18nresource/listi18nresbyappnames";
    /** 获取国际化资源类型 */
    public static String I18NRESOURCE_LISTLANGUAGETYPES = "i18nresource/listlanguagetypes";
    /**
     * 账号角色微服务
     */
    // ==============================账号角色=============================
    /** 显示角色状态 */
    public static String ACCOUNTROLE_LISTACCOUNTROLESTATUS = "accountrole/listaccountrolestatus";
    /** 获取角色信息 */
    public static String ACCOUNTROLE_LISTROLES = "accountrole/listroles";
    /** 获取所有角色 */
    public static String ACCOUNTROLE_LISTALLROLES = "accountrole/listallroles";
    /** 激活指定角色 */
    public static String ACCOUNTROLE_ACTIVEROLES = "accountrole/activeroles";
    /** 禁用指定角色 */
    public static String ACCOUNTROLE_DISABLEROLES = "accountrole/disableroles";
    /** 创建新的角色 */
    public static String ACCOUNTROLE_CREATEROLE = "accountrole/createrole";
    /** 根据id获取角色 */
    public static String ACCOUNTROLE_GETROLEBYID = "accountrole/getrolebyid";
    /** 根据id列表获取角色列表 */
    public static String ACCOUNTROLE_LISTROLEBYIDS = "accountrole/listrolebyids";
    /** 根据角色名获取角色 */
    public static String ACCOUNTROLE_GETROLEBYNAME = "accountrole/getrolebyname";
    /** 根据角色名列表获取角色列表 */
    public static String ACCOUNTROLE_LISTROLEBYNAMES = "accountrole/listrolebynames";
    /** 移除账号对应的角色 */
    public static String ACCOUNTROLE_REMOVEROLESBYACCOUNT = "accountrole/removerolesbyaccount";
    /** 增加账号对应的角色 */
    public static String ACCOUNTROLE_ADDROLESBYACCOUNT = "accountrole/addrolesbyaccount";
    /** 移除角色对应的账号 */
    public static String ACCOUNTROLE_REMOVEACCOUNTSBYROLE = "accountrole/removeaccountsbyrole";
    /** 增加角色对应的账号 */
    public static String ACCOUNTROLE_ADDACCOUNTSBYROLE = "accountrole/addaccountsbyrole";
    /** 更新账号与角色的关系信息 */
    public static String ACCOUNTROLE_UPDATEACCOUNTROLERELATION = "accountrole/updateaccountrolerelation";
    /** 根据角色信息列表获取账号信息 */
    public static String ACCOUNTROLE_LISTACCOUNTSBYROLE = "accountrole/listaccountsbyrole";
    /** 根据账号列表获取角色信息 */
    public static String ACCOUNTROLE_LISTROLESBYACCOUNTS = "accountrole/listrolesbyaccounts";
    /** 更新角色下关联的账号信息 */
    public static String ACCOUNTROLE_UPDATEACCOUNTSBYROLE = "accountrole/updateaccountsbyrole";
    /** 更新账号下关联的角色信息 */
    public static String ACCOUNTROLE_UPDATEROLESBYACCOUNT = "accountrole/updaterolesbyaccount";

    public static String ACCOUNTROLE_UPDATEACCOUNTROLE = "accountrole/updateaccountrole";
    // ==============================账号角色=================================
    // ////////////////////////////////////////////

    /**
     * 资源权限服务
     */
    // ==========================应用资源权限==================

    /** 根据条件查询 应用资源信息 */
    public static String APPRESOURCE_LISTAPPRESOURCES = "appresource/listappresources";
    /** 根据条件查询 应用资源类型信息 */
    public static String APPRESOURCE_LISTAPPRESOURCETYPES = "appresource/listappresourcetypes";
    /** 查询 应用资源信息 */
    public static String APPRESOURCE_LISTALLAPPRESOURCES = "appresource/listallappresources";
    /** 保存应用资源信息 */
    public static String APPRESOURCE_CREATEAPPRESOURCE = "appresource/createappresource";
    /** 删除应用资源信息 */
    public static String APPRESOURCE_REMOVEAPPRESOURCES = "appresource/removeappresources";
    /** 修改应用系统资源 */
    public static String APPRESOURCE_UPDATEAPPRESOURCE = "appresource/updateappresource";
    /** 根据系统资源 id 获取资源信息 */
    public static String APPRESOURCE_GETAPPRESOURCEBYID = "appresource/getappresourcebyid";
    /** 根据多个 id ,查询系统资源信息(查询应用资源) */
    public static String APPRESOURCE_LISTAPPRESOURCEBYIDS = "appresource/listappresourcebyids";
    /** 通过应用资源名称，查询应用资源信息 */
    public static String APPRESOURCE_LISTAPPRESOURCEBYNAMES = "appresource/listappresourcebynames";
    /** 获取整个应用系统可用的菜单资源 */
    public static String APPRESOURCE_LISTALLMENURESOURCES = "appresource/listallmenuresources";
    // ==========================账号、角色资源权限信息
    /** 检查指定账号/角色是否拥有某个资源权限 */
    public static String APPRESPERMIT_CHECKRESPERMISSION = "apprespermit/checkrespermission";
    /** 创建角色对应的资源权限 */
    public static String APPRESPERMIT_CREATEROLEAPPRESPERMISSIONS = "apprespermit/createroleapprespermissions";
    /** 创建账号对应的资源权限 */
    public static String APPRESPERMIT_CREATEACCAPPRESPERMISSIONS = "apprespermit/createaccapprespermissions";
    /** 获取账号可以访问的应用资源权限 */
    public static String APPRESPERMIT_LISTALLOWAPPRESOURCESBYACC = "apprespermit/listallowappresourcesbyacc";
    /** 获取角色可以访问的应用资源权限 */
    public static String APPRESPERMIT_LISTALLOWAPPRESOURCESBYROLE = "apprespermit/listallowappresourcesbyrole";
    /** 获取指定系统资源对应的角色权限信息 */
    public static String APPRESPERMIT_LISTROLERESPERMISSIONSBYRES = "apprespermit/listrolerespermissionsbyres";
    /** 获取角色对应的角色权限信息 */
    public static String APPRESPERMIT_LISTROLERESPERMISSIONSBYROLE = "apprespermit/listrolerespermissionsbyrole";
    /** 获取账号对应的账号权限信息 */
    public static String APPRESPERMIT_LISTACCRESPERMISSIONSBYACC = "apprespermit/listaccrespermissionsbyacc";
    /** 获得角色资源权限信息 */
    public static String APPRESPERMIT_LISTROLEAPPRESPERMIT = "apprespermit/listroleapprespermit";
    /** 根据条件查询 应用资源权限类型信息 */
    public static String APPRESPERMIT_LISTPERMITTYPES = "apprespermit/listpermittypes";
    /** 获得账号资源权限信息 */
    public static String APPRESPERMIT_LISTACCAPPRESPERMIT = "apprespermit/listaccapprespermit";
    /** 根据id删除角色资源权限信息 */
    public static String APPRESPERMIT_DELETEROLEAPPPERMIT = "apprespermit/deleteroleapppermit";
    /** 根据id删除账号资源权限信息 */
    public static String APPRESPERMIT_DELETEACCAPPPERMIT = "apprespermit/deleteaccapppermit";

    // /////////////////////////////////////////////////////////////

    /** 删除公共资源权限 */
    public static String PUBRESPERMIT_REMOVEALLPERMISSIONBYACCPUBRESIDS = "pubrespermit/removeallpermissionbyaccpubresids";
    public static String PUBRESPERMIT_CREATERESPERMISSION = "pubrespermit/createrespermission";
    /** 查询公共资源权限 */
    public static String PUBRESPERMIT_LISTACCOUNTPUBRES = "pubrespermit/listaccountpubres";
    public static String PUBRESPERMIT_LISTPERMISSIONBYACC = "pubrespermit/listpermissionbyacc";
    public static String PUBRESPERMIT_LISTPERMISSIONBYRES = "pubrespermit/listpermissionbyres";
    public static String PUBRESPERMIT_REMOVEALLPERMISSIONBYACC = "pubrespermit/removeallpermissionbyacc";
    public static String PUBRESPERMIT_REMOVEALLPERMISSIONBYRES = "pubrespermit/removeallpermissionbyres";
    public static String PUBRESPERMIT_UPDATEPERMISSIONS = "pubrespermit/updatePermissions";
    public static String PUBRESPERMIT_UPDATEPERMISSIONSBYRESTYPE = "pubrespermit/updatepermissionsbyrestype";

    public static String APPRESPERMIT_REMOVEALLPERMISSIONSBYRES = "apprespermit/removeallpermissionsbyres";
    public static String APPRESPERMIT_REMOVEALLPERMISSIONSBYROLE = "apprespermit/removeallpermissionsbyrole";
    public static String APPRESPERMIT_REMOVEALLPERMISSIONSBYACC = "apprespermit/removeallpermissionsbyacc";
    public static String APPRESPERMIT_UPDATEROLEAPPRESPERMISSIONS = "apprespermit/updateroleapprespermissions";
    public static String APPRESPERMIT_UPDATEACCAPPRESPERMISSIONS = "apprespermit/updateaccapprespermissions";

    /** 显示公共资源状态 */
    public static String PUBRESOURCE_LISTPUBRESOURCESTATUS = "pubresource/listpubresourcestatus";

    /** 显示公共资源类型列表 */
    public static String PUBRESOURCE_LISTPUBRESOURCETYPES = "pubresource/listpubresourcetypes";

    /** 显示公共资源列表 */
    public static String PUBRESOURCE_LISTPUBRESOURCES = "pubresource/listpubresources";
    /** 显示公共资源 */
    public static String PUBRESOURCE_GETPUBRESOURCEBYID = "pubresource/getpubresourcebyid";
    /** 显示公共资源 */
    public static String PUBRESOURCE_GETPUBRESOURCEBYPUBNUM = "pubresource/getpubresourcebypubnum";
    /** 创建公共资源列表 */
    public static String PUBRESOURCE_CREATEPUBRESOURCE = "pubresource/createpubresource";
    /** 更新公共资源列表 */
    public static String PUBRESOURCE_UPDATEPUBRESOURCE = "pubresource/updatepubresource";
    /** 激活公共资源列表 */
    public static String PUBRESOURCE_ACTIVEPUBRESOURCESBYID = "pubresource/activepubresourcesbyid";
    /** 激活公共资源列表 */
    public static String PUBRESOURCE_ACTIVEPUBRESOURCESBYPUBNUM = "pubresource/activepubresourcesbypubnum";
    /** 禁用公共资源列表 */
    public static String PUBRESOURCE_DISABLEPUBRESOURCESBYID = "pubresource/disablepubresourcesbyid";
    /** 禁用公共资源列表 */
    public static String PUBRESOURCE_DISABLEPUBRESOURCESBYPUBNUM = "pubresource/disablepubresourcesbypubnum";

}
