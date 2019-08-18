package org.linlinjava.litemall.core.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 格式 HH:mm
     *
     * @param localTimeStr
     * @return
     */
    public static LocalTime stringToLocalTimeShort(String localTimeStr) {
        LocalTime localTime = null;
        if(localTimeStr == null || "".equals(localTimeStr)) {
            //localTime = LocalTime.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            localTime = LocalTime.parse(localTimeStr, formatter);
        }
        return localTime;
    }

    /**
     * 生成一周的日历，包含日期和星期
     * @return
     */
    public static List<Map<String, Object>> initCalendar(int size) {
        LocalDate dateNow = LocalDate.now();
        List<Map<String, Object>> calendar = new ArrayList<Map<String, Object>>();
        String[][] weekArray = {{"MONDAY", "一"}, {"TUESDAY", "二"}, {"WEDNESDAY", "三"}, {"THURSDAY", "四"}, {"FRIDAY", "五"}, {"SATURDAY", "六"}, {"SUNDAY", "日"}};

        for(int i=0; i<size; i++) {
            Map<String, Object> temp = new HashMap<String, Object>();
            if (i == 0) {
                temp.put("date", LocalDate.now());
                temp.put("week", "今天");
                calendar.add(temp);
            } else if (i == 1) {
                temp.put("date", LocalDate.now().plusDays(1));
                temp.put("week", "明天");
                calendar.add(temp);
            } else {
                LocalDate date = LocalDate.now().plusDays(i);
                String week = String.valueOf(date.getDayOfWeek());
                //获取行数
                for (int j = 0; j < weekArray.length; j++) {
                    if (week.equals(weekArray[j][0])) {
                        week = weekArray[j][1];
                        break;
                    }
                }
                temp.put("date", date);
                temp.put("week", ("星期" + week));
                calendar.add(temp);
            }
        }
        return calendar;
    }
}
