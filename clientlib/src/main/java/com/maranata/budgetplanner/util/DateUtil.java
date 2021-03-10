package com.maranata.budgetplanner.util;

import java.time.LocalDate;
import java.time.ZoneId;

public class DateUtil {

    public static long getMiliseconds(LocalDate date){
        ZoneId zoneId = ZoneId.systemDefault();
        return date.atStartOfDay(zoneId).toInstant().toEpochMilli();
    }
}
