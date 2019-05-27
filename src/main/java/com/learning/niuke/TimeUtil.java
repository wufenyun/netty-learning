package com.learning.niuke;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> 时间工具类
 *
 * @author weigang
 */
public class TimeUtil {

    /**
     * yyyy-MM-dd
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * <p> 创建一个{@link #YYYY_MM_DD_HH_MM_SS}格式的DateTimeFormatter
     *
     * @return DateTimeFormatter
     */
    public static DateTimeFormatter getYyyyMmDdHhMmSs() {
        return DateTimeFormat.forPattern(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * <p> 创建一个dateFormat格式的DateTimeFormatter
     *
     * @param dateFormat 时间格式
     * @return DateTimeFormatter
     */
    public static DateTimeFormatter getDateFormat(String dateFormat) {
        return DateTimeFormat.forPattern(dateFormat);
    }

    /**
     * <p> 判断一个字符串是否是{@link #YYYY_MM_DD_HH_MM_SS}时间格式
     *
     * @param dateTime {@link #YYYY_MM_DD_HH_MM_SS}格式的时间
     * @return 是则返回true，反之false
     */
    public static boolean isDate(String dateTime) {
        try {
            getYyyyMmDdHhMmSs().parseDateTime(dateTime);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <p> 判断一个字符串是否是{@link #YYYY_MM_DD}时间格式
     *
     * @param dateTime {@link #YYYY_MM_DD}格式的时间
     * @return 是则返回true，反之false
     */
    public static boolean isDay(String dateTime) {
        try {
            new SimpleDateFormat(YYYY_MM_DD).parse(dateTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * <p> 判断一个字符串是否是dateFormat时间格式
     *
     * @param dateTime   dateFormat格式的时间
     * @param dateFormat 时间格式
     * @return 是则返回true，反之false
     */
    public static boolean isDate(String dateTime, String dateFormat) {
        try {
            getDateFormat(dateFormat).parseDateTime(dateTime);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <p> 计算时间差
     *
     * @param before 前一个时间
     * @param after  后一个时间
     * @return 时间差
     */
    public static long dateDiff(long before, long after) {
        return after - before;
    }

    /**
     * <p> 计算时间差
     *
     * @param before 前一个时间
     * @param after  后一个时间
     * @param unit   时间单位，毫秒的倍数
     * @return 根据时间单位返回时间差
     */
    public static long dateDiff(long before, long after, long unit) {
        return (after - before) / unit;
    }

    /**
     * <p> 取当前时间的10位时间戳
     * 当前时间超过2038/01/19 11:14:07时，返回0
     *
     * @return 10位时间戳
     */
    public static int getTime() {
        int time = (int) (System.currentTimeMillis() / 1000);
        if (time < 0) {
            return 0;
        }
        return time;
    }

    /**
     * <p> 取得{@link #YYYY_MM_DD_HH_MM_SS}格式的当前时间字符串
     *
     * @return 时间字符串
     */
    public static String getCurrentDate() {
        return DateTime.now().toString(getYyyyMmDdHhMmSs());
    }

    /**
     * <p> 取得dateFormat格式的当前时间字符串
     *
     * @param dateFormat 时间格式
     * @return 时间字符串
     */
    public static String getCurrentDate(String dateFormat) {
        return DateTime.now().toString(getDateFormat(dateFormat));
    }

    /**
     * <p> 取得dateFormat格式的当前时间字符串
     *
     * @param dateFormat 时间格式
     * @return 时间字符串
     */
    public static Date getCurrentByDate(String dateFormat) {
        return new DateTime(DateTime.now().toString(getDateFormat(dateFormat))).toDate();
    }

    /**
     * <p> 以{@link #YYYY_MM_DD_HH_MM_SS}格式化时间字符串，当字符串不符合格式时返回null
     *
     * @param dateTime {@link #YYYY_MM_DD_HH_MM_SS}格式的时间
     * @return 格式化的时间
     */
    public static Date formatDate(String dateTime) {
        try {
            return getYyyyMmDdHhMmSs().parseDateTime(dateTime).toDate();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * <p> 以dateFormat格式化时间字符串，当字符串不符合格式时返回null
     *
     * @param dateTime   dateFormat格式的时间
     * @param dateFormat 时间格式
     * @return 格式化的时间
     */
    public static Date formatDate(String dateTime, String dateFormat) {
        try {
            return getDateFormat(dateFormat).parseDateTime(dateTime).toDate();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * <p> 以{@link #YYYY_MM_DD_HH_MM_SS}格式化long型时间
     *
     * @param dateTime long时间
     * @return 格式化的时间
     */
    public static String formatDate(long dateTime) {
        return new DateTime(dateTime).toString(getYyyyMmDdHhMmSs());
    }

    /**
     * <p> 以dateFormate格式化long型时间
     *
     * @param datetime   long时间
     * @param dateFormat 时间格式
     * @return 格式化的时间
     */
    public static String formatDate(long datetime, String dateFormat) {
        return new DateTime(datetime).toString(getDateFormat(dateFormat));
    }

    /**
     * <p> 以{@link #YYYY_MM_DD_HH_MM_SS}格式化时间
     *
     * @param dateTime 时间
     * @return 格式化的时间
     */
    public static String formatDate(Date dateTime) {
        return new DateTime(dateTime).toString(getYyyyMmDdHhMmSs());
    }

    /**
     * <p> 以dateFormat格式化时间
     *
     * @param dateTime   时间
     * @param dateFormat 时间格式
     * @return 格式化的时间
     */
    public static String formatDate(Date dateTime, String dateFormat) {
        return new DateTime(dateTime).toString(getDateFormat(dateFormat));
    }

    /**
     * 计算今日还剩的秒数
     *
     * @return 秒数
     */
    public static int computeTodayRemainSeconds() {
        DateTime dateTime = DateTime.now();
        DateTime withMaximumValue = dateTime.millisOfDay().withMaximumValue();
        return (int) ((withMaximumValue.getMillis() - dateTime.getMillis()) / 1000);
    }

    /**
     * 传入时间
     *
     * @return 秒数
     */
    public static Integer getSecondsByDate(Date date) {
        return (int) ((new DateTime(date).getMillis()) / 1000);
    }

    /**
     * 传入时间秒数
     *
     * @return 秒数
     */
    public static Date getDateBySeconds(Integer second) {
        return new DateTime(Long.valueOf(second) * 1000).toDate();
    }

}
