package com.omniselling.common;

import com.omniselling.common.enumeration.LanguageCode;

/**
 * 
 * 系统全局变量定义
 */
public class Constant
{
    /**
     * session账号
     */
    final public static String SESSIONUSER = "viewSessionAccount";
    /**
     * 系统管理员id
     */
    final public static Long SystemOperatorId = -99999L;
    /**
     * 默认语言
     */
    final public static LanguageCode DefaultLanguage = LanguageCode.EN_US;
    /**
     * 公用系统名称 用于公共资源访问
     */
    final public static String CommonAppName = "common";

    final public static String DEFAULT_PHONE_NUMBER = "000-000-0000";

    final public static int MicrosecondsInSec = 1000;
    final public static int SecondsInMinute = 60;
    final public static int MinutesInHour = 60;
    final public static int HoursInDay = 24;
    final public static int MicrosecondsInHour = 1000 * 60 * 60;
    final public static String DateTimeFormatString = "yyyy-MM-dd HH:mm:ss";
    final public static String DateTimeENUSFormatString = "HH:mm:ss MMM dd, yyyy z";
    final public static String DateFormatString = "yyyy-MM-dd";
    final public static String DATE_FORMAT_STRING_YYYYMMDDTHHMMSS = "yyyy-MM-dd'T'HH:mm:ss";
    final public static String DateFormatSelectString = "yyyy/MM/dd";
    final public static String DateHMFormatString = "yyyy-MM-dd HH:mm";

    final public static String ShortDateFormatString = "MMM-dd-yy";

    public static final String ShortDateFormatStringBeginDate = "dd-MMM-yy";
    public static String TsvSuffix = ".tsv";
    public static String CsvSuffix = ".csv";
    public static String TxtSuffix = ".txt";
    public static String xlsSuffix = ".xls";
    public static String xlsxSuffix = ".xlsx";
    public static String pdfSuffix = ".pdf";
    public static final double KG_PER_LB = 2.20462;
    public static final double KG_PER_OZS = 35.27396;
    public static final double INCH_PER_CM = 0.393701;
    public static final double FT_PER_CM = 0.0328084;

    public static String ApplicationContextPath;
    public static String ENCODING = "GBK";

    public final static String FBA_PACKAGELABELS_NAME = "fbalabels";
    public final static String FBA_FILE_TYPE_ZIP = ".zip";
    public final static String FBA_FILE_TYPE_PDF = ".pdf";

    public final static Integer exportMaxLimit = 60000;
    public final static Integer exportDefaultLimit = 30;

    public final static String ENUMRATION_PREFIX = "Enum.";
    public static final String WAREHOUSE_ISSUE_TYPE = "WRHSISSUE";

    public final static String DASHBOARD_SPLIT = "|";
    public final static String REGEX_POSITIVE_INTEGER_NOT_EQUAL_TO_ZERO = "^[1-9]+[0-9]*$";

    public final static String DATASOURCE_NAME = "bizDataSource";
}
