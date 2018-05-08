package com.omniselling.common.enumeration;

import com.omniselling.common.ErrorCodeEnum;

public enum MsBillingErrorCode implements ErrorCodeEnum
{
    /** 参数转换错误 **/
    BILLING_PARAM_FORMAT_ERROR,
    /** 无计费配置错误 **/
    NO_BILLING_CONFIG_ERROR,
    /** 计费配置错误 **/
    EXCESS_BILLING_CONFIG_ERROR,
    /** 计费算法策略未返回计算结果 **/
    BILLING_STRATEGY_NOT_RETURN_VALUE,
    /** 非法的计费对象错误 **/
    BILLING_INVALID_OBJECT_ERROR,
    /** 包裹超出限制错误 **/
    BILLING_PARCEL_OVER_ERROR,
    /** 当前用户存在该计费配置 */
    CONFIG_FOR_CURRENT_USER_EXIST,
    /** 存在适用所有用户的计费配置 */
    CONFIG_FOR_ALL_USER_EXIST,
    /** 优惠活动已绑定优惠组 */
    PROMOTION_BINDED_GROUP
}
