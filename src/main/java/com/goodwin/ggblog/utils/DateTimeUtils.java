package com.goodwin.ggblog.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author goodwin
 */
public class DateTimeUtils {

    public static String dateTimeFormat(Date date, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return date == null ? "" : dateFormat.format(date);
    }

    public static String ridDot(String datetime){
        if(datetime == null) {
            return null;
        }
        return datetime.substring(0,datetime.indexOf("."));
    }

    public static int compareTo(String datetime1,String datetime2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date1 = LocalDateTime.parse(datetime1,formatter);
        LocalDateTime date2 = LocalDateTime.parse(datetime2,formatter);
        return date1.compareTo(date2);
    }

    public static void main(String[] args) {
        System.out.println(DateTimeUtils.compareTo("2000-11-11 10:10:31","2000-11-11 10:10:21"));
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        System.out.println(formatter.format(LocalDateTime.now()));
//
//        LocalDateTime dateTime = LocalDateTime.parse("2022-05-30 22:12:17",formatter);
//        System.out.println(dateTime);
    }

}
