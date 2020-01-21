package com.seek.assignment.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtils {

  public static DateTimeFormatter getDateTimeFormatter(String pattern) {
    return DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
  }

  public static LocalDateTime getLocalDateTimeForStringDateTimeWithPattern(
      String dateTime, String pattern) {
    return LocalDateTime.parse(dateTime, getDateTimeFormatter("yyyy-MM-dd'T'HH:mm:ss"));
  }
}
