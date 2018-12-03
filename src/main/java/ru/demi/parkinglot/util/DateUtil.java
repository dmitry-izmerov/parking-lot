package ru.demi.parkinglot.util;

import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@UtilityClass
public class DateUtil {

    public static Date convertToDate(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date, ISO_LOCAL_DATE_TIME);
        return Timestamp.valueOf(localDateTime);
    }
}
