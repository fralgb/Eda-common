package com.omniselling.common.util;

import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;

import com.omniselling.common.Constant;

public final class TimeFormatterUtil
{

    /**
     * 将指定格式的字符串转换为日期
     * 
     * @param date
     *            日期字符串
     * @param pattern
     *            格式
     * @param timeZone
     *            时区
     * @return 转换后的日期
     */
    public static Date convertToDate(String date, String pattern, TimeZone timeZone)
    {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(pattern))
        {
            return null;
        }
        DateTime dateTime = DateTimeFormat.forPattern(pattern).withZone(DateTimeZone.forTimeZone(timeZone))
                .parseDateTime(date);
        return dateTime.toDate();
    }

    /**
     * 根据指定格式转换日期
     * 
     * @param date
     *            需要转换的日期
     * @param pattern
     *            转换的格式
     * @param timeZone
     *            时区
     * @return 转换后的字符串
     */
    public static String convertToString(Date date, String pattern, TimeZone timeZone)
    {
        if (date == null || StringUtils.isEmpty(pattern))
        {
            return null;
        }
        String dateString = new DateTime(date.getTime()).withZone(DateTimeZone.forTimeZone(timeZone)).toString(pattern);
        return dateString;
    }

    /**
     * 将指定格式的字符串转换为日期
     * 
     * @param date
     *            日期字符串
     * @param pattern
     *            格式
     * @return 转换后的日期
     */
    public static Date convertToDate(String date, String pattern)
    {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(pattern))
        {
            return null;
        }
        DateTime dateTime = DateTimeFormat.forPattern(pattern).parseDateTime(date);
        return dateTime.toDate();
    }

    /**
     * 根据指定格式转换日期
     * 
     * @param date
     *            需要转换的日期
     * @param pattern
     *            转换的格式
     * @return 转换后的字符串
     */
    public static String convertToString(Date date, String pattern)
    {
        if (date == null || StringUtils.isEmpty(pattern))
        {
            return null;
        }
        String dateString = new DateTime(date.getTime()).toString(pattern);
        return dateString;
    }

    /**
     * 将字符串转换为日期
     * 
     * @param date
     *            格式 yyyy-MM-dd HH:mm:ss
     * @return 日期
     */
    public static Date convertToYYYYMMDDHHMMSS(String date)
    {

        return convertToDate(date, Constant.DateTimeFormatString);

    }

    /**
     * 将字符串转换为日期
     * 
     * @param date
     *            格式 HH:mm:ss MMM dd, yyyy z
     * @return 日期
     */
    public static Date convertToENUSDateTime(String date)
    {
        return convertToDate(date, Constant.DateTimeENUSFormatString);
    }

    /**
     * 将字符串转换为日期
     * 
     * @param date
     *            格式 yyyy-MM-dd
     * @return 日期
     */
    public static Date convertToYYYYMMDD(String date)
    {

        return convertToDate(date, Constant.DateFormatString);
    }

    /**
     * 将字符串转换为日期
     * 
     * @param date
     *            格式 yyyy/MM/dd
     * @return 日期
     */
    public static Date convertToYYYYMMDDWithSlant(String date)
    {
        return convertToDate(date, Constant.DateFormatSelectString);
    }

    /**
     * 添加天数，并将 时、分、秒设置为0
     * 
     * @param date
     *            日期
     * @param day
     *            天数
     * @return 新的日期
     */
    public static Date convertToYMDOOODate(Date date, int day)
    {
        DateTime dateTime = new DateTime(date.getTime()).plusDays(day).withHourOfDay(0).withMinuteOfHour(0)
                .withSecondOfMinute(0);
        return dateTime.toDate();
    }

    /**
     * 把时间转换为字符串 格式：yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     *            需要转换的日期
     * @return 转换后的字符串
     */
    public static String convertToYYYYMMDDHHMMSS(Date date)
    {
        return convertToString(date, Constant.DateTimeFormatString);
    }

    /**
     * 把时间转换为字符串 格式：yyyy-MM-dd HH:mm
     * 
     * @param date
     *            需要转换的日期
     * @return 转换后的字符串
     */
    public static String convertToDateHMString(Date date)
    {

        return convertToString(date, Constant.DateHMFormatString);
    }

    /**
     * 把时间转换为字符串 格式：yyyy-MM-dd
     * 
     * @param date
     *            需要转换的日期
     * @return 转换后的字符串
     */
    public static String convertToDateYMDString(Date date)
    {
        return convertToString(date, Constant.DateFormatString);
    }

    /**
     * 获取months个月以前的时间，并且小时、分钟、秒、毫秒设置为0
     * 
     * @param months
     *            多少个月
     * @return 新的日期
     */
    public static Date getBeforeMonthDate(int months)
    {
        DateTime date = new DateTime().minusMonths(months).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
                .withMillisOfSecond(0);

        return date.toDate();
    }

    /**
     * 获取months个月以前的时间字符串，并且小时、分钟、秒、毫秒设置为0
     * 
     * @param months
     *            多少个月
     * @return 新的日期字符串 格式为：yyyy-MM-dd HH:mm:ss
     */
    public static String getBeforeMonthDateString(int months)
    {
        Date date = getBeforeMonthDate(months);
        return convertToYYYYMMDDHHMMSS(date);
    }

    /**
     * 获取指定时间的小时
     * 
     * @param date
     *            指定的时间
     * @return 小时
     */
    public static Integer getHours(Date date)
    {
        return new DateTime(date.getTime()).getHourOfDay();
    }

    /**
     * 给指定的日期添加指定的小时数
     * 
     * @param date
     *            指定的日期
     * @param hours
     *            指定的小时数
     * @return 新的日期
     */
    public static Date addDateByHours(Date date, int hours)
    {
        return new DateTime(date.getTime()).plusHours(hours).toDate();
    }

    /**
     * 计算两个日期相差的天数
     * 
     * @param beginTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return 相差的天数
     */
    public static Integer daysBetween(Date beginTime, Date endTime)

    {
        DateTime begin = new DateTime(beginTime.getTime());
        DateTime end = new DateTime(endTime.getTime());
        return Days.daysBetween(begin, end).getDays();
    }

    /**
     * 时间减去
     * 
     * @param date
     *            日期
     * @param day
     *            减去的天数
     * @return 新的时间
     */
    public static Date subtractDay(Date date, int day)
    {
        return new DateTime(date).minusDays(day).toDate();
    }

    /**
     * 時間大小比較，返回大值
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date timeCompare(Date beginDate, Date endDate)
    {

        int result = beginDate.compareTo(endDate);
        if (result == 0)
        {
            return beginDate;
        }
        else if (result < 0)
        {
            return endDate;
        }
        else
        {
            return beginDate;
        }
    }

    /**
     * 将日期转换为字符串 格式：yyyy-MM-dd'T'HH:mm:ss
     * 
     * @param date
     *            需要转换的日期
     * @return 转换后的字符串 yyyy-MM-dd'T'HH:mm:ss
     */
    public static String convertToYYYYMMDDTHHMMSS(Date date)
    {
        return convertToString(date, Constant.DATE_FORMAT_STRING_YYYYMMDDTHHMMSS);
    }

}
