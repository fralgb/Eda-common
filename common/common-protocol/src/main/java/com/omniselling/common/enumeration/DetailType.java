package com.omniselling.common.enumeration;

/**
 * 详细类型 model
 * 
 * @author log
 * @version 1.0
 * @created 26-十二月-2015 14:35:51
 */
public enum DetailType
{
    WEIGHT_BASE, /** 基础费用 */
    WEIGHT_RANGE, /** 重量范围 */
    WEIGHT_OVER, /** 续重类型 */
    DAY_RANGE, /** 日期范围 */
    MAX_BILLING, /** 最高收费 */
    MIN_BILLING, /** 最低收费 */
    UNIT_BILLING /** 单位计费 */
}