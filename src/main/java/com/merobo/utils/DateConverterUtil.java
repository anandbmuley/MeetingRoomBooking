package com.merobo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public abstract class DateConverterUtil {

    public static String PATTERN_DD_MM_YYYY_HH_MM_MERIDIAN = "dd-MM-yyyy hh:mm a";
    public static String PATTERN_DD_MM_YYYY = "dd-MM-yyyy";

    public static String toString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String formatTime(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern(PATTERN_DD_MM_YYYY_HH_MM_MERIDIAN).format(dateTime);
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(PATTERN_DD_MM_YYYY));
    }

    public static LocalDateTime parseTime(String value) {
        return LocalDateTime.parse(value,
                DateTimeFormatter.ofPattern(PATTERN_DD_MM_YYYY_HH_MM_MERIDIAN));
    }

    public static Date toDate(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date givenTime = sdf.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(givenTime);
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR));
        todayCalendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
        todayCalendar.set(Calendar.AM_PM, calendar.get(Calendar.AM_PM));
        return todayCalendar.getTime();
    }

}
