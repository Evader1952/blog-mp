package com.mp.blog.common.utils;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @author lvlu
 * @date 2019-02-26 17:46
 **/
public class DateUtils {

    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_YYYYMMDDHHMMSS_WITH_MILLSECOND = "yyyyMMddHHmmssS";
    public static final String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String PATTERN_YYYYMMDD = "yyyyMMdd";
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String PATTERN_YYYY_MM = "yyyy-MM";


    /**
     *@description(将 date 转换成指定格式的字符串)
     *@return java.lang.String
     */
    public static String formatDate(Date date, String format){
        LocalDateTime localDateTime=LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(format);

        return localDateTime.format(formatter);
    }

    /**
     *@description(是否闰年)
     *@return boolean
     */
    public static boolean isLeapYear(Date date){
        LocalDate localDate= date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.isLeapYear();
    }

    /**
     *@description(间隔天数)
     *@return long
     */
    public static long betweenDays(Date start,Date end){
        LocalDateTime nowDateTime= LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault());
        LocalDateTime thirdDateTime= LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault());
        long between = ChronoUnit.DAYS.between(nowDateTime, thirdDateTime);
        return between;
    }

    /**
     *@description(几天前的日期)
     *@return Date
     */
    public static Date getBeforeDate(Date date,Integer day){
        LocalDateTime localDate= LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime beforeDate = localDate.plusDays(day * -1);
        return Date.from(beforeDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     *@description(几月前的日期)
     *@return Date
     */
    public static Date getBeforeMonth(Date date,Integer month){
        LocalDateTime localDate= LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        localDate.plusMonths(month * -1);
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     *@description(几年前的日期)
     *@return Date
     */
    public static Date getBeforeYear(Date date,Integer year){
        LocalDateTime localDate= LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        localDate.plusMonths(year * -1);
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     *@Description(将带T 的字符串，转成 date类型)
     *@return java.util.Date
     * 请使用 org.apache.commons.lang3.time.FastDateFormat ,更加方便
     */
    @Deprecated
    public static Date parseDate(String str){
        TemporalAccessor date = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.systemDefault()).parse(str);

        return Date.from(Instant.from(date));
    }

    public static Date parseDate(String source,String pattern){
        try {
            return org.apache.commons.lang3.time.FastDateFormat.getInstance(pattern).parse(source);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(getBeforeDate(new Date(),-30));
    }
}
