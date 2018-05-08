package com.omniselling.common.enumeration;

import com.omniselling.common.ErrorCodeEnum;

public enum MsAppErrorCode implements ErrorCodeEnum
{
    /**
     * 账户
     */
    /** 查询黑白名单 */
    ACCOUNT_LISTRESOURCEPERMITTYPE_ERROR,
    /** 定义校验错误，长度和null判断*/
    ACCOUNT_LOGINID_EMPTY,
    ACCOUNT_LOGINID_TOOLONG,
    ACCOUNT_ACCOUNTNAME_EMPTY,
    ACCOUNT_ACCOUNTNAME_TOOLONG,
    ACCOUNT_PASSWORD_TOOLONG,
    ACCOUNT_EMAIL_EMPTY,
    ACCOUNT_EMAIL_TOOLONG,
    
    /**
     * 角色
     */
    /** 定义校验错误，长度和null判断 */
    ACCOUNTROLE_ROLENAME_EMPTY,
    ACCOUNTROLE_ROLENAME_TOOLONG,
    ACCOUNTROLE_ROLESTATUS_EMPTY,
    ACCOUNTROLE_ROLESTATUS_TOOLONG,
    ACCOUNTROLE_ROLEDESCRIPTION_EMPTY,
    ACCOUNTROLE_ROLEDESCRIPTION_TOOLONG,
    /**
     * appResource
     */
    /** 定义校验错误，长度和null判断 */
    APPRESOURCE_RESOURCENAME_EMPTY,
    APPRESOURCE_RESOURCENAME_TOOLONG,
    APPRESOURCE_APPLICATIONNAME_EMPTY,
    APPRESOURCE_APPLICATIONNAME_TOOLONG,
    APPRESOURCE_RESOURCETYPE_EMPTY,
    APPRESOURCE_RESOURCETYPE_TOOLONG,
    APPRESOURCE_RESOURCEURL_TOOLONG,
    APPRESOURCE_RESOURCEGROUP_TOOLONG,
    
    /**
     * pubResource
     */
    /** 定义校验错误，长度和null判断 */
    PUBRES_RESOURCETYPE_EMPTY,
    PUBRES_RESOURCETYPE_TOOLONG,
    PUBRES_RESOURCENAME_EMPTY,
    PUBRES_RESOURCENAME_TOOLONG,
    PUBRES_PUBLISHNUM_EMPTY,
    PUBRES_PUBLISHNUM_TOOLONG,
    PUBRES_RESOURCESTATUS_EMPTY,
    PUBRES_RESOURCESTATUS_TOOLONG,
    PUBRES_PUBLISHDATE_EMPTY,
    PUBRES_PUBLISHDATE_TOOLONG,
    PUBRES_INVALIDDATE_EMPTY,
    PUBRES_INVALIDDATE_TOOLONG,
    PUBRES_OWNERID_EMPTY,
    /**
     * i18n
     */
    /** 定义校验错误，长度和null判断*/
    I18N_APPLICATIONNAME_EMPTY, 
    I18N_APPLICATIONNAME_TOOLONG, 
    I18N_RESOURCEKEY_EMPTY, 
    I18N_RESOURCEKEY_TOOLONG, 
    I18N_RESOURCEVALUE_EMPTY, 
    I18N_RESOURCEVALUE_TOOLONG, 
    I18N_I18NRESOURCEITEMS_EMPTY,

    /////////////////////////////////////////////

    /**
     * 公共资源
     */
    /** 查询公共资源 */
    PUBRES_LISTPUBRESOURCES_ERROR,
    /** 查询公共资源 */
    PUBRES_LISTPUBRESOURCESBYCOND_ERROR,
    /** 激活公共资源根据公共资源编号 */
    PUBRES_ACTIVEPUBRESOURCESBYID_ERROR,
    /** 禁用公共资源根据公共资源编号 */
    PUBRES_DISABLEPUBRESOURCESBYID_ERROR,
    /** 删除账户公共资源权限 */
    PUBRES_DELETEACCPUBRESPERMITBYIDS_ERROR,
    /** 添加账户公共资源权限 */
    PUBRES_ADDACCOUNTPUBRES_ERROR,
    /** 查询公共资源根据公共资源编号 */
    PUBRES_GETPUBRESOURCEBYID_ERROR,
    /** 查询公共资源类型 */
    PUBRES_LISTPUBRESOURCETYPES_ERROR,
    /** 查询公共资源状态 */
    PUBRES_LISTPUBRESOURCESTATUS_ERROR,
    /** 查询账户状态 */
    PUBRES_LISTACCOUNTROLESTATUS_ERROR,
    /** 查询账户状态 */
    PUBRES_LISTACCOUNTSTATUS_ERROR,
    

    /** 修改账户下的某资源类型的账户公共资源权限 */
    PUBRES_UPDATEPERMISSIONSBYRESTYPE_ERROR,
    //勿删，预留中
    //  /**
    //     * 公共资源
    //     */
    //    /** 激活公共资源根据公共资源发布编号 */
    //    PUBRES_ACTIVEPUBRESOURCESBYPUBNUM_ERROR,
    //    /** 禁用公共资源根据公共资源发布编号 */
    //    PUBRES_DISABLEPUBRESOURCESBYPUBNUM_ERROR,
    //    /** 查询公共资源根据发布编号获取 */
    //    PUBRES_GETPUBRESOURCEBYPUBNUM_ERROR,
    //    /** 创建公共资源 */
    //    PUBRES_CREATEPUBRESOURCE_ERROR,
    //    /** 公共资源已存在 */
    //    PUBRES_REPEAT_ERROR,
    //    /** 修改公共资源 */
    //    PUBRES_UPDATEPUBRESOURCE_ERROR,
    //    /**
    //     * 公共资源服务错误
    //     */
    //    PUBRES_OBJECT_CONFLICT,
    //    /** 查询公共资源 */
    //    PUBRES_SHOW_ERROR,
    //    /** 查询公共资源记录条数 */
    //    PUBRES_COUNT_ERROR,
    //    /** 修改账户公共资源权限 */
    //    PUBRES_UPDATEPERMISSIONS_ERROR,
    //    /** 创建账户公共资源权限 */
    //    PUBRES_CREATERESPERMISSION_ERROR,
    //    /** 创建批量账户公共资源权限 */
    //    PUBRES_INSERTBATCHACCPUBRESPERMITS_ERROR,
    //    /** 查询账户公共资源权限通过账户编号 */
    //    PUBRES_LISTPERMISSIONBYACC_ERROR,
    //    /** 查询账户公共资源权限 */
    //    PUBRES_LISTACCPUBRESPERMITS_ERROR,
    //    /** 查询账户公共资源权限通过资源编号 */
    //    PUBRES_LISTPERMISSIONBYRES_ERROR,
    //    /** 删除账户公共资源权限通过账户编号 */
    //    PUBRES_REMOVEALLPERMISSIONBYACC_ERROR,
    //    /** 删除账户公共资源权限通过资源编号 */
    //    PUBRES_REMOVEALLPERMISSIONBYRES_ERROR,
    ///////////////////////////////////////////////

}
