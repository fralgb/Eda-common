package com.omniselling.common.enumeration;

import com.omniselling.common.ErrorCodeEnum;

/**
 * core微服务错误代码
 *
 */
public enum MsCoreErrorCode implements ErrorCodeEnum
{

    /**
     * 账号服务错误
     */

    /** 账号冲突，loginId或email存在重复 */
    ACCOUNT_LOGINID_EMAIL_CONFLICT,
    /** 账号创建存在错误 */
    ACCOUNT_CREATE_DATA_ERROR,
    /** 账号数据更新存在错误 */
    ACCOUNT_UPDATE_DATA_ERROR,
    /** 账号loginId修改错误 */
    ACCOUNT_LOGIDID_MODIFY_ERROR,
    /** 账号email修改错误 */
    ACCOUNT_EMAIL_PARAM_INVALID,
    /** 账号不存在 */
    ACCOUNT_EMPTY_ERROR,
    /** 账号禁用错误 */
    ACCOUNT_DISABLE_ERROR,

    // /////////////////////////////////////////////
    /**
     * 账号角色服务错误
     */
    /** 账号角色冲突，角色名l存在重复 */
    ACCOUNTROLE_NAME_CONFLICT,
    /** 可添加角色为空 */
    ACCOUNTTOLE_ADD_ROLES_IS_EMPTY,
    /** 可添加账号为空 */
    ACCOUNTTOLE_ADD_ACCOUNTS_IS_EMPTY,
    /** 账号角色为空 */
    ACCOUNTTOLE_ROLE_IS_EMPTY,

    ///////////////////////////////////////////////

    /**
     * 应用系统资源服务错误
     */
    APPRES_NAME_CONFLICT,
    /** 数据已存在数据库中 */
    APPRES_CREATE_DATA_EXIST,
    /** 参数无效 */
    APPRES_PARAMETER_INVALID,
    /** 父资源类型错误 */
    APPRES_PARAMETER_TYPE_INVALID,
    /** 移除失败 */
    APPRES_REMOVE_DATA_ERROR,
    // /////////////////////////////////////////////

    /**
     * 公共资源服务错误
     */
    PUBRES_OBJECT_CONFLICT, 
    PUBRES_SHOW_ERROR,
    PUBRES_COUNT_ERROR,
    ///////////////////////////////////////////////

    /**
     * 国际化I18n资源服务错误
     */
    PUBRES_RESKEY_CONFLICT,
    ///////////////////////////////////////////////

    /**
     * 应用系统资源权限服务错误
     */
    APPRESPERMIT_NAME_CONFLICT,
    /** 参数无效 */
    APPRESPERMIT_PARAMETER_INVALID,
    /** 数据已存在 */
    APPRESPERMIT_CREATE_DATA_EXIST,
    // /////////////////////////////////////////////

    /**
     * 公共资源权限服务错误
     */

    ///////////////////////////////////////////////

}
