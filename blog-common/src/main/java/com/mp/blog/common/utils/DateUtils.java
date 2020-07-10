package com.mp.blog.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author tuchuan
 * @description
 * @date 2017/7/6 14:41
 */
public class DateUtils {
    private static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    private static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    private static final String yyyy_MM_dd = "yyyy-MM-dd";
    private static final String yyyy_MM = "yyyy-MM";


    /**
     * 将日期转为字符串 yyyy-mm-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String yyyy_MM_dd_HH_mm_ss(Date date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(yyyy_MM_dd_HH_mm_ss);
        LocalDateTime localDateTime = dateToDateTime(date);
        return formatter.format(localDateTime);
    }

    /**
     * 将日期转为字符串 yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String yyyyMMddHHmmss(Date date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(yyyyMMddHHmmss);
        LocalDateTime localDateTime = dateToDateTime(date);
        return formatter.format(localDateTime);
    }

    /**
     * date 转LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    /**
     * date 转LocalDate
     *
     * @param localDate
     * @return
     */
    public static Date localToDate(LocalDate localDate) {
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }

    /**
     * date 转DateTime
     *
     * @param date
     * @return
     */
    public static LocalTime dateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        LocalTime localTime = instant.atZone(ZoneId.systemDefault()).toLocalTime();
        return localTime;
    }

    /**
     * date 转LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToDateTime(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime;
    }

    /**
     * date 转LocalTime
     *
     * @param localTime
     * @return
     */
    public static Date localToDate(LocalTime localTime) {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }


}
