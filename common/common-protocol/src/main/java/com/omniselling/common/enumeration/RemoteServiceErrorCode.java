package com.omniselling.common.enumeration;

import com.omniselling.common.ErrorCodeEnum;

/**
 * 远程微服务调用错误代码
 *
 */
public enum RemoteServiceErrorCode implements ErrorCodeEnum
{
    /** 未识别的微服务名称 */
    SERVICE_NAME_UNIDENTIFIED,
    /** 微服务未找到 */
    SERVICE_NOT_FOUND,
    /** 服务请求被禁止  4XX*/
    SERVICE_FORBIDDEN,
    /** 微服务请求Url错误 */
    SERVICE_REQUESTURL_ERROR,
    /** 服务连接超时  */
    SERVICE_CONNECT_TIMEOUT,
    /** 服务请求内部错误 5XX  */
    SERVICE_INTERNAL_ERROR,
    /** 服务返回数据转换错误 */
    REPSONSE_DATA_TRANS_ERROR,
    /** 请求参数转换错误 */
    REQUEST_PARAM_TRANS_ERROR,
    /** 请求参数为空 */
    REQUEST_PARAM_EMPTY,
    /** 客户端协议错误 */
    CLIENT_PROTOCOL_ERROR,
    /** 客户端连接IO错误，连接被中断 */
    SERVICE_CONNECTION_IOERROR,
    /** 未能识别的错误 */
    UNKNOWN_ERROR,
    
}
