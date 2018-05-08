package com.omniselling.common.enumeration;

import com.omniselling.common.ErrorCodeEnum;

/**
 * 系统定义的统一错误代码
 *
 */
public enum CommonErrorCode implements ErrorCodeEnum
{

    /** 参数错误, 方法调用传入的参数有误 */
    PARAM_ERROR,
    /** 无数据 */
    NO_DATA,
    /** 系统错误，不确定原因的错误 */
    SYSTEM_ERROR,
    /** 数据库查询记录错误 */
    DB_SELECT_ERROR,
    /** 数据库新增记录错误 */
    DB_INSERT_ERROR,
    /** 数据库删除记录错误, 字段定义错误 */
    DB_DELETE_FIELD_ERROR,
    /** 数据库删除记录错误, ID错误 */
    DB_DELETE_ID_ERROR,
    /** 数据库删除记录为空错误 */
    DB_DELETE_EMPTY_ERROR,
    /** 数据库删除记录异常错误 */
    DB_DELETE_ERROR,
    /** 数据库删除未知名错误 */
    DB_DELETE_UNKNOWN_ERROR,
    /** 数据库更新记录错误 */
    DB_UPDATE_ERROR,
    /** 数据库操作错误 */
    DB_ERROR,
    
}
