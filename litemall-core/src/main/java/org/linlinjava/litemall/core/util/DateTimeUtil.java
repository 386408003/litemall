package org.linlinjava.litemall.core.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期格式化工具类
 */
public class DateTimeUtil {

    /**
     * 格式 yyyy年MM月dd日 HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getDateTimeDisplayString(LocalDateTime dateTime) {
        String strDate = "";
        if(dateTime != null) {
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
            strDate = dtf2.format(dateTime);
        }
        return strDate;
    }

    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String localDateTimeToString(LocalDateTime dateTime) {
        String strDate = "";
        if(dateTime != null) {
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            strDate = dtf2.format(dateTime);
        }
        return strDate;
    }

    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @param dateTimeStr
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String dateTimeStr) {
        LocalDateTime localDateTime = null;
        if(dateTimeStr == null || "".equals(dateTimeStr)) {
            //localDateTime = LocalDateTime.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            localDateTime = LocalDateTime.parse(dateTimeStr, formatter);
        }
        return localDateTime;
    }

    /**
     * 格式 yyyy-MM-dd
     *
     * @param localDate
     * @return
     */
    public static String localDateToString(LocalDate localDate) {
        String strDate = "";
        if(localDate != null) {
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            strDate = dtf2.format(localDate);
        }
        return strDate;
    }

    /**
     * 格式 yyyy-MM-dd
     *
     * @param localDateStr
     * @return
     */
    public static LocalDate stringToLocalDate(String localDateStr) {
        LocalDate localDate = null;
        if(localDateStr == null || "".equals(localDateStr)) {
            //localDate = LocalDate.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            localDate = LocalDate.parse(localDateStr, formatter);
        }
        return localDate;
    }

    /**
     * 格式 HH:mm:ss
     *
     * @param localTime
     * @return
     */
    public static String localTimeToString(LocalTime localTime) {
        String strDate = "";
        if(localTime != null) {
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            strDate = dtf2.format(localTime);
        }
        return strDate;
    }

    /**
     * 格式 HH:mm:ss
     *
     * @param localTimeStr
     * @return
     */
    public static LocalTime stringToLocalTime(String localTimeStr) {
        LocalTime localTime = null;
        if(localTimeStr == null || "".equals(localTimeStr)) {
            //localTime = LocalTime.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            localTime = LocalTime.parse(localTimeStr, formatter);
        }
        return localTime;
    }
}
