package com.practis.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class WebGridUtils {

  /**
   * To be added.
   */
  public static LocalDateTime parseDate(final String date) {
    try {
      final var timePattern = DateTimeFormatter.ofPattern("h:m a");
      return LocalDateTime.of(LocalDate.now(), LocalTime.from(timePattern.parse(date)));
    } catch (DateTimeParseException ex1) {
      try {
        final var timePattern = DateTimeFormatter.ofPattern("dd/M/yy");
        return LocalDateTime.of(LocalDate.from(timePattern.parse(date)), LocalTime.of(0, 0));
      } catch (Exception ex2) {
        log.error("Can't parse date from string {}", date);
        return null;
      }
    }
  }
}
