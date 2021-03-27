package com.maranata.budgetplanner.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static long getMiliseconds(LocalDate date){
        ZoneId zoneId = ZoneId.systemDefault();
        return date.atStartOfDay(zoneId).toInstant().toEpochMilli();
    }

    public static LocalDate toLocalDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate toLocalDate (Long value){
        Instant instant = Instant.ofEpochMilli(value);
        return LocalDateTime.ofInstant(instant,ZoneId.systemDefault()).toLocalDate();
    }

    public static Date toDate(LocalDate localDate) {
        Instant instantDay = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instantDay);
    }

    public static String toFriendlyMonthDisplay(Long value) {
        return toLocalDate(value).getMonth().getDisplayName(TextStyle.SHORT, Locale.getDefault());
    }
}
